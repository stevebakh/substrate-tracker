package substrate.tracker.api;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jayway.restassured.RestAssured;

import static com.jayway.restassured.RestAssured.*;

@RunWith(Arquillian.class)
public class PingResourceTest {
    @BeforeClass
    public static void setRestAssuredDefaults() {
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
