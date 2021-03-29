import com.example.application.Application;

import com.example.application.dataProviders.DataProvider;
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
public class DataProviderTest {
    @Autowired
    private DataProvider dataProvider;
    @Test
    public void createFollowPostRequest_BooleanResponseReceived(){
        boolean created = dataProvider.createFollowPost("some string");
        Assert.assertTrue(created);
    }
    @Test
    public void authenticate_TokenDerived(){
        String token = dataProvider.authenticate("username", "password");
        Assert.assertTrue(token!=null);
    }
    @Test
    public void starProjectRequest_BooleanResponseReceived(){
        boolean response = dataProvider.createStarPost("some string");
        Assert.assertTrue(response);
    }
}
