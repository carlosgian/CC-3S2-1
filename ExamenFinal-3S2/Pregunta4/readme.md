## Pregunta 4 - Implementando Product-Composite-Service.
Continuando de la actividad de [Microservicios](https://github.com/carlosgian/CC-3S2-1/tree/master/Actividades/Microservicios) desarrollada en clase.

Luego de implementar los microservicios `Product`, `Review` y `Recommendation` independientemente, vamos a implementar el servicio `Product-Composite` que, como su nombre lo dice, usa todos los anteriores juntos.

Para empezar crearemos la clase `ProductCompositeIntegration` que se encargará de implementar los métodos de cada microservicio individual en uno solo. Para esto la clase implementa `ProductService`, `ReviewService` y `RecommendationService`. Es decir, esta clase implementará tres métodos, que devolverán un Producto, una lista de Recomendaciones y una lista de Reviews en ese orden. 

La clase está ubicada en la `productcompositeservice/services` junto a la clase `ProductCompositeServiceImpl` la cual explicaremos luego.
Para implementar correctamente la clase `ProductCompositeIntegration` se realizaron las siguientes importaciones:
```
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.kapumota.api.core.review.ReviewService;
import com.kapumota.api.core.recommendation.RecommendationService;
import com.kapumota.api.core.product.ProductService;
import com.kapumota.api.core.review.Review;
import com.kapumota.api.core.recommendation.Recommendation;
import com.kapumota.api.core.product.Product;
import com.kapumota.util.http.ServiceUtil;
```
Importamos `Component` y `Autowired` para la anotación de la clase y su constructor respectivamente. Cabe recordar que `Component` es una anotación genérica que le dice a Spring Boot que dicha clase será usada en el proyecto. Y que `Autowired` delega a SpringBoot la inyección de dependencias de clase, en este caso, `ServiceUtil`.

Seguido de ello, importamos las clases a implementar. Y luego las clases que definen la estructura de cada servicio(`Product`, `Recommendation` y `Review`), pues los métodos a implementar devuelven objetos de este tipo.

Luego, escribimos la implementación, es básicamente lo mismo que los archivos `Impl` de cada microservicio, pero quitamos el manejo de errores pues eso lo manejará `ProductCompositeServiceImpl`. La implementación es como sigue:
```
//private static final Logger LOG = LoggerFactory.getLogger(ProductCompositeServiceImpl.class);

  	private final ServiceUtil serviceUtil;

  	@Autowired
  	public ProductCompositeIntegration(ServiceUtil serviceUtil) {
    		this.serviceUtil = serviceUtil;
  	}
	
	@Override
  	public Product getProduct(int productId) {

    	return new Product(productId, "nombre-" + productId, 123, serviceUtil.getServiceAddress());
  	}
  	
  	
  	@Override
  	public List<Recommendation> getRecommendations(int productId) {
    
    	Recommendation recom1 = new Recommendation(productId, 1, "Gian", 4, "Producto bueno", serviceUtil.getServiceAddress());
    	List<Recommendation> recomList = new ArrayList<Recommendation>();
    	recomList.add(recom1);
    	return recomList;
 	 }
  	
	@Override
  	public List<Review> getReviews(int productId) {
     
    		Review review1 = new Review(productId, 1, "Gian", "Subject", "Producto bueno", serviceUtil.getServiceAddress());
    		List<Review> reviewList = new ArrayList<Review>();
    		reviewList.add(review1);
    		return reviewList;
  	}
```

Luego de esto pasamos a la implementación de `ProductCompositeServiceImpl`. Esta clase de encarga de implementar `ProductCompositeService` y su único método que devuelve una instancia de la clase `ProductAggregate`. Como plantilla hemos usado la clase `ProductServiceImpl`, del microservicio Product.

Es evidente que queremos devolver una instancia de la clase `ProductAggregate` por ello la línea de retorno:
```
    return new ProductAggregate(product.getProductId(),"producto1", 50, recommendations, reviews, addresses);
```
Lo primero que hacemos para obtener los argumentos del constructor de `ProductAggregate` es instanciar la clase `ProductCompositeIntegration` para así poder llamar a cada uno de los microservicios previamente implementados.

Una vez hecho eso nos topamos con un problema. `ProductAggregate` toma como argumento listas de `RecommendationSummary`, `ReviewSummary` y `ServiceAddresses` respectivamente. Pero nuestros métodos de `ProductCompositeIntegration` devuelven listas de recomendaciones y reviews a secas.
Para resolver este problema nos vamos directamente a la clase `Review` y `Recommendation` y agregamos métodos que "exportan" los objetos a `RecommendationSummary` y `ReviewSummary` respectivamente:

Para `Recommendation`:
```
public RecommendationSummary exportToRecommendationSummary(){
    return new RecommendationSummary(recommendationId, author, rate);
  }
```

Para `Review`:
```
public ReviewSummary exportToReviewSummary(){
    return new ReviewSummary(reviewId, author, subject);
  }
```
Por supuesto también tenemos que importar las clases correspondientes para cada uno.

Una vez hecho eso, podemos implementar la lógica principal de `productAggregate getProduct()` como sigue:
```
ProductCompositeIntegration integration = new ProductCompositeIntegration(serviceUtil);
    Product product = integration.getProduct(productId);
    
    List<RecommendationSummary> recommendations = new ArrayList<>();
    recommendations.add(integration.getRecommendations(productId).get(0).exportToRecommendationSummary());
    String rec = integration.getRecommendations(productId).get(0).getServiceAddress();
    
    List<ReviewSummary> reviews = new ArrayList<>();
    reviews.add(integration.getReviews(productId).get(0).exportToReviewSummary());
    String rev = integration.getReviews(productId).get(0).getServiceAddress();
    
    ServiceAddresses addresses = new ServiceAddresses(serviceUtil.getServiceAddress(),product.getServiceAddress(),rev,rec);
    return new ProductAggregate(product.getProductId(),"producto1", 50, recommendations, reviews, addresses);
```
Producto se obtiene de manera normal. La lista de recomendaciones/reviews "summary", se obtiene llamando al método de exportación creado en el paso anterior y agrégandolo a una nueva lista definida en la misma clase.

Cabe mencionar también la clase `ServiceAddresses` que es solamente un conjunto de las 4 `ServiceAddress` de cada microservicio, los cuales se están obteniendo a través del metodo directo que cada constructor básico posee `getServiceAddress`.

**Probando el microservicio**
Ejecutamos `./gradle build` y posteriorement `java -jar microservicios/product-composite-service/build/libs/*.jar &` para empezar a correr el microservicio agregado:

![](https://github.com/carlosgian/CC-3S2-1/blob/master/ExamenFinal-3S2/images/preg1im7.png)

![](https://github.com/carlosgian/CC-3S2-1/blob/master/ExamenFinal-3S2/images/preg1im8.png)

Podemos verlo en el browser:

![](https://github.com/carlosgian/CC-3S2-1/blob/master/ExamenFinal-3S2/images/preg1im9.png)

Y podemos hacer HTTP request con CURL:

![](https://github.com/carlosgian/CC-3S2-1/blob/master/ExamenFinal-3S2/images/preg1im10.png)

Y darle un forma bonito con un poco de ayuda:

![](https://github.com/carlosgian/CC-3S2-1/blob/master/ExamenFinal-3S2/images/preg1im11.png)

Luego podemos terminar el servicio con `kill $(jobs -p)`

![](https://github.com/carlosgian/CC-3S2-1/blob/master/ExamenFinal-3S2/images/preg1im12.png)





