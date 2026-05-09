package SeleniumExercises;

import BaseTest.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

// Selenium's org.openqa.selenium.interactions.Actions is used via fully qualified name (this class is also named Actions).
public class Actions extends BaseTest {

    private static final String MOUSE_PAGE_URL = "https://aa-practice-test-automation.vercel.app/Pages/mouse.html";
    private static final String KEY_PRESSES_URL = "https://the-internet.herokuapp.com/key_presses";
    private static final String SCROLLING_PAGE_URL = "https://aa-practice-test-automation.vercel.app/Pages/scrolling.html";

    By doubleClickButton = By.id("dblClickBtn");
    By rightClickButton = By.id("riClickBtn");

    // Heroku "Key Presses" example: document keydown updates #result
    By keyTargetInput = By.id("target");
    By keyResult = By.id("result");
    By pageBody = By.tagName("body");

    // Scrolling practice: #scroll_text starts disabled; scrolling.js removes disabled only when the whole field fits in the viewport.
    By scrollTextInput = By.id("scroll_text");

    @Test
    public void doubleClickAndRightClickChangeButtonColors() {
        getDriver().navigate().to(MOUSE_PAGE_URL);

        findElement(doubleClickButton);
        new org.openqa.selenium.interactions.Actions(getDriver()).moveToElement(findElement(doubleClickButton)).doubleClick().perform();

        new org.openqa.selenium.interactions.Actions(getDriver()).moveToElement(findElement(rightClickButton)).contextClick().perform();
    }

    @Test
    public void keyboardKeyPressesAreDisplayed() {
        getDriver().navigate().to(KEY_PRESSES_URL);

        // Focus the page outside the form so Enter is not treated as form submit on #target.
        findElement(pageBody).click();

        org.openqa.selenium.interactions.Actions keyboard = new org.openqa.selenium.interactions.Actions(getDriver());

        keyboard.sendKeys(Keys.ENTER).perform();
        Assert.assertEquals(
                findElement(keyResult).getText(),
                "You entered: ENTER",
                "Enter should be reported in the result paragraph.");

        keyboard.sendKeys(Keys.TAB).perform();
        Assert.assertEquals(
                findElement(keyResult).getText(),
                "You entered: TAB",
                "Tab should be reported in the result paragraph.");

        keyboard.sendKeys(Keys.ESCAPE).perform();
        Assert.assertEquals(
                findElement(keyResult).getText(),
                "You entered: ESCAPE",
                "Escape should be reported in the result paragraph.");

        // TODO: Release All Actions
        ((RemoteWebDriver) getDriver()).resetInputState();

        // Typing into the field still triggers the same document keydown handler.
        WebElement target = findElement(keyTargetInput);
        target.click();
        target.sendKeys("a");
        Assert.assertEquals(
                findElement(keyResult).getText(),
                "You entered: A",
                "Letter key typed into the target field should be reported.");
    }

    @Test
    public void scrollToTextBox() {
        getDriver().navigate().to(SCROLLING_PAGE_URL);

        findElement(scrollTextInput);
        new org.openqa.selenium.interactions.Actions(getDriver()).scrollToElement(findElement(scrollTextInput)).perform();
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");

        findElement(scrollTextInput).click();
        findElement(scrollTextInput).clear();
        findElement(scrollTextInput).sendKeys("Scrolled and typed");
        Assert.assertEquals(findElement(scrollTextInput).getAttribute("value"), "Scrolled and typed", "Text box should accept input after scroll.");
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
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(StaleElementReferenceException.class);
    }
}
