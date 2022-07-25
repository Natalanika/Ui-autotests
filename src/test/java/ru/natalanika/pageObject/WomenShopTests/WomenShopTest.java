package ru.natalanika.pageObject.WomenShopTests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ru.natalanika.pageObject.BaseTests;
import ru.natalanika.pageObject.WomenShopTests.pages.ConstantPart;
import ru.natalanika.pageObject.WomenShopTests.pages.MainPage;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование магазина женских платьев")
public class WomenShopTest extends BaseTests {
    String login = "gaponova-lanika@yandex.ru";
    String password = "12345";

    @BeforeEach
    void getPage() {
        webDriver.get("http://automationpractice.com/index.php");
    }


    @Description("Проверяем, отображаются ли платья в наличии")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Отображение наличия товара в магазине")
    @Test
    public void InStockTest() {

        boolean myCheck = new MainPage(webDriver)
                .getConstantPart()
                .login(login, password)
                .goToDresses()
                .isAnyDressesInTheStock();

        assertThat(myCheck).isTrue();
    }

    @Feature("Покупка")
    @Description("Покупаем случайное платье")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Покупка одного платья")
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


    @Description("Вводим неверный пароль")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Авторизация под неверными данными")
    @Test
    public void WrongPasswordTest() {
        String invalidPassword = "123";

        new MainPage(webDriver)
                .getConstantPart()
                .loginInvalid(login, invalidPassword);
    }

    @Description("Меняем фамилию в аккаунте")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Редактирование персональных данных в аккаунте")
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

    @Feature("Покупка")
    @Description("Выбираем размер платья")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Выбор размера товара")
    @Test
    public void selectSizeOfDress() throws InterruptedException {
        String size = "L";
        new MainPage(webDriver)
                .goToDresses()
                .selectRandomDress()
                .changeSize(size)
                .addDressToCartAndGoToCart();
        Thread.sleep(3000);
        String [] sizeOnPage = webDriver.findElement(By.xpath("//div[@class='cart_last_product_content']//small//a")).getAttribute("innerText").split(" ");

      assertThat(sizeOnPage).contains(size);
    }
}
