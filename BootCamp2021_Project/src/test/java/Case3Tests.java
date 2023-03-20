import Backend.Steps.APISteps.Case3ApiSteps;
import Frontend.Steps.Case3UiSteps;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

@Epic("Validate books list size and last element UI and API")
@Feature("write publisher text box, validate returned books size in UI and  count of books with publisher in API , Validate  books last element title ")
@Story("validate returned books size UI and count of books with publisher API and Validate  books last element title in  UI and in API")


public class Case3Tests extends BaseTest {


    @Test
    @Description("Validate returned books list size and last element title")

    public void booksListSizeAndLastElementTitle() {
        open("/books");
        Case3UiSteps case3UiSteps = new Case3UiSteps();
        Case3ApiSteps case3ApiSteps = new Case3ApiSteps();


        case3UiSteps.validateLastElementTitleUI();
        case3ApiSteps.validateLastElementTitleAPI();

        case3UiSteps
                .setTextBox()
                .validateReturnedBooksListSize();

    }
}
