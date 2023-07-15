package prueba;

import java.io.InputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MockHttpURLConnection extends HttpURLConnection{

    private InputStream inputStream;

    protected MockHttpURLConnection(URL url){
        super(url);
    }

    public void setupGetInputStream(InputStream inputStream){
        this.inputStream = inputStream;
    }
    public InputStream getInputStream() {
        return inputStream;
    }

    @Override
    public void connect() throws IOException {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public boolean usingProxy() {
        return false;
    }

}
