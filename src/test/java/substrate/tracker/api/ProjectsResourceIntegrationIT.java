package substrate.tracker.api;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

/**
 * Integration tests for the ProjectsResource class.
 */
public class ProjectsResourceIntegrationIT {

    @BeforeClass
    public void setRestAssuredDefaults() {
        RestAssured.basePath = "/tracker/api";
        RestAssured.requestContentType(ContentType.JSON);
    }


    // General tests against the /projects endpoint


    /**
     * The projects endpoint should return content in application/json
     * format when no media type is specified in the Accept header.
     */
    @Test
    public void serviceReturnsJsonContentTypeByDefault() {
        expect().
                statusCode(200).
                header("Content-Type", ContentType.JSON.toString()).
                when().
                get("/projects");
    }

    /**
     * Make sure the endpoints can return the appropriate response
     * format specified in the Accept header of the request.
     *
     * @param mediaType The media type to be passed in the Accept header
     */
    @Test(dataProvider = "supportedMediaTypes")
    public void requestSupportedMediaTypeReturns200Response(final String mediaType) {
        expect().
                statusCode(200).
                when().
                request().
                header("Accept", mediaType).
                get("/projects");
    }

    /**
     * The endpoints should return a 406 Not Acceptable HTTP response when
     * the Accept header of the request contains an unsupported media type.
     *
     * @param mediaType The media type to be passed in the Accept header
     */
    @Test(dataProvider = "unsupportedMediaTypes")
    public void requestUnsupportedMediaTypeReturns406Response(final String mediaType) {
        expect().
                statusCode(406).
                when().
                request().
                header("Accept", mediaType).
                get("/projects");
    }


    // Test the /projects GET endpoint


    /**
     * The /projects endpoint should return (at most) the number of
     * projects defined in the limit parameter.
     */
    @Test(dataProvider = "validLimitValues")
    public void getListOfProjectsWithDefinedLimit(final String limit) {
        given()
                .queryParam("limit", limit)
                .expect()
                .statusCode(200)
                .when()
                .get("/projects");
    }

    /**
     * The /projects endpoint should provide a offset mechanism that works
     * with the limit param to allow "paging" through a list of projects.
     */
    @Test(enabled = false)
    public void getListOfProjectsWithDefinedLimitAndOffset() {

    }

    /**
     * The /projects endpoint should return the appropriate HTTP response
     * code if an invalid limit value is used in the request.
     *
     * @param limit The value to pass in the limit parameter
     */
    @Test(dataProvider = "invalidLimitOrOffsetValues")
    public void getProjectsWithInvalidLimitReturns400Response(final String limit) {
        given()
                .queryParam("limit", limit)
                .expect()
                .statusCode(400)
                .when()
                .get("/projects");
    }

    /**
     * The /projects endpoint should return the appropriate HTTP response
     * code if an invalid offset value is used in the request.
     *
     * @param offset The value to pass in the offset parameter
     */
    @Test(dataProvider = "invalidLimitOrOffsetValues")
    public void getProjectsWithInvalidOffsetReturns400Response(final String offset) {
        given().
                queryParam("offset", offset).
                expect().
                statusCode(400).
                when().
                get("/projects");
    }

    /**
     * The /projects endpoint should return a 401 Unauthorised HTTP response
     * when a GET is made for a project but the client is not authenticated.
     */
    @Test(enabled = false)
    public void getProjectsWhenNotAuthenticatedReturns401Response() {

    }

    /**
     * The /projects endpoint should return a 403 Unauthorised HTTP response
     * when a GET is made for a project but the client is not authorised.
     */
    @Test(enabled = false)
    public void getProjectsWhenNotAuthorisedReturns403Response() {

    }


    // Test the /projects/{projectId} GET endpoint


    /**
     * The /projects/{projectId} endpoint should return a valid project
     * record when a GET is made with a valid ID.
     */
    @Test(enabled = false)
    public void getProjectById() {

    }

    /**
     * The /projects/{projectId} endpoint should return a 404 not found HTTP
     * response when a GET is made for a project ID that does not exist.
     */
    @Test(enabled = false)
    public void getNonExistentProjectReturns404Response() {
        given().
                pathParam("projectId", "1234").
                expect().
                statusCode(404).
                when().
                get("/projects/{projectId}");
    }

    /**
     * The /projects/{projectId} endpoint should return a 401 Unauthorised HTTP
     * response when a GET is made for a project but the client is not authenticated.
     */
    @Test(enabled = false)
    public void getProjectWhenNotAuthenticatedReturns401Response() {

    }

    /**
     * The /projects/{projectId} endpoint should return a 403 Forbidden HTTP
     * response when a GET is made for a project but the client is not authorised.
     */
    @Test(enabled = false)
    public void getProjectWhenNotAuthorisedReturns403Response() {

    }


    // Test the /projects POST (create) endpoint


    /**
     * The /projects endpoint should return a 201 Created HTTP response code
     * on success, along with a working link to the newly created project.
     */
    @Test
    public void createProjectReturns201ResponseAndValidLocationOfCreatedResource() {
        // first, ensure the create call works as expected
        final Response response = given().
                header("Content-Type", ContentType.JSON.toString()).
                body("{\"title\": \"Test Project\"}").
                expect().
                statusCode(201).
                header("Location", notNullValue()).
                when().
                post("/projects");

        // now make sure the URI returned in the Location header works
        expect().
                statusCode(200).
                body(containsString("Test Project")).
                when().
                get(response.getHeader("Location"));
    }

