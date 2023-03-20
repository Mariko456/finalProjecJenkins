package Frontend.Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BooksHomePage {
    public ElementsCollection booksReturnedList = $(".rt-table").find(".rt-tbody").findAll(".action-buttons");
    public SelenideElement searchBox = $(By.id("searchBox"));
}
