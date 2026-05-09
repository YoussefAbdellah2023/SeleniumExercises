package SeleniumExercises;

import BaseTest.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;

import java.time.Duration;

public class SeleniumChallenge extends BaseTest {

    By firstNameInput = By.id("first-name");
    By lastNameInput = By.id("last-name");
    By jobTitleInput = By.id("job-title");
    By educationLevelRadioButtons = By.id("radio-button-2");
    By sexRadioButtons = By.id("checkbox-1");
    By experienceDropdown = By.id("select-menu");
    By experienceOption2 = By.cssSelector("#select-menu option[value='2']");
    By submitButton = By.id("submit-btn");

    // Open 'https://formy-project.herokuapp.com/form' and fill in the form with test data, then submit it.

    @Test
    public void fillFormAndSubmit() {
        getDriver().navigate().to("https://aa-practice-test-automation.vercel.app/Pages/Challenge.html");
        findElement(firstNameInput).sendKeys("Youssef");
        findElement(lastNameInput).sendKeys("Abdellah");
        findElement(jobTitleInput).sendKeys("QA Engineer");
        findElement(educationLevelRadioButtons).click();
        Actions actions = new Actions(getDriver());
        actions.moveToElement(findElement(sexRadioButtons)).click().perform();
        actions.moveToElement(findElement(experienceDropdown)).click().perform();
        findElement(experienceOption2).click();
        actions.moveToElement(findElement(submitButton)).click().perform();
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
                .ignoring(org.openqa.selenium.ElementClickInterceptedException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(NoSuchElementException.class);
    }
}
