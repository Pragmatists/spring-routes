package org.springroutes;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.condition.ConsumesRequestCondition;
import org.springframework.web.servlet.mvc.condition.HeadersRequestCondition;
import org.springframework.web.servlet.mvc.condition.ParamsRequestCondition;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

public class Routes {

    private ApplicationContext applicationContext;

    private List<Route> routes = new ArrayList<>();

    public Routes(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Object bean(String bean) {
        return applicationContext.getBean(bean);
    }
    public Object bean(Class beanClass) {
        return applicationContext.getBean(beanClass);
    }

    public Route route() {
        return new Route("");
    }

    public void clear() {
        routes.clear();
    }

    public interface Callback {
        public void handle(Req req, Res res, Next next);
    }

    public class Route implements Cloneable {

        private Callback callback = (req, res, next) -> next.next();

        private RequestMappingInfo mapping;


        public Route(String url) {

            mapping = new RequestMappingInfo(
                    new PatternsRequestCondition(url),
                    new RequestMethodsRequestCondition(),
                    new ParamsRequestCondition(),
                    new HeadersRequestCondition(),
                    new ConsumesRequestCondition(),
                    new ProducesRequestCondition(),
                    null);
        }

        public void handle(HttpServletRequest req, HttpServletResponse res) {

            List<String> urls = new ArrayList<>(mapping.getPatternsCondition().getPatterns());

            callback.handle(new Req(req, urls.get(0)), new Res(res), () -> {
            });
        }

        public Route get(String url, Callback handler) {

            Route copy = this.copy();
            copy.mapping = mapping.combine(new RequestMappingInfo(
                    new PatternsRequestCondition(url),
                    new RequestMethodsRequestCondition(RequestMethod.GET),
                    new ParamsRequestCondition(),
                    new HeadersRequestCondition(),
                    new ConsumesRequestCondition(),
                    new ProducesRequestCondition(),
                    null
            ));
            copy.chain(handler);

            registerRoute(copy);

            return this;
        }

        private boolean registerRoute(Route route) {

            System.out.println("Registering: " + route.mapping);

            return routes.add(route);
        }

        private Route copy() {
            try {
                return (Route) clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }

        public Route post(String url, Callback handler) {

            Route copy = this.copy();
            copy.mapping = mapping.combine(new RequestMappingInfo(
                    new PatternsRequestCondition(url),
                    new RequestMethodsRequestCondition(RequestMethod.POST),
                    new ParamsRequestCondition(),
                    new HeadersRequestCondition(),
                    new ConsumesRequestCondition(),
                    new ProducesRequestCondition(),
                    null
            ));
            copy.chain(handler);

            registerRoute(copy);
            return this;
        }

        public Route put(String url, Callback handler) {
            
            Route copy = this.copy();
            copy.mapping = mapping.combine(new RequestMappingInfo(
                    new PatternsRequestCondition(url),
                    new RequestMethodsRequestCondition(RequestMethod.PUT),
                    new ParamsRequestCondition(),
                    new HeadersRequestCondition(),
                    new ConsumesRequestCondition(),
                    new ProducesRequestCondition(),
                    null
                    ));
            copy.chain(handler);
            
            registerRoute(copy);
            return this;
        }
        
        public Route del(String url, Callback handler) {
            
            Route copy = this.copy();
            copy.mapping = mapping.combine(new RequestMappingInfo(
                    new PatternsRequestCondition(url),
                    new RequestMethodsRequestCondition(RequestMethod.DELETE),
                    new ParamsRequestCondition(),
                    new HeadersRequestCondition(),
                    new ConsumesRequestCondition(),
                    new ProducesRequestCondition(),
                    null
                    ));
            copy.chain(handler);
            
            registerRoute(copy);
            return this;
        }
        
        public Route use(Callback handler) {

            Route copy = this.copy();
            copy.chain(handler);

            return copy;
        }

        private void chain(Callback handler) {
            Callback parent = callback;
            callback = new Callback() {

                @Override
                public void handle(Req req, Res res, Next next) {
                    parent.handle(req, res, () -> handler.handle(req, res, next));
                }
            };
        }

        public Route path(String url) {

            Route copy = this.copy();
            copy.mapping = mapping.combine(new RequestMappingInfo(
                    new PatternsRequestCondition(url),
                    new RequestMethodsRequestCondition(),
                    new ParamsRequestCondition(),
                    new HeadersRequestCondition(),
                    new ConsumesRequestCondition(),
                    new ProducesRequestCondition(),
                    null
            ));
            return copy;
        }

        public Route consumes(String contentType) {

            Route copy = this.copy();
            copy.mapping = mapping.combine(new RequestMappingInfo(
                    new PatternsRequestCondition(),
                    new RequestMethodsRequestCondition(),
                    new ParamsRequestCondition(),
                    new HeadersRequestCondition(),
                    new ConsumesRequestCondition(contentType),
                    new ProducesRequestCondition(),
                    null
            ));
            return copy;
        }

        public Route produces(String contentType) {

            Route copy = this.copy();
            copy.mapping = mapping.combine(new RequestMappingInfo(
                    new PatternsRequestCondition(),
                    new RequestMethodsRequestCondition(),
                    new ParamsRequestCondition(),
                    new HeadersRequestCondition(),
                    new ConsumesRequestCondition(),
                    new ProducesRequestCondition(contentType),
                    null
            ));
            return copy;
        }

        public boolean handles(HttpServletRequest request) {
            return mapping.getMatchingCondition(request) != null;
        }

    }

    public void registerRoutesFrom(String script) throws ScriptException {

        System.out.println("Loading " + script);

        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

        SimpleScriptContext context = new SimpleScriptContext();
        context.setAttribute("_app", this, ScriptContext.ENGINE_SCOPE);
        engine.eval(script("org/springroutes/route.js"), context);
        engine.eval(script(script), context);
    }

    private InputStreamReader script(String script) {
        try {
            InputStreamReader reader = new InputStreamReader(new ClassPathResource(script).getInputStream());
            return reader;
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("Script '%s' was not found", script), e);
        }
    }

    public void log(String message) {
        System.out.println(message);
    }

    public boolean hasHandlerFor(HttpServletRequest request) {

        for (Route route : routes) {
            if (route.handles(request)) {
                return true;
            }
        }
        return false;
    }

    public void handle(HttpServletRequest request, HttpServletResponse response) {

        for (Route route : routes) {
            if (route.handles(request)) {
                route.handle(request, response);
                return;
            }
        }
    }
}
