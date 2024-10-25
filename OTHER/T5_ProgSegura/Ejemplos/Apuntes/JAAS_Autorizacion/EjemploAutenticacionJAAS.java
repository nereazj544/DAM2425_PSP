package T5_ProgSegura.Ejemplos.Apuntes.JAAS_Autorizacion;

import java.security.PrivilegedAction;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import T5_ProgSegura.Ejemplos.Apuntes.JAAS_Autenticacion.MyCallbackHandler;

public class EjemploAutenticacionJAAS {
	public static void main(String[] args) {
		/* datos proporcionados desde la linea de comando a la JVM */
		String usuario = System.getProperty("usuario");
		String clave = System.getProperty("clave");
		LoginContext loginContext = null;
		/* Se crea el CallbackHandler pasándole en el constructor el nombre
		* de usuario y la clave para que el LoginModule acceda a ellos */
		CallbackHandler handler = new MyCallbackHandler(usuario, clave);
		try {
		loginContext = new LoginContext("EjemploLogin", handler);
		/* invocamos al método login para realizar la autenticación */
		loginContext.login();
		System.out.println("Usuario autenticado ");
		} catch (LoginException e) {
		/* la autenticación no tiene éxito */
		System.err.println("Usuario no autenticado: " + e.getLocalizedMessage());
		System.exit(-1);
		}
		Subject subject = loginContext.getSubject();
		PrivilegedAction action = new EjemploAccion();
		try {
		Subject.doAsPrivileged(subject, action, null);
		} catch (SecurityException e) {
		System.err.println("Acceso denegado: " + e.getLocalizedMessage());
		}
		try {
		loginContext.logout();
		} catch (LoginException e) {
		System.out.println("Logout: " + e.getLocalizedMessage());
		}
		}
}
