package org.example.lesson05;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CheckArrivingTrain {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        //options.addArguments("--headless");
        options.addArguments("start-maximized");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://www.rzd.ru");

        Thread.sleep(2000l);

        Set<String> firstHandles = driver.getWindowHandles();

        Actions action = new Actions(driver);

        WebElement services = driver.findElement(By.cssSelector(".header_actions-item:nth-child(4) .j-menu-open use"));
        services.click();

        WebElement onlineScoreboardAtStations = driver.findElement(By.cssSelector(".menu-list:nth-child(2) li:nth-child(16) .menu-list-item_link"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", onlineScoreboardAtStations);
        onlineScoreboardAtStations.click();

        Thread.sleep(3000l);

        Set<String> secondHandles = driver.getWindowHandles();
        for (String handle : secondHandles) {

            if (!firstHandles.contains(handle)) {
                driver.switchTo().window(handle);
            }
        }

        WebElement chooseCity = driver.findElement(By.xpath("//*[contains(@name,'st_name')]"));
        action.moveToElement(chooseCity)
                .sendKeys("Москва")
                .build()
                .perform();


        WebElement chooseStation = driver.findElement(By.xpath("//*[contains(@class,'station-item') and text()=\"МОСКВА ЯРОСЛАВСКАЯ (ЯРОСЛАВСКИЙ ВОКЗАЛ)\"]"));
        chooseStation.click();

        WebElement buttonCheck = driver.findElement(By.xpath("//*[contains(@class,'btn btn-default')]"));
        buttonCheck.click();

        WebElement buttonArrivingTrains = driver.findElement(By.cssSelector(".tabs_mode:nth-child(2) > .text-bold"));
        buttonArrivingTrains.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30), Duration.ofSeconds(5));
        WebElement buttonTomorrow = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".link-date:nth-child(2)")));
        buttonTomorrow.click();


        Thread.sleep(10000l);

        driver.quit();

    }

}
