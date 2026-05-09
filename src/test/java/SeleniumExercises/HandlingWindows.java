package SeleniumExercises;

import BaseTest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class HandlingWindows extends BaseTest {

    private static final String WINDOWS_URL = "https://the-internet.herokuapp.com/windows";

    By openNewWindowLink = By.linkText("Click Here");
    By newWindowHeading = By.cssSelector("div.example h3");

    @Test
    public void openNewWindowSwitchValidateAndReturn() {
        getDriver().navigate().to(WINDOWS_URL);

        String parent = getDriver().getWindowHandle();

        findElement(openNewWindowLink).click();

        String child = getDriver().getWindowHandles().toArray()[1].toString();
        getDriver().switchTo().window(child);
        Assert.assertEquals(findElement(newWindowHeading).getText(), "New Window");

        getDriver().close();
        getDriver().switchTo().window(parent);
    }


    public WebElement findElement(By locator) {
        return buildFluentWait()
                .until(
                        d -> {
                            return d.findElement(locator);
                        });
    }

    private Wait<WebDriver> buildFluentWait() {
        Duration timeout = Duration.ofSeconds(50);
        Duration polling = Duration.ofMillis(300);

        return new FluentWait<>(getDriver())
                .withTimeout(timeout)
                .pollingEvery(polling)
                .ignoring(ElementNotInteractableException.class);
    }
}
