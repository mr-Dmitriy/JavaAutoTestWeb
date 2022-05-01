package org.example.lesson05;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public abstract class AbstractTest {

    private static final Logger logger = LoggerFactory.getLogger(AbstractTest.class);

    static Integer myTestInt = 0;

    private static WebDriver driver;

    @BeforeAll
    static void init(){

        logger.info("Let's start the tests");

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        //options.addArguments("--headless");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @BeforeEach
    void goTo(){
        myTestInt++;
        System.out.println("Test №"+myTestInt);

        Assertions.assertDoesNotThrow( ()-> driver.navigate().to("https://www.rzd.ru"),
                "Страница не доступна");


        if (isDisplayed(By.xpath(".//button[@class='cookie-alert__btn btn-primary']"))) {
            getDriver().findElement(By.xpath(".//button[@class='cookie-alert__btn btn-primary']")).click();
        }
    }

    boolean isDisplayed(By locator) {
        try {
            return getDriver().findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    @AfterAll
    static void close() throws InterruptedException {

        logger.info("The end of tests");
        Thread.sleep(5000L);
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
