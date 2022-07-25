package ru.natalanika.selenide.MobileShopTests.Blocks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.natalanika.selenide.MobileShopTests.pages.AccountPage;
import ru.natalanika.selenide.MobileShopTests.pages.CartPage;
import ru.natalanika.selenide.MobileShopTests.pages.PhonesPage;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class HeaderBlock {

    private SelenideElement userNameInput = $x("//input[contains(@id, 'Username')]");
    private SelenideElement userPasswordInput = $x("//input[contains(@id, 'Password')]");
    private SelenideElement submitButton = $x("//button[.='Submit']");
    private SelenideElement cartButton = $x("//a[.='CART']");
    private SelenideElement account = $x("//a[.='ACCOUNT']");
    private SelenideElement loginButton = $x("//button[.='LOGIN']");

    @Step ("Авторизоваться пользователем {login} {password}")
    public PhonesPage login (String login, String password) {
        loginButton.click();
        userNameInput.setValue(login);
        userPasswordInput.setValue(password);
        submitButton.click();
        $(byText("LOGOUT")).shouldBe(Condition.visible);
        return page (PhonesPage.class);
    }

    @Step ("Перейти в корзину")
    public CartPage goToCart () {
        cartButton.click();
        return page (CartPage.class);
    }

    @Step ("Нажать на кнопку Аккаунт")
    public AccountPage goToAccount () {
        account.click();
        return page(AccountPage.class);
    }
}

