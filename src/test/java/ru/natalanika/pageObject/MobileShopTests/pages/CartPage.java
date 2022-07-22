package ru.natalanika.pageObject.MobileShopTests.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.assertThat;

public class CartPage extends BasePage {

    @FindBy (xpath = "//button[.='Checkout']")
    private WebElement checkout;
    @FindBy (xpath = "//button[.='Confirm']")
    private WebElement confirm;
    @FindBy (className ="cart-items")
    private WebElement cartItems;
    @FindBy (xpath = "//button[@title='Remove this item from the cart']")
    private WebElement removeFromCart;
    @FindBy (xpath = "//p/b[contains(text(), 'Number of')]/..")
    private WebElement numberOfItems;
    By oneTableOrderRaw = By.xpath(".//table//tbody/tr");
    By oneTableCell = By.xpath("./td");

    public CartPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Проверить, что в корзине только телефоны {phoneName}")
    public CartPage checkCartContainsExactly (String... phoneName) {
        List<String> actualPhoneList = cartItems
                .findElements(oneTableOrderRaw)
                .stream()
                .map(el -> el.findElements(oneTableCell).get(1).getText())
                .collect(Collectors.toList());
        System.out.println(actualPhoneList);
        assertThat(actualPhoneList).containsExactlyInAnyOrder(phoneName);
        return this;
    }

    @Step("Проверить, что в корзине есть телефоны {phoneName}")
    public CartPage checkCartContains (String... phoneName) {
        List<String> actualPhoneList = cartItems
                .findElements(oneTableOrderRaw)
                .stream()
                .map(el -> el.findElements(oneTableCell).get(1).getText())
                .collect(Collectors.toList());
        System.out.println(actualPhoneList);
        assertThat(actualPhoneList).contains(phoneName);
        return this;
    }

    @Step("Нажать на кнопку Checkout")
    public CartPage clickCheckout() {
        checkout.click();
        return new CartPage(webDriver);
    }

    @Step("Нажать на кнопку Confirm")
    public CartPage clickConfirm(){
        confirm.click();
        return new CartPage(webDriver);
    }

    @Step("Проверить, что заказ оформлен успешно")
    public CartPage checkThatCheckoutIsSuccesful() {
        new WebDriverWait(webDriver, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[text()=\"Your order has been received. The items you've ordered will be sent to your address.\"]")));
        return new CartPage(webDriver);
    }

    @Step("Проверить, что пустая корзина содержит текст: {text}")
    public CartPage checkEmptyCartHasText (String text) {
        assertThat(webDriver.findElement(By.xpath("//div[@class='cart-items']/h1")).getText()).
                as("Должна открыться пустая корзина с текстом" + text).
                isEqualTo(text);
        return this;
    }

    @Step("Проверить, что корзина пустая")
    public CartPage checkIfEmptyCart () {
        String text = numberOfItems.getText();
        assertThat(text).isEqualTo("Number of items: 0");
        return this;
    }

    @Step("Удалить заказ из корзины")
    public CartPage deleteOrderFromCart () {
        removeFromCart.click();
        new WebDriverWait(webDriver, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='cart-items']/h1")));
        return new CartPage(webDriver);
    }
}
