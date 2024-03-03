package _01_DragDrop;

import Utility.BaseDriver;
import Utility.MyFunctions;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class _03_Assignment extends BaseDriver {
    @Test
    public void Test3(){
        driver.get("http://dhtmlgoodies.com/scripts/drag-drop-nodes-quiz/drag-drop-nodes-quiz.html");

        List<WebElement> cities = driver.findElements(By.xpath("//ul[@id='allItems']/li"));
        List<WebElement> countries = driver.findElements(By.xpath("//div[@id='dhtmlgoodies_mainContainer']//ul"));

        Actions driverAction = new Actions(driver);

        wait.until(ExpectedConditions.visibilityOfAllElements(cities));

        for(WebElement city : cities){
            for(WebElement country : countries){
                wait.until(ExpectedConditions.elementToBeClickable(city));
                Action dragAction = driverAction.clickAndHold(city).build();
                MyFunctions.wait(1);
                dragAction.perform();

                Action dropAction = driverAction.moveToElement(country).release().build();
                MyFunctions.wait(1);
                dropAction.perform();
                try{
                    driver.switchTo().alert().accept();
                } catch (NoAlertPresentException e){

                }
               wait.until(ExpectedConditions.elementToBeClickable(city));
               String groupId = city.getAttribute("groupid");
               String boxId = country.getAttribute("id");
               if(groupId.equals(boxId)){
                    List<WebElement> control = country.findElements(By.tagName("li"));
                    if(control.size() == 3)
                        countries.remove(country);
                    break;
                }
            }
        }
        WaitClose();
    }
}
