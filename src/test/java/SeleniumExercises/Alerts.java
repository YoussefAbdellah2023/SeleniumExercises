package SeleniumExercises;

import BaseTest.BaseTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Alerts extends BaseTest {

    private static final String JAVASCRIPT_ALERTS_URL = "https://the-internet.herokuapp.com/javascript_alerts";

    By jsAlertButton = By.xpath("//button[text()='Click for JS Alert']");
    By jsConfirmButton = By.xpath("//button[text()='Click for JS Confirm']");
    By jsPromptButton = By.xpath("//button[text()='Click for JS Prompt']");
    By result = By.id("result");

    @Test
    public void handleJsAlertConfirmAndPrompt() {
        getDriver().navigate().to(JAVASCRIPT_ALERTS_URL);

        findElement(jsAlertButton).click();
        Alert alert = waitForAlert();
        Assert.assertEquals(alert.getText(), "I am a JS Alert", "Alert text should match the page script.");
        alert.accept();
        Assert.assertEquals(
                findElement(result).getText(),
                "You successfully clicked an alert",
                "Page should log success after accepting the alert.");

        findElement(jsConfirmButton).click();
        alert = waitForAlert();
        Assert.assertEquals(alert.getText(), "I am a JS Confirm", "Confirm dialog text should match.");
        alert.accept();
        Assert.assertEquals(findElement(result).getText(), "You clicked: Ok", "Accepting confirm should log Ok.");

        findElement(jsConfirmButton).click();
        alert = waitForAlert();
        Assert.assertEquals(alert.getText(), "I am a JS Confirm", "Confirm dialog text should match.");
        alert.dismiss();
        Assert.assertEquals(findElement(result).getText(), "You clicked: Cancel", "Dismissing confirm should log Cancel.");

        findElement(jsPromptButton).click();
        alert = waitForAlert();
        Assert.assertEquals(alert.getText(), "I am a JS prompt", "Prompt dialog text should match.");
        String promptInput = "Selenium typed this";
        alert.sendKeys(promptInput);
        alert.accept();
        Assert.assertEquals(
                findElement(result).getText(),
                "You entered: " + promptInput,
                "Accepting prompt with text should log the entered value.");

        findElement(jsPromptButton).click();
        alert = waitForAlert();
        Assert.assertEquals(alert.getText(), "I am a JS prompt", "Prompt dialog text should match.");
        alert.dismiss();
        Assert.assertEquals(
                findElement(result).getText(),
                "You entered: null",
                "Canceling prompt yields null in the page log.");
    }

    private Alert waitForAlert() {
        return buildFluentWait().until(ExpectedConditions.alertIsPresent());
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
