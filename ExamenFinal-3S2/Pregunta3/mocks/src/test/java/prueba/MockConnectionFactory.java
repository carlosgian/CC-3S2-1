package prueba;

import produccion.ConnectionFactory;

import java.io.InputStream;

public class MockConnectionFactory implements ConnectionFactory {
    private InputStream inputStream;

    public void setData(InputStream inputStream){
        this.inputStream = inputStream;
    }
    @Override
    public InputStream getData() throws Exception {
        return inputStream;
    }
}
