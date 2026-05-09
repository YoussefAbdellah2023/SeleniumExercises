package SeleniumExercises;

import BaseTest.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ShadowDom extends BaseTest {

    private static final String BOOKS_URL = "https://books-pwakit.appspot.com/";
    private static final String HEROKU_SHADOW_DOM_URL = "https://the-internet.herokuapp.com/shadowdom";
    private static final String WATIR_SHADOW_DOM_URL = "http://watir.com/examples/shadow_dom.html";

    // Locators — same style as {@link InteractWithElement} (instance {@code By} fields)

    // Books PWA (light DOM + shadow traversal)
    By bookApp = By.cssSelector("book-app");
    By directSearchInput = By.cssSelector("input#input, input[type='search']");
    By appHeader = By.cssSelector("app-header");
    By bookInputDecorator = By.cssSelector("book-input-decorator");
    By booksSearchInput = By.cssSelector("#input");

    // Heroku shadow DOM demo
    By herokuMyParagraph = By.cssSelector("my-paragraph");
    By herokuShadowParagraph = By.cssSelector("p");

    // Watir shadow DOM demo
    By watirShadowHost = By.id("shadow_host");
    By watirTextInput = By.cssSelector("input[type='text']");
    By watirCheckbox = By.cssSelector("input[type='checkbox']");
    By watirNestedHost = By.id("nested_shadow_host");
    By watirNestedText = By.cssSelector("#nested_shadow_content div");


    private static WebElement findInShadowRoot(WebElement shadowHost, By locator) {
        return shadowHost.getShadowRoot().findElement(locator);
    }

    @Test
    public void interactWithBooksShadowDomInput() {
        getDriver().navigate().to(BOOKS_URL);

        WebElement searchInput = waitForBooksSearchInput();

        String searchText = "Selenium";
        searchInput.clear();
        searchInput.sendKeys(searchText);

        Assert.assertEquals(searchInput.getAttribute("value"), searchText, "Books search input should contain typed text.");
    }

    @Test
    public void readShadowDomTextOnHerokuPage() {
        getDriver().navigate().to(HEROKU_SHADOW_DOM_URL);

        List<WebElement> shadowHosts = findElements(herokuMyParagraph, 2);
        Assert.assertTrue(shadowHosts.size() >= 2, "Expected at least two shadow hosts on the Heroku page.");

        String firstShadowText = findInShadowRoot(shadowHosts.get(0), herokuShadowParagraph).getText();
        String secondShadowText = findInShadowRoot(shadowHosts.get(1), herokuShadowParagraph).getText();

        Assert.assertTrue(firstShadowText.contains("different text"), "First shadow paragraph text should be visible.");
        Assert.assertTrue(secondShadowText.contains("different text"), "Second shadow paragraph text should be visible.");
    }


    @Test
    public void interactWithWatirShadowDomInputs() {
        getDriver().navigate().to(WATIR_SHADOW_DOM_URL);

        WebElement shadowHost = findElement(watirShadowHost);

        WebElement textInput = findInShadowRoot(shadowHost, watirTextInput);
        WebElement checkboxInput = findInShadowRoot(shadowHost, watirCheckbox);
        WebElement nestedHost = findInShadowRoot(shadowHost, watirNestedHost);
        WebElement nestedText = findInShadowRoot(nestedHost, watirNestedText);

        String typedText = "Hello Shadow DOM";
        textInput.sendKeys(typedText);
        if (!checkboxInput.isSelected()) {
            checkboxInput.click();
        }

        Assert.assertEquals(textInput.getAttribute("value"), typedText, "Shadow text input should contain typed text.");
        Assert.assertTrue(checkboxInput.isSelected(), "Shadow checkbox should be selected.");
        Assert.assertEquals(nestedText.getText(), "nested text", "Nested shadow text should match expected content.");
    }

    public WebElement findElement(By locator) {
        return buildFluentWait().until(d -> d.findElement(locator));
    }

    public List<WebElement> findElements(By locator, int minimumCount) {
        if (minimumCount < 1) {
            throw new IllegalArgumentException("minimumCount must be >= 1");
        }
        return buildFluentWait()
                .until(
                        d -> {
                            List<WebElement> found = d.findElements(locator);
                            return found.size() >= minimumCount ? found : null;
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

    @SuppressWarnings("null")
    private WebElement waitForBooksSearchInput() {
        return buildFluentWait()
                .until(
                        driver -> {
                            List<WebElement> directInputs = driver.findElements(directSearchInput);
                            if (!directInputs.isEmpty() && directInputs.get(0).isDisplayed()) {
                                return directInputs.get(0);
                            }

                            List<WebElement> bookAppHosts = driver.findElements(bookApp);
                            if (bookAppHosts.isEmpty()) {
                                return null;
                            }

                            WebElement bookAppHost = bookAppHosts.get(0);
                            try {
                                return bookAppHost
                                        .getShadowRoot()
                                        .findElement(appHeader)
                                        .getShadowRoot()
                                        .findElement(bookInputDecorator)
                                        .getShadowRoot()
                                        .findElement(booksSearchInput);
                            } catch (NoSuchElementException ignored) {
                                // try alternate structure
                            }

                            try {
                                return bookAppHost
                                        .getShadowRoot()
                                        .findElement(bookInputDecorator)
                                        .getShadowRoot()
                                        .findElement(booksSearchInput);
                            } catch (NoSuchElementException ignored) {
                                return null;
                            }
                        });
    }
}
