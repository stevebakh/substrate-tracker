package substrate.tracker.api;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.expect;

public class PingResourceIntegrationIT {
    @BeforeClass
    public void setRestAssuredDefaults() {
        RestAssured.basePath = "/tracker/api";
    }

    @Test
    public void pingReturnsSuccessfulResponse() {
        expect().statusCode(200).when().get("/ping");
    }
}
