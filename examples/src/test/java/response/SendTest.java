package response;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import javax.script.ScriptException;

import org.junit.Before;
import org.junit.Test;
import org.springroutes.RoutesBaseTest;

public class SendTest extends RoutesBaseTest{

    @Before
    public void setUp() throws ScriptException {
        registerOnly("response/send.js");
    }

    @Test
    public void should_send_given_body_content() {
        when()
                .get("/hello").
        then()
                .body(containsString("Sent body"));
    }
}
