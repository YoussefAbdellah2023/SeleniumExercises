package SeleniumExercises;

import BaseTest.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;

import java.time.Duration;

public class JavaScriptExecutorAndActions extends BaseTest {

    //Use JS to scroll to an element, click it, and set a value in an input field.

    By scrollToElement = By.id("scroll_text");

    @Test
    public void useJavaScriptExecutorScrollToElement() {
        getDriver().navigate().to("https://aa-practice-test-automation.vercel.app/Pages/scrolling.html");
        ((JavascriptExecutor) getDriver()).
                executeScript("arguments[0].scrollIntoView();", findElement(scrollToElement));
    }

    @Test
    public void useJavaScriptExecutorClickElement() {
        getDriver().navigate().to("https://aa-practice-test-automation.vercel.app/Pages/scrolling.html");
        ((JavascriptExecutor) getDriver()).
                executeScript("arguments[0].click();", findElement(scrollToElement));
    }

    @Test
    public void useJavaScriptExecutorSetValue() {
        getDriver().navigate().to("https://aa-practice-test-automation.vercel.app/Pages/scrolling.html");
        ((JavascriptExecutor) getDriver()).
                executeScript("arguments[0].value='Hello, World!';", findElement(scrollToElement));
    }

    @Test
    public void userActionsClickElement() {
        getDriver().navigate().to("https://aa-practice-test-automation.vercel.app/Pages/scrolling.html");
        Actions actions = new Actions(getDriver());
        actions.click(findElement(scrollToElement)).perform();
    }

    @Test
    public void userActionsSetValue() {
        getDriver().navigate().to("https://aa-practice-test-automation.vercel.app/Pages/scrolling.html");
        Actions actions = new Actions(getDriver());
        actions.sendKeys(findElement(scrollToElement), "Hello, World!").perform();
    }

    @Test
    public void userActionsScrollByOffset() {
        getDriver().navigate().to("https://aa-practice-test-automation.vercel.app/Pages/scrolling.html");
        Actions actions = new Actions(getDriver());
        actions.scrollByAmount(0, 500).perform(); // Scroll down by 500 pixels
    }


    @Test
    public void userActionsScrollToElement() {
        getDriver().navigate().to("https://aa-practice-test-automation.vercel.app/Pages/scrolling.html");
        Actions actions = new Actions(getDriver());
        actions.scrollToElement(findElement(scrollToElement)).perform();
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
