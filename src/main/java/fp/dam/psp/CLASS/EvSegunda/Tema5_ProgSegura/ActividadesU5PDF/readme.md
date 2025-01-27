# Actividades - Tema 5: Técnicas de Programación Segura

## Actividad 1

Modifica un ejercicio cliente/servidor de la unidad 3 para que tanto cliente como servidor utilicen un gestor de seguridad y un fichero de políticas que les conceda, sólo a ellos, los permisos siguientes:

- Al cliente, permiso de lectura para conectarse con el servidor en el puerto que corresponda.
- Al servidor, permiso para escuchar en el puerto que corresponda.

## Actividad 2

Crea un programa Java que reciba a través de un parámetro de línea de comando la ruta a un fichero de texto. El programa tiene que generar un resumen del contenido del fichero usando el algoritmo SHA-256 y almacenarlo en otro fichero con el mismo nombre, pero con extensión .sha.

Crea otro programa Java que reciba a través de parámetros de línea de comando la ruta al fichero de texto y la ruta al fichero que contiene su resumen y compruebe si el fichero de texto ha sido modificado, mostrando el mensaje correspondiente.

## Actividad 3

Crea un programa Java con interfaz gráfica de usuario que pueda realizar las tareas que se describen a continuación:

- Generar la firma de cualquier fichero con los datos almacenados en él, usando una clave privada almacenada en otro fichero o en un almacén de claves.
- Verificar la firma usando la clave pública correspondiente almacenada en un fichero o en un almacén de claves.

## Actividad 4

El modo de cifrado ECB, usado por el algoritmo de cifrado en los dos ejemplos anteriores, presenta una vulnerabilidad, que lo hace inadecuado para cifrar más de un bloque de datos. Modifica ambos ejemplos para que el algoritmo de cifrado use el modo CBC.

## Actividad 5

Modifica el servidor de contactos desarrollado en la unidad 3 y el cliente para utilicen sockets seguros.

## Actividad 6

Modificar el ejemplo anterior para que nos pida el nombre de usuario y la clave por la entrada estándar.