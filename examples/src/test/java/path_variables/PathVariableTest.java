package path_variables;

import com.jayway.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.springroutes.RoutesBaseTest;

import javax.script.ScriptException;

import static com.jayway.restassured.RestAssured.get;
import static org.assertj.core.api.Assertions.assertThat;


public class PathVariableTest extends RoutesBaseTest {
    
    @Before
    public void setUp() throws ScriptException {

        routes.registerRoutesFrom("path_variables/path_variables.js");
    }
    
    @Test
    public void should_support_path_variables() throws Exception {

        // given:
        
        // when:
        String response = get("/hello/john").body().asString();
        
        // then:
        assertThat(response).contains("Hello john!");
    }

    @Test
    public void should_support_path_variables_with_regexp() throws Exception {
        
        // given:
        
        // when:
        String response = get("/hello-regexp/adam").body().asString();
        
        // then:
        assertThat(response).contains("Hello adam!");
    }
    
    @Test
    public void should_support_path_variables_with_regexp_2() throws Exception {
        
        // given:
        
        // when:
        Response response = get("/hello-regexp/bob");
        
        // then:
        assertThat(response.getStatusCode()).isEqualTo(404);
        
    }
    
}
