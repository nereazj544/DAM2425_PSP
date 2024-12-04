package fp.dam.psp.CLASS.EvPrimera.TEMA2.OCTUBRE.Dia4;
public class Contador {
    private int n; //! Objeto de la secion critica.
    public Contador(int n) {
        this.n = n;
    }
    public synchronized void inc() {
        n++;
        //? Operacion atomica
        // ? traducido a byte code / lengueja maquina, serian varias instrucines;

        // ? incrementa el registro interno. Escribir el registro interno en la memoria.
    }
    public int get() {
        return n;
    }
}
// aqui hay una sentencia critica, donde haceden