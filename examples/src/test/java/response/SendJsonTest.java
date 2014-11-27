package response;

import static com.jayway.restassured.RestAssured.when;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONAs;

import javax.script.ScriptException;

import org.junit.Before;
import org.junit.Test;
import org.springroutes.RoutesBaseTest;

import com.jayway.restassured.http.ContentType;

public class SendJsonTest extends RoutesBaseTest{

    @Before
    public void setUp() throws ScriptException {
        registerOnly("response/send-json.js");
    }

    @Test
    public void should_send_json_as_body() {
        when()
                .get("/hello-json").
                then()
                .body(sameJSONAs("{" +
                        "   stringValue: 'hello'," +
                        "   number: 3," +
                        "   array: [1, 2, 3]," +
                        "   object: {" +
                        "    property: 'value'" +
                        "   }" +
                        "}"));
    }

    @Test
    public void should_set_content_type_for_json() {
        when()
                .get("/hello-json").
                then()
                .contentType(ContentType.JSON);
    }

    @Test
    public void should_respect_content_type_set_by_user_for_json() {
        when()
                .get("/hello-json-with-custom-content-type").
                then()
                .contentType("custom_content_type");
    }
}
