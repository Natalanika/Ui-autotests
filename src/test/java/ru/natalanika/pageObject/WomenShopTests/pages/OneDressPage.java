package ru.natalanika.pageObject.WomenShopTests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OneDressPage extends BasePage {
    public OneDressPage(WebDriver webDriver) {
        super(webDriver);
    }
    @FindBy (xpath = "//button[.//span[text()='Add to cart']]")
    private WebElement addToCartButton;
    @FindBy (xpath = "//a[@title='Proceed to checkout']")
    private WebElement proceedToCheckoutButton;
    @FindBy (xpath = " //select[@id='group_1']")
    private WebElement selectSizeDropDown;

    public CartPage addDressToCartAndGoToCart () {
        addToCartButton.click();
       proceedToCheckoutButton.click();
       new WebDriverWait(webDriver, 4).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='cart_title']")));
       return new CartPage(webDriver);
    }
    public OneDressPage addDressToCart () {
        addToCartButton.click();
        new WebDriverWait(webDriver, 4).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Proceed to checkout']")));
        return this;
    }

    public OneDressPage changeSize (String size) {
        Select select = new Select(selectSizeDropDown);
        select.selectByVisibleText(size);
        return this;
    }
}
