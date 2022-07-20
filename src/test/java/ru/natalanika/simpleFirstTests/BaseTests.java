package ru.natalanika.simpleFirstTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseTests {
   protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;

    protected void login () {
        webDriver.get("http://localhost:3000/");
        webDriver.findElement(By.xpath("//button[.='LOGIN']")).click();
        webDriver.findElement(By.xpath("//input[contains(@id, 'Username')]")).sendKeys("admin");
        webDriver.findElement(By.xpath("//input[contains(@id, 'Password')]")).sendKeys("12345");
        webDriver.findElement(By.xpath("//button[.='Submit']")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='LOGOUT']")));
    }

    @BeforeEach
    void setUp() {
        webDriver = WebDriverManager.chromedriver().create();
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(webDriver, 5);
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }

}
