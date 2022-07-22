package ru.natalanika.pageObject.WomenShopTests.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy (xpath = "(//a[.='Dresses'])[2]")
    private WebElement dressesButton;

    @Step("Перейти в раздел Платья")
    public DressesPage goToDresses () {
        dressesButton.click();
        dressesButton.click();
        return new DressesPage(webDriver);
    }
}
