## Pregunta3

**Parte1**
Para empezar nos piden crear dos mocks `MockHttpURLConnection` y `MockURL`. El primero nos permite implementar el método `InputStream()` que vamos a usar en el segundo. Ambos mocks usan la librería java.net para e implementan `HttpURLConnection` y `URLConnection` respectivamente. 

Luego de implementar estos dos mocks, podemos implementar la primera prueba:
```
public class HTTP_FirstTest {
    @Test
    public void testGetContentOk() throws Exception{
        URL url = new URL("http://www.baeldung.com");

        MockHttpURLConnection mockConnection = new MockHttpURLConnection(url);
        mockConnection.setupGetInputStream( new ByteArrayInputStream("It works".getBytes()));

        MockURL mockURL = new MockURL(url);
        mockURL.openConnection(mockConnection);

        WebClient client = new WebClient();
        String workingContent = client.getContent(mockURL.getURL());
        assertEquals("It works", workingContent);
    }
}
```

La prueba no llega a pasar, algo no está funcionando bien en la conexión.
De hecho, al usar la opción de debug de Intellij podemos ver el problema:

![](https://github.com/carlosgian/CC-3S2-1/blob/master/ExamenFinal-3S2/images/preg1im13.png)

La URL en getContent() no es la misma que la de mockConnection ni tanmpoco igual a la original(http://www.baeldung.com).


**Parte2**
Refactorizar WebClient. Observamos la siguiente línea:
```
HttpURLConnection connection = (HttpURLConnection) url.openConnection();
```

Podemos separar (HttpURLConnection) url.openConnection()` a su propio método, lo cual hacemos, creando WebClient1 con las siguientes modificaciones:
```
HttpURLConnection connection = getHTTP(url);

public HttpURLConnection getHTTP(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }
```

**Parte3**
Queremos hacer una refactorización que permita que todo recurso pase por WebContent, sea una `URL` o una HttpURLConnection`, para evitar tener que volver engorrosa la firma de código, y además porque `URL` se recupera del mismo `HttpURLConnection`, usamos la interfaz `ConnectionFactory` que permite pasar una conexión genérica a la firma del método `getContent()`. La parte modificada queda así, además ya no es necesario el método aparte `getHTTP(URL url)`.
```
public String getContent(ConnectionFactory connectionFactory){
        StringBuffer content = new StringBuffer();
        try{
            InputStream is = connectionFactory.getData();
```

Esta es una mejor solución, porque permite pasar a `getContent()` cualquier tipo de conexión siempre y cuando implemente `ConnectionFactory`

**Parte4**
Implementamos la clase `HttpURLConnectionFactory` que nos fue proporcionada. Esta versión implementa ConnectionFactory.

Posteriormente usamos nuestro conocimiento sobre Mocks para implementar `MockConnectionFactory`

Implementamos la expectativa para evitar una fuga de recursos usando el inputStream, esta clase también no es proporcionada.

Implementamos la última prueba, `TestWebClientFail` y probamos, primero vemos que no pasa. Sin embargo, si comentamos la última línea
`mockInputStream.verify()` la prueba pasa, por qué? Pues, verify() obliga a llamar una excepción si es que no se ha cerrado el inputStream.
Para arreglarlo simplemente agregamos una línea antes del verify:
```
mockInputStream.close();
mockInputStream.verify();
```
Con el stream cerrado, la prueba `TestWebClientFail` pasa.

![](https://github.com/carlosgian/CC-3S2-1/blob/master/ExamenFinal-3S2/images/preg1im14.png)





