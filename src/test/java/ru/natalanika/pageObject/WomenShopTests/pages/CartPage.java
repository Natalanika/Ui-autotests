package ru.natalanika.pageObject.WomenShopTests.pages;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

public class CartPage extends BasePage {
    public CartPage(WebDriver webDriver) {
        super(webDriver);
    }
    @FindBy (xpath = "(//a[@title='Proceed to checkout'])[2]")
    private WebElement proceedToCheckout;
    @FindBy (xpath = "//button[@name='processAddress']")
    private WebElement confirmAddress;
    @FindBy (xpath = "//input[@type='checkbox']")
    private WebElement checkboxAgreeShipping;
    @FindBy (xpath = "//button[@name='processCarrier']")
    private WebElement confirmShipping;
    @FindBy (xpath = "//a[@title='Pay by check.']")
    private WebElement payByCheckButton;
    @FindBy (xpath = "//button[.//i[@class='icon-chevron-right right']]")
    private WebElement confirmMyOrderButton;
    @Getter
    @FindBy (xpath = "//p[@class='alert alert-success']")
    private WebElement messageAboutSuccess;

    @Step("Проверить, что корзина не пустая")
    public CartPage checkCartIsNotEmpty () {
        List<WebElement> dressesInCart = webDriver.findElements(By.xpath("//table//*[@class='product-name']"));
        assertThat(dressesInCart).isNotEmpty();
        return this;
    }

    @Step("Завершить процесс оформления заказа")
    public CartPage completeToBuy () {
        proceedToCheckout.click();
        confirmAddress.click();
        checkboxAgreeShipping.click();
        confirmShipping.click();
        payByCheckButton.click();
        confirmMyOrderButton.click();
        return this;
    }
}
