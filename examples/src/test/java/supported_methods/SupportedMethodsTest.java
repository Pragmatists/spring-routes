package supported_methods;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.post;
import static com.jayway.restassured.RestAssured.put;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {EmptyApplication.class, ScriptHandler.class})
@IntegrationTest
@WebAppConfiguration
public class SupportedMethodsTest {

    @Autowired
    private Routes routes;

    @Before
    public void setUp() throws ScriptException {

        routes.registerRoutesFrom("supported_methods/supported_methods.js");
    }
    
    @Test
    public void should_support_GET() throws Exception {

        // given:
        
        // when:
        String response = get("/hello").body().asString();
        
        // then:
        assertThat(response).contains("Hello GET!");
        
    }

    @Test
    public void should_support_POST() throws Exception {
        
        // given:
        
        // when:
        String response = post("/hello").body().asString();
        
        // then:
        assertThat(response).contains("Hello POST!");
        
    }
    
    @Test
    public void should_support_PUT() throws Exception {
        
        // given:
        
        // when:
        String response = put("/hello").body().asString();
        
        // then:
        assertThat(response).contains("Hello PUT!");
        
    }
    
    @Test
    public void should_support_DELETE() throws Exception {
        
        // given:
        
        // when:
        String response = delete("/hello").body().asString();
        
        // then:
        assertThat(response).contains("Hello DELETE!");
        
    }

    @Test
    public void should_support_path_building() throws Exception {

        // given:
        
        // when:
        String response = get("/parent/child").body().asString();
        
        // then:
        assertThat(response).contains("Hello from child!");
    }
    
}
