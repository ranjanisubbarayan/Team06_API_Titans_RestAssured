package stepDefinition;

import java.io.IOException;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojoclass.JsonTestData;
import pojoclass.LoginResponse;
import pojoclass.TestcaseWrapper;
import utilities.Base;
import utilities.JsonReader;
import utilities.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Hooks extends Base {

    RequestSpecification request;
    Response response;

    private static final Logger log = LoggerFactory.getLogger(Hooks.class);
    
    public static Scenario scenario;

    @Before(order = 0)
    public void captureScenario(Scenario scenario) {
        Hooks.scenario = scenario;
    }
    
    @Before(value = "@authToken", order = 1)
    public void generateTokenBeforeScenario() throws IOException {

        if (Token.token == null || Token.token.isEmpty()) {

            TestcaseWrapper wrapper = getTestData();

            JsonTestData testData = JsonReader.getTestDataByScenarioName(
                    "Login API",
                    wrapper.getPostRequest()
            );

            request = createRequest();

            response = request
                    .body(testData.getLoginRequest())
                    .when()
                    .post(testData.getEndpoint());

            LoginResponse loginResponse = response.as(LoginResponse.class);

            Token.token = loginResponse.getToken();

            log.info("Token generated successfully: " + Token.token);
        }
    }
}