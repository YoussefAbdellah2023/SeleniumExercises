package SeleniumExercises;

import BaseTest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Objects;

public class SeleniumBasicMethod extends BaseTest {

    // Open the Chrome browser and navigate to https://example.com
    // Print the current URL, page title, and HTML source code
    // Print the unique ID of the current browser window.

    @Test
    public void printPageDetailsAndWindowHandle() {
        Wait<WebDriver> wait = buildFluentWait();
        getDriver().get("https://example.com");
        wait.until(
                d -> {
                    String title = d.getTitle();
                    return title != null && title.contains("Example Domain");
                });
        System.out.println("Current URL: " + getDriver().getCurrentUrl());
        System.out.println("Page Title: " + getDriver().getTitle());
        System.out.println("Page Source: " + getDriver().getPageSource());
        System.out.println("Window Handle: " + getDriver().getWindowHandle());
    }

    // Navigate to https://www.selenium.dev.
    // Go back to the previous page, then forward again, then refresh the page.
    // Print the current browser window's size and position on the screen.
    // Resize the browser window to Width = 1024, Height = 768.
    // Move the browser window to position X = 200, Y = 150 on the screen.
    // Maximize the browser window, then switch to full screen.
    // Close the current tab.
    // Launch again and quit the entire browser session after opening a new site.

    @Test
    public void navigationWindowResizeAndTabSwitch() {
        Wait<WebDriver> wait = buildFluentWait();
        Objects.requireNonNull(getDriver(), "Driver must be initialized.").get("https://www.selenium.dev");
        wait.until(
                d -> {
                    String currentUrl = d.getCurrentUrl();
                    return currentUrl != null && currentUrl.contains("selenium.dev");
                });
        getDriver().navigate().back();
        getDriver().navigate().forward();
        wait.until(
                d -> {
                    String currentUrl = d.getCurrentUrl();
                    return currentUrl != null && currentUrl.contains("selenium.dev");
                });
        getDriver().navigate().refresh();
        wait.until(
                d -> {
                    String currentUrl = d.getCurrentUrl();
                    return currentUrl != null && currentUrl.contains("selenium.dev");
                });
        System.out.println("Window Size: " + getDriver().manage().window().getSize());
        System.out.println("Window Position: " + getDriver().manage().window().getPosition());
        getDriver().manage().window().setSize(new org.openqa.selenium.Dimension(1024, 768));
        getDriver().manage().window().setPosition(new org.openqa.selenium.Point(200, 150));
        getDriver().manage().window().maximize();
        // Open a new tab so we still have a window after closing the current one (avoids Connection reset)
        String originalHandle = getDriver().getWindowHandle();
        getDriver().switchTo().newWindow(WindowType.TAB);
        getDriver().switchTo().window(originalHandle);
        getDriver().close();
        String remainingHandle = Objects.requireNonNull(getDriver().getWindowHandles().iterator().next());
        getDriver().switchTo().window(remainingHandle);
        getDriver().navigate().to("https://www.google.com");
    }

    public WebElement findElement(By locator) {
        return buildFluentWait().until(d -> d.findElement(locator));
    }

    /** Same FluentWait setup as {@link RelativeLocators}. */
    private Wait<WebDriver> buildFluentWait() {
        Duration timeout = Duration.ofSeconds(2);
        Duration polling = Duration.ofMillis(300);

        return new FluentWait<>(getDriver())
                .withTimeout(timeout)
                .pollingEvery(polling)
                .ignoring(ElementNotInteractableException.class);
    }
}
