package supported_methods;

import org.junit.Before;
import org.junit.Test;
import org.springroutes.RoutesBaseTest;

import javax.script.ScriptException;

import static com.jayway.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;


public class SupportedMethodsTest extends RoutesBaseTest {

    @Before
    public void setUp() throws ScriptException {

        registerOnly("supported_methods/supported_methods.js");
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
