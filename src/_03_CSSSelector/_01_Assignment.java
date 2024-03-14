package _03_CSSSelector;

import Utility.BaseDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class _01_Assignment extends BaseDriver {
    @Test
    public void test1(){
        driver.get("http://demoqa.com/text-box");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement fullName = driver.findElement(By.cssSelector("[id='userName']"));
        WebElement userEmail = driver.findElement(By.cssSelector("[id='userEmail']"));
        WebElement currentAddress = driver.findElement(By.cssSelector("[id='currentAddress']"));
        WebElement permanentAddress = driver.findElement(By.cssSelector("[id='permanentAddress']"));
        WebElement submitButton = driver.findElement(By.cssSelector("[id='submit']"));

        fullName.sendKeys("Automation");
        userEmail.sendKeys("Testing@gmail.com");
        currentAddress.sendKeys("Testing Current Address");
        permanentAddress.sendKeys("Testing Permanent Address");

        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        submitButton.click();

        WebElement name = driver.findElement(By.cssSelector("[id='name']"));
        WebElement email = driver.findElement(By.cssSelector("[id='email']"));

        Assert.assertTrue("User name is false",name.getText().split(":")[1].equals("Automation"));
        Assert.assertTrue("Email is false",email.getText().split(":")[1].equals("Testing@gmail.com"));

        WaitClose();
    }
}
