import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenShot {
    WebDriver driver;
    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    String fileName;
    File des = new File("src/test/resources/" + fileName + ".png");

    public void saveScreenshotAs(String fileName) {
        try {
            FileUtils.copyFile(src, des);
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }
}
