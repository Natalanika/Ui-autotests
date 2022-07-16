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
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.assertThat;


public class ShopTest {
    WebDriver webDriver;
    WebDriverWait webDriverWait;

    @BeforeEach
    void setUp() {
        //ChromeOptions chromeOptions = new ChromeOptions().addArguments("--blink-settings=imagesEnabled=false");
        webDriver = WebDriverManager.chromedriver().create();
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(webDriver, 3);
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }

    @Test
    public void buyHTCTest() {
        String phoneName = "HTC U11";
        webDriver.get("http://localhost:3000/");
        webDriver.findElement(By.xpath("//button[.='LOGIN']")).click();
        webDriver.findElement(By.xpath("//input[contains(@id, 'Username')]")).sendKeys("admin");
        webDriver.findElement(By.xpath("//input[contains(@id, 'Password')]")).sendKeys("12345");
        webDriver.findElement(By.xpath("//button[.='Submit']")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='LOGOUT']")));
        List<WebElement> phones = webDriver.findElements(By.className("product"));
        phones.forEach(p -> System.out.println(p.getText() + "\n"));
        phones.stream()
                .filter(p -> p.getText().contains(phoneName))
                .findFirst()
                .orElseThrow()
                .findElement(By.xpath(".//a[.='See more']")).click();

        assertThat(webDriver.findElement(By.cssSelector(".product-details-container h1")).getText()).
                as("Должна открыться страница с телефоном " + phoneName).
                isEqualTo(phoneName);
        webDriver.findElement(By.xpath("//button[.='Add to cart']")).click();
        webDriver.findElement(By.xpath("//a[.='CART']")).click();

        List<String> actualPhoneList = webDriver.findElement(By.className("cart-items"))
                .findElements(By.xpath(".//table//tbody/tr"))
                .stream()
                .map(el -> el.findElements(By.xpath("./td")).get(1).getText())
                .collect(Collectors.toList());
        System.out.println(actualPhoneList);

        assertThat(actualPhoneList).containsExactlyInAnyOrder(phoneName);

        webDriver.findElement(By.xpath("//button[.='Checkout']")).click();
        webDriverWait.until(ExpectedConditions.textToBe(By.xpath("//h1[text()='Checkout Information']/../table//tbody/tr/td[1]"), phoneName));
        webDriver.findElement(By.xpath("//button[.='Confirm']")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[.='OK']"))).click();
    }

    @Test
    public void HuaweiAndLgBuyTest() {
        String huaweiName = "Huawei P10";
        String lgName = "LG V30";
        webDriver.get("http://localhost:3000/");
        webDriver.findElement(By.xpath("//button[.='LOGIN']")).click();
        webDriver.findElement(By.xpath("//input[contains(@id, 'Username')]")).sendKeys("admin");
        webDriver.findElement(By.xpath("//input[contains(@id, 'Password')]")).sendKeys("12345");
        webDriver.findElement(By.xpath("//button[.='Submit']")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='LOGOUT']")));
        webDriver.findElement(By.xpath("//a[@href='/product/62cc7315950643acfd99c0c3']/div/div/span")).click();
        assertThat(webDriver.findElement(By.cssSelector(".product-details-container h1")).getText()).
                as("Должна открыться страница с телефоном " + huaweiName).
                isEqualTo(huaweiName);
        webDriver.findElement(By.xpath("//button[.='Add to cart']")).click();
        webDriver.findElement(By.xpath("//a[.='Back to catalog']")).click();
        webDriver.findElement(By.xpath("//a[@href='/product/62cc7315950643acfd99c0c5']/div/div/span")).click();
        assertThat(webDriver.findElement(By.cssSelector(".product-details-container h1")).getText()).
                as("Должна открыться страница с телефоном " + lgName).
                isEqualTo(lgName);
        webDriver.findElement(By.xpath("//button[.='Add to cart']")).click();
        webDriver.findElement(By.xpath("//a[.='CART']")).click();
        List<String> actualPhoneList = webDriver.findElement(By.className("cart-items"))
                .findElements(By.xpath(".//table//tbody/tr"))
                .stream()
                .map(el -> el.findElements(By.xpath("./td")).get(1).getText())
                .collect(Collectors.toList());
        System.out.println(actualPhoneList);
        assertThat(actualPhoneList).contains(huaweiName);
        assertThat(actualPhoneList).contains(lgName);
        webDriver.findElement(By.xpath("//button[.='Checkout']")).click();
        webDriver.findElement(By.xpath("//button[.='Confirm']")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[.='OK']"))).click();
    }

    @Test
    public void chooseBrandTest() {
        List<String> samsungList = List.of("Samsung Galaxy A3", "Samsung Galaxy Note 8", "Samsung Galaxy S8");
        webDriver.get("http://localhost:3000/");
        webDriver.findElement(By.xpath("//button[.='LOGIN']")).click();
        webDriver.findElement(By.xpath("//input[contains(@id, 'Username')]")).sendKeys("admin");
        webDriver.findElement(By.xpath("//input[contains(@id, 'Password')]")).sendKeys("12345");
        webDriver.findElement(By.xpath("//button[.='Submit']")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='LOGOUT']")));
        webDriver.findElement(By.xpath("//div[text()='Brand']")).click();
        webDriver.findElement(By.xpath("//input[@nestedlevel = '1'][@value='samsung']")).click();
        Boolean PhoneList = webDriver.findElements(By.xpath("//div[@class='content-left']/h3"))
                .stream()
                .map(WebElement::getText)
                .allMatch(el -> el.contains("Samsung"));
        System.out.println(PhoneList);
        assertThat(PhoneList).isTrue();
    }

    @Test
    public void orderHistoryTest() {
        webDriver.get("http://localhost:3000/");
        webDriver.findElement(By.xpath("//button[.='LOGIN']")).click();
        webDriver.findElement(By.xpath("//input[contains(@id, 'Username')]")).sendKeys("admin");
        webDriver.findElement(By.xpath("//input[contains(@id, 'Password')]")).sendKeys("12345");
        webDriver.findElement(By.xpath("//button[.='Submit']")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='LOGOUT']")));
        webDriver.findElement(By.xpath("//a[.='ACCOUNT']")).click();
        List<String> orderHistory = webDriver.findElements(By.xpath("//tbody/tr"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        assertThat(orderHistory).isNotEmpty();
    }

    @Test
    public void emptyCartTest() {
        String text = "No items in the cart.";
        webDriver.get("http://localhost:3000/");
        webDriver.findElement(By.xpath("//button[.='LOGIN']")).click();
        webDriver.findElement(By.xpath("//input[contains(@id, 'Username')]")).sendKeys("admin");
        webDriver.findElement(By.xpath("//input[contains(@id, 'Password')]")).sendKeys("12345");
        webDriver.findElement(By.xpath("//button[.='Submit']")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='LOGOUT']")));
        webDriver.findElement(By.xpath("//a[.='CART']")).click();
        assertThat(webDriver.findElement(By.xpath("//div[@class='cart-items']/h1")).getText()).
                as("Должна открыться пустая корзина с текстом" + text).
                isEqualTo(text);
    }
}
