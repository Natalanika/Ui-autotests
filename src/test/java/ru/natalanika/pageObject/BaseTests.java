package ru.natalanika.pageObject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.decorators.WebDriverDecorator;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class BaseTests {
   protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;

    @BeforeEach
    void setUp() {
        webDriver = new WebDriverDecorator().decorate(WebDriverManager.chromedriver().create());
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }

}
