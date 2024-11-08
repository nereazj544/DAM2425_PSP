# Executor

Es una intefaz parte del framework de concurrencia de Java y proporciona una forma de desacoplar la ejecución de tareas de la mecánica de creación y gestión de hilos. 
- Simplifica la gestión de hilos en aplicaciones concurrentes.
- Permite ejecutar tareas asíncronas sin manejar directamente los hilos.
- Ofrece diferentes implementaciones para diversos escenarios de concurrencia.
- Mejora el rendimiento al reutilizar hilos en un pool, reduciendo la sobrecarga de creación de hilos.
<br>
<br>
El uso de Executor es una práctica recomendada en Java moderno para manejar tareas concurrentes de manera eficiente y escalable.
<br>
<br>
![img.png](img.png)
- Solo cuando se pausa esta madre se ven los hilos


Los hilos que se crean se denominan: ```WORKERTHREADS``` y estos hilos finalizarion solo con un ```shutdownNow``` o ```shutdown```

# Diferencias ente ```shutdownNow``` y ```shutdown```

La diferencia entre estos es: 
- Now intenta detenerlos de forma inmediatamente, mientras que shutdown permite que las tareas en ejecucion termine.
- La interrupción: shutdownNow() intenta interrumpir los hilos en ejecución, shutdown() no lo hace.
- 