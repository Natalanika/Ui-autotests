package ru.natalanika.pageObject.listener;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import static io.qameta.allure.Allure.step;


public class EventAllureListener implements WebDriverListener {
    @Override
    public void beforeClick(WebElement element) {
        step ("Нажимаем на элемент " + element.getText());
    }

    @Override
    public void afterClick(WebElement element) {
        step ("Успешно!");
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        step ("Вводим текст в элемент " + element.getAttribute("id"));
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        step ("Успешно!");
    }
}
