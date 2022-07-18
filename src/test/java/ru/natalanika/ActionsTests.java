package ru.natalanika;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;
import static org.assertj.core.api.InstanceOfAssertFactories.DURATION;

public class ActionsTests extends BaseTests{
    @Test
    void dragAndDropActionsTests() {
        webDriver.get("https://demoqa.com/sortable");
        List<WebElement> blocks = webDriver.findElements(By.xpath("//div[contains(@class, 'vertical-list-container')]//div[contains(@class, 'list-group-item')]"));
        System.out.println(blocks.get(0).getText());
        assertThat(blocks.get(0).getText()).isEqualTo("One");
        assertThat(blocks.get(1).getText()).isEqualTo("Two");
        assertThat(blocks.get(2).getText()).isEqualTo("Three");
        assertThat(blocks.get(3).getText()).isEqualTo("Four");
        assertThat(blocks.get(4).getText()).isEqualTo("Five");
        assertThat(blocks.get(5).getText()).isEqualTo("Six");

        Actions actions = new Actions(webDriver);
        actions.moveToElement(blocks.get(0))
                .clickAndHold()
                .pause(Duration.ofSeconds(1))
                .moveToElement(blocks.get(5))
                .release()
                .build()
                .perform();

        assertThat(blocks.get(0).getText()).isEqualTo("Two");
        assertThat(blocks.get(1).getText()).isEqualTo("Three");
        assertThat(blocks.get(2).getText()).isEqualTo("Four");
        assertThat(blocks.get(3).getText()).isEqualTo("Five");
        assertThat(blocks.get(4).getText()).isEqualTo("Six");
        assertThat(blocks.get(5).getText()).isEqualTo("One");

        actions.dragAndDrop(blocks.get(0), blocks.get(5))
                .build()
                .perform();

        assertThat(blocks.get(0).getText()).isEqualTo("Three");
        assertThat(blocks.get(1).getText()).isEqualTo("Four");
        assertThat(blocks.get(2).getText()).isEqualTo("Five");
        assertThat(blocks.get(3).getText()).isEqualTo("Six");
        assertThat(blocks.get(4).getText()).isEqualTo("One");
        assertThat(blocks.get(5).getText()).isEqualTo("Two");



    }
}
