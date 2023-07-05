**Prueba 1**
**Pregunta 1** Se escribe una implementación simple del problema dado.
El primer `if` cuenta la cantidad de números que se van repitiendo,
y el `else if` incrementa el nClumps si es que cambia el número, y si la cantidad de elementos repetidos fue mayor o igual a 2.

El `if`  luego del `for` se asegura de contar el clump final en caso el arreglo termine con un clump, o el arreglo entero sea un único clump.
```
for(int i = 1; i < nums.length; i++){
            if (nums[i] == previousValue) count++;
            else if (nums[i] != previousValue) {
                if (count >= 2) nClumps++;
                count = 1;
            }
            previousValue = nums[i];
        }
if (count >= 2) nClumps++;
```

**Pregunta 2**
Sean los siguientes casos de prueba:
T1: int[] es null
T2: int[] es vacío
T3: int[] es unitario.
T4: int[] tiene todos sus elementos repetidos(un solo clump)

T1 y T2 no pasan porque no hemos considerado los casos límite del arreglo. Para arreglar este problema agregamos una porción de código al inicio de nuestra función:
```
if(nums == null || nums.length == 0) return 0;
```

T3 pasa sin necesidad de modificar el código. Un arreglo de longitud 1 ignora el bucle `for`, pues no hay ningún valor que iterar entre i = 1 y i < 1.

T4 también pasa, pues el `if` después del `for` se asegura de contar al menos 1 clump en caso no haya cambios en lus números.

**Pregunta 3**
Mostrar los resultados del Code Coverage:

![]([https://github.com/carlosgian/PracticaCalificada3/codeCoverage.png](https://github.com/carlosgian/CC-3S2-1/blob/master/PracticaCalificada3/images/codeCoverage.png)

**Pregunta 4**
T5: int[] tiene un único clump al principio.
T6: int[] tiene un único clump al final.
T7: int[] el programa tiene únicamente dos clumps, uno al final y otro al inicio.
T8: int[] el programa un único clump al medio.

Todos los casos pasan sin problemas, lo que refuerza la confianza en nuestro código.


**Prueba 3**
Empezamos por programar una prueba para el caso T1: Solo hay una reserva
![](https://github.com/carlosgian/PracticaCalificada3/preg3paso1.png)

Para hacer que pase programamos solo lo suficiente en la clase.
![](https://github.com/carlosgian/PracticaCalificada3/preg3paso2.png)


