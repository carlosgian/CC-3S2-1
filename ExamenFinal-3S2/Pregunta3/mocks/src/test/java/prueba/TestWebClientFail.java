package prueba;

import org.junit.jupiter.api.Test;
import produccion.WebClient2;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWebClientFail {
    @Test
    public void testGetContentOk() throws Exception{
        MockConnectionFactory mockConnectionFactory = new MockConnectionFactory();
        MockInputStream mockInputStream = new MockInputStream();
        mockInputStream.setBuffer("Esto funciona!");
        mockConnectionFactory.setData(mockInputStream);
        WebClient2 client = new WebClient2();
        String workingContent = client.getContent(mockConnectionFactory);

        assertEquals("Esto funciona!",workingContent);
        mockInputStream.close();
        mockInputStream.verify();
    }
}
