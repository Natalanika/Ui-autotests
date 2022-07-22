package ru.natalanika.pageObject.MobileShopTests.Blocks;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.natalanika.pageObject.MobileShopTests.pages.AccountPage;
import ru.natalanika.pageObject.MobileShopTests.pages.BaseView;
import ru.natalanika.pageObject.MobileShopTests.pages.CartPage;
import ru.natalanika.pageObject.MobileShopTests.pages.PhonesPage;

import java.time.Duration;

public class HeaderBlock extends BaseView {

    public HeaderBlock(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//input[contains(@id, 'Username')]")
    private WebElement userNameInput;
    @FindBy (xpath = "//input[contains(@id, 'Password')]")
    private WebElement userPasswordInput;
    @FindBy (xpath = "//button[.='Submit']")
    private WebElement submitButton;
    @FindBy (xpath = "//a[.='CART']")
    private WebElement cartButton;
    @FindBy (xpath = "//a[.='ACCOUNT']")
    private WebElement account;
    By loginButton = By.xpath("//button[.='LOGIN']"); // можно и так, тогда ленивая инициализация не нужна

    @Step ("Авторизоваться пользователем {login} {password}")
    public PhonesPage login (String login, String password) {
        webDriver.findElement(loginButton).click();
        userNameInput.sendKeys(login);
        userPasswordInput.sendKeys(password);
        submitButton.click();
        new WebDriverWait(webDriver, Duration.ofSeconds(1)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='LOGOUT']")));
        return new PhonesPage(webDriver);
    }

    @Step ("Перейти в корзину")
    public CartPage goToCart () {
        cartButton.click();
        return new CartPage(webDriver);
    }

    @Step ("Нажать на кнопку Аккаунт")
    public AccountPage goToAccount () {
        account.click();
        return new AccountPage(webDriver);
    }
}

