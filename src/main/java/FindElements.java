import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class FindElements {
    WebDriver driver;

    public void testCase() {
        driver = new EdgeDriver();
        driver.get("https://www.google.com");
     
    }


    // TODO: Find element by id, name, class, tag, link text, partial link text



    public void findElementById() {
        // TODO: Find element by id DOM --> ##inputUsername
        driver.findElement(By.id("inputUsername"));
    }

    public void findElementByName() {
        // TODO: Find element by name DOM --> [name="user"]
        driver.findElement(By.name("user"));
    }

    public void findElementByClassName() {
        // TODO: Find element by class name DOM --> .form-control
        driver.findElement(By.className("form-control"));
    }

    public void findElementByTagName() {
        // TODO: Find element by tag name DOM --> //input
        driver.findElement(By.tagName("input"));
    }

    public void findElementByLinkText() {
        // TODO: Find element by link text DOM --> [href="/login"]
        driver.findElement(By.linkText("Login"));
    }

    public void findElementByPartialLinkText() {
        // TODO: Find element by partial link text DOM --> [href="/login"]
        driver.findElement(By.partialLinkText("Login"));
    }



    // TODO: Find element by css selector id, class, tag'


    public void findElementByCssSelectorID() {
        // TODO: Find element by css selector id DOM --> #inputUsername
        driver.findElement(By.cssSelector("#inputUsername"));
    }

    public void findElementByCssSelectorClass() {
        // TODO: Find element by css selector class DOM --> .form-control
        driver.findElement(By.cssSelector(".form-control"));
    }

    public void findElementByCssSelectorTag() {
        // TODO: Find element by css selector tag DOM --> input
        driver.findElement(By.cssSelector("input"));
    }




    // TODO: Find element by css selector attribute


    public void findElementByCssSelectorAttribute() {
        // TODO: Find element by css selector attribute DOM --> [attribute="value"]
        driver.findElement(By.cssSelector("[attribute='value']"));
    }

    public void findElementByCssSelectorAttributeStartsWith() {
        // TODO: Find element by css selector attribute starts with DOM --> [attribute^="value"]
        driver.findElement(By.cssSelector("[attribute^='value']"));
    }

    public void findElementByCssSelectorAttributeEndsWith() {
        // TODO: Find element by css selector attribute ends with DOM --> [attribute$="value"]
        driver.findElement(By.cssSelector("[attribute$='value']"));
    }

    public void findElementByCssSelectorAttributeContains() {
        // TODO: Find element by css selector attribute contains DOM --> [attribute*="value"]
        driver.findElement(By.cssSelector("[attribute*='value']"));
    }

    public void findElementByCssSelectorAttributeNot() {
        // TODO: Find element by css selector attribute not DOM --> [attribute!="value"]
        driver.findElement(By.cssSelector("[attribute!='value']"));
    }

    public void findElementByCssSelectorAttributeNotContains() {
        // TODO: Find element by css selector attribute not contains DOM --> [attribute!*="value"]
        driver.findElement(By.cssSelector("[attribute!*='value']"));
    }

    public void findElementByCssSelectorAttributeNotNot() {
        // TODO: Find element by css selector attribute not not DOM --> [attribute!="value"]
        driver.findElement(By.cssSelector("[attribute!='value']"));
    }

    public void findElementByCssSelectorAttributeNotNotStartsWith() {
        // TODO: Find element by css selector attribute not not starts with DOM --> [attribute!^="value"]
        driver.findElement(By.cssSelector("[attribute!^='value']"));
    }

    public void findElementByCssSelectorAttributeNotNotEndsWith() {
        // TODO: Find element by css selector attribute not not ends with DOM --> [attribute!$="value"]
        driver.findElement(By.cssSelector("[attribute!$='value']"));
    }



    // TODO: Find element by css selector combining attributes
    

    public void findElementByCssSelectorCombiningAttributes() {
        // TODO: Find element by css selector combining attributes Tag, class, id, and attribute DOM --> [tag][class][id][attribute="value"]
        driver.findElement(By.cssSelector("tag.class#id[attribute=value]"));    
    }





    // TODO: Find element by css selector with child & Parent


    public void findElementByCssSelectorWithChild() {
        // TODO: Find element by css selector with child DOM --> [tag][class][id][attribute="value"]
        driver.findElement(By.cssSelector("tag.class#id[attribute=value]"));    
    }
    public void findElementByCssSelectorWithChildAndChild() {
        // TODO: Find element by css selector with child and child DOM --> [tag][class][id][attribute="value"]
        driver.findElement(By.cssSelector("tag.class#id[attribute=value]"));    
    }
    public void findElementByCssSelectorWithParent() {
        // TODO: Find element by css selector with parent DOM --> [tag][class][id][attribute="value"]
        driver.findElement(By.cssSelector("parent>child"));    
    }
    public void findElementByCssSelectorWithParentAndParent() {
        // TODO: Find element by css selector with parent and parent DOM --> [tag][class][id][attribute="value"]
        driver.findElement(By.cssSelector("parent>child>parent"));    
    }
    public void findElementByCssSelectorWithParentAndParentAndParent() {
        // TODO: Find element by css selector with parent and parent and parent DOM --> [tag][class][id][attribute="value"]
        driver.findElement(By.cssSelector("parent>child>parent>child"));    
    }
    public void findElementByCssSelectorWithParentAndChild() {
        // TODO: Find element by css selector with parent and child DOM --> [tag][class][id][attribute="value"]
        driver.findElement(By.cssSelector("parent>child"));    
    }
    public void findElementByCssSelectorWithParentAndChildAndChild() {
        // TODO: Find element by css selector with parent and child and child DOM --> [tag][class][id][attribute="value"]
        driver.findElement(By.cssSelector("parent>child>child"));    
    }
}