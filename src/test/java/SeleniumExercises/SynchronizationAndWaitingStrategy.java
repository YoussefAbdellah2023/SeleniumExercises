package SeleniumExercises;

import BaseTest.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class SynchronizationAndWaitingStrategy extends BaseTest {

    private static final String DYNAMIC_LOADING_URL = "https://the-internet.herokuapp.com/dynamic_loading/2";

    By startButton = By.xpath("//button");
    By finish = By.cssSelector("#finish>h4");

    @Test
    public void waitForDynamicContentAfterStart() {
        getDriver().navigate().to(DYNAMIC_LOADING_URL);
        findElement(startButton).click();
        Assert.assertTrue(findElement(finish).isDisplayed(), "Finish area should be visible after loading.");
        Assert.assertEquals(findElement(finish).getText(), "Hello World!", "Loaded text should match expected message.");
    }

    public WebElement findElement(By locator) {
        return buildFluentWait()
                .until(
                        d -> {
                            return d.findElement(locator);
                        });
    }

    private Wait<WebDriver> buildFluentWait() {
        Duration timeout = Duration.ofSeconds(10);
        Duration polling = Duration.ofMillis(300);

        return new FluentWait<>(getDriver())
                .withTimeout(timeout)
                .pollingEvery(polling)
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class);

    }
}
