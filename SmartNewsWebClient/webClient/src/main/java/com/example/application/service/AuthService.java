package com.example.application.service;

import com.example.application.dataProviders.UserDataProvider;
import com.example.application.views.admin.MainAdminView;
import com.example.application.views.admin.categories.CategoriesMainViewAdmin;
import com.example.application.views.admin.news.NewsMainViewAdmin;
import com.example.application.views.admin.resources.ResourcesMainViewAdmin;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {
    private final UserDataProvider userDataProvider;

    public void logOut(String username) {
        UI.getCurrent().getPage().setLocation("login");
        VaadinSession.getCurrent().getSession().invalidate();
        VaadinSession.getCurrent().close();
    }

    public static class AuthorisedRoute{
        public String path;
        public Class<?extends Component> view;

        public AuthorisedRoute(String path, Class<?extends Component> view) {
            this.path =path;
            this.view = view;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public Class<? extends Component> getView() {
            return view;
        }

        public void setView(Class<? extends Component> view) {
            this.view = view;
        }
    }

    public AuthService(UserDataProvider userDataProvider) {
        this.userDataProvider = userDataProvider;
    }

    public boolean authenticate(String username, String password){
        String response  = userDataProvider.authenticate(username, password);
        if(!response.equals("denied")){
            this.getRoutes().forEach(route -> RouteConfiguration.forSessionScope().setRoute(route.path, route.view));
            VaadinSession.getCurrent().setAttribute("username", username);
            return true;
        }
        else return false;
    }

    public List<AuthorisedRoute> getRoutes(){
        ArrayList<AuthorisedRoute> routes = new ArrayList<>();
        routes.add(new AuthorisedRoute("personal", MainAdminView.class));
        routes.add(new AuthorisedRoute("admin/categories", CategoriesMainViewAdmin.class));
        routes.add(new AuthorisedRoute("admin/news", NewsMainViewAdmin.class));
        routes.add(new AuthorisedRoute("admin/resources", ResourcesMainViewAdmin.class));
        return routes;
    }
}
