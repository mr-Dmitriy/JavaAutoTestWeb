package org.example.lesson05;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

        WebElement buttonLogInMyTickets = driver.findElement(By.cssSelector(".red-bottom-link:nth-child(1)"));
        buttonLogInMyTickets.click();

        Actions logIn = new Actions(driver);

        WebElement userLogin = driver.findElement(By.name("j_username"));
        WebElement UserPassword = driver.findElement(By.name("j_password"));
        WebElement buttonLogIn = driver.findElement(By.cssSelector(".login-input_wrap"));

        logIn
                .moveToElement(userLogin)
                .click()
                .sendKeys("DemoUserSt")
                .moveToElement(UserPassword)
                .click()
                .sendKeys("123456dD?")
                .moveToElement(buttonLogIn)
                .click()
                .build()
                .perform();

        Thread.sleep(10000L);

        WebElement myTickets = driver.findElement(By.cssSelector(".block:nth-child(1) > .third-block_content use"));
        myTickets.click();

        Thread.sleep(2000L);
        driver.quit();
    }
}
