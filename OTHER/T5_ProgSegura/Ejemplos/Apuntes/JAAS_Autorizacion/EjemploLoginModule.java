package T5_ProgSegura.Ejemplos.Apuntes.JAAS_Autorizacion;

import java.util.Map;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class EjemploLoginModule implements LoginModule {
	private Subject subject;
	private CallbackHandler callbackHandler;
	private String usuario;
	private String clave;
	private EjemploPrincipal usuarioPrincipal;

	public boolean abort() throws LoginException {
		return true;
	}

	public boolean commit() throws LoginException {
		usuarioPrincipal = new EjemploPrincipal(usuario);
		/* se añade el principal (identidad autenticada) al sujeto */
		if (!subject.getPrincipals().contains(usuarioPrincipal))
			subject.getPublicCredentials().add(usuarioPrincipal);
		return true;
	}

	public boolean logout() throws LoginException {
		return true;
	}

	public void initialize(Subject subject, CallbackHandler handler, Map State, Map options) {
		this.subject = subject;
		this.callbackHandler = handler;
	}

	public boolean login() throws LoginException {
		boolean autenticado = false;
		if (callbackHandler == null) {
			throw new LoginException("Se necesita CallbackHandler");
		}
		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("Nombre de usuario: ");
		callbacks[1] = new PasswordCallback("Clave: ", false);
		try {
			/*
			 * se invoca al método handle del CallbackHandler para solicitar el usuario y la
			 * contraseña
			 */
			callbackHandler.handle(callbacks);
			String usuario = ((NameCallback) callbacks[0]).getName();
			char[] passw = ((PasswordCallback) callbacks[1]).getPassword();
			String clave = new String(passw);
			/* La autenticación se realiza aqui */
			autenticado = ("maria".equalsIgnoreCase(usuario) & "practicas".equals(clave))
					|| ("juan".equalsIgnoreCase(usuario) & "practicas".equals(clave));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return autenticado;
	}
}