Haz un fork del siguiente repositorio:

https://github.com/pspfleming/signatureverify

Completa la aplicación para que funcionen los dos botones que se muestran en la interfaz gráfica de usuario:

Botón firmar: cuando el usuario seleccione un fichero (ya está implementado) crea una firma, codifícala en Base64, concaténale "#" + nombre_del_algoritmo_de_firma + "#" + certificado_codificado_en_Base64. Finaliza este proceso almacenando la cadena obtenida en un fichero con el mismo nombre que el original + ".signature".
Botón verificar: cuando el usuario seleccione un fichero (ya está implementado) envía a un servidor de verificación de firmas el contenido del fichero que contiene la firma (si existe) seguido del fichero seleccionado y muestra la respuesta del servidor en un JOptionPane.
Todos los errores que se produzcan en cualquiera de los procesos se mostrarán en un JOptionPane.
En la carpeta de recursos del proyecto se encuentra un keystore que debes cargar en memoria, que contiene claves privadas y sus certificados asociados. Una vez cargado en memoria se deben almacenar en la variable de instancia "aliases" (ya está declarada como un ArrayList) todos los alias del keystore.

Entregar el proyecto de IntelliJ comprimido en formato ZIP.