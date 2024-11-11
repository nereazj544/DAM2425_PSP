Cinco filósofos y filósofas de la antigua china, 孔夫子, 楊朱, 謝道韞, 商鞅 y 吳誠真, pasan su vida sentados alrededor
de una mesa comiendo o pensando. A continuación, se proporciona una descripción detallada del proceso:
• Cada filósofo o filósofa tiene un plato de arroz que nunca se acaba, un palillo a la izquierda de su plato y
otro a la derecha.
• Comparten los palillos a su izquierda y a su derecha con quienes se sientan a su izquierda y a su derecha
respectivamente.
• En todo momento se encontrará en una de las situaciones siguientes:
o Pensando: durante un periodo de tiempo aleatorio en el que no estarán usando ninguno de los
palillos.
o Esperando: desde el momento en que deciden comer, hasta que consiguen los dos palillos. Sólo
podrán coger cada palillo si nadie lo está usando.
o Comiendo: durante un periodo de tiempo aleatorio que comenzará inmediatamente después de
que hayan cogido el segundo palillo. Cuando finalice este periodo de tiempo, dejarán los palillos
libres.
Crear un programa Java que simule este proceso mediante hilos, uno por cada filósofo o filósofa, para que ejecuten
las acciones de pensar, esperar o comer satisfaciendo la exclusión mutua sobre los palillos (dos no pueden emplear el
mismo palillo a la vez). Además, se han de evitar el interbloqueo y la inanición. Desarrollar la aplicación con interfaz
gráfica usando clases de la librería Swing y Java2D para mostrar una animación de la simulación con los elementos
gráficos que se estimen oportunos, permitiendo la posibilidad de pausarla y reanudarla.
A continuación, se propone una forma de representar la escena en cada instante:
• Representar la mesa con un círculo.
• Representar cada plato mediante un círculo de un color que lo diferencie del resto y distribuirlos
uniformemente alrededor de la mesa.
• Cuando un filósofo adquiere un palillo, dibujarlo del mismo color que su plato.
• Mostrar dentro de cada plato un icono que represente la acción que esté realizando el filósofo en cada
instante: pensar, esperar o comer.
Cuando un palillo no está siendo utilizado por ningún filósofo, se mostrará de un color diferente a
cualquiera de los asignados a los platos.