## Pregunta1

**Antes**

Para empezar creamos el proyecto Antes de la carpeta Pregunta1, aquí esbozaremos una versión muy inicial del proyecto.

Creamos la clase Flight con un único parámetro flightType que nos indicará el tipo de vuelo(económico o de negocios).
Como la clase Flight usa una objeto tipo Passenger, esbozamos el objeto Passenger con dos parámetros `name` y `isVip` que nos dará el nombre del usuario así como el hecho de si es VIP o no. 
Además esbozamos creamos un esbozo de la clase Airport donde creamos dos vuelos y dos pasajeros, y llamamos a los métodos addPassenger y removePassenger, y ademas imprimimos sus resultados para ver si funcionan como lo esperado.

**Fase 1**

Implementamos pruebas para el esbozo de lal clase Airport que escribimos antes.

Aunque todo parece funcionar correctamente excepto que notamos una gran inconsistencia. Si bien en teoría el método `removePassenger` debe devolver `true` siempre que se le pase un pasajero que no sea VIP, ¿qué pasa cuando el vuelo es de negocios y el pasajero no es VIP?, la función devolverá true sin pensarlo mucho; sin embargo, ¿tiene sentido retirar un pasajero que no pudo ser admitido en primer lugar? No, en definitiva no. Esto es un problema en la lógica del proyecto y sugiere que una REFACTORIZACIÓN es necesaria. Además de eso, la lógica implementada con if/else en los métodos de la clase `Flight` no se ve muy bien y sugiere una separación de clases con una interfaz común.

**Fase 2**

En la fase 2 reemplazamos el código condicional que controla el comportamiento de `Flight` por diferentes versiones de esta clase. Implementar "diferentes" versiones de una misma clase se llama Polimorfismo y es uno de los 4 pilares del POO. Primero creamos la interface `Flight`. Como ahora cada clase implementada representa un tipo de vuelo, el parámetro `flightType` se vuelve innecesario, así que lo quitamos. Solo basta escribir las firmas de los métodos `addPassenger` y  `getPassenger` en la interfaz. Ahora pasamos a crear `BusinessFlight` y `EconomyFlight` que implementan la interfaz `Flight`. Definimos el comportamiento de acuerdo a si el pasajero es VIP o no y listo.

Ahora necesitamos reescribir las pruebas para que reflejen los cambios que hemos hecho.
Los únicos cambios necesarios han sido la creación de los vuelos(flights) que ahora necesitan llamar a `BusinessFlight` o `EconomyFlight` directamente. Y en la parte de las aserciones hemos considerado que un vuelo de negocios siempre devuelva false con el método `removePassenger` pues si es VIP no se puede remover, y si no es VIP, entonces no estaba en el vuelo en el primer lugar. En cualquiera de los casos no se remueve nada.

En cuanto a la cobertura de código, se están cubriendo todo lo hasta aquí implementado en las clases implementadas de `Flight`.
La calidad de código ha mejorado considerablemente pues ahora los métodos `getPassenger` y `removePassenger` se ven mucho más legibles con menos condicionales.

**Fase 3**

En esta parte nos centramos en refactorizar nuestro código guiándonos de una clase de prueba que ya viene refactorizada: `AirportTest`.

Viendo la clase AirportTest, podemos ver que podemos refactorizar varias cosas en nuestro código.
Para empezar vemos que los contructores de los vuelos toman un parámetro `id`, esto para identificar uno de otro. Además vemos que se usa lista de pasajeros(`PassengerList`) que almacena los pasajeros que se van agregando. Esto es mucho mejor que solamente devolver un booleano que indica si el pasajero podría ser agregado o no, que era lo que teníamos hasta la Fase2. 

Por supuesto, también hemos agregado getters `getId` y `getPassengerList` en la interface `Flight`.

