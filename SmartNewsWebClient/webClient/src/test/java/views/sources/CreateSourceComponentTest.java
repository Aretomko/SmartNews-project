package views.sources;

import com.example.application.App;
import com.example.application.dataProvider.NewsDataProvider;
import com.example.application.domain.domain.News;
import com.example.application.domain.domain.Source;
import com.example.application.views.admin.news.sources.CreateSourceComponent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.textfield.TextField;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class CreateSourceComponentTest {
    @Autowired
    private NewsDataProvider newsDataProvider;
    @Test
    public void addSource_ValidData_SourceCreated(){
        News news = newsDataProvider.getAllNews().get(0);
        CreateSourceComponent createSourceComponent = new CreateSourceComponent(
                newsDataProvider,
                new Grid<Source>(),
                news
        );
        int numberOfSourcesBefore = newsDataProvider.getSourcesByNews(news).size();
        String name = "new name";
        createSourceComponent.setNameField(new TextField(name));
        String reference = "new reference";
        createSourceComponent.setReferenceField(new TextField(reference));
        createSourceComponent.addSource();
        Assert.assertEquals(numberOfSourcesBefore+1, newsDataProvider.getSourcesByNews(news).size());
    }
    @Test
    public void addSource_ValidData_SourceWithRightNameCreated(){
        News news = newsDataProvider.getAllNews().get(0);
        CreateSourceComponent createSourceComponent = new CreateSourceComponent(
                newsDataProvider,
                new Grid<Source>(),
                news
        );
        int numberOfSourcesBefore = newsDataProvider.getSourcesByNews(news).size();
        String name = "new name";
        createSourceComponent.setNameField(new TextField(name));
        String reference = "new reference";
        createSourceComponent.setReferenceField(new TextField(reference));
        createSourceComponent.addSource();
        List<Source> sources = newsDataProvider.getSourcesByNews(news);
        sources.sort(Comparator.comparing(Source::getId).reversed());
        Assert.assertEquals(sources.get(0).getName(), name);
    }
    @Test
    public void addSource_ValidData_SourceWithRightReferenceCreated(){
        News news = newsDataProvider.getAllNews().get(0);
        CreateSourceComponent createSourceComponent = new CreateSourceComponent(
                newsDataProvider,
                new Grid<Source>(),
                news
        );
        int numberOfSourcesBefore = newsDataProvider.getSourcesByNews(news).size();
        String name = "new name";
        createSourceComponent.setNameField(new TextField(name));
        String reference = "new reference";
        createSourceComponent.setReferenceField(new TextField(reference));
        createSourceComponent.addSource();
        List<Source> sources = newsDataProvider.getSourcesByNews(news);
        sources.sort(Comparator.comparing(Source::getId).reversed());
        Assert.assertEquals(sources.get(0).getReference(), reference);
    }
}
