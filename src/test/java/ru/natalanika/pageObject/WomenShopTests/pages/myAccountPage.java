package ru.natalanika.pageObject.WomenShopTests.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class myAccountPage extends BasePage {
    public myAccountPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy (xpath = "//input[@id = 'lastname']")
    private WebElement inputLastName;
    @FindBy (xpath = "//button[@name = 'submitIdentity']")
    private WebElement saveButton;
    @FindBy (xpath = "//input[@name = 'old_passwd']")
    private WebElement inputCurrentPassword;

    @Step("Изменить фамилию")
    public myAccountPage changeLastName (String lastName, String password) {
        inputLastName.clear();
        inputLastName.sendKeys(lastName);
        inputCurrentPassword.sendKeys(password);
        saveButton.click();
        return new myAccountPage(webDriver);
    }

    @Step("Проверить, что изменение персональных данных прошло успешно")
    public myAccountPage checkThatChangingPersonalInformationIsSuccesful() {
        new WebDriverWait(webDriver, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[@class='alert alert-success']")));
        return new myAccountPage(webDriver);
    }
}
