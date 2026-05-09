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

public class WebElementsandLocators extends BaseTest {

    // Open a 'https://www.facebook.com/r.php?entry_point=login'
    // and locate fields by id, name, className, tagName, xpath, and cssSelector. Fill in test data.

    By firstNameField = By.id("_R_1cl2p4jikacppb6amH1_");
    By lastNameField = By.id("_R_1kl2p4jikacppb6amH1_");
    By dateOfBirthField = By.xpath("(//*[@role='combobox'])[1]");
    By monthOfBirthField = By.xpath("(//*[@role='combobox'])[2]");
    By yearOfBirthField = By.xpath("(//*[@role='combobox'])[3]");
    By dayOption1 = By.xpath("//*[@role='option'][.='1']");
    By monthOptionJanuary = By.xpath("//*[@role='option'][.='January']");
    By yearOption1990 = By.xpath("//*[@role='option'][.='1990']");
    By genderField = By.xpath("(//*[@role='combobox'])[4]");
    By genderOptionMale = By.xpath("//*[@role='option'][.='Male']");
    By mobileOrEmailField =
            By.xpath(
                    "(//input[@type='text'])[3]");
    By mobileOrEmailFallback = By.xpath("(//*[@role='textbox'])[3]");
    By passwordField = By.cssSelector("input[type='password']");
    By submitButton = By.xpath("//*[@role='button'][.='Submit']");

    @Test
    public void fillFacebookSignupFormUsingLocators() {
        getDriver().get("https://www.facebook.com/r.php?entry_point=login");
        // fill the first name field
        findElement(firstNameField).sendKeys("Youssef");
        // fill the last name field
        findElement(lastNameField).sendKeys("Abdellah");
        // Day: open combobox and click option "1"
        findElement(dateOfBirthField).click();
        findElement(dayOption1).click();
        // Month: open combobox and click option "January" (Facebook uses full month name)
        findElement(monthOfBirthField).click();
        findElement(monthOptionJanuary).click();
        // Year: open combobox and click option "1990"
        findElement(yearOfBirthField).click();
        findElement(yearOption1990).click();
        // Gender: open combobox and click "Male"
        findElement(genderField).click();
        findElement(genderOptionMale).click();
        // Mobile number or email address (aria-label/placeholder, fallback to 3rd textbox)
        findElement(mobileOrEmailField).sendKeys("test.example@email.com");
        // Password
        findElement(passwordField).sendKeys("TestPassword123!");
        // Submit button
        findElement(submitButton).click();
    }

    public WebElement findElement(By locator) {
        return buildFluentWait().until(d -> d.findElement(locator));
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
