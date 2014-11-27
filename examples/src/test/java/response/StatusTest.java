package response;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;

import javax.script.ScriptException;

import org.junit.Before;
import org.junit.Test;
import org.springroutes.RoutesBaseTest;

public class StatusTest extends RoutesBaseTest {
    @Before
    public void setUp() throws ScriptException {
        registerOnly("response/status.js");
    }

    @Test
    public void should_return_proper_status_code() {
        when()
                .get("/hello").
        then()
                .statusCode(414);
    }
}
