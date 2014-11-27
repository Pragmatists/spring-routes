package org.springroutes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.jayway.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {BasicApplication.class, SpringBootTest.BasicController.class})
@IntegrationTest
@WebAppConfiguration
public class SpringBootTest {

    @Test
    public void shouldGreetJoe() throws Exception {
        get("/greet").then().assertThat().body(equalTo("Hi Joe"));

    }

    @Controller
    public static class BasicController {

        @RequestMapping(value = "/greet", method = RequestMethod.GET)
        @ResponseBody
        public String greet() {
            return "Hi Joe";
        }

    }
}
