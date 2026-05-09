package SeleniumExercises;

import BaseTest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
public class HandlingDropDown extends BaseTest {

    private static final String DROPDOWN_URL = "https://the-internet.herokuapp.com/dropdown";
    private static final String DROPDOWN_URL_2 = "https://www.hdfc.bank.in/";



    By dropdown = By.id("dropdown");
    By discoverProductsButton = By.cssSelector("//*[@id='Discover_Products']");

    @Test
    public void selectOptionsByIndexAndVisibleText() {
        getDriver().navigate().to(DROPDOWN_URL);

        Select select = new Select(findElement(dropdown));

        // First option is the disabled "Please select an option"; index 1 = "Option 1", index 2 = "Option 2"
        select.selectByIndex(1);
        Assert.assertEquals(
                select.getFirstSelectedOption().getText(),
                "Option 1",
                "Selecting by index 1 should choose Option 1.");

        select.selectByVisibleText("Option 2");
        Assert.assertEquals(
                select.getFirstSelectedOption().getText(),
                "Option 2",
                "Selecting by visible text should choose Option 2.");
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
