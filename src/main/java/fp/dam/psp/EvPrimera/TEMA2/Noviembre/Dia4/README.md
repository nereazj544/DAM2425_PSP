# Chismes de alto niveles

- Locks 
- Semaforos
- Executors
- Variables atomicas.

Hay más recursos de alto nivel que no aparecen, pero que mencionara viendolos por encima.

# EXECUTORS
La que usaremos sera: ``ExecutorService``. Los hilos se crearan internamente, instanciando la clase pero la instancia el chisme, los hilos que crea se reusan.
Lo que se ejucatara, lo tendremos que poner en Runnable como objetos Calleble y retornarlos en objetos Future.