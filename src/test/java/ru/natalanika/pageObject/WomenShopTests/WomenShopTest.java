package ru.natalanika.pageObject.WomenShopTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ru.natalanika.pageObject.BaseTests;
import ru.natalanika.pageObject.WomenShopTests.pages.ConstantPart;
import ru.natalanika.pageObject.WomenShopTests.pages.MainPage;

import static org.assertj.core.api.Assertions.assertThat;


public class WomenShopTest extends BaseTests {
    String login = "gaponova-lanika@yandex.ru";
    String password = "12345";
    String invalidPassword = "123";

    @BeforeEach
    void getPage() {
        webDriver.get("http://automationpractice.com/index.php");
    }

    @Test
    public void InStockTest() {

        boolean myCheck = new MainPage(webDriver)
                .getConstantPart()
                .login(login, password)
                .goToDresses()
                .isAnyDressesInTheStock();

        assertThat(myCheck).isTrue();
    }

    @Test
    public void BuyDress() {
        String successText = "Your order on My Store is complete.";

        String myCheck = new MainPage(webDriver)
                .getConstantPart()
                .login(login, password)
                .goToDresses()
                .selectRandomDress()
                .addDressToCartAndGoToCart()
                .checkCartIsNotEmpty()
                .completeToBuy()
                .getMessageAboutSuccess().getText();

        assertThat(myCheck).isEqualTo(successText);
    }

    @Test
    public void WrongPasswordTest() {

        new MainPage(webDriver)
                .getConstantPart()
                .loginInvalid(login, invalidPassword);
    }

    @Test
    public void changePersonalInfo() {
        String lastName = "MyNewLastName";
        new MainPage(webDriver)
                .getConstantPart()
                .login(login, password)
                .getConstantPart()
                .goToPersonalInfo()
                .changeLastName(lastName, password)
                .checkThatChangingPersonalInformationIsSuccesful();
        String newLastName = new ConstantPart(webDriver).getNameAndLastNameOnPage().getText().split(" ")[1];
        System.out.println(newLastName);

        assertThat(newLastName).isEqualTo(lastName);

    }

    @Test
    public void selectSizeOfDress() {
        String size = "M";
        new MainPage(webDriver)
                .getConstantPart()
                .login(login, password)
                .goToDresses()
                .selectRandomDress()
                .changeSize(size)
                .addDressToCartAndGoToCart();
        String sizeOnPage = webDriver.findElement(By.xpath("//div[@class='cart_last_product_content']//small//a")).getAttribute("innerHTML").split(" ")[5];

        assertThat(sizeOnPage).isEqualTo(size);
    }
}
