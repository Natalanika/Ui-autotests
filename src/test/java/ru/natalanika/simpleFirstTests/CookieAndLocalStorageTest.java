package ru.natalanika.simpleFirstTests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.assertj.core.api.Assertions.assertThat;

public class CookieAndLocalStorageTest extends BaseTests {
    @Test
    void cookieTest() {
        login();
        assertThat(webDriver.manage().getCookies()).isEmpty();
        Cookie myCookie = new Cookie("myCookie", "12345678");
        webDriver.manage().addCookie(myCookie);
        assertThat(webDriver.manage().getCookies()).containsExactlyInAnyOrder(myCookie);
        webDriver.manage().deleteAllCookies();
        assertThat(webDriver.manage().getCookies()).isEmpty();
    }

    @Test
    void localStorageTest() {
        webDriver.get("http://localhost:3000/");
        //login();
        //Thread.sleep(3000);
        //String token = ((WebStorage) webDriver).getLocalStorage().getItem("token");
        ((WebStorage) webDriver).getLocalStorage().setItem("token", "eyJhbGciOiJIUzI1NiJ9.YWRtaW4.MvnWZ6OdCsCt0asd49VCLvYNEBq5KKoFqdOkDzjxw6s");
        webDriver.navigate().refresh();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='LOGOUT']")));
    }

    @Test
    void localStorage2Test() throws InterruptedException {
        webDriver.get("https://qa-mesto.praktikum-services.ru/");
        //login();
        //Thread.sleep(3000);
        //String token = ((WebStorage) webDriver).getLocalStorage().getItem("token");
        ((WebStorage) webDriver).getLocalStorage().setItem("jwt", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MmQ4MDliZmQzYjg2YTAwM2Q2N2NhNTgiLCJpYXQiOjE2NTgzMjU0MzksImV4cCI6MTY1ODkzMDIzOX0.UtJWWJbkpHSFn_za__KwN4nAin2f2U9cSPnCTI_5KVY");
        webDriver.navigate().refresh();
        //webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='LOGOUT']")));
        Thread.sleep(5000);
    }
}
