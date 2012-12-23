package substrate.tracker.api;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.*;

public class PingResourceTest extends Arquillian {
    @BeforeClass
    public void setRestAssuredDefaults() {
        RestAssured.basePath = "/tracker";
        RestAssured.port = 8228;
    }

    @Deployment
    public static WebArchive setupDeployment() {
        return ShrinkWrap.create(WebArchive.class, "tracker.war").
                setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
    }

    @Test
    @RunAsClient
    public void pingReturnsSuccessfulResponse() {
        expect().statusCode(200).when().get("/api/ping");
    }
}
