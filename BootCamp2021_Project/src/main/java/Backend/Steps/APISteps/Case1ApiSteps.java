package Backend.Steps.APISteps;

import Backend.Models.RequestModel.AuthorizationRequest;
import Backend.Models.RequestModel.CreateUserRequest;
import Backend.Models.ResponseModel.ErrorAuthorizeResponse;
import Backend.Utils.Endpoints;
import Backend.Utils.StatusCodeUtil.StatusCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import Backend.Data.ConstantData;


public class Case1ApiSteps {
    ObjectMapper mapper = new ObjectMapper();

    @Step("Create user")
    public Response createUserStep(String username, String password, StatusCode statusCode) {
        CreateUserRequest createUserRequest = new CreateUserRequest(username, password);
        String body;
        try {
            body = mapper.writeValueAsString(createUserRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Response response = RestAssured.given()
                .filter(new AllureRestAssured())
                .header("Content-Type", "application/json")
                .body(body)
                .post(Endpoints.Accounts.user);
        StatusCode.assertStatusCode(response, statusCode);
        return response;

    }


    @Step("Authorize with deleted credentials")
    public Response Authorize(String username, String password, StatusCode statusCode) {
        AuthorizationRequest auth = new AuthorizationRequest(username, password);
        String body;
        try {
            body = mapper.writeValueAsString(auth);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Response response = RestAssured.given()
                .filter(new AllureRestAssured())
                .header("Content-Type", "application/json")
                .body(body)
                .post(Endpoints.Accounts.authorized);
        StatusCode.assertStatusCode(response, statusCode);
        return response;
    }


    @Step("Validate authorize response")
    public void validateAuthorizeMessage(Response response) {
        ErrorAuthorizeResponse errorAuthorizeResponse = response.body().as(ErrorAuthorizeResponse.class);
        Assert.assertEquals(errorAuthorizeResponse.message(), ConstantData.authorizedApiUserNotFound);

    }

}
