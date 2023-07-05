**Ejercicio 1**
**Pregunta 1** 
De la consulta SQL presentada:
SELECT * FROM FACTURA WHERE VALOR > 50 AND VALOR < 200,
los predicados son VALOR > 50 y VALOR < 200 respectivamente. Y estos son los que pueden tomar el valor de true o false, lo que da un total de 4 posibles combinaciones.

(1)Para empezar la construcción de la clase FacturaDAO, queremos implementar una prueba para la función más básica: SELECT; que en nuestro caso es llamada por el método Todo(). Sin embargo, dado que se necesita al menos una factura en la base de datos para que devuelva algo, vamos a primero implementar el método guardar(). Lo único que la clase guardar tiene que hacer es agregar una factura a la base de datos. Por lo que implementamos una prueba simple para la cual el código de producción es rápidamente implementado.

(2)

```

```

**Preg2. Método 1**
(1)Primero escribimos un Dockerfile como el indicado. Como sabemos el FROM indica la imagen que estamos usando, el RUN indica los comandos que docker correrá, el COPY copia archivos al contenedor activo, EXPOSE publica el puerto para que pueda ser accedido desde el exterior.

(2) Al crear la imagen con Dockerfile, nos aseguramos que los otros archivos: html.tar.gz, nginx.conf, default.conf estén dentro de una carpeta llamada files, a su vez la carpeta files debe estar en el mismo directorio que Dockerfile.
También debemos asegurarnos de que haya un espacio antes de emepezar los corchetes, para las instrucciones ENTRYPOINT y CMD. Por último asegurarnos que los directorios estén bien escritos y que no haya espacios innecesarios en ellos.
El Dockerfile con las comprobaciones correspondientes quedaría:
```
FROM alpine:latest
LABEL maintainer = "Cesar Lara Avila<checha@claraa.io>"
LABEL description = "Este Dockerfile de ejemplo instala NGINX"
RUN apk add --update nginx && \
	rm -rf /var/cache/apk/* && \
	mkdir -p /tmp/nginx/
	
COPY files/nginx.conf /etc/nginx/nginx.conf
COPY files/default.conf /etc/nginx/conf.d/default.conf
ADD files/html.tar.gz /usr/share/nginx/
EXPOSE 80/tcp
ENTRYPOINT ["nginx"]
CMD ["-g", "daemon off;"]
```

(3) Construimos la imagen usando el dockerfile con el comando:
```
sudo docker build -t dockerfile-example .
```
![](https://github.com/carlosgian/CC-3S2-1/blob/master/PracticaCalificada5/images/imagebuild.png)
Luego podemos revisar la imagen creada usando
```
sudo docker images
```
![](https://github.com/carlosgian/CC-3S2-1/blob/master/PracticaCalificada5/images/images.png)
(4)Creamos un contenedor, y sucesivamente verificamos verificamos que se está ejecutando con los comandos:
```
sudo docker container run -d --name dockerfile-example -p 8080:80 dockerfile-example
sudo docker container ls
```
![](https://github.com/carlosgian/CC-3S2-1/blob/master/PracticaCalificada5/images/containerRunOnList.png)

(5)A continuación corremos dos comandos que sirven para mostrar que todo está funcionando correctametne:
```
$ docker container run --name nginx-version local: dockerfile- example -v
$ docker image inspect -f {{.Config.Labels}} local: dockerfile- example
```
![](https://github.com/carlosgian/CC-3S2-1/blob/master/PracticaCalificada5/images/comandosTesteo.png)
Como vemos, el primer comando simplemente imprime la versión, mientras que el segundo imprime las etiquetas de configuración.

(6)Finalmente detenemos y removemos el contenedos con los comandos correspondientes: docker container stop, docker container rm

**Preg2. Método 2**
Para el método dos, creamos un contenedos usando una de las imágenes oficiales de Docker Hub.

Para empezar, tenemos que crear una copia de la imagen localmente, esto lo hacemos con docker pull.
```
sudo docker image pull alpine:latest
```
Luego creamos un contenedor a partir de la imagen obtenida. Queremos poder interactuar con el, así que agregamos -it
```
sudo docker run -it --name alpine-test alpine /bin/sh
```
Una vez corriendo el contenedor, podemos instalar cosas en él. Esto es independiente de lo instalado en nuestro sistema físico.
![](https://github.com/carlosgian/CC-3S2-1/blob/master/PracticaCalificada5/images/metodo2run.png)

Ahora queremos guardar los cambios hecho con el comando commit, para esto primero salimos del shell del contenedor, con exit.

Ahora corremos:
```
sudo docker container commit alpine-test local:broken-container
```
Lo llamamos broken-container para ejemplificar una de las ventajas de este método, la cual es que permite guardar contenedores fallidos que permiten a otros replicar el mismo error de manera exacta y así tratar de identificar la raíz de este.

Finalmente para salvar el archivo corremos el comando:
```
$ sudo docker image save -o broken-container.tar local:broken-container
```
![](https://github.com/carlosgian/CC-3S2-1/blob/master/PracticaCalificada5/images/saveimage.png)

**Preg2. Método 3**
Desplegamos la imagen desde 0.
Para crear una imagen desde 0, creamos el siguiente dockerfile:
```
FROM scratch
ADD files/alpine-minirootfs-3.11.3-x86_64.tar.gz /
CMD ["/bin/sh"]
```
"scratch" es una imagen oficial vacía de Docker Hub. Luego estamos añadiendo una versión mínima de Alpine Linux. Y finalmente estamos pasando al shell de este alpine linux un directorio, que es desde el cual queremos empezar.

¿por qué se descarga el archivo alpine-minirootfs-3.11.3-x86_64.tar.gz? ¿No se podría haber usado http://dl-cdn.alpinelinux.org/alpine/v3.11/releases/x86_64/alpine-minirootfs-3.11.3-x86_64.tar.gz en su lugar?
En el dockerfile tenemos que pasar una dirección local como la ubicación del archivo, por ello no se podría.

Luego de crear el dockerfile, procedemos a construirlo con `sudo $ docker image build --tag local:fromscratch .` Y luego procemos a comparar el tamaño con otras imágenes creadas, vemos que es la más pequeña de todas pues esta se ha creado desde 0 y solo se ha añadido una implementación mínima de Alpine Linux.

![](https://github.com/carlosgian/CC-3S2-1/blob/master/PracticaCalificada5/images/imagescomparisonwithscratch.png)

Por último probamos que la imagen se ha creado correctamente, instanciando un contenedor y mostrando un archivo que permite ver la configuración del alpine linux que está siendo ejecutado virtualmente en el contenedor.
![](https://github.com/carlosgian/CC-3S2-1/blob/master/PracticaCalificada5/images/detailsAlpineLinux.png)

**Pregunta 3**

![](https://github.com/carlosgian/CC-3S2-1/blob/master/PracticaCalificada5/images/detailsAlpineLinux.png)

**Pregunta 4**
La arquitectura basada en microservicios consiste en idear una aplicación como un conjunto de pequeños servicios. En contraste con la arquitectura monolítica clásica, permite independizar diferentes tareas de manera que cada una pueda desarrollarse,testearse e incluso fallar independientemente sin afectar a las otras. La automatización de la entrega y de pruebas consiste en poder entregar una nueva versión/probar tu programa de manera extensiva y con solo el ejecutar de una acción. La arquitectura de microservicios ayuda a este propósito, pues dado que cada servicio que agregas es independiente, es fácil agregarlo a tu maquinaria de automatización ya implementada.


