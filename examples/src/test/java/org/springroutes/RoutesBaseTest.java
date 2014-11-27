package org.springroutes;

import javax.script.ScriptException;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {EmptyApplication.class, ScriptHandler.class})
@IntegrationTest
@WebAppConfiguration
public abstract class RoutesBaseTest {
    @Autowired
    protected Routes routes;

    protected void registerOnly(String script) throws ScriptException {
        routes.clear();
        routes.registerRoutesFrom(script);
    }
}
