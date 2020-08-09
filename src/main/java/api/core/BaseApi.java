package api.core;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseApi {

    private static Response response;

    public static Response getResponse() {
        return response;
    }

    /**
     * send POST by object
     *
     * @param url  - URL of API endpoint
     * @param body - Body by object
     * @return Response object instance
     */
    public static Response sendPost(String url, Object body) {
        response =
                given().
                        contentType(ContentType.JSON).
                        body(body).
                        when().
                        post(url).
                        then().
                        log().ifValidationFails().
                        extract().response();
        return response;
    }



}
