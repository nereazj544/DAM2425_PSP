package T5_ProgSegura.Ejemplos.Apuntes.JAAS_Autenticacion;

import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class EjemploAutenticacionJAAS {
	public static void main(String[] args) {
		/* datos proporcionados desde la linea de comando a la JVM */
		String usuario = System.getProperty("usuario");
		String clave = System.getProperty("clave");
		/*
		 * Se crea el CallbackHandler pasándole en el constructor el nombre de usuario y
		 * la clave para que el LoginModule acceda a ellos
		 */
		CallbackHandler handler = new MyCallbackHandler(usuario, clave);
		try {
			LoginContext loginContext = new LoginContext("EjemploLogin", handler);
			/* invocamos al método login para realizar la autenticación */
			loginContext.login();
			System.out.println("Usuario autenticado ");
		} catch (LoginException e) {
			/* la autenticación no tiene éxito */
			System.err.println("No se puede autenticar el usuario");
		}
	}
}
