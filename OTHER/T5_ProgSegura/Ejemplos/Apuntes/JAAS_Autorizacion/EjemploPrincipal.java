package T5_ProgSegura.Ejemplos.Apuntes.JAAS_Autorizacion;

import java.security.Principal;

public class EjemploPrincipal implements Principal, java.io.Serializable {
	private String name;

	public EjemploPrincipal(String name) {
		if (name == null)
			throw new NullPointerException("Entrada nula");
		this.name = name;
	}

	public String getName() {
		return name;
	}

	/*
	 * Compara el objeto especificado con el Principal retornando true si son
	 * iguales
	 */
	public boolean equals(Object o) {
		if (o == null || !(o instanceof EjemploPrincipal))
			return false;
		return this == o || getName().equals(((EjemploPrincipal) o).getName());
	}

	public int hashCode() {
		return name.hashCode();
	}

	public String toString() {
		return (name);
	}
}
