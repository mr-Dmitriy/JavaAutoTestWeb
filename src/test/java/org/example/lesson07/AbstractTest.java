package org.example.lesson07;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.time.Duration;
import java.util.List;

public class AbstractTest {

    static EventFiringWebDriver eventDriver;

    @BeforeAll
    static void setDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
       // options.addArguments("--headless");
        options.addArguments("start-maximized");
        options.setPageLoadTimeout(Duration.ofSeconds(10));

        eventDriver = new EventFiringWebDriver(new ChromeDriver(options));
        eventDriver.register(new MyWebDriverEventListener());

        eventDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @BeforeEach
    void initMainPage(){
        Assertions.assertDoesNotThrow( ()-> getDriver().navigate().to("https://www.rzd.ru"),
                "Страница не доступна");
        Assertions.assertTrue(true);

        AcceptCookiesInfo();
        new AccountPage(getDriver()).logOut();

        try {
            getDriver().findElement(By.cssSelector(".header_actions-item:nth-child(4) .j-menu-open use"));
        } catch (NoSuchElementException e){
            MyUtils.makeScreenshot(getDriver(),
                    "fail-with-the-test-" + System.currentTimeMillis() + ".png");
        }

    }

    @AfterEach
    public void checkBrowser(){
        List<LogEntry> allLogRows = getDriver().manage().logs().get(LogType.BROWSER).getAll();
        if(!allLogRows.isEmpty()){
            if (allLogRows.size() > 0 ) {
                allLogRows.forEach(logEntry -> {
                    System.out.println(logEntry.getMessage());
                });
            }
        }
    }

    public void AcceptCookiesInfo() {
        if (new CookiesPage(getDriver()).isDisplayedCookies()) {
            new CookiesPage(getDriver()).clickButtonAcceptCoockies();
        }
    }

    @AfterAll
    public static void exit(){

        if(eventDriver !=null) eventDriver.quit();
    }

    public WebDriver getDriver(){
        return this.eventDriver;
    }
}
