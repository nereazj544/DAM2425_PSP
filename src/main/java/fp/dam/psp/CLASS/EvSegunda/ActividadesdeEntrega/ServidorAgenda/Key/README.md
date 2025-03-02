keytool -genkeypair -alias servidor -keyalg RSA -keysize 2048 -validity 365 -keystore Servidor.p12 -storetype PKCS12 -storepass servidor -dname "CN=Servidor, OU=PSP, O=PSP, C=ES"


 keytool -genkeypair -alias cliente -keyalg RSA -keysize 2048 -validity 365 -keystore Cliente.p12 -storetype PKCS12 -storepass cliente -dname "CN=Cliente, OU=PSP, O=PSP, C=ES"