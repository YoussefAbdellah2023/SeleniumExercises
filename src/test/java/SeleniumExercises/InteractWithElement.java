package SeleniumExercises;

import BaseTest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class InteractWithElement extends BaseTest {

    // upload file on both elements here: https://aa-practice-test-automation.vercel.app/Pages/uploadFile.html

    // automate form: https://www.dummyticket.com/dummy-ticket-for-visa-application/
    private static final String DUMMY_TICKET_URL = "https://www.dummyticket.com/dummy-ticket-for-visa-application/";
    By fileInput = By.id("fileInput");
    String userDirectory = System.getProperty("user.dir");
    String fileName = "test.txt";
    String filePath = userDirectory + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + fileName;
    // 1. Choose ticket: Dummy ticket for Visa Application
    By ticketOptionVisa = By.xpath("//input[@value='549']");
    // 2. Passenger details
    By firstNameInput = By.id("travname");
    By lastNameInput = By.id("travlastname");
    // 3. Date of birth
    By dateOfBirthInput = By.id("dob");
    By selectDateOfBirth = By.xpath("//*[@id='ui-datepicker-div']//a[@data-date='28']");
    // 4. Sex
    By sexMale = By.id("sex_1");
    // 5. Travel details
    By travelType = By.id("traveltype_1");
    By fromCityInput = By.id("fromcity");
    By toCityInput = By.id("tocity");
    By departureDateInput = By.id("departon");
    // 6. Billing details
    By phone = By.id("billing_phone");
    By emailAddress = By.id("billing_email");
    By countryInput = By.id("select2-billing_country-container");
    By selectCountry = By.xpath("//li[text()='Egypt']");
    By streetAddress_1 = By.id("billing_address_1");
    By streetAddress_2 = By.id("billing_address_2");
    By cityInput = By.id("billing_city");
    By provinceInput = By.id("select2-billing_state-container");
    By selectProvince = By.xpath("//li[contains(@class,'select2-results__option') and contains(.,'Cairo')]");
    By zipCodeInput = By.id("billing_postcode");
    // 7. Payment details (card number/expiry/cvc are often inside separate iframes)
    By paymentIframe = By.cssSelector("iframe[title='Secure payment input frame']");
    By cardNumberInput = By.id("payment-numberInput");
    By expiryDateInput = By.id("payment-expiryInput");
    By cvvInput = By.id("payment-cvcInput");
    // 8. Submit button
    By submitButton = By.id("place_order");

    @Test
    public void uploadFile() {
        getDriver().navigate().to("https://aa-practice-test-automation.vercel.app/Pages/uploadFile.html");
        System.out.println("User directory: " + userDirectory);
        findElement(fileInput).sendKeys(filePath);
    }

    public WebElement findElement(By locator) {

        return buildFluentWait().until(
                d -> d.findElement(locator));
    }

    @Test
    public void automateForm() {
        getDriver().navigate().to(DUMMY_TICKET_URL);
        findElement(ticketOptionVisa).click();
        findElement(firstNameInput).sendKeys("John");
        findElement(lastNameInput).sendKeys("Doe");
        findElement(dateOfBirthInput).click();
        findElement(selectDateOfBirth).click();
        findElement(sexMale).click();
        findElement(travelType).click();
        findElement(fromCityInput).sendKeys("London");
        findElement(toCityInput).sendKeys("Paris");
        findElement(departureDateInput).sendKeys("25/12/2025");
        findElement(phone).sendKeys("1234567890");
        findElement(emailAddress).sendKeys("john.doe@example.com");
        findElement(countryInput).click();
        findElement(selectCountry).click();
        findElement(streetAddress_1).sendKeys("123 Main Street");
        findElement(streetAddress_2).sendKeys("123 Main Street");
        findElement(cityInput).sendKeys("New York");
        findElement(provinceInput).click();
        findElement(selectProvince).click();
        findElement(zipCodeInput).sendKeys("10001");
        fillPaymentField("1234567890123456", "12/25", "123");
        findElement(submitButton).click();
    }

    /**
     * Switch to payment iframe by name or id, fill fields inside that frame, then return to default content.
     * Note: Stripe often uses separate iframes per field; if only one field appears per frame, call this once per iframe.
     */
    private void fillPaymentField(String cardNumber, String expiryDate, String cvv) {


        getDriver().switchTo().frame(findElement(paymentIframe));
        findElement(cardNumberInput).sendKeys(cardNumber);
        findElement(expiryDateInput).sendKeys(expiryDate);
        findElement(cvvInput).sendKeys(cvv);

        getDriver().switchTo().defaultContent();
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
