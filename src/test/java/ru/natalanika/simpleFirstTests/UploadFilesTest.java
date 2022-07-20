package ru.natalanika.simpleFirstTests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class UploadFilesTest extends BaseTests {
    @Test
    void uploadTest() throws InterruptedException {
        webDriver.get("https://demoqa.com/upload-download");
        webDriver.findElement(By.xpath("//input[@type='file']")).
                sendKeys("/Users/natali/learnUp/ui-autotests/ui-autotests/src/test/resources/chromedriver");
        Thread.sleep(5000);
    }
}
