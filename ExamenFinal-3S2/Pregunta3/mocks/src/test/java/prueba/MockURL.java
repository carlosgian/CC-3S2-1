package prueba;

import java.net.URL;
import java.net.URLConnection;
import java.io.InputStream;
import java.io.IOException;
public class MockURL extends URLConnection{
    private MockHttpURLConnection connection;

    protected MockURL(URL url){
        super(url);
    }

    public void openConnection(MockHttpURLConnection connection){
        this.connection = connection;
    }

    public InputStream geInStream(){
        return connection.getInputStream();
    }

    @Override
    public void connect() throws IOException {

    }
}
