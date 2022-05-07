package org.example.lesson06;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class AbstractTest {

    static WebDriver driver;

    @BeforeAll
    static void setDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        //options.addArguments("--headless");
        options.addArguments("start-maximized");
        options.setPageLoadTimeout(Duration.ofSeconds(10));


        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @BeforeEach
    void initMainPage(){
        Assertions.assertDoesNotThrow( ()-> getDriver().navigate().to("https://www.rzd.ru"),
                "Страница не доступна");
        Assertions.assertTrue(true);


        AcceptCookiesInfo();
        new AccountPage(getDriver()).logOut();

    }

    public void AcceptCookiesInfo() {
        if (new CookiesPage(getDriver()).isDisplayedCookies()) {
            new CookiesPage(getDriver()).clickButtonAcceptCoockies();
        }
    }

    @AfterAll
    public static void exit(){

        if(driver !=null) driver.quit();
    }

    public WebDriver getDriver(){
        return this.driver;
    }
}
