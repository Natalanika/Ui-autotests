package ru.natalanika.selenide.MobileShopTests.pages;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class OnePhonePage extends BasePage {

    private SelenideElement backToCatalogButton = $x("//span[.='Back to catalog']");
    private SelenideElement addToCartButton = $x("//button[.='Add to cart']");
    private SelenideElement quantity = $x("//input[@type='number']");

    @Step("Проверить, что открылась страница телефона {phoneName}")
    public OnePhonePage checkPhoneName(String phoneName) {
        $(By.cssSelector(".product-details-container h1"))
                .shouldBe (exactText(phoneName).
                        because("Должна открыться страница с телефоном " + phoneName));
        return this;
    }

    @Step("Нажать на кнопку Add to cart")
    public OnePhonePage clickAddToCart() {
        addToCartButton.click();
        return this;
    }

    @Step("Нажать на кнопку Back to catalog")
    public PhonesPage backToCatalog () {
        backToCatalogButton.click();
        return page (PhonesPage.class);
    }

    @Step ("Ввести количество")
    public OnePhonePage setQuantity (String quantityOfPhones) {
        quantity.clear();
        quantity.setValue(quantityOfPhones);
        addToCartButton.click();
        return page(OnePhonePage.class);
    }
}

