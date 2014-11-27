package response;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.*;

import javax.script.ScriptException;

import org.junit.Before;
import org.junit.Test;
import org.springroutes.RoutesBaseTest;

import com.jayway.restassured.http.ContentType;

public class SendTest extends RoutesBaseTest{

    @Before
    public void setUp() throws ScriptException {
        routes.registerRoutesFrom("response/send.js");
    }

    @Test
    public void should_send_given_body_content() {
        when()
                .get("/hello").
        then()
                .body(containsString("Sent body"));
    }
}
