package response;

import static com.jayway.restassured.RestAssured.when;

import javax.script.ScriptException;

import org.junit.Before;
import org.junit.Test;
import org.springroutes.RoutesBaseTest;

import com.jayway.restassured.http.ContentType;

public class HeaderTest extends RoutesBaseTest {

    @Before
    public void setUp() throws ScriptException {
        registerOnly("response/header.js");
    }

    @Test
    public void should_set_header_property() {
        when()
                .get("/hello").
        then()
                .header("headerProperty", "headerValue");
    }

    @Test
    public void should_set_header_from_object() {
        when()
                .get("/set-header-from-object").
                then()
                .header("headerProperty", "headerValue")
                .header("otherProperty", "otherValue");
    }
}
