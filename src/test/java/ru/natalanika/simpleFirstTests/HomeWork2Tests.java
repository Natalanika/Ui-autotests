package ru.natalanika.simpleFirstTests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomeWork2Tests extends BaseTests {

    @Test
    void dragAndDropTest() throws InterruptedException {
        webDriver.get("https://crossbrowsertesting.github.io/drag-and-drop.html");
        Actions actions = new Actions(webDriver);
        WebElement elementA = webDriver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement elementB = webDriver.findElement(By.xpath("//div[@id='droppable']"));
        actions.dragAndDrop(elementA, elementB)
                .perform();
        Thread.sleep(3000);
        //actions.moveToElement(elementA).clickAndHold().moveToElement(elementB).release().build().perform();
    }

    @Test
    void openWindowTest() {
        webDriver.get("https://demoqa.com/");
        String firstWindow = webDriver.getWindowHandle();
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.open()");
        String secondWindow = (String) webDriver.getWindowHandles().toArray()[1];
        webDriver.switchTo().window(secondWindow);
        webDriver.close();
        webDriver.switchTo().window(firstWindow);
    }

    @Test
    void alertsTest() throws InterruptedException {
        webDriver.get("https://demoqa.com/alerts");
        webDriver.findElement(By.id("timerAlertButton")).click();
        webDriverWait.until(ExpectedConditions.alertIsPresent()).accept();

        webDriver.findElement(By.id("confirmButton")).click();
        webDriverWait.until(ExpectedConditions.alertIsPresent()).dismiss();

        webDriver.findElement(By.id("promtButton")).click();
        webDriverWait.until(ExpectedConditions.alertIsPresent()).sendKeys("Natalya");
        webDriver.switchTo().alert().accept();
        Thread.sleep(3000);
    }

    @Test
    void loadFile() throws InterruptedException {
        webDriver.get("http://the-internet.herokuapp.com/upload");
        webDriver.findElement(By.xpath("//input[@id='file-upload']"))
                .sendKeys("/Users/natali/Documents/Картинка.jpeg");
        Thread.sleep(5000);
    }

    @Test
    void setTokenTest() throws InterruptedException {
        webDriver.get("https://qa-mesto.praktikum-services.ru/");
        ((WebStorage) webDriver).getLocalStorage().setItem("jwt", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MmQ4MDliZmQzYjg2YTAwM2Q2N2NhNTgiLCJpYXQiOjE2NTgzMjU0MzksImV4cCI6MTY1ODkzMDIzOX0.UtJWWJbkpHSFn_za__KwN4nAin2f2U9cSPnCTI_5KVY");
        webDriver.navigate().refresh();
        Thread.sleep(5000);
    }
}


