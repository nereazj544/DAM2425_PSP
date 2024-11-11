# Barbero dormilón

Ejercicio 10 del PDF de los ejercicios de refuerzo.

>[!NOTE]
> Se usarán de alto nivel para realizar este ejercicio.

<br>

<br>
Crear una aplicación Java para consola que simule el funcionamiento de una barbería en la que hay 
un sillón de barbero y una sala de espera con n sillas. 
En la barbería trabaja un barbero. La simulación de su trabajo, que va a consistir en atender a los 
clientes afeitándoles la barba (este proceso durará 1 segundo), se realizará creando un hilo para tal 
efecto. También se ha de simular que el barbero duerme cuando no está atendiendo a un cliente.  
A la barbería acudirán varios clientes. La simulación de la llegada de cada cliente y su estancia en 
ella hasta que se vaya se simulará creando un hilo por cada uno de ellos. Se habrá de tener en 
cuenta que si un cliente llega a la barbería y la sala de espera está llena, se irá sin ser atendido y ya 
no volverá. 
<br>
Si un cliente llega a la peluquería y el barbero está durmiendo, tendrá que despertarlo. Si se 
encuentra atendiendo a otro cliente, tendrá que esperar su turno. El orden en que serán atendidos 
los clientes no tendrá por qué ser el mismo que el de llegada. 
Durante la ejecución de la aplicación será interesante saber en qué momento entra cada cliente en 
la peluquería, qué clientes acceden a la sala de espera, qué clientes se van sin ser atendidos y 
cuándo comienza el peluquero a cortar el pelo a cada cliente. 
El número de clientes que acudirán a la peluquería y el número de sillas en la sala de espera serán 
datos que tendrá que introducir el usuario cuando ejecute el programa. 
Cuando la aplicación esté terminada, se ha de hacer una prueba creando una barbería con 5 sillas 
en la sala de espera y 10 clientes.




## Clases que se necesitan:
- Barbero
- Cliente
- Barberia
- Main

### Clase: Barbero
### Clase: Cliente
El cliente ha de tener referencia a la barberia.
### Clase: Barberia
