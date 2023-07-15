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




