package SeleniumExercises;

import BaseTest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;

import java.time.Duration;

public class RelativeLocators extends BaseTest {

    // Open 'https://aa-practice-test-automation.vercel.app/index.html '
    // and use Selenium's relative locators to find a label element that is above the password field
    // and a checkbox near a text label.

    By usernameField = By.xpath("//input[@id='inputPassword']/preceding-sibling::input");
    By passwordField = By.xpath("//input[@id='inputUsername']/following-sibling::input");
    By checkboxWithValueMe = By.xpath("//*[contains(@value,'me')]");
    By signButton = By.xpath("//*[contains(text(),'Sign')]");

    ScreenShots screenShots = new ScreenShots();

    @Test
    public void findLabelElementAbovePasswordFieldAndCheckboxNearTextLabel() {
        getDriver().get("https://aa-practice-test-automation.vercel.app/index.html");

        findElement(usernameField).sendKeys("admin");
        findElement(passwordField).sendKeys("admin");
        findElement(checkboxWithValueMe).click();
        findElement(signButton).click();
//        screenShots.saveScreenshotAs("relative_locators_test.png");
    }

    public WebElement findElement(By locator) {

        return buildFluentWait().until(
                d -> d.findElement(locator));
    }

    private Wait<WebDriver> buildFluentWait() {
        Duration timeout = Duration.ofSeconds(2);
        Duration polling = Duration.ofMillis(300);

        return new FluentWait<>(getDriver())
                .withTimeout(timeout)
                .pollingEvery(polling)
                .ignoring(ElementNotInteractableException.class);
    }
}
