
import Backend.Data.ConstantData;
import Frontend.Data.FrontEndConstants;
import Frontend.Steps.ProfilePageSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import Backend.Utils.StatusCodeUtil;
import Frontend.Steps.HomePageSteps;
import Backend.Steps.APISteps.Case1ApiSteps;
import static com.codeborne.selenide.Selenide.open;

@Epic("User register and login scenarios")
@Feature("Register new user, login with new user credentials, login and authorize with deleted credentials")
@Story("Create new user, Login with newly added user, Delete user account and validate popup message, " +
        "Login with deleted user and validate error text, Authorize with deleted user credentials, validate message")
public class Case1Test extends BaseTest {

    Case1ApiSteps step = new Case1ApiSteps();
    HomePageSteps homePageSteps = new HomePageSteps();
    ProfilePageSteps profilePageSteps = new ProfilePageSteps();


    @Test(priority = 1)
    @Description("Create new user")
    public void createUserTest() {
        step.createUserStep(Backend.Data.ConstantData.userName, ConstantData.passwordForCase1, StatusCodeUtil.StatusCode.equals(HttpStatus.SC_CREATED));
    }


    @Test(priority = 2)
    @Description("Login with newly added user (successful scenario)")
    public void loginWithNewlyAddedUser() {
        open("https://demoqa.com/login");
        homePageSteps
                .setUserName(ConstantData.userName)
                .setPassword(ConstantData.passwordForCase1)
                .clickOnLoginBtn();
    }


    @Test(priority = 3)
    @Description("Delete user account")
    public void deleteUserAccount() {
        profilePageSteps
                .clickOnDeleteBtn()
                .confirmDelete()
                .validatePopupWindowText(FrontEndConstants.popupMessageUserDeleted);
    }


    @Test(priority = 4)
    @Description("Login with deleted user credentials")
    public void loginWithDeletedCredentials() {
        loginWithNewlyAddedUser();
        homePageSteps.errorTextValidation(FrontEndConstants.uiLoginErrorMessage);
    }


    @Test(priority = 5)
    @Description("Authorize with deleted user credentials")
    public void authorizeWithDeletedCredentials()  {
        Response response = step.Authorize(ConstantData.userName, ConstantData.passwordForCase1, StatusCodeUtil.StatusCode.equals(HttpStatus.SC_NOT_FOUND));
        step.validateAuthorizeMessage(response);
    }

}
