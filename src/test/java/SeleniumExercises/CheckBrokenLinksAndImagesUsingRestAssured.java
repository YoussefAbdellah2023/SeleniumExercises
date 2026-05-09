package SeleniumExercises;

import BaseTest.BaseTest;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class CheckBrokenLinksAndImagesUsingRestAssured extends BaseTest {

    //Use RestAssured to check for broken links and images on a webpage.

    // Navigate to the https://the-internet.herokuapp.com/broken_images
    // Use RestAssured to send HTTP requests to the URLs of the images And Links on the page and verify that they return a 200 status code (indicating that the image And Links are not broken ).
    // Print out any URLs that return a status code other than 200, along with the status code received.

    By brokenLink = By.cssSelector("[target=\"_blank\"]");
    By brokenLinks = By.xpath("//a[@href]");

    By brokenImage = By.cssSelector("[src=\"hjkl.jpg\"]");
    By brokenImages = By.xpath("//div[@class='example']//img");


    @Test
    public void checkBrokenLink() {
        getDriver().navigate().to("https://the-internet.herokuapp.com/broken_images");
        String href = findElement(brokenLink).getDomProperty("href");

        try {
            URL url = new URI(href).toURL();
            Response response = io.restassured.RestAssured.get(url);
            System.out.println(response.getStatusLine());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Test
    public void checkAllBrokenLinks() {
        getDriver().navigate().to("https://the-internet.herokuapp.com/broken_images");
        List<WebElement> links = findElements(brokenLinks);

        for (WebElement link : links) {
            String href = link.getDomProperty("href");

            try {
                URL url = new URI(href).toURL();
                Response response = io.restassured.RestAssured.get(url);
                System.out.println(href + " - " + response.getStatusLine());
            } catch (MalformedURLException e) {
                System.out.println(href + " - Malformed URL: " + e.getMessage());
            } catch (Exception e) {
                System.out.println(href + " - Error: " + e.getMessage());
            }
        }
    }


    @Test
    public void checkBrokenImage() {
        getDriver().navigate().to("https://the-internet.herokuapp.com/broken_images");
        String src = findElement(brokenImage).getDomProperty("src");

        try {
            URL url = new URI(src).toURL();
            Response response = io.restassured.RestAssured.get(url);
            System.out.println(response.getStatusLine());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Test
    public void checkAllBrokenImages() {
        getDriver().navigate().to("https://the-internet.herokuapp.com/broken_images");
        List<WebElement> images = findElements(brokenImages);

        for (WebElement image : images) {
            String src = image.getDomProperty("src");

            try {
                URL url = new URI(src).toURL();
                Response response = io.restassured.RestAssured.get(url);
                System.out.println(src + " - " + response.getStatusLine());
            } catch (MalformedURLException e) {
                System.out.println(src + " - Malformed URL: " + e.getMessage());
            } catch (Exception e) {
                System.out.println(src + " - Error: " + e.getMessage());
            }
        }
    }


    public WebElement findElement(By locator) {
        return buildFluentWait()
                .until(
                        d -> {
                            return d.findElement(locator);
                        });
    }

    // Method FindElements
    public List<WebElement> findElements(By locator) {
        return buildFluentWait()
                .until(
                        d -> {
                            return d.findElements(locator);
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
