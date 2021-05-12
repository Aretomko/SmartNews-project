package dataProvidersTest;

import com.example.application.App;
import com.example.application.dataProvider.ResourcesDataProvider;
import com.example.application.domain.domain.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class ResourcesDataProviderTest {
    @Autowired
    private ResourcesDataProvider resourcesDataProvider;

    @Test
    public void getAllResourcesTest(){
        List<Resource> resources = resourcesDataProvider.getAllResources();
        Assert.assertTrue(resources.size()>0);
    }
    @Test
    public void createResource(){
        boolean created = resourcesDataProvider.createResource("name", "reference");
        Assert.assertTrue(created);
    }
    @Test
    public void editResource(){
        boolean edited = resourcesDataProvider.editResource("26", "new name", "new reference");
        Assert.assertTrue(edited);
    }
}
