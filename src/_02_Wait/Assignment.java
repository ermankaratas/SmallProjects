package _02_Wait;

import Utility.BaseDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class Assignment extends BaseDriver {
    @Test
    public void Test1(){
        driver.get("https://testsheepnz.github.io/BasicCalculator.html");

        WebElement number1 = driver.findElement(By.name("number1"));
        WebElement number2 = driver.findElement(By.name("number2"));
        WebElement answer = driver.findElement(By.name("numberAnswer"));
        WebElement operation = driver.findElement(By.id("selectOperationDropdown"));
        WebElement calculate = driver.findElement(By.id("calculateButton"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");

        Select operationMenu = new Select(operation);
        for (int j =0; j < 5 ; j++) {

            number1.clear();
            number2.clear();

            int randomNumber1 = (int)(Math.random() * 100) + 1;
            int randomNumber2 = (int)(Math.random() * 100) + 1;
            number1.sendKeys(String.valueOf(randomNumber1));
            number2.sendKeys(String.valueOf(randomNumber2));

            for (int i = 0; i < 5; i++) {
                wait.until(ExpectedConditions.elementToBeClickable(operation));
                operationMenu.selectByValue(String.valueOf(i));
                calculate.click();
                wait.until(ExpectedConditions.visibilityOfAllElements(answer));
                String answerText = answer.getAttribute("value");
                double answerDouble = Double.parseDouble(answerText);

                switch (i) {
                    case (0):
                        double total = randomNumber1 + randomNumber2;
                        Assert.assertTrue("addition is wrong", answerDouble == total);
                        break;
                    case (1):
                        double sub = randomNumber1 - randomNumber2;
                        Assert.assertTrue("subtraction is wrong", answerDouble == sub);
                        break;
                    case (2):
                        double multiply = randomNumber1 * randomNumber2;
                        Assert.assertTrue("multiplication is wrong", answerDouble == multiply);
                        break;
                    case (3):
                        double divide = (double) randomNumber1 / (double) randomNumber2;
                        Assert.assertTrue("division is wrong", answerDouble == divide);
                        break;
                    case (4):
                        String randomNumber1Str = String.valueOf(randomNumber1);
                        String randomNumber2Str = String.valueOf(randomNumber2);
                        String conc = randomNumber1Str + randomNumber2Str;
                        Assert.assertTrue("concatenate is wrong", conc.equals(answerText));
                        break;
                }
            }
        }
        WaitClose();
    }
}
