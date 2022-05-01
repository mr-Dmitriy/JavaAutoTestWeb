package org.example.lesson05;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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


        WebElement hotelAndExcursionBookingService = driver.findElement(By.cssSelector(".block-img-1060 .headerlink_text"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", hotelAndExcursionBookingService);
        hotelAndExcursionBookingService.click();

        Thread.sleep(5000l);


        Actions chooseDirectionOrHotel = new Actions(driver);
            WebElement inputHotelOrDestination = driver.findElement(By.xpath("//input[starts-with(@id,'mui-')]"));
            chooseDirectionOrHotel
                                  .moveToElement(inputHotelOrDestination)
                                  .click()
                                  .sendKeys("Москва")
                                  .build()
                                  .perform();

            WebElement popUpList = driver.findElement(By.xpath("//input[contains(@aria-controls,'-popup')]"));
            WebElement choiceOption = driver.findElement(By.xpath("//span[contains(.,'Москва')]"));
            chooseDirectionOrHotel
                                  .moveToElement(popUpList)
                                  .click()
                                  .moveToElement(choiceOption)
                                  .click()
                                  .build()
                                  .perform();


        Actions chooseDate = new Actions(driver);
            WebElement dateOfDepartureAndReturn = driver.findElement(By.xpath("//button//*[contains(@class,'jss') and contains(@class,'jss')][1]/*[contains(@class,'MuiFormControl-root')]"));
            chooseDate
                      .moveToElement(dateOfDepartureAndReturn)
                      .click()
                      .build()
                      .perform();

            WebElement popupListOfDate = driver.findElement(By.xpath("//button//*[contains(@class,'jss') and contains(@class,'jss')]/div[1]/div"));
            WebElement dateFrom = driver.findElement(By.xpath("//*[contains(@class,'Root')][2]//*[contains(@class,'week')][2]/div[2]"));
            WebElement dateTill = driver.findElement(By.xpath("//*[contains(@class,'Root')][2]//*[contains(@class,'week')][2]/div[5]"));
            chooseDate
                      .moveToElement(popupListOfDate)
                      .moveToElement(dateFrom)
                      .click()
                      .moveToElement(dateTill)
                      .click()
                      .build()
                      .perform();


        WebElement buttonSearchHotel = driver.findElement(By.xpath("//span[contains(@class,'MuiButton-label')]"));
        buttonSearchHotel.click();

        Thread.sleep(3000l);

        driver.quit();
    }
}
