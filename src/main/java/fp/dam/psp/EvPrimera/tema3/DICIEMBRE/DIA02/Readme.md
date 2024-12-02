# Clases de programacion de comunicacion.

Hay tres niveles de comunicacion para dentificar el destino y el origen:
- mac
- ip
- nº puerto (socke)

En estos ej tenemos que fijarnos en la Ip = Nombre del Host || Se usa el protocolo de DNS.

# Clase InetAdress
Te da info de lo que sea.

## Características Principales

- Representa una dirección IP y un nombre de host
- Proporciona métodos para resolver nombres de host a direcciones IP
- Permite validar y manipular direcciones IP
- No tiene constructor público (se usan métodos factory)


## Métodos Importantes

```java
// Obtener dirección IP local
InetAddress direccionLocal = InetAddress.getLocalHost();

// Obtener dirección IP por nombre de host
InetAddress googleIP = InetAddress.getByName("www.google.com");

// Obtener todas las direcciones IP asociadas a un host
InetAddress[] todasLasIP = InetAddress.getAllByName("www.google.com");

// Verificar si un host está alcanzable
boolean alcanzable = googleIP.isReachable(5000); // timeout 5 segundos
```


## Métodos Adicionales

- **getHostName()**: Devuelve el nombre del host
- **getHostAddress()**: Devuelve la dirección IP en formato String
- **getAddress()**: Devuelve la dirección IP como array de bytes
- **isReachable()**: Comprueba si el host es alcanzable