##Actividad Microservicios

**Introducción al panorama de microservicios**

Podemos construir el esqueleto del proyecto con:
```
spring init --boot-version=3.1.1 --build=gradle --type=gradle-project --java-version=1.8 --packaging=jar --name=product-service --package-name=com.kapumota.microservicios.core.product --groupId=com.kapumota.microservicios.core.product --dependencies=actuator,webflux --version=1.0.0-SNAPSHOT product-service 
```
El mismo comando es ingresado otras 3 veces pero cambiando el nombre, al final tenemos el esqueleto de 4 microservicios creados:
```
microservicios/ 

├── product-composite-service 

├── product-service 

├── recommendation-service 

└── review-service 
```

Hemos agregado --type=gradle-project para especificar que tipo de proyecto queremos, en este caso se uso Gradle con Groovy por defecto.

Verificamos que el proyecto se ha creado correctamente con la línea: `find product-service -type f`, esto nos permite ver que archivos han sido creados por spring init para `product-service`
```
product-service/gradlew.bat
product-service/gradle/wrapper/gradle-wrapper.jar
product-service/gradle/wrapper/gradle-wrapper.properties
product-service/settings.gradle
product-service/build.gradle
product-service/gradlew
product-service/HELP.md
product-service/.gitignore
product-service/src/test/java/com/kapumota/microservicios/core/product/ProductServiceApplicationTests.java
product-service/src/main/resources/application.properties
product-service/src/main/java/com/kapumota/microservicios/core/product/ProductServiceApplication.java
```
**Pregunta: Identifica y explica cuales son los archivos que creó Spring Initializr en este proyecto.**
`gradlew.bat` y `gradlew` son scripts de inicialización para sistemas operativos Windows y UNIX respectivamente.
`gradle-wrapper.jar` los archivos .class de tu proyecto y es llamado por los scripts de inicialización.
`gradle-wrapper.properties` contiene información sobre gradle-wrapper, como por ejemplo la versión de Gradle que será usada.
`build.gradle` es la configuración de como se construirá tu proyecto.
`ProductServiceApplication.java` y `ProductServiceApplicationTests.java` son plantillas de clases de producción y prueba respectivamente.

---

Subsecuentemente podemos construir cada microservicio por separado yendo al directorio y corriendo `./gradlew build` 4 veces.

Pero también podemos hacer que los cuatro proyectos de construyan juntos usando un archivo `settings.gradle` como el siguiente:
```
include ':microservicios:product-service' 
include ':microservicios:review-service' 
include ':microservicios:recommendation-service' 
include ':microservicios:product-composite-service' 
```
Luego copiamos los ejecutables de Gradle de un proyecto para reutilizarlos en varios proyectos, como todos los proyectos fueron creados con el mismo esqueleto, no debería haber problema.
```
 cp -r microservicios/product-service/gradle . 
 cp microservicios/product-service/gradlew .  
 cp microservicios/product-service/.gitignore . 
```
Luego borramos los ejecutables gradle de cada microservicio individual, puesto que ya no son necesarios.
```
 find microservicios -depth -name "gradle" -exec rm -rfv "{}" \;
 find microservicios -depth -name "gradlew*" -exec rm -fv "{}" \; 
```
Ahora podemos construir los 4 proyectos al mismo tiempo con `./gradlew build` en el directorio donde está la carpeta que contiene los 4 microservicios.
En nuestro caso:
```
~/2-rest-servicios-basicos$ ./gradlew build

> Task :microservicios:product-composite-service:test
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

> Task :microservicios:product-service:test
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

> Task :microservicios:recommendation-service:test
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

> Task :microservicios:review-service:test
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

BUILD SUCCESSFUL in 16s
28 actionable tasks: 28 executed
```
---
**Agregar el proyecto api y util**
Los proyectos `api` y `util` se empaquetan como librerías, lastimosamente no podemos usar Spring Initializr para crear librerías así que se crean desde 0. 
Analicemos el código de una de las interfaces, en este caso `ProductService.java`
```
package com.kapumota.api.core.product; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PathVariable;  
public interface ProductService { 

  @GetMapping ( 
    value = "/product/{productId}", 
    produces = "application/json") 
    Product getProduct(@PathVariable int productId); 
} 
```
***Explique el código de la interfaz***
Como sabemos una interfaz se usa para implementar otras clases. Los métodos declarados en la interfaz deben ser implementados obligatoriamente en las clases que implementan. En cierta manera puede ser una forma de forzar a otras clases a declarar ciertos métodos.

En este caso cada clase que implemente esta interfaz debe declarar un método `getProduct` que devolverá un objeto del tipo Producto.

Ademaś esta clase esta anotada com `@GetMapping`. Esto es una anotación especializada que indica que la función se usará para una llamada GET. El valor indica el directorio donde se encontrará. `{productId}` se define de manera dinámica con el argumento de la función `int productId`, para ello el argumento también necesita marcarse con `@PathVariable`. El parámetro `produces = "aplication/json"` indica el formato en que se presentará la respuesta, en este caso se usa json.

---








