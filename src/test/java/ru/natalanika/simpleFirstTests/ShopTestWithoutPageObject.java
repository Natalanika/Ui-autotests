package ru.natalanika.simpleFirstTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.assertThat;


public class ShopTestWithoutPageObject extends BaseTests {

    @ParameterizedTest
    @ValueSource(strings = {"HTC U11"})
    public void buyHTCTest(String phoneName) {
       login();
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
       login();
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
        login();
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
      login();
        webDriver.findElement(By.xpath("//a[.='ACCOUNT']")).click();
        List<String> orderHistory = webDriver.findElements(By.xpath("//tbody/tr"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        assertThat(orderHistory).isNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {"No items in the cart."})
    public void emptyCartTest(String text) {
        login();
        webDriver.findElement(By.xpath("//a[.='CART']")).click();
        assertThat(webDriver.findElement(By.xpath("//div[@class='cart-items']/h1")).getText()).
                as("Должна открыться пустая корзина с текстом" + text).
                isEqualTo(text);
    }
}
