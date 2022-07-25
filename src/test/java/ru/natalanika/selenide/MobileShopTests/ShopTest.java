package ru.natalanika.selenide.MobileShopTests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.natalanika.selenide.MobileShopTests.ext.UiTestsExt;
import ru.natalanika.selenide.MobileShopTests.pages.PhonesPage;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.parameter;

@DisplayName("Тестирование магазина телефонов")
@ExtendWith(UiTestsExt.class)
public class ShopTest {
    String login = "admin";
    String password = "12345";

    @Feature("Новая корзина")
    @Description("В этом тесте покупаем телефон")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Покупка одного телефона")
    @ParameterizedTest(name = "Покупка телефона {0}")
    @ValueSource(strings = {"HTC U11", "LG V30"})
    public void buyOnePhoneTest(String phoneName) {
        parameter("Название телефона ", phoneName);

        open("http://localhost:3000/", PhonesPage.class)
                .getHeaderBlock()
                .login(login, password)
                .selectPhone(phoneName)
                .checkPhoneName(phoneName)
                .clickAddToCart()
                .getHeaderBlock()
                .goToCart()
                .checkCartContainsExactly(phoneName)
                .clickCheckout()
                .clickConfirm()
                .checkThatCheckoutIsSuccessful();
    }

    @Feature("Авторизация пользователя")
    @Description("В этом тесте авторизуемся")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Авторизация")
    @Test
    public void authTest() {
        open("http://localhost:3000/", PhonesPage.class)
                .getHeaderBlock()
                .login(login, password);

    }

    @Description("Покупаем два телефона")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Покупка двух телефонов сразу")
    @Test
    public void twoPhoneByTest() {
        String huaweiName = "Huawei P10";
        String lgName = "LG V30";

        open("http://localhost:3000/", PhonesPage.class)
                .getHeaderBlock()
                .login(login, password)
                .selectPhone(huaweiName)
                .clickAddToCart()
                .backToCatalog()
                .selectPhone(lgName)
                .clickAddToCart()
                .getHeaderBlock()
                .goToCart()
                .checkCartContains(huaweiName)
                .checkCartContains(lgName)
                .clickCheckout()
                .clickConfirm()
                .checkThatCheckoutIsSuccessful();
    }

    @Feature("Бренды")
    @Description("В этом тесте выбираем бренд")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Выбор телефонов бренда Samsung")
    @Test
    public void chooseBrandTest() {
        open("http://localhost:3000/", PhonesPage.class)
                .selectSamsungBrand()
                .arePhonesOfSamsung();
    }

    @Description("В этом тесте проверяем отображение истории заказов в аккаунте пользователя")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("История заказов в аккаунте пользователя")
    @Test
    public void orderHistoryTest() {

        open("http://localhost:3000/", PhonesPage.class)
                .getHeaderBlock()
                .login(login, password)
                .getHeaderBlock()
                .goToAccount()
                .CheckOrderHistory();
    }

    @Description("В этом тесте проверяем текст в пустой корзине")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Отображение текста в пустой корзине")
    @ParameterizedTest
    @ValueSource(strings = {"No items in the cart."})
    public void emptyCartMessageTest(String text) {
        open("http://localhost:3000/", PhonesPage.class)
                .getHeaderBlock()
                .login(login, password)
                .getHeaderBlock()
                .goToCart()
                .checkEmptyCartHasText(text);
    }

    @Description("В этом тесте проверям возможность удаления заказа из корзины")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Удаление заказа из корзины")
    @ParameterizedTest
    @ValueSource(strings = {"HTC U11", "Apple iPhone X"})
    public void deleteFromCart(String phone) {
        open("http://localhost:3000/", PhonesPage.class)
                .getHeaderBlock()
                .login(login, password)
                .selectPhone(phone)
                .clickAddToCart()
                .getHeaderBlock()
                .goToCart()
                .checkCartContainsExactly(phone)
                .deleteOrderFromCart()
                .checkIfEmptyCart();
    }

    @Description("В этом тесте выбираем количество телефонов для заказа")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Выбор количества телефонов")
    @ParameterizedTest
    @ValueSource(strings = {"HTC U11", "LG V30"})
    public void changeQuantityTest(String phoneName) {
        String value = "5";
        open("http://localhost:3000/", PhonesPage.class)
                .getHeaderBlock()
                .login(login, password)
                .selectPhone(phoneName)
                .setQuantity(value)
                .getHeaderBlock()
                .goToCart()
                .checkCartContainsExactly(phoneName)
                .checkCartContainsExactlyQuantity(value)
                .deleteOrderFromCart();
    }

    @Severity(SeverityLevel.MINOR)
    @DisplayName("Выбор телефонов черного цвета")
    @Test
    public void chooseColourTest() {
        String color = "black";
        open("http://localhost:3000/", PhonesPage.class)
                .selectColour(color);
    }
}
