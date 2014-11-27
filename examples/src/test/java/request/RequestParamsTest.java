package request;

import org.junit.Before;
import org.junit.Test;
import org.springroutes.RoutesBaseTest;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONAs;

public class RequestParamsTest extends RoutesBaseTest {

    @Before
    public void setUp() throws Exception {
        registerOnly("request/request_params.js");

    }

    @Test
    public void allow_access_to_single_query_string_parameter() throws Exception {

        when()
                .get("/hello?name=John").
                then()
                .body(containsString("Hello John!"));

    }

    @Test
    public void allow_access_to_single_query_string_array_parameter() throws Exception {

        when()
                .get("/hello-array?name=John&name=Doe").
                then()
                .body(containsString("Hello John Doe!"));

    }

    @Test
    public void allow_access_to_all_query_string_parameters() throws Exception {

        when()
                .get("/echo?firstname=George&lastname=R.R.&lastname=Martin&fantasy").
                then()
                .body(sameJSONAs("{ firstname: 'George', lastname: ['R.R.', 'Martin'], fantasy: ''}"));

    }

}
