package org.example.lesson07;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;


@Story("Story of my leaning how to use allure")
public class RzdTest extends AbstractTest {

    @Test
    @Tag("Positive")
    @DisplayName("Successful authorization")
    @Link("https://www.rzd.ru")
    @Severity(SeverityLevel.CRITICAL)
    public void authorizationTest() throws InterruptedException {

        Thread.sleep(2000L);


        new AuthorizationPage(getDriver()).clickLogInAccount();

        Assertions.assertTrue(getDriver().findElement(By.xpath(".//div[@class='login-form-shade j-auth j-auth-wrap']"))
                .isDisplayed(), "Menu for authorization doesn't work");

        new AuthorizationPage(getDriver()).loginIn("DemoUserSt", "123456dD?");

        Thread.sleep(2000L);

        Cookie afterLogin = getDriver().manage()
                .getCookieNamed("LtpaToken2");
        assertThat(afterLogin.getValue(), is(not(nullValue())));

        MyUtils.makeScreenshot(getDriver(),"test-AuthorizationPage-" + System.currentTimeMillis() + ".png");

    }

    @Test
    @Tag("Positive")
    @DisplayName("Successful change first and last name in profile")
    @Link("https://www.rzd.ru")
    @Severity(SeverityLevel.CRITICAL)
    public void changeFirstAndLastNameTest() throws InterruptedException {

        Thread.sleep(2000L);

        new AuthorizationPage(getDriver()).clickLogInAccount();
        new AuthorizationPage(getDriver()).loginIn("DemoUserSt", "123456dD?");

        Thread.sleep(2000L);

        new ProfilePage(getDriver()).changeDateInProfile("Фамилия", "Имя");

        Assertions.assertTrue(getDriver().findElement(By.xpath(".//div[contains(@class, 'post-status__text')]"))
                .isDisplayed(), "There some problems with trying to change the data in profile");

        Assertions.assertEquals("Фамилия", new ProfilePage(getDriver()).getFieldUserLastName().getAttribute("value"),
                "the expected LastName does not match the actual");
        Assertions.assertEquals("Имя", new ProfilePage(getDriver()).getFieldUserFirstName().getAttribute("value"),
                "the expected FirstName does not match the actual");

        MyUtils.makeScreenshot(getDriver(),"test-changeFirstAndLastName-ProfilePage-" + System.currentTimeMillis() + ".png");


    }

    @Test
    @Tag("Positive")
    @DisplayName("Successful check arriving train")
    @Link("https://www.rzd.ru")
    @Severity(SeverityLevel.CRITICAL)
    public void checkArrivingTrainTest() throws InterruptedException {

        new ServicesPage(getDriver()).openOnlineScoreboardAtStations();

        Thread.sleep(3000L);


        new CheckArrivingTrainPage(getDriver()).checkArrivingTrainPage("Москва");

        Thread.sleep(3000L);

        Assertions.assertTrue(getDriver().findElement(By.xpath("//span[text()='Прибытие']/ancestor::div[contains(@class,'tabs_mode current text-bold')]"))
                .isDisplayed(), "There aren't any trains");


        try {
            getDriver().findElement(By.xpath("//span[text()='Прибытие']/ancestor::div[contains(@class,'tabs_mode current text-bold')]"));
        } catch (NoSuchElementException e){
            MyUtils.makeScreenshot(getDriver(),
                    "fail-with-the-test-CheckArrivingTrainPage-" + System.currentTimeMillis() + ".png");
        }

    }

    @Test
    @Tag("Positive")
    @DisplayName("Successful test my ticket list")
    @Link("https://www.rzd.ru")
    @Severity(SeverityLevel.CRITICAL)
    public void myTicketListTest() throws InterruptedException {

        new MyTicketListPage(getDriver()).myTicketList("DemoUserSt", "123456dD?");

        Cookie afterLogin = getDriver().manage()
                .getCookieNamed("LtpaToken2");
        assertThat(afterLogin.getValue(), is(not(nullValue())));

        assertEquals("Мои заказы", getDriver().findElement(By.xpath("//span[text()='Мои заказы']")).getText());

        MyUtils.makeScreenshot(getDriver(),"test-MyTicketListPage-" + System.currentTimeMillis() + ".png");

    }

    @Test
    @Tag("Positive")
    @DisplayName("Successful hotel search")
    @Link("https://www.rzd.ru")
    @Severity(SeverityLevel.MINOR)
    public void searchHotelTest() throws InterruptedException {

        new SearchHotelPage(getDriver()).findHotel("Москва");

        Thread.sleep(5000L);

        Assertions.assertTrue(getDriver().findElement(By.xpath("//div[@data-autotestid='SearchResultsCityName']"))
                .isDisplayed(), "The site page doesn't open");

        MyUtils.makeScreenshot(getDriver(),"test-SearchHotelPage-" + System.currentTimeMillis() + ".png");


    }

    @Test
    @Tag("Positive")
    @DisplayName("Successful ticket search")
    @Link("https://www.rzd.ru")
    @Severity(SeverityLevel.CRITICAL)
    public void searchTicketTest() throws InterruptedException {

        new SearchTicketPage(getDriver()).findTicket("Москва","Санкт-Петербург");
        Thread.sleep(3000L);

        Thread.sleep(5000L);

        Assertions.assertTrue(getDriver().findElement(By.xpath("//div[contains(@class,'searchresults__list')]"))
                .isDisplayed(), "The site page doesn't open");

        MyUtils.makeScreenshot(getDriver(),"test-SearchTicketPage.png");

        MyUtils.makeScreenshot(getDriver(),"test-SearchTicketPage-" + System.currentTimeMillis() + ".png");

    }




}
