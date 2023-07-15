package prueba;

import org.junit.jupiter.api.Test;
import produccion.WebClient;

import java.io.ByteArrayInputStream;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
