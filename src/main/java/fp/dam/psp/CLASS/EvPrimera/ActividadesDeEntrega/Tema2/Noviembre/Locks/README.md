# LOCK ~~(no blue Lock)~~
Bloqueos para acceder a bloques compartidos.
Con estos cosas son sencillitas, supuestamente.
![img.png](img.png)
Si deja entrar pilla el bloqueo, de tal manera que ya puede aceder, los otros no accederan hasta que el hilo salga con unlock
![img_1.png](img_1.png)

Con ReentrantLock lo implementa