package _01_DragDrop;

import Utility.BaseDriver;
import Utility.MyFunctions;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class _01_Assignment extends BaseDriver {
    @Test
    public void test1(){
        driver.get("http://dhtmlgoodies.com/scripts/drag-drop-quiz/drag-drop-quiz-d2.html");

        List<WebElement> cities = driver.findElements(By.xpath("//div[@id='answerDiv']/div[@class='dragDropSmallBox']"));

        Actions driverAction = new Actions(driver);

        wait.until(ExpectedConditions.visibilityOfAllElements(cities));

        for (WebElement city : cities ){
            for (int i = 1; i < cities.size()+1; i++) {
                WebElement target = driver.findElement(By.xpath("//div[@id='questionDiv']/div[@class='destinationBox']["+i+"]"));
                if ( target.getText().isEmpty()){
                    Action dragAction = driverAction.clickAndHold(city).build();
                    MyFunctions.wait(1);
                    dragAction.perform();

                    Action dropAction = driverAction.moveToElement(target).release().build();
                    MyFunctions.wait(1);
                    dropAction.perform();

                    WebElement control = driver.findElement(By.xpath("//div[@id='questionDiv']/div[@class='destinationBox']["+i+"]/div"));
                    wait.until(ExpectedConditions.visibilityOfAllElements(control));
                    if (control.getAttribute("class").equals("correctAnswer"))
                        i = cities.size()+1;
                }
            }
        }
        WaitClose();
    }
}
