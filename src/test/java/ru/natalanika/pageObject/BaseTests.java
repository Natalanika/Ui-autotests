package ru.natalanika.pageObject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.decorators.WebDriverDecorator;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.natalanika.pageObject.listener.EventAllureListener;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTests {
   protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;

    @BeforeEach
    void setUp() {
        WebDriverListener listener = new EventAllureListener();
        webDriver= new EventFiringDecorator(listener).decorate(WebDriverManager.chromedriver().create());
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }

}
