## NOTAS
Vamos por el ejemplo 6.

Resumir lo que viene siendo el metodo interrupt.
Lo unico que hace el hilo interrupt es señalizar la intencion de interrumpir al hilo.  
Si los hilos ignoran este hecho son hilos obedientes sino son desobedientes.  
No es bueno usar este sistema :)

esta el metodo  el interrupt(),  interrupted(), isInterrupted().
Mecanismos simples, pero dificiles de manejar, dan pie a que nos equivoquemos demasiado facil y por simples que sean cuesta trabajo asimilarlos.



**! Nos quedamos por aqui Suspensión y reanudación seguras**

Los mecanismos de sincornizacion sirven para evitar los errores con los hilos que se juntan concurrentemente (paralelo), esto ocurre cuando van varios a un mismo sitio-