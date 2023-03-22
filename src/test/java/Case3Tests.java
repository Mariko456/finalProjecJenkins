import Backend.Steps.APISteps.Case3ApiSteps;
import Frontend.Steps.Case3UiSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import java.util.List;
import static com.codeborne.selenide.Selenide.open;

@Epic("Validate books list size and last element UI and API")
@Feature("write publisher text box, validate returned books size in UI and  count of books with publisher in API , Validate  books last element title ")
@Story("validate returned books size UI and count of books with publisher API and Validate  books last element title in  UI and in API")


public class Case3Tests extends BaseTest {


    @Test
    @Description("Validate returned books list size and last element title")

    public void booksListSizeAndLastBookTitle() {
        open("/books");
        Case3UiSteps case3UiSteps = new Case3UiSteps();
        Case3ApiSteps case3ApiSteps = new Case3ApiSteps();
        JsonPath jsonPath = case3ApiSteps.getBooks();
        List<String> booksTitle = jsonPath.getList("books.title");


        case3UiSteps.validateLastBookTitleUI();
        case3ApiSteps.validateLastBookTitleAPI(booksTitle);

        case3UiSteps
                .setTextBox()
                .validateReturnedBooksListSize(jsonPath.getList("books"));

    }
}
