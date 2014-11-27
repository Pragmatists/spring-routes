package org.springroutes;

import org.apache.commons.io.IOUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

public class Req {

    private HttpServletRequest req;
    private String url;

    public Map<String, String> params;
    public String body;

    public Req(HttpServletRequest req, String url) {
        this.req = req;
        this.url = url;

        fillRequest();
    }

    private void fillRequest() {

        try {

            body = IOUtils.toString(req.getInputStream());
            String actual = new UrlPathHelper().getLookupPathForRequest(req);

            AntPathMatcher pathMatcher = new AntPathMatcher();
            params = pathMatcher.extractUriTemplateVariables(url, actual);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Object param(String name) {
        return params.get(name);
    }

    public String body() {
        return body;
    }

}