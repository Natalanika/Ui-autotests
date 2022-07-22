package ru.natalanika.pageObject.WomenShopTests.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;

public class DressesPage extends BasePage {
    public DressesPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Проверить наличие товара в магазине")
    public boolean isAnyDressesInTheStock () {
        List<WebElement> dresses = webDriver.findElements(By.className("right-block"));
        dresses.forEach(p -> System.out.println(p.getText() + "\n"));
        boolean isInStock = dresses.stream().anyMatch(el -> el.getText().contains("In stock"));
        return isInStock;
    }

    @Step("Выбрать случайное платье")
    public OneDressPage selectRandomDress () {
        Random rand = new Random();
        webDriver.findElements(By.xpath("//div//h5[@itemprop='name']//a[@class='product-name']")).get(rand.nextInt(5)).click();
        return new OneDressPage(webDriver);
    }
}
