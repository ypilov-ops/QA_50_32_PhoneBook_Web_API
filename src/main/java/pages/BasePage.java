package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasePage {
    static WebDriver driver;

    public static void setDriver(WebDriver wd){
        driver = wd;
    }

    public void pause (int time){
        try {
            Thread.sleep(time*1000L);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    public boolean isTextInElementPresent(WebElement element, String text){
        return element.getText().contains(text);

    }
}
