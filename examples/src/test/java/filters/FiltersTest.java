package filters;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;

import javax.script.ScriptException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springroutes.EmptyApplication;
import org.springroutes.Routes;
import org.springroutes.RoutesBaseTest;
import org.springroutes.ScriptHandler;

public class FiltersTest extends RoutesBaseTest{

    @Before
    public void setUp() throws ScriptException {

        registerOnly("filters/filters.js");
    }
    
    @Test
    public void decorate_method_with_use() throws Exception {
        
        when().
            get("/hello").
        then().
            body(containsString("Hello World!"));
    }
    
}
