package Backend.Steps.APISteps;

import Backend.Data.ConstantData;
import Backend.Utils.Endpoints;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import java.util.List;
import static io.restassured.RestAssured.given;

public class Case3ApiSteps {

    @Step("return Books")
    public JsonPath getBooks() {
        return  given()
                .get(Endpoints.Books.books)
                .then()
                .extract()
                .jsonPath();

    }

    @Step("Validate that book with title 'Understanding ECMAScript 6' is the last element in API")
    public void validateLastBookTitleAPI(List<String> booksTitle) {
        String lastBookTitle = booksTitle.get(booksTitle.size()-1);
        Assert.assertEquals(lastBookTitle, ConstantData.lastBookTitle);

    }

}
