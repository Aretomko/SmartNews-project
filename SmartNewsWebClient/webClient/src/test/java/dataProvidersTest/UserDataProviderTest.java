package dataProvidersTest;

import com.example.application.App;
import com.example.application.dataProvider.UserDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class UserDataProviderTest {
    @Autowired
    private UserDataProvider userDataProvider;

    @Test
    public void login() {
        String token = userDataProvider.authenticate("admin", "123");
        Assert.assertTrue(!token.equals("denied"));
    }
}

