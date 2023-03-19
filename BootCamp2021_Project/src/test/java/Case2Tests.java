import Backend.Data.ConstantData;
import Backend.Steps.APISteps.Case2ApiSteps;
import Backend.Utils.StatusCodeUtil.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Epic("generate token scenarios")
@Feature("generate token with only username, password and without username and password")
@Story("generate token  with only username, password and without username and password,   validate with response message," +
        "and  status code " )
public class Case2Tests {

    Case2ApiSteps step = new Case2ApiSteps();

    @Test
    @Description("generate token with password")
    public void generateTokenSetOnlyPasswordTest() throws JsonProcessingException {
        Response response = step.setGenerateTokenOnlyPassword(ConstantData.password, StatusCode.equals(HttpStatus.SC_BAD_REQUEST));
        step.validateGenerateTokenMessage(response);
    }

    @Test
    @Description("generate token with username")
    public void generateTokenSetOnlyUserNameTest() throws JsonProcessingException {
        Response response = step.setGenerateTokenOnlyUserName(ConstantData.userName, StatusCode.equals(HttpStatus.SC_BAD_REQUEST));
        step.validateGenerateTokenMessage(response);
    }

    @Test
    @Description("generate token without username and password")
    public void generateTokenWithoutUserNameAndPasswordTest() throws JsonProcessingException {
        Response response = step.generateTokenWithoutUserAndPassword(StatusCode.equals(HttpStatus.SC_BAD_REQUEST));
        step.validateGenerateTokenMessage(response);
    }

    @Test
    @Description("generate token without username and password")
    public void generateTokenWithInvalidCredentialsTest() throws JsonProcessingException {
        Response response = step.generateTokenWithInvalidCredentials(ConstantData.userName, ConstantData.password, StatusCode.equals(HttpStatus.SC_OK));
        step.validateGenerateTokenMessageWithInvalidCredentials(response);
    }
}