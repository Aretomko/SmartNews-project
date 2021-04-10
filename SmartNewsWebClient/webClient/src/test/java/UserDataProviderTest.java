import com.example.application.Application;
import com.example.application.dataProviders.UserDataProvider;
import com.thinhda.spring.grpc.core.model.LoginRequest;
import com.thinhda.spring.grpc.core.model.LoginResponse;
import com.thinhda.spring.grpc.core.model.LoginServiceGrpc;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class UserDataProviderTest {
    @Autowired
    private UserDataProvider userDataProvider;
    @Test
    public void login(){
        String token = userDataProvider.authenticate("admin", "123");
            Assert.assertTrue(!token.equals("denied"));
    }
}
