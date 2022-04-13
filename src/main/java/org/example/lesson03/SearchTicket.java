package org.example.lesson03;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;

public class SearchTicket {


    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        //options.addArguments("--headless");
        options.addArguments("start-maximized");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://www.rzd.ru");


        Actions action = new Actions(driver);

        WebElement webElement1 = driver.findElement(By.id("direction-from"));
        action.moveToElement(webElement1).click().sendKeys("Москва").perform();

        WebElement webElement2 = driver.findElement(By.id("direction-to"));
        action.moveToElement(webElement2).click().sendKeys("Санкт-Петербург").perform();

        WebElement webElement3 = driver.findElement(By.id("datepicker-from"));
        action.moveToElement(webElement3).click().perform();

        WebElement webElement4 = driver.findElement(By.xpath("//*[contains(@class,'rzd-datepicker-group-middle')]//tr[4]/td[2]"));
        action.moveToElement(webElement4).click().perform();

        WebElement webElement5 = driver.findElement(By.id("datepicker-to"));
        action.moveToElement(webElement5).click().perform();

        WebElement webElement6 = driver.findElement(By.xpath("//*[contains(@class,'rzd-datepicker-group-middle')]//tr[4]/td[5]"));
        action.moveToElement(webElement6).click().perform();

        WebElement webElement7 = driver.findElement(By.linkText("2"));
        action.moveToElement(webElement7).click().perform();

        WebElement webElement8 = driver.findElement(By.cssSelector(".rzd-button"));
        action.moveToElement(webElement8).click().perform();

        Thread.sleep(2000l);
        driver.quit();
    }

}
