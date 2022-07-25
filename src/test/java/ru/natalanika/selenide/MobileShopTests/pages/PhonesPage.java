package ru.natalanika.selenide.MobileShopTests.pages;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.*;

public class PhonesPage extends BasePage {

    private SelenideElement brand = $x("//div[text()='Brand']");
    private SelenideElement colour = $x("//div[text()='Color']//preceding-sibling::*");
    By phoneOnPage = By.className("product");
    By seeMore = By.xpath(".//a[.='See more']");

    @Step("Выбрать телефон {phoneName}")
    public OnePhonePage selectPhone(String phoneName) {
    ElementsCollection phones = $$(phoneOnPage);
        phones.stream()
                .filter(p -> p.getText().contains(phoneName))
            .findFirst()
                .orElseThrow()
                .findElement(seeMore).click();
        return page (OnePhonePage.class);
    }

    @Step("Выбрать бренд Samsung")
    public PhonesPage selectSamsungBrand () {
        brand.click();
        $x("//input[@nestedlevel = '1'][@value='samsung']").click();
        return page (PhonesPage.class);
    }

    @Step("Выбрать цвет")
    public PhonesPage selectColour (String color) {
        colour.click();
        $(byValue(color)).click();
        return page (PhonesPage.class);
    }

    @Step("Проверить, что странице остались лишь телефоны выбранного бренда")
    public PhonesPage arePhonesOfSamsung () {
        $$x("//div[@class='content-left']/h3").texts()
                .stream()
                .allMatch(el -> el.contains("Samsung"));
        return this;
    }
}
