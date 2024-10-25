package T2_ProgMultiH.Ejercicios.Actividad14_Locks;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		Banco banco = new Banco(3);
		int cantPer = 10;
		List<Thread> grupo = new ArrayList<>();
		for (int i = 0; i<cantPer; i++)  {
			Thread thread = new Thread(new Persona(banco, ""+i));
			grupo.add(thread);
		}
		grupo.forEach((e) -> e.run());
	}
}
