package ejemplos;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

public class WelcomeEmailTest {
    @ExtendWith(MockitoExtension.class)
    class WelcomeEmailTest {
        @Mock
        private MailServer mailServer;

        @Test
        public void sendWelcomeEmail(){
            var notifications;
        }
    }
}
