package org.springroutes;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class ScriptHandler implements HandlerMapping, HandlerAdapter, InitializingBean, Ordered, ApplicationContextAware, ImportAware {

    private ApplicationContext applicationContext;

    private Routes routes;

    private List<String> scripts = new ArrayList<>();

    @Override
    public void afterPropertiesSet() throws Exception {

        routes = new Routes(applicationContext);

        scripts.forEach(this::loadScript);

    }

    private void loadScript(String s) {
        try {
            routes.registerRoutesFrom(s);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public Routes routes() {
        return routes;
    }

    @Override
    public HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {

        if (routes.hasHandlerFor(request)) {
            return new HandlerExecutionChain(routes);
        }

        return null;
    }

    @Override
    public boolean supports(Object handler) {

        return handler instanceof Routes;
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Routes route = (Routes) handler;
        route.handle(request, response);

        return null;
    }

    @Override
    public long getLastModified(HttpServletRequest request, Object handler) {
        return -1;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setImportMetadata(AnnotationMetadata metadata) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(EnableRoutes.class.getName(), true));
        this.scripts.addAll(Arrays.asList(attributes.getStringArray("value")));
    }

}
