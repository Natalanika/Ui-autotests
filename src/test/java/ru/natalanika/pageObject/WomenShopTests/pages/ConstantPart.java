package ru.natalanika.pageObject.WomenShopTests.pages;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstantPart extends BaseView {
    public ConstantPart(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//a[@class='login']")
    private WebElement signInButton;
    @FindBy(xpath = "//input[@id='email']")
    private WebElement userNameInput;
    @FindBy (xpath = "//input[@id='passwd']")
    private WebElement userPasswordInput;
    @FindBy (xpath = "//button[@id='SubmitLogin']")
    private WebElement submitButton;
    @FindBy (xpath = "//p[text()='There is 1 error']")
    private WebElement errorMessage;
    @FindBy (xpath = "//a[@title = 'Manage my personal information']")
    private WebElement myPersonalInfo;
    @Getter
    @FindBy (xpath = "//a[@title='View my customer account']/span")
    private WebElement nameAndLastNameOnPage;

    @Step("Авторизоваться пользователем {login} {password}")
    public MainPage login (String login, String password) {
        signInButton.click();
        userNameInput.sendKeys(login);
        userPasswordInput.sendKeys(password);
        submitButton.click();
        new WebDriverWait(webDriver, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='logout']")));
        return new MainPage(webDriver);
    }

    @Step("Авторизоваться под неверными данными {login} {password}")
    public MainPage loginInvalid (String login, String password) {
        signInButton.click();
        userNameInput.sendKeys(login);
        userPasswordInput.sendKeys(password);
        submitButton.click();
        new WebDriverWait(webDriver, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='There is 1 error']")));
        return new MainPage(webDriver);
    }

    @Step("Перейти в раздел персональных данных")
    public myAccountPage goToPersonalInfo () {
        myPersonalInfo.click();
        new WebDriverWait(webDriver, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[@class = 'page-subheading']")));
        return new myAccountPage(webDriver);
    }
}
