package fp.dam.psp.EvPrimera.TEMA2.OCTUBRE.CarpetadeOtroDia;
public class Contador {
    //? una opcion es crearse un monitor
    private  static  final Object m = new Object(); //* Se crea un monitor (perooo esta era la manera de ChatGPT que nos dijo aquella vez, 🫤)
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
            //! se el metodo es estatico seria Contador.class
            n++;
            //en este se elige el monitor
        }
    }
    public int get() {
        return n;
    }
}
// aqui hay una sentencia critica, donde haceden

/*
En este caso, el méto-
do get() no está sincronizado,
lo que significa que no es una sección crítica en sí mismo.
Sin embargo, esto puede llevar a problemas de concurrencia si se accede al valor de 'n' mientras otro
hilo está modificándolo en el método inc().

Para hacer que get() sea thread-safe y parte de la sección crítica, podrías sincronizarlo así:

```java
public synchronized int get() {
    return n;
}
```

O usando un bloque sincronizado:

```java
public int get() {
    synchronized (Contador.class) {
        return n;
    }
}
```

Esto aseguraría que la lectura de 'n' sea atómica y consistente con las
modificaciones realizadas en inc().
Sin embargo, ten en cuenta que sincronizar get() puede afectar el rendimiento si se llama con
frecuencia.
 */