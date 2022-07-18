package ru.natalanika;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import static org.assertj.core.api.Assertions.assertThat;


public class PracticeTest {
    WebDriver webDriver;
    WebDriverWait webDriverWait;

    @BeforeEach
    void setUp() {
        webDriver = WebDriverManager.chromedriver().create();
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(webDriver, 3);
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }

    public void login() {
        webDriver.get("http://automationpractice.com/index.php");
        webDriver.findElement(By.xpath("//a[@class='login']")).click();
        webDriver.findElement(By.xpath("//input[@id='email']")).sendKeys("gaponova-lanika@yandex.ru");
        webDriver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("12345");
        webDriver.findElement(By.xpath("//button[@id='SubmitLogin']")).click();
    }

    @Test
    public void InStockTest() {
       login();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='logout']")));
        webDriver.findElement(By.xpath("(//a[.='Dresses'])[2]")).click();
        webDriver.findElement(By.xpath("(//a[.='Dresses'])[2]")).click();
        List<WebElement> dresses = webDriver.findElements(By.className("right-block"));
        dresses.forEach(p -> System.out.println(p.getText() + "\n"));
        Boolean isInStock = dresses.stream().allMatch(el -> el.getText().contains("In stock"));
        assertThat(isInStock).isTrue();
    }

    @Test
    public void BuyDress()  {
        String succesText = "Your order on My Store is complete.";
        login();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='logout']")));
        webDriver.findElement(By.xpath("(//a[.='Dresses'])[2]")).click();
        webDriver.findElement(By.xpath("(//a[.='Dresses'])[2]")).click();
        Random rand = new Random();
        webDriver.findElements(By.xpath("//div//h5[@itemprop='name']//a[@class='product-name']")).get(rand.nextInt(5)).click();
        webDriver.findElement(By.xpath("//button[.//span[text()='Add to cart']]")).click();
        webDriver.findElement(By.xpath("//a[@title='Proceed to checkout']")).click();
        webDriver.findElement(By.xpath("(//a[@title='Proceed to checkout'])[2]")).click();
        webDriver.findElement(By.xpath("//button[@name='processAddress']")).click();
        webDriver.findElement(By.xpath("//input[@type='checkbox']")).click();
        webDriver.findElement(By.xpath("//button[@name='processCarrier']")).click();
        webDriver.findElement(By.xpath("//a[@title='Pay by check.']")).click();
        webDriver.findElement(By.xpath("//button[.//i[@class='icon-chevron-right right']]")).click();
        String text = webDriver.findElement(By.xpath("//p[@class='alert alert-success']")).getText();
        assertThat(text).isEqualTo(succesText);
    }

    @Test
    public void WrongPasswordTest() {
        webDriver.get("http://automationpractice.com/index.php");
        webDriver.findElement(By.xpath("//a[@class='login']")).click();
        webDriver.findElement(By.xpath("//input[@id='email']")).sendKeys("gaponova-lanika@yandex.ru");
        webDriver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("123");
        webDriver.findElement(By.xpath("//button[@id='SubmitLogin']")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='There is 1 error']")));
    }
}
