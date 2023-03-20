package Frontend.Steps;

import Backend.Steps.APISteps.Case3ApiSteps;
import Frontend.Pages.BooksHomePage;
import io.qameta.allure.Step;
import org.testng.Assert;

import static Backend.Data.ConstantData.lastElementTitle;
import static Backend.Data.ConstantData.publisherName;

public class Case3UiSteps {
    BooksHomePage booksHomePage = new BooksHomePage();
    Case3ApiSteps case3ApiSteps = new Case3ApiSteps();


    @Step("return books last element title")
    public String lastElementTitleUI (){
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
    public void validateReturnedBooksListSize() {

        Assert.assertEquals(case3ApiSteps.countOfBooks(), returnedBooksListSize());

    }
    @Step("Validate that book with title 'Understanding ECMAScript 6' is the last element in UI")
    public void validateLastElementTitleUI() {

        Assert.assertEquals(lastElementTitleUI(),lastElementTitle);

    }

}
