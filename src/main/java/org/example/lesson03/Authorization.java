package org.example.lesson03;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public class Authorization {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        //options.addArguments("--headless");
        options.addArguments("start-maximized");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://www.rzd.ru");

        WebElement webElement1 = driver.findElement(By.cssSelector(".username > span"));
        webElement1.click();

        WebElement webElement2 = driver.findElement(By.name("j_username"));
        webElement2.click();
        webElement2.sendKeys("DemoUserSt");

        WebElement webElement3 = driver.findElement(By.name("j_password"));
        webElement3.click();
        webElement3.sendKeys("123456dD?");

        WebElement webElement4 = driver.findElement(By.cssSelector(".login-input_wrap"));
        webElement4.click();

        WebElement webElement5 = driver.findElement(By.xpath(".//*[contains(@class,'j-profile-links-loggedin')]" +
                "/a[@class='j-profile-username']"));
        webElement5.click();

        Thread.sleep(2000l);

        driver.quit();

    }


}