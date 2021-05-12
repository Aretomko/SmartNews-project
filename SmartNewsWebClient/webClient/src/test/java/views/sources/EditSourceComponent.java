package views.sources;

import com.example.application.App;
import com.example.application.dataProvider.NewsDataProvider;
import com.example.application.domain.domain.News;

import com.example.application.domain.domain.Source;
import com.example.application.views.admin.NavbarAdmin;
import com.example.application.views.admin.news.sources.CreateSourceComponent;
import com.example.application.views.admin.news.sources.CreateSourcesGridService;
import com.example.application.views.admin.news.sources.SourcesMainView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.textfield.TextField;
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
public class EditSourceComponent {
    @Autowired
    private NewsDataProvider newsDataProvider;
    @Test
    public void editSource_validData_NameUpdated(){
        News news = newsDataProvider.getAllNews().get(0);
        Source source = newsDataProvider.getSourcesByNews(news).get(0);
        com.example.application.views.admin.news.sources.EditSourceComponent editSourceComponent =new com.example.application.views.admin.news.sources.EditSourceComponent(
                source,
                newsDataProvider,
                new Grid<Source>(),
                new SourcesMainView(
                        newsDataProvider,
                        new CreateSourcesGridService(newsDataProvider)
                        ),
                new NavbarAdmin(),
                new CreateSourceComponent(newsDataProvider, new Grid<Source>(), news)
                );
        String editedName = "edited name";
        editSourceComponent.setNameField(new TextField(editedName));
        String editedReference= "EditedReference";
        editSourceComponent.setReferenceField(new TextField(editedReference));
        editSourceComponent.editSource();
        Source sourceAfterEdit = newsDataProvider.getSourcesByNews(news).get(0);
        Assert.assertEquals(sourceAfterEdit.getName(), editedName);
    }
    @Test
    public void editSource_validData_ReferenceUpdated(){
        News news = newsDataProvider.getAllNews().get(0);
        Source source = newsDataProvider.getSourcesByNews(news).get(0);
        com.example.application.views.admin.news.sources.EditSourceComponent editSourceComponent =new com.example.application.views.admin.news.sources.EditSourceComponent(
                source,
                newsDataProvider,
                new Grid<Source>(),
                new SourcesMainView(
                        newsDataProvider,
                        new CreateSourcesGridService(newsDataProvider)
                ),
                new NavbarAdmin(),
                new CreateSourceComponent(newsDataProvider, new Grid<Source>(), news)
        );
        String editedName = "edited name";
        editSourceComponent.setNameField(new TextField(editedName));
        String editedReference= "EditedReference";
        editSourceComponent.setReferenceField(new TextField(editedReference));
        editSourceComponent.editSource();
        Source sourceAfterEdit = newsDataProvider.getSourcesByNews(news).get(0);
        Assert.assertEquals(sourceAfterEdit.getReference(), editedReference);
    }
}
