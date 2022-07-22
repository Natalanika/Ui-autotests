package ru.natalanika.pageObject.MobileShopTests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.natalanika.pageObject.MobileShopTests.pages.PhonesPage;
import ru.natalanika.pageObject.BaseTests;
import java.util.List;
import static io.qameta.allure.Allure.parameter;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование магазина телефонов")
public class ShopTest extends BaseTests {
    String login = "admin";
    String password = "12345";

    @BeforeEach
    void getPage() {
        webDriver.get("http://localhost:3000/");
    }

    @Feature("Новая корзина")
    @Description("В этом тесте покупаем телефон")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Покупка одного телефона")
    @ParameterizedTest(name = "Покупка телефона {0}")
    @ValueSource(strings = {"HTC U11", "LG V30"})
    public void buyOnePhoneTest(String phoneName) {
        parameter("Название телефона ", phoneName);

        new PhonesPage(webDriver)
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
                .checkThatCheckoutIsSuccesful();
    }

    @Feature("Авторизация пользователя")
    @Description("В этом тесте авторизуемся")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Авторизация")
    @Test
    public void authTest() {
        new PhonesPage(webDriver)
                .getHeaderBlock()
                .login(login, password);

    }

    @DisplayName("Серый тест")
    @Test
    @Disabled("Тест не прогоняется")
    public void authGreyTest() {
        assertThat(true).isFalse();
    }

    @Description("Покупаем два телефона")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Покупка двух телефонов сразу")
    @Test
    public void twoPhoneByTest() {
        String huaweiName = "Huawei P10";
        String lgName = "LG V30";

        new PhonesPage(webDriver)
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
                .checkThatCheckoutIsSuccesful();
    }

    @Feature("Бренды")
    @Description("В этом тесте выбираем бренд")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Выбор телефонов бренда Samsung")
    @Test
    public void chooseBrandTest() {

        boolean myCheck = new PhonesPage(webDriver)
                .selectSamsungBrand()
                .arePhonesOfSamsung();

        assertThat(myCheck).isTrue();
    }


    @Description("В этом тесте проверяем отображение истории заказов в аккаунте пользователя")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("История заказов в аккаунте пользователя")
    @Test
    public void orderHistoryTest() {

        List<String> myCheck = new PhonesPage(webDriver)
                .getHeaderBlock()
                .login(login, password)
                .getHeaderBlock()
                .goToAccount()
                .getOrderHistory();

        assertThat(myCheck).isNotEmpty();
    }

    @Description("В этом тесте проверяем текст в пустой корзине")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Отображение текста в пустой корзине")
    @ParameterizedTest
    @ValueSource(strings = {"No items in the cart."})
    public void emptyCartMessageTest(String text) {

        new PhonesPage(webDriver)
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

        new PhonesPage(webDriver)
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
}
