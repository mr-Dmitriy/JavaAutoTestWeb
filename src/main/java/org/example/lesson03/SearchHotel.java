package org.example.lesson03;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;


public class SearchHotel {


    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        //options.addArguments("--headless");
        options.addArguments("start-maximized");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.rzd.ru");


        WebElement drag = driver.findElement(By.cssSelector(".block-img-1060 .headerlink_text"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", drag);
        drag.click();

        Thread.sleep(5000l);

        Actions action = new Actions(driver);

        WebElement webElement2 = driver.findElement(By.xpath("//input[starts-with(@id,'mui-')]"));
        action.moveToElement(webElement2)
              .click()
              .sendKeys("Москва")
              .perform();

        WebElement webElement3 = driver.findElement(By.xpath("//input[contains(@aria-controls,'-popup')]"));
        action.moveToElement(webElement3)
              .click()
              .perform();

        WebElement webElement4 = driver.findElement(By.xpath("//span[contains(.,'Москва')]"));
        action.moveToElement(webElement4)
              .click()
              .perform();

        WebElement webElement5 = driver.findElement(By.xpath("//button//*[contains(@class,'jss') and contains(@class,'jss')][1]/*[contains(@class,'MuiFormControl-root')]"));
        action.moveToElement(webElement5)
              .click()
              .perform();

        WebElement we = driver.findElement(By.xpath("//button//*[contains(@class,'jss') and contains(@class,'jss')]/div[1]/div"));
        action.moveToElement(we)
              .moveToElement(driver.findElement(By.xpath("//*[contains(@class,'Root')][2]//*[contains(@class,'week')][2]/div[2]")))
              .click()
              .moveToElement(driver.findElement(By.xpath("//*[contains(@class,'Root')][2]//*[contains(@class,'week')][2]/div[5]")))
              .click()
              .build()
              .perform();

        Thread.sleep(2000L);

        WebElement webElement9 = driver.findElement(By.xpath("//span[contains(@class,'MuiButton-label')]"));
        action.moveToElement(webElement9)
              .click()
              .build()
              .perform();

        Thread.sleep(2000l);

        driver.quit();
    }
}
