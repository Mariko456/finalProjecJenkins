package Frontend.Steps;

import Frontend.Pages.BooksHomePage;
import io.qameta.allure.Step;
import org.testng.Assert;
import java.util.List;
import static Backend.Data.ConstantData.lastBookTitle;
import static Backend.Data.ConstantData.publisherName;

public class Case3UiSteps {
    BooksHomePage booksHomePage = new BooksHomePage();


    @Step("return books last element title")
    public String lastBookTitle(){
        return  booksHomePage.booksReturnedList.last().text();

    }
    @Step("Write 'O'Reilly Media' in search textbox")
    public Case3UiSteps setTextBox (){
        booksHomePage.searchBox.sendKeys(publisherName);
        return this;

    }
    @Step("return books list size with publisher 'O'Reilly Media'")
    public int returnedBooksListSize (){
        return  booksHomePage.booksReturnedList.size();

    }
    @Step("Validate that count of books with publisher 'O'Reilly Media' is equals to returned list size in UI")
    public void validateReturnedBooksListSize(List books) {

        int count = 0;

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).toString().contains(publisherName)) {
                count++;
            }
        }
        Assert.assertEquals(count, returnedBooksListSize());
    }
    @Step("Validate that book with title 'Understanding ECMAScript 6' is the last element in UI")
    public void validateLastBookTitleUI() {

        Assert.assertEquals(lastBookTitle(), lastBookTitle);

    }
}
