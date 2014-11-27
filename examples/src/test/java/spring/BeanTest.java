package spring;

import static com.jayway.restassured.RestAssured.*;

import javax.script.ScriptException;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springroutes.RoutesBaseTest;

public class BeanTest extends RoutesBaseTest {

    @Before
    public void setUp() throws ScriptException {
        registerOnly("spring/bean.js");
    }

    @Test
    public void should_get_bean_by_name() {
        when()
              .get("/ask_bean_by_name").
        then()
              .body(Matchers.containsString("Hello World"));
    }

    @Test
    public void should_get_bean_by_class() {
        when()
              .get("/ask_bean_by_class").
        then()
              .body(Matchers.containsString("Hello World"));
    }
}
