package T5_ProgSegura.Ejemplos.Apuntes.JAAS_Autenticacion;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

public class MyCallbackHandler implements CallbackHandler {
	private String usuario;
	private String clave;

	public MyCallbackHandler(String usuario, String clave) {
		this.usuario = usuario;
		this.clave = clave;
	}

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		for (int i = 0; i < callbacks.length; i++) {
			Callback callback = callbacks[i];
			if (callback instanceof NameCallback) {
				NameCallback nameCB = (NameCallback) callback;
				/* se asigna al NameCallback el nombre de usuario */
				nameCB.setName(usuario);
			} else if (callback instanceof PasswordCallback) {
				PasswordCallback passwordCB = (PasswordCallback) callback;
				/* se asigna al PasswordCallback la clave */
				passwordCB.setPassword(clave.toCharArray());
			}
		}
	}
}