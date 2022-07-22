package ru.natalanika.pageObject.MobileShopTests.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class AccountPage extends BasePage {

    public AccountPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Посмотреть историю заказов")
    public List<String> getOrderHistory () {
        List<String> orderHistory = webDriver.findElements(By.xpath("//tbody/tr"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        return orderHistory;
    }
}
