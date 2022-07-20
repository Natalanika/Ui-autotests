package ru.natalanika.simpleFirstTests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class JavaScriptExecutorTest extends BaseTests {
    @Test
    void deleteElementTest() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) this.webDriver;
        webDriver.get("https://demoqa.com/modal-dialogs");
        javascriptExecutor.executeScript("arguments[0].click()", webDriver.findElement(By.xpath("//button[@id='showSmallModal']")));
        javascriptExecutor.executeScript("arguments[0].remove()", webDriver.findElement(By.xpath("//div[@class='modal-content']")));
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='modal-content']")));

    }
}
