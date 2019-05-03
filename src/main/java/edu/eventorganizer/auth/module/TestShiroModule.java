package edu.eventorganizer.auth.module;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.binder.AnnotatedBindingBuilder;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.config.Ini;
import org.apache.shiro.guice.web.ShiroWebModule;
import org.apache.shiro.realm.text.IniRealm;

import javax.servlet.ServletContext;
import java.net.MalformedURLException;

public class TestShiroModule extends ShiroWebModule {
    public TestShiroModule(ServletContext servletContext) {
        super(servletContext);
    }

    @Override
    protected void configureShiroWeb() {
        bindRealm().to(IniRealm.class);
//        try {
//            bindRealm().toConstructor(IniRealm.class.getConstructor(Ini.class));
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
    }

    @Provides
    @Singleton
    IniRealm loadIniRealm(Ini ini) {
        IniRealm realm = new IniRealm(ini);
        return realm;
    }

    //here could be filters if not added to shiro.ini file

    @Provides
    @Singleton
    Ini loadShiroIni() {
        return Ini.fromResourcePath("C:/Users/aleks/IdeaProjects/EventOrganizerGuice/src/main/webapp/WEB-INF/shiro.ini");
    }
}
