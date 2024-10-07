package fp.dam.psp.EvPrimera.TEMA2.OCTUBRE.Dia14;
public class Contador {
    private int n; //! Objeto de la secion critica.
    public Contador(int n) {
        this.n = n;
    }
//    public synchronized void inc() {
//        n++; //! Metodo con el bloque
//        //? Operacion atomica
//        // ? traducido a byte code / lengueja maquina, serian varias instrucines;
//
//        // ? incrementa el registro interno. Escribir el registro interno en la memoria.
//    }
//    ? BLOQUES
    public void inc (){
//        synchronized (this){
        synchronized (Contador.class){
            n++;
            //en este se elige el monitor
        }
    }
    public int get() {
        return n;
    }
}
// aqui hay una sentencia critica, donde haceden