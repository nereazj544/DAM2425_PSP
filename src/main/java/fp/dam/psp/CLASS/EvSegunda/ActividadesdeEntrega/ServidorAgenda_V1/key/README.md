Generar las claves:

keytool -genkeypair -alias servidor -keyalg RSA -keysize 2048 -keystore Servidor.p12 -storetype PKCS12 -validity 365

Contraseña: server  

keytool -genkeypair -alias cliente -keyalg RSA -keysize 2048 -keystore cliente.p12 -storetype PKCS12 -validity 365

Contraseña: cliente