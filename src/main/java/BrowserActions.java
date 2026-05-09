import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.edge.EdgeDriver;

public class BrowserActions {

    WebDriver driver;

    
    public void testCase() {
        driver = new EdgeDriver();
        navigateTo("https://www.google.com");
        navigateTo("https://www.facebook.com");
        navigateBack();
        navigateForward();
        refreshPage();
        setWindowSize(800, 600);
        setWindowPosition(100, 100);
        getWindowSize();
        getWindowPosition();
        System.out.println("Current URL: " + getCurrentUrl());
        System.out.println("Page Title: " + getPageTitle());
        System.out.println("Page Source: " + getPageSource());

        getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
//        closeBrowser();
        quitBrowser();
    }

    // TODO: Selenium Navigation methods for browser actions

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public void navigateForward() {
        driver.navigate().forward();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    // TODO: Selenium Manage methods for browser actions

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public void setWindowSize(int width, int height) {
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(width, height));
    }

    public void setWindowPosition(int x, int y) {
        driver.manage().window().setPosition(new org.openqa.selenium.Point(x, y));
    }

    public void getWindowSize() {
        org.openqa.selenium.Dimension size = driver.manage().window().getSize();
        System.out.println("Window Size: " + size);
    }

    public void getWindowPosition() {
        org.openqa.selenium.Point position = driver.manage().window().getPosition();
        System.out.println("Window Position: " + position);
    }

    // TODO: Selenium Webpage Information methods for browser actions
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public java.util.Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public void closeBrowser() {
        driver.close();
    }

    public void quitBrowser() {
        driver.quit();
    }

}
