package Backend.Steps.APISteps;

import Backend.Utils.Endpoints;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static Backend.Data.ConstantData.lastElementTitle;
import static Backend.Data.ConstantData.publisherName;
import static io.restassured.RestAssured.given;

public class Case3ApiSteps {

    @Step("return jsonPath")
    public JsonPath jsonPath() {
        return  given()
                .get(Endpoints.Books.books)
                .then()
                .extract()
                .jsonPath();

    }

    @Step("return list of books publisher")
    public List booksPublisher() {
        return
                jsonPath().get("books.publisher");

    }

    @Step("return books list size with publisher 'O'Reilly Media'")
    public int countOfBooks() {
        List<String> Books = new ArrayList<String>();

        for (int i = 0; i < booksPublisher().size(); i++) {
            if (booksPublisher().get(i).toString().contains(publisherName)) {
                Books.add((String) booksPublisher().get(i));
            }
        }
        return Books.size();
    }

    @Step("return list of books title")
    public List booksTitleList() {
        return jsonPath().get("books.title");

    }

    @Step("return books last element title")
    public String lastElementTitleAPI() {
        return (String) booksTitleList().get(booksTitleList().size()-1);

    }
    @Step("Validate that book with title 'Understanding ECMAScript 6' is the last element in API")
    public void validateLastElementTitleAPI() {

        Assert.assertEquals(lastElementTitleAPI(),lastElementTitle);

    }

}
