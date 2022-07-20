package ru.natalanika.pageObject.MobileShopTests.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import ru.natalanika.pageObject.MobileShopTests.Blocks.HeaderBlock;

public class BasePage extends BaseView {

    @Getter
    private HeaderBlock headerBlock = new HeaderBlock(webDriver);

    public BasePage(WebDriver webDriver) {
        super(webDriver);
    }
}
