Interbloqueos

Todos se pueden desarrollar con este ejercicio.

# Monitores
Al usar un monitor en un bloque tambien bloquea los demas bloques. Se establece al mismo tiempo la exclusion mutua.

Cuando entran en waiting liberan el cerrojo, pero se quedara el bloque sincronizado, y el siguiente hilo podra aceder a este chisme :)

Ejemplo: ``Mark no se encuentra ninguna rueda de caminon debe de esperar a que lo pongan, 
cuando lo ponga puede entrenar`` 

Notify = solo a uno de los metodos que esten dentro
NotifyAll = a todos; todos compiten por ello y el que no lo pille se bloquea

Estar dentro de un wait no le va a garantizar nada que pille el cerrojo, si no lo pilla lo bloquea.
Finalizar de forma ordenada, espera a que el hilo finaliza este finaliza


