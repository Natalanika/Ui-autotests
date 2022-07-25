package ru.natalanika.selenide.MobileShopTests.pages;

import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$$;

public class AccountPage extends BasePage {

    @Step("Посмотреть историю заказов")
    public AccountPage CheckOrderHistory () {
        $$("tr").shouldHave(CollectionCondition.sizeGreaterThan(0));
        return this;
    }
}
