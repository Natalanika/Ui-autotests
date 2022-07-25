package ru.natalanika.selenide.MobileShopTests.pages;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CartPage extends BasePage {

    private SelenideElement checkout = $x("//button[.='Checkout']");
    private SelenideElement confirm = $x("//button[.='Confirm']");
    private SelenideElement cartItems = $(By.className("cart-items"));
    private SelenideElement removeFromCart = $x("//button[@title='Remove this item from the cart']");
    private SelenideElement numberOfItems = $x("//p/b[contains(text(), 'Number of')]/..");
    private ElementsCollection cellsOfOrder = $$x("//table//tbody/tr/td");
    By oneTableOrderRaw = By.xpath(".//table//tbody/tr//a");

    @Step("Проверить, что в корзине только телефоны {phoneName}")
    public CartPage checkCartContainsExactly (String... phoneName) {
        cartItems.$$(oneTableOrderRaw).shouldHave(CollectionCondition.exactTextsCaseSensitiveInAnyOrder(phoneName));
        return this;
    }
    @Step("Проверить количество телефонов в корзине")
    public CartPage checkCartContainsExactlyQuantity (String quantity) {
        cellsOfOrder.get(3).shouldHave(exactText(quantity));
        return this;
    }

    @Step("Проверить, что в корзине есть телефоны {phoneName}")
    public CartPage checkCartContains (String... phoneName) {
        cartItems.$$(oneTableOrderRaw).shouldHave(CollectionCondition.containExactTextsCaseSensitive(phoneName));
        return this;
    }

    @Step("Нажать на кнопку Checkout")
    public CartPage clickCheckout() {
        checkout.click();
        return page (CartPage.class);
    }

    @Step("Нажать на кнопку Confirm")
    public CartPage clickConfirm(){
        confirm.click();
        return page (CartPage.class);
    }

    @Step("Проверить, что заказ оформлен успешно")
    public CartPage checkThatCheckoutIsSuccessful() {
        $(byText("Your order has been received. The items you've ordered will be sent to your address.")).shouldBe(visible);
        return page (CartPage.class);
    }

    @Step("Проверить, что пустая корзина содержит текст: {text}")
    public CartPage checkEmptyCartHasText (String text) {
        $x("//div[@class='cart-items']/h1").shouldHave(exactText(text));
        return this;
    }

    @Step("Проверить, что корзина пустая")
    public CartPage checkIfEmptyCart () {
        numberOfItems.shouldHave(exactText("Number of items: 0"));
        return this;
    }

    @Step("Удалить заказ из корзины")
    public CartPage deleteOrderFromCart () {
        removeFromCart.click();
        $x("//div[@class='cart-items']/h1").shouldBe(visible);
        return page (CartPage.class);
    }
}
