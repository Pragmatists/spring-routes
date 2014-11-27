package request;

import com.jayway.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.springroutes.RoutesBaseTest;

import javax.script.ScriptException;
import javax.servlet.http.HttpServletResponse;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.post;
import static org.assertj.core.api.Assertions.assertThat;

public class ConsumesTest extends RoutesBaseTest {

    @Before
    public void setUp() throws ScriptException {

        routes.registerRoutesFrom("request/consumes.js");
    }

    @Test
    public void shouldConsumeAnyContentIfNotLimited() throws Exception {
        // given:

        // when:
        int code = given().contentType(ContentType.TEXT).post("/without_consumes").statusCode();

        // then:
        assertThat(code).isEqualTo(HttpServletResponse.SC_OK);
    }

    @Test
    public void shouldFailIfConsumeIsNotSpecifiedInRequest() throws Exception {
        // given:

        // when:
        int code = post("/text").statusCode();

        // then:
        assertThat(code).isEqualTo(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void shouldSucceedIfContentTypeMatches() throws Exception {
        // given:

        // when:
        int code = given().contentType(ContentType.TEXT).post("/text").statusCode();

        // then:
        assertThat(code).isEqualTo(HttpServletResponse.SC_OK);
    }

    @Test
    public void shouldSucceedIfContentTypeMatchesWithWildcard() throws Exception {
        // given:

        // when:
        int code = given().contentType(ContentType.HTML).post("/text_wildcard").statusCode();

        // then:
        assertThat(code).isEqualTo(HttpServletResponse.SC_OK);
    }

    @Test
    public void shouldFailIfContentTypeNotMatches() throws Exception {
        // given:

        // when:
        int code = given().contentType(ContentType.BINARY).post("/text").statusCode();

        // then:
        assertThat(code).isEqualTo(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
