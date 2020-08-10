package api.core;

import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseApi {

    private String token = "bd0de7602793e27c149e4443d1ad1fd94d1098b2";
    private Response response;
    private String baseUri = "https://api.todoist.com/rest/v1";
    private RequestSpecification req = given().baseUri(baseUri).header("Authorization", "Bearer " + token);

    public BaseApi() {
    }

    public Response getResponse() {
        return response;
    }

    public Response sendPost(String url, Object body) {
        response =
                given().
                        spec(req).
                        contentType(ContentType.JSON).
                        body(body).
                        when().
                        post(url).
                        then().
                        log().ifValidationFails().
                        extract().response();
        return response;
    }

    public Response sendGet(String url) {

        response =
                given().
                        spec(req).
                        contentType(ContentType.JSON).
                        when().
                        get(url).
                        then().
                        log().ifValidationFails().
                        extract().response();
        return response;
    }

    public void validateStatusCode(int statusCode) {
        response.
                then().
                log().ifValidationFails().
                statusCode(statusCode);
    }

    public Response sendDelete(String url) {
        response =
                given().
                        spec(req).
                        when().
                        delete(url).
                        then().
                        log().ifValidationFails().
                        extract().response();
        return response;
    }

    public <T> T getJsonAsObject(Class<T> clazz) {
        return response.body().as(clazz, ObjectMapperType.GSON);
    }

    public String getJsonAsString() {
        return response.body().asString();
    }
}
