package _03_CSSSelector;

import Utility.BaseDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class _03_Assignment extends BaseDriver {
    @Test
    public void test1(){
        driver.get("https://www.snapdeal.com/");

        WebElement searchBox = driver.findElement(By.cssSelector("[id='inputValEnter']"));
        searchBox.sendKeys("teddy bear");

        WebElement searchButton = driver.findElement(By.cssSelector("button[class='searchformButton col-xs-4 rippleGrey']"));
        searchButton.click();

        WebElement text = driver.findElement(By.cssSelector("[id='searchMessageContainer']"));

        Assert.assertTrue("Text don't match",text.getText().equals("We've got 273 results for teddy bear"));

        WaitClose();
    }
}
