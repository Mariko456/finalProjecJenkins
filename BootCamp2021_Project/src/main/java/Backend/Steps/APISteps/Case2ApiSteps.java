package Backend.Steps.APISteps;

import Backend.Data.ConstantData;
import Backend.Models.RequestModel.GenerateTokenRequest;
import Backend.Models.ResponseModel.ErrorGenerateTokenResponse;
import Backend.Models.ResponseModel.GenerateTokenResponse;
import Backend.Utils.StatusCodeUtil.StatusCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import Backend.Utils.Endpoints;

public class Case2ApiSteps {

    ObjectMapper mapper = new ObjectMapper();

    private Response generateToken(GenerateTokenRequest request, StatusCode statusCode) throws JsonProcessingException {
        String body = mapper.writeValueAsString(request);
        Response response = RestAssured.given()
                .filter(new AllureRestAssured())
                .header("Content-Type", "application/json")
                .body(body)
                .post(Endpoints.Accounts.generateToken);

        StatusCode.assertStatusCode(response,statusCode);

        return response;
    }

    @Step("Generate Token without username and password")
    public Response generateTokenWithoutUserAndPassword(StatusCode statusCode) throws JsonProcessingException {
        GenerateTokenRequest generateTokenRequest = new GenerateTokenRequest();

        return generateToken(generateTokenRequest, statusCode);
    }

    @Step("Generates token with password")
    public Response setGenerateTokenOnlyPassword(String password, StatusCode statusCode) throws JsonProcessingException {
        GenerateTokenRequest generateTokenRequest = new GenerateTokenRequest();
        generateTokenRequest.setPassword(password);

        return generateToken(generateTokenRequest, statusCode);
    }

    @Step("Generates token with username")
    public Response setGenerateTokenOnlyUserName(String userName, StatusCode statusCode) throws JsonProcessingException {
        GenerateTokenRequest generateTokenRequest = new GenerateTokenRequest();
        generateTokenRequest.setUserName(userName);

        return generateToken(generateTokenRequest, statusCode);
    }

    @Step("Validate generate tokens with code and message response")
    public void validateGenerateTokenMessage(Response response) {
        ErrorGenerateTokenResponse errorGenerateResponse = response.body().as(ErrorGenerateTokenResponse.class);
        Assert.assertEquals(errorGenerateResponse.code(), ConstantData.generateTokenResponseCodeMessage);
        Assert.assertEquals(errorGenerateResponse.message(), ConstantData.generateTokenApiUserAndPasswordRequiredMessage);
    }

    @Step("Generate token with invalid credentials")
    public Response generateTokenWithInvalidCredentials(String invalidUserName, String invalidPassword, StatusCode statusCode) throws JsonProcessingException {
        GenerateTokenRequest generateTokenRequest = new GenerateTokenRequest();
        generateTokenRequest.setUserName(invalidUserName);
        generateTokenRequest.setPassword(invalidPassword);

        return generateToken(generateTokenRequest, statusCode);
    }

    @Step("Validate generate tokens with code and message from GenerateTokenResponse")
    public void validateGenerateTokenMessageWithInvalidCredentials(Response response) {
        GenerateTokenResponse generateTokenResponse = response.body().as(GenerateTokenResponse.class);
        Assert.assertNull(generateTokenResponse.getToken());
        Assert.assertNull(generateTokenResponse.getExpires());
    }
}