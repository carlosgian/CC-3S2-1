##Actividad Kubernetes
***Proceso de Instalación***
Como estamos en el sistema operativo ubuntu, seguimos los pasos instalación del siguiente [link](https://kubernetes.io/docs/tasks/tools/install-kubectl-linux/)

Usamos los siguientes comandos para instalar kubectl:
```
$curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
$curl -LO "https://dl.k8s.io/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl.sha256"
$echo "$(cat kubectl.sha256)  kubectl" | sha256sum --check
$sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```
El primer comando recupera el binario del servidor de kubernetes. El segundo descarga la checksum del binario, esto nos permite comprobar la integridad del archivo con el tercer comando. Finalmente instalamos el binario con el cuarto comando.

**Creación de un clúster con la herramienta de apoyo elegida**
Luego necesitamos una herramienta que nos facilite el uso local de Kubernetes.
Una opción es Docker Desktop; pero como ya tenemos instalado Docker Engine, y no es recomendado tener los dos instalados al mismo tiempo(puede crear conflictos), la descartamos.

La segunda opción es kind, pero no hay una forma simple de instalarlo, a excepción de la instalación con go. Sin embargo, incluso luego de una instalación exitosa, se requiere modificar la variable de entorno `PATH` para que reconozca `go` y `kind`, además de tener que prefijar su uso con sudo para otorgar permisos necesarios.

A vista de esto, nos decantamos por la tercera opción: Minikube. 
Para instalarlo, vamos a [minikube](https://minikube.sigs.k8s.io/docs/start/) y elegimos los tags que describen nuestra plataforma, en nuestro caso nos da los siguientes comandos:
```
curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64
sudo install minikube-linux-amd64 /usr/local/bin/minikube
```
Una ves instalado, simplemente corremos: `minikube start`
Nos podemos topar con un par de problemas en esta parte. El primero es que, si aún necesitas sudo para ejecutar comandos de docker, entonces tendrás que hacer `sudo minikube start`. Sin embargo, Minikube te advertirá que no se debe correr docker con privilegios de superusuario y abortará la creación del clúster. Si bien podemos forzar la creación del clúster con `sudo minikube start --force`, no es recomendable porque es probable que tu instalación de minikube no haya sido a nivel root y esto cause conflictos más adelante. Para correr docker sin tener que prefijarlo con `sudo` cada vez, podemos seguir las instrucciones en este [link](https://docs.docker.com/engine/install/linux-postinstall/).

Una vez hecho eso, `minikube start` debería correr sin problemas, aunque es posible que se tope un problema de configuración en el archivo .kube/config. Sin embargo, el mismo minikube te sugiere el comando que resuelve el problema:
```
$sudo chown $USER $HOME/.kube/config && chmod 600 $HOME/.kube/config
```

Con estos dos cambios `minukube start` debería crear un clúster exitosamente.

Podemos verificar la creación del clúster con kubectl, escribiendo `kubectl cluster-info` y deberíamos ver algo similar a
```
Kubernetes control plane is running at https://192.168.49.2:8443
CoreDNS is running at https://192.168.49.2:8443/api/v1/namespaces/kube-system/services/kube-dns:dns/proxy

To further debug and diagnose cluster problems, use 'kubectl cluster-info dump'.
```

Con esto ya podemos pasar a usar kubernetes para crear trabajos que generalemente se pasarán en un archivo `yaml`.

**Creación de un trabajo en kubernetes usando un archivo yaml**
Vamos a usar un archivo yaml(`deployment.yaml`) para crear un trabajo.
```
apiVersion: apps/v1 
kind: Deployment 
metadata: 
  name: calculador2-deployment 
  labels: 
    app: calculador2 
spec: 
  replicas: 3 
  selector: 
    matchLabels: 
      app: calculador2 
  template: 
    metadata: 
      labels: 
        app: calculador2 
    spec: 
      containers: 
      - name: calculador2 
        image: calculador2 
        ports: 
        - containerPort: 8080 
```
Creamos el trabajo con `kubectl apply -f deployment.yaml`, luego corremos `kubectl get pods` y vemos los 3 pods creados. Sin embargo, en la columna STATUS vemos ErrImagePull, lo cual indica que no se pudo recuperar correctamente la imagen `calculador2` indicada en el yaml, pero ¿ por qué ? La imagen fue previamente creada con el comando `docker build -t calculador2 .` como se indica en la actividad y también se verificó su correcto funcionamiento creando un contenedor `docker run -p 8080:8080 --name calculador2 calculador2` que verificamos en `http://localhost:8080/sum?a=1&b=2`. Además si corremos el comando `docker images` podemos ver la imagen calculador2 correctamente creada. 

El problema en este caso es que el "entorno de docker" que usa minikube no es el mismo que usamos por defecto. Podemos ver esto si corremos la sig. línea: `minikube docker-env` lo que nos debe generar:
```
export DOCKER_TLS_VERIFY="1"
export DOCKER_HOST="tcp://192.168.49.2:2376"
export DOCKER_CERT_PATH="/home/gian/.minikube/certs"
export MINIKUBE_ACTIVE_DOCKERD="minikube"

# To point your shell to minikube's docker-daemon, run:
# eval $(minikube -p minikube docker-env)
```
La clave está en las últimas dos líneas, donde nos indican como hacer que nuestro shell apunte al docker de minikube, entonces corremos la instrucción: `eval $(minikube -p minikube docker-env)` y podemos comprobar que estamos en otro entorno docker al correr `docker images` y ver imágenes completamente diferentes. Esta configuración solo aplica al terminal donde estamos trabajando, por lo que no debemos preocuparnos de no poder volver a nuestro entorno docker anterior.

Ahora simplemente creamos de nuevo la imagen con `docker build -t calculador2 .`, borramos el trabajo creado anteriormente con `kubectl delete -f deployment.yaml` y volvemos a ejecutar `kubectl apply -f deployment.yaml`
```
apiVersion: apps/v1 
kind: Deployment 
metadata: 
  name: calculador2-deployment 
  labels: 
    app: calculador2 
spec: 
  replicas: 3 
  selector: 
    matchLabels: 
      app: calculador2 
  template: 
    metadata: 
      labels: 
        app: calculador2 
    spec: 
      containers: 
      - name: calculador2 
        image: calculador2 
        imagePullPolicy: Never 
        ports: 
        - containerPort: 8080 
```
Además hemos agregado al yaml la línea `imagePullPolicy: Never` para asegurarnos de que minikube no intente descargar la imagen de internet, y que use la local.

Ahora podemos correr `kubectl get pods` y ver tres pods con STATUS Running, que es lo esperado.

Y podemos correr `$ kubectl logs pods/calculador2-(nombre-del-pod)` para verificar que está corriendo un contenedor de docker correctametne.

**Implementación de un servicio en kubernetes**
Podemos usar un servicio(`service`) para agregar configuraciones adicionales a nuestros pods. En este caso queremos configurar como acceder a la aplicación desde el exterior. Escribimos el siguiente YAML(service.yaml)
```
apiVersion: v1 
kind: Service 
metadata: 
  name: calculador2-service 
spec: 
  type: NodePort 
  selector: 
    app: calculador2 
  ports: 
  - port: 8080 
```

Y lo corremos con `kubectl apply -f service.yaml ` 

Podemos verficar que está corriendo con `kubectl get service calculador2-service` que nos debe dar:
```
NAME                  TYPE       CLUSTER-IP      EXTERNAL-IP   PORT(S)          AGE
calculador2-service   NodePort   10.96.123.151   <none>        8080:32303/TCP   13s
```

Y podemos verificar que se está ejecutando para los 3 pods con `kubectl describe service calculador2-service | grep Endpoints` que nos da:
```
Endpoints:                10.244.0.26:8080,10.244.0.27:8080,10.244.0.28:8080
```
donde vemos 3 ips correspondientes a los 3 pods que estamos ejecutando.

**Exponiendo una aplicación**


