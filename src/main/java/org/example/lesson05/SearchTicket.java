package org.example.lesson05;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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


        Actions chooseCities = new Actions(driver);

            WebElement cityOfDeparture = driver.findElement(By.id("direction-from"));
            WebElement cityOfArrival = driver.findElement(By.id("direction-to"));

            chooseCities
                        .moveToElement(cityOfDeparture)
                        .click()
                        .sendKeys("Москва")
                        .moveToElement(cityOfArrival)
                        .click()
                        .sendKeys("Санкт-Петербург")
                        .build()
                        .perform();


        Actions chooseDates = new Actions(driver);

            WebElement dateOfDeparture = driver.findElement(By.id("datepicker-from"));
            chooseDates
                        .moveToElement(dateOfDeparture)
                        .click()
                        .click()
                        .perform();

            WebElement chooseDateOfDeparture = driver.findElement(By.xpath("//*[contains(@class,'rzd-datepicker-group-middle')]//tr[4]/td[2]"));
            chooseDates
                        .moveToElement(chooseDateOfDeparture)
                        .click()
                        .perform();

            WebElement dateBackOfDeparture = driver.findElement(By.id("datepicker-to"));
            chooseDates
                        .moveToElement(dateBackOfDeparture)
                        .click()
                        .build()
                        .perform();

            WebElement chooseDateBackOfDeparture = driver.findElement(By.xpath("//*[contains(@class,'rzd-datepicker-group-middle')]//tr[4]/td[5]"));
            chooseDates
                        .moveToElement(chooseDateBackOfDeparture)
                        .click()
                        .build()
                        .perform();


        WebElement buttonSearchTrain = driver.findElement(By.cssSelector(".rzd-button"));
        buttonSearchTrain.click();


        Thread.sleep(4000L);
        driver.quit();
    }

}
