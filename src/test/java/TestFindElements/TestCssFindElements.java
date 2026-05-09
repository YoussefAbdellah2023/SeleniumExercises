package TestFindElements;

import BaseTest.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TestCssFindElements extends BaseTest {

    @Test
    public void CSSUsingId () {
        getDriver().navigate().to("https://www.saucedemo.com/");
        getDriver().findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        getDriver().findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        getDriver().findElement(By.cssSelector("#login-button")).click();   
    }
    @Test
    public void CSSUsingClassName () {
        getDriver().navigate().to("https://www.saucedemo.com/");
        getDriver().findElement(By.cssSelector(".form_input")).sendKeys("standard_user");
        getDriver().findElement(By.cssSelector(".form_input")).sendKeys("secret_sauce");
        getDriver().findElement(By.cssSelector(".btn_action")).click();
    }
    @Test
    public void CSSUsingattribute () {
        getDriver().navigate().to("https://www.saucedemo.com/");
        getDriver().findElement(By.cssSelector("input[id='user-name']")).sendKeys("standard_user");
        getDriver().findElement(By.cssSelector("input[id='password']")).sendKeys("secret_sauce");
        getDriver().findElement(By.cssSelector("input[id='login-button']")).click();
    }
    @Test
    public void CssUsingCombination1 () {
        getDriver().navigate().to("https://www.saucedemo.com/");
        getDriver().findElement(By.cssSelector("input[id='user-name'][name='user-name']")).sendKeys("standard_user");
        getDriver().findElement(By.cssSelector("input[id='password'][name='password']")).sendKeys("secret_sauce");
        getDriver().findElement(By.cssSelector("input[id='login-button'][name='login-button']")).click();
    }
    @Test
    public void CssUsingCombination2 () {
        getDriver().navigate().to("https://www.saucedemo.com/");
        getDriver().findElement(By.cssSelector("input.form_input#user-name[name=user-name]")).sendKeys("standard_user");
        getDriver().findElement(By.cssSelector("div.form_group>input.form_input#password[name=password]")).sendKeys("secret_sauce");
        getDriver().findElement(By.cssSelector("input.btn_action#login-button[value=Login]")).click();
    }
}
