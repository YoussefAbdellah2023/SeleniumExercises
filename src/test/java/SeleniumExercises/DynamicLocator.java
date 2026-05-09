package SeleniumExercises;

import BaseTest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;

import java.time.Duration;

public class DynamicLocator extends BaseTest {


    public void chooseCategory(String category) {
        new Actions(getDriver()).moveToElement
                        (findElement(By.xpath("//ul[@class='links']//li/a[.='" + category + "']")))
                .perform();
    }

    public void chooseSubCategory(String subCategory) {
        new Actions(getDriver()).moveToElement
                        (findElement(By.xpath("//ul[@class='links']//li/a[.='" + subCategory + "']")))
                .click()
                .perform();
    }

    @Test
    public void selectCategoryAndSubCategory() {
        getDriver().navigate().to("https://jqueryui.com/");
        chooseCategory("OpenJS Foundation");
        chooseSubCategory("Join");
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