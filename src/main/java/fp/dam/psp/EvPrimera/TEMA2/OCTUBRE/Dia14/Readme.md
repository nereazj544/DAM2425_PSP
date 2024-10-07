las condiciones de carrera:
- seciones critias son puntos concurrentes del programa
- Se resuelve con "Exclusion Mutua" 
  - esto va a estar contradolo por un monitor
    - Cualquier objeto puede ser cualquier monitor. El monitor va a tener una expecie de candado que va a estar abierto.
      - Lo primero que hay que hacer para controlarlo hay que decidir quien es el monitor. ¿como se decide? se limita con un bloque sincornizado.
      ![img.png](img.png)
      - Depende mucho como se limita la vaina esta. 
        - Como un metodo completo nosotros no elegimos el monitor, solo lo elejimos si lo hacemos a nivel de bloque.
        - 