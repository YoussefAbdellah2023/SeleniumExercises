package BaseTest;

import SeleniumExercises.ScreenShots;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @BeforeMethod
    public void setUp() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
//        options.addArguments("--headless=new");
//        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popups");
//        options.setBrowserVersion("latest");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new EdgeDriver(options);

    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {

            ScreenShots screenShots = new ScreenShots();
            screenShots.setDriver(driver);
            screenShots.saveScreenshotAs2(result.getName() + ".png");
        } else {
            ScreenShots screenShots = new ScreenShots();
            screenShots.setDriver(driver);
            screenShots.saveScreenshotAs2(result.getName() + ".png");
        }

        driver.quit();

    }
}
