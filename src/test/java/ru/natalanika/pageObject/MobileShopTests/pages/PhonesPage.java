package ru.natalanika.pageObject.MobileShopTests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PhonesPage extends BasePage {

    @FindBy (xpath = "//div[text()='Brand']")
    private WebElement brand;
    By phoneOnPage = By.className("product");
    By seeMore = By.xpath(".//a[.='See more']");


    public PhonesPage(WebDriver webDriver) {
        super(webDriver);
    }

    public OnePhonePage selectPhone(String phoneName) {
    List<WebElement> phones = webDriver.findElements(phoneOnPage);
        phones.stream()
                .filter(p -> p.getText().contains(phoneName))
            .findFirst()
                .orElseThrow()
                .findElement(seeMore).click();
        return new OnePhonePage(webDriver);
    }

    public PhonesPage selectSamsungBrand () {
        brand.click();
        webDriver.findElement(By.xpath("//input[@nestedlevel = '1'][@value='samsung']")).click();
        return new PhonesPage(webDriver);
    }

    public boolean arePhonesOfSamsung () {
        boolean hasPhoneList = webDriver.findElements(By.xpath("//div[@class='content-left']/h3"))
                .stream()
                .map(WebElement::getText)
                .allMatch(el -> el.contains("Samsung"));
        return hasPhoneList;
    }
}
