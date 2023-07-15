package produccion;

import java.io.IOException;
import java.io.InputStream;

public class WebClient2 {
    public String getContent(ConnectionFactory connectionFactory){
        StringBuffer content = new StringBuffer();
        try{
            InputStream is = connectionFactory.getData();

            byte[] buffer = new byte[2048];
            int count;
            while(-1 != (count = is.read(buffer))){
                content.append(new String(buffer, 0, count));
            }

        }catch (IOException e){
            throw new RuntimeException(e);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return content.toString();
    }
}
