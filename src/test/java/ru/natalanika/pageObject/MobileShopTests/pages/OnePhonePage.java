package ru.natalanika.pageObject.MobileShopTests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.assertj.core.api.Assertions.assertThat;

public class OnePhonePage extends BasePage {

    @FindBy (xpath = "//span[.='Back to catalog']")
    private WebElement backToCatalogButton;

    @FindBy(xpath = "//button[.='Add to cart']")
    private WebElement addToCartButton;

    public OnePhonePage(WebDriver webDriver) {
        super(webDriver);
    }

    public OnePhonePage checkPhoneName(String phoneName) {
        assertThat(webDriver.findElement(By.cssSelector(".product-details-container h1")).getText()).
                as("Должна открыться страница с телефоном " + phoneName).
                isEqualTo(phoneName);
        return this;
    }

    public OnePhonePage clickAddToCart() {
        addToCartButton.click();
        return this;
    }

    public PhonesPage backToCatalog () {
        backToCatalogButton.click();
        return new PhonesPage(webDriver);

    }
}

