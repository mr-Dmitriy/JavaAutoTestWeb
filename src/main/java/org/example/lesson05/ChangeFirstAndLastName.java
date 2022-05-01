package org.example.lesson05;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class ChangeFirstAndLastName {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        //options.addArguments("--headless");
        options.addArguments("start-maximized");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://www.rzd.ru");

        WebElement logInAccount = driver.findElement(By.cssSelector(".username > span"));
        logInAccount.click();

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

        WebElement userProfile = driver.findElement(By.xpath(".//*[contains(@class,'j-profile-links-loggedin')]" +
                "/a[@class='j-profile-username']"));
        userProfile.click();

        Thread.sleep(2000l);

        Actions changeDateInProfile = new Actions(driver);

        WebElement fieldUserLastName = driver.findElement(By.name("lastName"));
        WebElement fieldUserFirstName = driver.findElement(By.name("firstName"));
        WebElement buttonSave = driver.findElement(By.cssSelector(".active .btn-primary"));

        changeDateInProfile
                .moveToElement(fieldUserLastName).click().sendKeys("LastName")
                .moveToElement(fieldUserFirstName).click().sendKeys("FirstName")
                .moveToElement(buttonSave).click()
                .build()
                .perform();


        Thread.sleep(2000l);

        driver.quit();

    }

}
