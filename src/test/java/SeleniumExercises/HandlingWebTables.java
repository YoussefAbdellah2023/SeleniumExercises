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
import java.util.List;

public class HandlingWebTables extends BaseTest {

    private static final String TABLES_URL = "https://www.w3schools.com/html/html_tables.asp";

    // W3Schools HTML Tables example (Company / Contact / Country)
    By customersTable = By.id("customers");

    @Test
    public void extractHeadersAndPrintEachRow() {
        getDriver().navigate().to(TABLES_URL);

        List<WebElement> headerCells = findElements(customersTable, By.xpath(".//tr[1]/th"));
        List<String> headers = headerCells.stream().map(WebElement::getText).map(String::trim).toList();
        System.out.println(String.join("\t", headers));

        List<WebElement> rows = findElements(customersTable, By.xpath(".//tr[position() > 1]"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.isEmpty()) {
                continue;
            }
            List<String> values = cells.stream().map(WebElement::getText).map(String::trim).toList();
            System.out.println(String.join("\t", values));
        }

        Assert.assertEquals(
                headers,
                List.of("Company", "Contact", "Country"),
                "Column headers should match the W3Schools example.");

        WebElement firstDataRow = rows.stream()
                .filter(r -> !r.findElements(By.tagName("td")).isEmpty())
                .findFirst()
                .orElseThrow(() -> new AssertionError("Expected at least one data row with <td> cells."));
        Assert.assertEquals(
                valuesForRow(firstDataRow),
                List.of("Alfreds Futterkiste", "Maria Anders", "Germany"),
                "First data row should match the W3Schools example.");
    }

    private List<String> valuesForRow(WebElement row) {
        List<WebElement> cells = findElements(row, By.tagName("td"));
        return cells.stream().map(WebElement::getText).map(String::trim).toList();
    }

    public WebElement findElement(By locator) {
        return buildFluentWait()
                .until(
                        d -> {
                            return d.findElement(locator);
                        });
    }

    /** FluentWait until at least one match in the current document. */
    public List<WebElement> findElements(By locator) {
        return buildFluentWait()
                .until(
                        d -> {
                            List<WebElement> found = d.findElements(locator);
                            return found.isEmpty() ? null : found;
                        });
    }

    /** FluentWait: resolve {@code rootLocator} each poll, then wait until descendant matches are non-empty. */
    public List<WebElement> findElements(By rootLocator, By descendantLocator) {
        return buildFluentWait()
                .until(
                        d -> {
                            WebElement root = d.findElement(rootLocator);
                            List<WebElement> found = root.findElements(descendantLocator);
                            return found.isEmpty() ? null : found;
                        });
    }

    /** FluentWait until {@code context} has at least one match for {@code locator}. */
    public List<WebElement> findElements(WebElement context, By locator) {
        return buildFluentWait()
                .until(
                        ignored -> {
                            List<WebElement> found = context.findElements(locator);
                            return found.isEmpty() ? null : found;
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
