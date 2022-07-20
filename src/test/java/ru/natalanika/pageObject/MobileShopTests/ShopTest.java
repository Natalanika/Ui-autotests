package ru.natalanika.pageObject.MobileShopTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.natalanika.pageObject.MobileShopTests.pages.PhonesPage;
import ru.natalanika.simpleFirstTests.BaseTests;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class ShopTest extends BaseTests {
    String login = "admin";
    String password = "12345";

    @BeforeEach
    void getPage() {
        webDriver.get("http://localhost:3000/");
    }

    @ParameterizedTest
    @ValueSource(strings = {"HTC U11", "LG V30"})
    public void buyOnePhoneTest(String phoneName) {

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

    @Test
    public void chooseBrandTest() {

        boolean myCheck = new PhonesPage(webDriver)
                .selectSamsungBrand()
                .arePhonesOfSamsung();

        assertThat(myCheck).isTrue();
    }

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

    @ParameterizedTest
    @ValueSource (strings = {"HTC U11", "Apple iPhone X"})
    public void deleteFromCart (String phone) {

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
