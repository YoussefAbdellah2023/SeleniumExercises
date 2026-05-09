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

public class Frames extends BaseTest {

    private static final String IFRAME_URL = "https://the-internet.herokuapp.com/iframe";

    // TinyMCE wraps the textarea in an iframe inside the example block
    By closesButton = By.cssSelector("[role='alert'] > button");
    By editorFrame = By.cssSelector("div.example iframe");
    By tinymceBody = By.id("tinymce");
    By pageHeading = By.cssSelector("div.example h3");

    @Test
    public void switchToFrameTypeInEditorAndReturnToDefault() {
        getDriver().navigate().to(IFRAME_URL);
        findElement(closesButton).click();

        getDriver().switchTo().frame(findElement(editorFrame));

        findElement(tinymceBody).click();

        Assert.assertTrue(
                findElement(tinymceBody).getText().contains("Your content goes here"),
                "TinyMCE body should contain the text typed inside the frame.");

        getDriver().switchTo().defaultContent();

        Assert.assertTrue(
                findElement(pageHeading).getText().contains("iFrame"),
                "After defaultContent(), the main page heading should be visible again.");
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
