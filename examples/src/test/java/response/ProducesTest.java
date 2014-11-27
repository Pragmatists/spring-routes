package response;

import com.jayway.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.springroutes.RoutesBaseTest;

import javax.script.ScriptException;
import javax.servlet.http.HttpServletResponse;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ProducesTest extends RoutesBaseTest {

    @Before
    public void setUp() throws ScriptException {

        routes.registerRoutesFrom("response/produces.js");
    }

    @Test
    public void shouldProduceWhenNothingSpecified() throws Exception {
        // given:

        // when:
        String contentType = get("/without_produces").contentType();

        // then:
        assertThat(contentType).isEqualTo("application/json;charset=UTF-8");
    }

    @Test
    public void shouldProduceSpecifiedContentType() throws Exception {
        // given:

        // when:
        String contentType = get("/text").contentType();

        // then:
        assertThat(contentType).isEqualTo("text/plain");
    }

    @Test
    public void shouldFailIfAcceptTypeNotMatches() throws Exception {
        // given:

        // when:
        int code = given().accept(ContentType.BINARY).get("/text").statusCode();

        // then:
        assertThat(code).isEqualTo(HttpServletResponse.SC_NOT_ACCEPTABLE);
    }
}
