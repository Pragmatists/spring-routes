package response;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;

import javax.script.ScriptException;

import org.junit.Before;
import org.junit.Test;
import org.springroutes.RoutesBaseTest;

import com.jayway.restassured.http.ContentType;

public class ContentTypeTest extends RoutesBaseTest {

    @Before
    public void setUp() throws ScriptException {
        routes.registerRoutesFrom("response/content-type.js");
    }

    @Test
    public void should_return_given_content_type() {
        when()
                .get("/hello").
                then()
                .contentType(ContentType.HTML);
    }
}
