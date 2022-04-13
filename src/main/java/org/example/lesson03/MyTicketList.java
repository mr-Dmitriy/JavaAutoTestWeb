package org.example.lesson03;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class MyTicketList {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        //options.addArguments("--headless");
        options.addArguments("start-maximized");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://www.rzd.ru");

        Actions action = new Actions(driver);

        WebElement webElement1 = driver.findElement(By.cssSelector(".red-bottom-link:nth-child(1)"));
        action.moveToElement(webElement1)
              .click()
              .perform();

        WebElement webElement2 = driver.findElement(By.name("j_username"));
        action.moveToElement(webElement2)
              .click()
              .sendKeys("DemoUserSt")
              .perform();

        WebElement webElement3 = driver.findElement(By.name("j_password"));
        action.moveToElement(webElement3)
              .click()
              .sendKeys("123456dD?")
              .perform();

        WebElement webElement4 = driver.findElement(By.cssSelector(".login-input_wrap"));
        action.moveToElement(webElement4)
              .click()
              .perform();

        Thread.sleep(10000l);

        WebElement webElement5 = driver.findElement(By.cssSelector(".block:nth-child(1) > .third-block_content use"));
        action.moveToElement(webElement5)
              .click()
              .perform();

        Thread.sleep(2000l);
        driver.quit();
    }
}
