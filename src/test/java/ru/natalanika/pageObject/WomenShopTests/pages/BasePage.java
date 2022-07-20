package ru.natalanika.pageObject.WomenShopTests.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

public class BasePage extends BaseView {

    public BasePage(WebDriver webDriver) {
        super(webDriver);
    }
    @Getter
    private ConstantPart constantPart = new ConstantPart(webDriver);
}
