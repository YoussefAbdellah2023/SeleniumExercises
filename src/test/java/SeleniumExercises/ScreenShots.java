package SeleniumExercises;

import BaseTest.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

public class ScreenShots extends BaseTest {

    private static final String LOGIN_URL = "https://aa-practice-test-automation.vercel.app/index.html";

    By usernameInput = By.id("inputUsername");
    By passwordInput = By.id("inputPassword");
    By signInButton = By.id("loginButton");
    By logoutButton = By.cssSelector("button.logout");

    @Test
    public void captureScreenshotAfterLoginSuccess() {
        getDriver().navigate().to(LOGIN_URL);

        findElement(usernameInput).sendKeys("admin");
        findElement(passwordInput).sendKeys("admin");
        findElement(signInButton).click();

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/Pages/main.html"), "Successful login should redirect to the main page.");
        Assert.assertTrue(
                findElement(logoutButton).isDisplayed(),
                "Logout button should be visible after successful login.");

    }

    public void saveScreenshotAs(String fileName) {
        File screenshotFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        Path destination = Path.of(fileName);
        try {
            Files.copy(screenshotFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot to: " + destination.toAbsolutePath(), e);
        }
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

    public void saveScreenshotAs2(String fileName) {
        File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        File des = new File("src/test/resources/" + fileName + ".png");
        try {
            FileUtils.copyFile(src, des);
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }
}
