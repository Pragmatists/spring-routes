package org.springroutes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Res {

    private HttpServletResponse resp;

    public Res(HttpServletResponse resp) {
        this.resp = resp;
    }

    public void send(String text) throws IOException {
        resp.getOutputStream().println(text);
    }

    public void contentType(String contentType) {
        resp.setContentType(contentType);
    }

    public String getContentType() {
        return resp.getContentType();
    }

    public void status(int status) {
        resp.setStatus(status);
    }
}
