uno de los problemas que surge, cuando varios hilos copiten por un recurso.
Se produce el llamado "condicion de carrera" (race condition).
aquel lugar del codigo se llama seccion critica donde se crea el fenomeno anteriormente menciondo.

Ejemplo:
``
public void inc() {
 n++;
 }
``

hay dos formas para esto:
- Synchronized
-
Dentro de la seccion critica ya no va a ver concurrecia, entonces hay que ver como se limita.


![img.png](img.png)

>[!NOTE]
> en el segundo ejemplo H2 permanece en estado de bloqueo, hasta que H1 acabe.
> Después de que salga compiten por el cerrojo. 
> 
