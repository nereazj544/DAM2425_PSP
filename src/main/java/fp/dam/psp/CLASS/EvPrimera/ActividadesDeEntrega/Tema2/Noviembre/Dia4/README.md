# Chismes de alto niveles

- Locks 
- Semaforos
- Executors
- Variables atomicas.

Hay más recursos de alto nivel que no aparecen, pero que mencionara viendolos por encima.

# EXECUTORS
La que usaremos sera: ``ExecutorService``. Los hilos se crearan internamente, instanciando la clase pero la instancia el chisme, los hilos que crea se reusan.
Lo que se ejucatara, lo tendremos que poner en Runnable como objetos Calleble y retornarlos en objetos Future.
La diferencia es que los Calleble retorna un resultado, lo que NO se puede hacer con Runnable (de forma simple).

# Pools de hilos
Se pueden crear un numero de hilos iliminatados de estos.

## Como se crea esta vaina??
res: pegandote un tiro :)

La creacion de esto es usando:
``ExecutorService executorService = Executors.``