El tipo de retorno de los métodos siguen siendo `boolean`, y devuelve `true` solo cuando agregan/remueven un pasajero con éxito, es decir, no solo toma en cuenta si el pasajero puede ser agregado/removido sino si en realidad está siendo agregado/removido a la lista de pasajeros.

Luego de hacer los cambios necesarios, corremos `AirportTest` y vemos todo en verde.

**Fase 4**

Siguiendo la filosofía TDD, creamos una nueva clase PremiumFlight, pero no implementamos aún ninguna lógica en sus métodos.

Implementamos primero las pruebas, nos guiamos de como han sido implementada las otras para crear la nueva sección `PremiumFlightTest`.
Usamos la anotación `@DisplayName` para patentar los criterios de aceptación que están representando las pruebas, además de que se ven mejor en la IDE.
También usamos la anotación `@Nested` para las clases dentro de la principal, así marcamos una jerarquía clara y nos permite separar grupos de tests para cada criterio de aceptación de manera ordenada.

Luego de programar los resultados esperados en las aserciones, corremos las pruebas:

![](https://github.com/carlosgian/CC-3S2-1/blob/master/ExamenFinal-3S2/images/preg1im1.png)

Vemos algo interesante, y es que la prueba para un pasajero regular en un vuelo premium pasa sin haber implementado ninguna lógica, lo cual tiene sentido pues este pasajero no puede ser agregado y por lo tanto tampoco retirado de un vuelo premium.

Luego procedemos a implementar la lógica para un pasajero VIP en un vuelo Premium.

Una vez agregado, volvemos a ver las pruebas y vemos que todas pasan:

![](https://github.com/carlosgian/CC-3S2-1/blob/master/ExamenFinal-3S2/images/preg1im2.png)

Luego nos vamos a inspeccionar la cobertura de código y vemos que la clases `BusinessFlight` y `PremiumFlight` solo están siendo cubiertas al 80%. Esto es porque no estamos testeando los métodos `getId` y `getPassengerList` de estas clases. Rápidamente agregamos algunas líneas en las aserciones para asegurarnos que la cobertura de código este al 100%.

![](https://github.com/carlosgian/CC-3S2-1/blob/master/ExamenFinal-3S2/images/preg1im3.png)

Luego de asegurarnos que la cobertura este al 100% pasamos a la Fase5.

**Fase 5**

En esta parte el cliente nos ha pedido asegurarnos que no se puedan agregar el mismo cliente dos veces a la misma lista. Siguiente el estilo TDD, implementaremos primero las pruebas que esto implica. Una vez con las pruebas implementadas, corremos los tests:

![](https://github.com/carlosgian/CC-3S2-1/blob/master/ExamenFinal-3S2/images/preg1im4.png)

El problema principal es que la lista tipo `ArrayList` que estamos usando hasta ahora permite añadir el mismo pasajero dos veces sin ningún problema. Para evitar agregar una serie de if/else complicados que dañarían la legibilidad del código, se decide refactorizar y cambiar los ArrayList a Mapas, en espécifico HashMaps. Los Mapas son una estructura de datos que están compuesta por varios pares de `Llave:Valor`, y permiten acceder a cada valor a través de su llave. Una característica de los HashMaps específicamente es que solo acepta llaves únicas, lo cual nos ayudará a evitar la duplicación de un pasajero.

Veamos también como esta refactorización afecta al resto del código.

![](https://github.com/carlosgian/CC-3S2-1/blob/master/ExamenFinal-3S2/images/preg1im5.png)

Para empezar ahora inicializamos `passengers` como un HashMap. 

Así mismo modificamos la parte donde se agrega y removen pasajeros. Como cada elemento de un mapa necesita un par llave:valor, decidimos ingresar a los pasajeros en pares de su nombre(`passenger.getName()`) y el objeto passenger como valor. Al momento de hacer estos cambios nos damos cuenta de que el valor de retorno de los métodos para agregar y remover elementos de un mapa(`put` y `remove` respectivamente) nos devuelven un objeto y no un booleano. La intuición me dice que devuelven el valor correspondiente a la llave que ha sido agregada/removida, por lo que modificamos esa parte a `passengers.put(passenger.getName(), passenger).equal(passenger)` para hacer que devuelvan `true`. Sin embargo al hacer esto y correr las pruebas vemos que casi ninguna pasa, ¿por qué?

Al analizar el mensaje de error de Intellij, vemos que nos informa de que no se puede llamar a `.equal(passenger)` porque lo que devuelve el método `.put` de la clase `java.io.map` es un `null`, contrario a lo que nosotros creíamos. Entonces modificamos las líneas en el retorno de las funciones a:
```
return passengers.put(passenger.getName(), passenger) == null;
passengers.remove(passenger.getName()) == null;
```
Al hacer esto y correr de nuevos las pruebas, vemos algo interesante, las pruebas nuevas que agregamos para verificar que no se agreguen dos pasajeros iguales pasan, sin embargo, algunas de las anteriores no pasan, por qué? Revisamos de nuevo los mensajes de IntelliJ, y vemos que nos dice que al remover un pasajero exitosamente, se espera `true` pero recibe `false`, lo que significa que al remover un pasajero exitosamente, la función `remove` de `java.io.map` no devuelve un `null`. Para evitar seguir cayendo en errores debido a suposiciones, revisamos la [documentación](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html) directamente. Aquí podemos ver precisamente que devuelve `put` y `remove` en cada caso. 

`put` devuelve `null` si la llave ingresada es nueva, o el valor que se está reemplazando si la llave ya existe. Este comportamiento se encarga de asegurar de que no se puedan agregar pasajeros duplicados a un vuelo, pues el "mismo" valor reemplaza al anterior, y como en este caso devuelve un objeto no nulo, la función booleana `addPassenger()` devuelve `false`, lo cual es correcto, pues no se está agregando un nuevo pasajero.

`remove` devuelve el valor del par `llave:valor` retirado, o `null` si no se retira nada.

Entonces las líneas de retorno donde se agregan/remueven pasajeros deben quedar así:
```
return passengers.put(passenger.getName(), passenger) == null;
return passengers.remove(passenger.getName()).equal(passenger);
```
Corremos de nuevo las pruebas y pasan todas... excepto un caso. El caso donde retiramos un pasajero VIP de un vuelo premium. ¿Qué sucede aquí?
Tras un poco de inspección, nos damos cuenta del problema. Antes, cuando teníamos aún `arraylist` teníamos el sig. método para `PremiumFlight.removePassenger()`:
```
public boolean removePassenger(Passenger passenger) {
        return passengers.remove(passenger);
    }
```
Y esto bastaba, pues en caso se removiera, devolvía `true` en caso se removiera algo, y `false` en caso no se removiera nada, funcionaba bien para ambos tipos de pasajeros, los VIP y los regulares. Pero en cambio al cambiar a HashMaps y usar la línea:
```
public boolean removePassenger(Passenger passenger) {
        return passengers.remove(passenger.getName()).equals(passenger);
    }
```
Funciona bien cuando hay algo que remover, es decir, cuando el pasajero es VIP. Pero en el caso de un pasajero regular, al no tener nada que remover, el método `java.io.Map.remove` devuelve `null` y no podemos llamar al método `equal` desde un objeto nulo. La solución a esto es simple, debemos verificar que el pasajero sea VIP antes de intentar removerlo, entonces cambiamos la implementación del método:
```
public boolean removePassenger(Passenger passenger) {
        if (passenger.getIfVip()) return passengers.remove(passenger.getName()).equals(passenger);
        return false;
    }
```
Y listo, con eso volvemos a correr las pruebas y vemos que todas pasan. Damos una última revisasa a la cobertura de código y vemos todas las clase que implementan la lógica del programa al 100%:

![](https://github.com/carlosgian/CC-3S2-1/blob/master/ExamenFinal-3S2/images/preg1im6.png)

Damos por concluido el TDD.

