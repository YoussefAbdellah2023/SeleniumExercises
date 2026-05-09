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

public class CheckboxesAndRadioButtons extends BaseTest {

    private static final String PAGE_URL = "https://aa-practice-test-automation.vercel.app/Pages/checkbox_Radio.html";

    // Checkboxes
    By ahlyCheckbox = By.id("Ahly");
    By zamalekCheckbox = By.id("Zamalek");
    By noClubCheckbox = By.id("noClub");

    // Radio buttons
    By highSchoolRadio = By.id("radio-button-1");
    By collegeRadio = By.id("radio-button-2");
    By gradSchoolRadio = By.id("radio-button-3");

    @Test
    public void selectCheckboxesAndRadioButtonsAndValidateSelection() {
        getDriver().get(PAGE_URL);

        // Select specific checkboxes: Al-Zamalek and no club.
        selectIfNotSelected(zamalekCheckbox);
        selectIfNotSelected(noClubCheckbox);

        // Keep Al-Ahly unchecked to validate targeted selection.
        unselectIfSelected(ahlyCheckbox);

        Assert.assertFalse(findElement(ahlyCheckbox).isSelected(), "Al-Ahly checkbox should be unselected.");
        Assert.assertTrue(findElement(zamalekCheckbox).isSelected(), "Al-Zamalek checkbox should be selected.");
        Assert.assertTrue(findElement(noClubCheckbox).isSelected(), "No-club checkbox should be selected.");

        // Select a specific radio button and validate single selection behavior.
        selectIfNotSelected(collegeRadio);

        Assert.assertFalse(findElement(highSchoolRadio).isSelected(), "High School radio button should be unselected.");
        Assert.assertTrue(findElement(collegeRadio).isSelected(), "College radio button should be selected.");
        Assert.assertFalse(findElement(gradSchoolRadio).isSelected(), "Grad School radio button should be unselected.");
    }

    private Wait<WebDriver> buildFluentWait(WebDriver driver) {
        Duration timeout = (Duration.ofSeconds(5));
        Duration polling = (Duration.ofMillis(300));

        return new FluentWait<>(driver)
                .withTimeout(timeout)
                .pollingEvery(polling)
                .ignoring(ElementNotInteractableException.class);
    }

    private void selectIfNotSelected(By locator) {
        Wait<WebDriver> wait = buildFluentWait(getDriver());
        wait.until(
                d -> {
                    findElement((locator));
                    if (!findElement((locator)).isSelected()) {
                        findElement((locator)).click();
                    }
                    return true;
                });
    }

    private void unselectIfSelected(By locator) {
        Wait<WebDriver> wait = buildFluentWait(getDriver());
        wait.until(
                d -> {
                    findElement((locator));
                    if (findElement((locator)).isSelected()) {
                        findElement((locator)).click();
                    }
                    return true;
                });
    }

    private WebElement findElement(By locator) {
        return getDriver().findElement((locator));
    }
}
