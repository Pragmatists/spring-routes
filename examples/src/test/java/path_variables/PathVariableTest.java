package path_variables;

import static com.jayway.restassured.RestAssured.get;
import static org.assertj.core.api.Assertions.assertThat;

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
import org.springroutes.ScriptHandler;

import com.jayway.restassured.response.Response;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {EmptyApplication.class, ScriptHandler.class})
@IntegrationTest
@WebAppConfiguration
public class PathVariableTest {
    
    @Autowired
    private Routes routes;

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