    /**
     * The /projects endpoint should return a 400 Bad Request HTTP response
     * when a POST is made containing malformed XML or JSON.
     *
     * @param mediaType A media type of the request body content
     * @param contentBody The payload to send in the request
     */
    @Test(dataProvider = "malformedContentBody")
    public void createProjectWithMalformedPayloadReturns400Response(
            final String mediaType,
            final String contentBody
    ) {
        expect().
                statusCode(400).
                when().
                request().
                header("Content-Type", mediaType).
                body(contentBody).
                post("/projects");
    }

    /**
     * The /projects endpoint should return a 400 bad Request HTTP response
     * when a POST is made containing invalid XML or JSON.
     */
    @Test(enabled = false)
    public void createProjectWithInvalidPayloadReturns400Response() {

    }

    /**
     * The /projects endpoint should return a 415 Unsupported Media Type HTTP
     * response when a POST contains a payload in an unsupported mediatype.
     */
    @Test(enabled = false)
    public void createProjectWithUnsupportedMediaTypeReturns415Response() {

    }

    /**
     * The /projects endpoint should return a 401 Unauthorised HTTP response
     * when attempting to create a project when the client is not authenticated.
     */
    @Test(enabled = false)
    public void createProjectWhenNotAuthenticatedReturns401Response() {

    }

    /**
     * The /projects endpoint should return a 403 Forbidden HTTP response
     * when attempting to create a project when the client is not authorised.
     */
    @Test(enabled = false)
    public void createProjectWhenNotAuthorisedReturns403Response() {

    }


    // Test the /projects/{projectId} PUT (update) endpoint


    /**
     * The /projects/{projectId} endpoint should return a 204 No Content HTTP
     * response after updating the project successfully.
     */
    @Test(enabled = false)
    public void updateProjectReturns204Response() {

    }

    /**
     * The /projects/{projectId} endpoint should return a 404 Not Found HTTP
     * response if the project to be updated does not exist (and should not create).
     */
    @Test(enabled = false)
    public void updateNonExistentProjectReturns404Response() {

    }

    /**
     * The /projects/{projectId} endpoint should return a 400 Bad Request HTTP
     * response when a PUT is made containing malformed XML or JSON.
     */
    @Test(dataProvider = "malformedContentBody")
    public void updateProjectWithMalformedPayloadReturns400Response(
            final String mediaType,
            final String contentBody
    ) {
        given().
                pathParam("projectId", "1234").
                expect().
                statusCode(400).
                when().
                request().
                header("Content-Type", mediaType).
                body(contentBody).
                put("/projects/{projectId}");
    }

    /**
     * The /projects endpoint should return a 400 bad Request HTTP response
     * when a PUT is made containing invalid XML or JSON.
     */
    @Test(enabled = false)
    public void updateProjectWithInvalidPayloadReturns400Response() {

    }

    /**
     * The /projects endpoint should return a 415 Unsupported Media Type HTTP
     * response when a PUT contains a payload in an unsupported mediatype.
     */
    @Test(enabled = false)
    public void updateProjectWithUnsupportedMediaTypeReturns415Response() {

    }

    /**
     * The /projects/{projectId} endpoint should return a 401 Unauthorised HTTP response
     * when attempting to update a project when the client is not authenticated.
     */
    @Test(enabled = false)
    public void updateProjectWhenNotAuthenticatedReturns401Response() {

    }

    /**
     * The /projects/{projectId} endpoint should return a 403 Forbidden HTTP response
     * when attempting to update a project when the client is not authorised.
     */
    @Test(enabled = false)
    public void updateProjectWhenNotAuthorisedReturns403Response() {

    }


    // Test the /projects/{projectId} DELETE endpoint


    /**
     * The /projects/{projectId} endpoint should return a 204 No Content HTTP
     * response after deleting the project successfully.
     */
    @Test(enabled = false)
    public void deleteProjectReturns204Response() {

    }

    /**
     * The /projects/{projectId} endpoint should return a 404 Not Found HTTP
     * response if the project to delete cannot be found
     */
    @Test(enabled = false)
    public void deleteNonExistentProjectReturns404Response() {

    }

    /**
     * The /projects/{projectId} endpoint should return a 401 Unauthorised HTTP response
     * when attempting to delete a project when the client is not authenticated.
     */
    @Test(enabled = false)
    public void deleteProjectWhenNotAuthenticatedReturns401Response() {

    }

    /**
     * The /projects/{projectId} endpoint should return a 403 Forbidden HTTP response
     * when attempting to delete a project when the client is not authorised.
     */
    @Test(enabled = false)
    public void deleteProjectWhenNotAuthorisedReturns403Response() {

    }


    // Data providers for the tests


    @DataProvider
    public Object[][] supportedMediaTypes() {
        return new Object[][] {
                {ContentType.JSON.getAcceptHeader()},
                {ContentType.XML.getAcceptHeader()}
        };
    }

    @DataProvider
    public Object[][] unsupportedMediaTypes() {
        return new Object[][] {
                {"application/atom+xml"},
                {"application/rdf+xml"},
                {"application/xhtml+xml"},
                {"text/html"},
                {"application/rss+xml"},
                {"application/pdf"},
                {"application/octet-stream"}
        };
    }

    @DataProvider
    public Object[][] malformedContentBody() {
        return new Object[][] {
                {ContentType.XML.toString(), "broken<malformed></xml>"},
                {ContentType.JSON.toString(), "broken{malformed:[}=json"}
        };
    }

    @DataProvider
    public Object[][] validLimitValues() {
        return new Object[][] {
                {"0"},
                {"1"},
                {"3"}
        };
    }

    @DataProvider
    public Object[][] invalidLimitOrOffsetValues() {
        return new Object[][] {
                {"abc"},
                {"x8x"},
                {"3.141596"},
                {">;kk"},
                {"-1"}
        };
    }
}
