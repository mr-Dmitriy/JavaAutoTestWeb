package org.example.lesson05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RzdTest extends AbstractTest{

    @Test
    @Tag("Positive")
    @DisplayName("Successful authorization")
    public void authorizationTest() throws InterruptedException {

        Thread.sleep(2000L);

        if (isDisplayed(By.xpath("//a[@class='j-profile-logout']"))) {
            WebElement logOut = getDriver().findElement(By.xpath("//a[@class='j-profile-logout']"));
            logOut.click();
        }

        WebElement logInAccount = getDriver().findElement(By.cssSelector(".username > span"));
        logInAccount.click();

        Assertions.assertTrue(getDriver().findElement(By.xpath(".//div[@class='login-form-shade j-auth j-auth-wrap']"))
                                         .isDisplayed(), "Menu for authorization doesn't work");

        Actions logIn = new Actions(getDriver());

        WebElement userLogin = getDriver().findElement(By.name("j_username"));
        WebElement UserPassword = getDriver().findElement(By.name("j_password"));
        WebElement buttonLogIn = getDriver().findElement(By.cssSelector(".login-input_wrap"));

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


        Thread.sleep(2000L);
        Cookie afterLogin = getDriver().manage()
                                       .getCookieNamed("LtpaToken2");
        assertThat(afterLogin.getValue(), is(not(nullValue())));


        WebElement userProfile = getDriver().findElement(By.xpath(".//*[contains(@class,'j-profile-links-loggedin')]" +
                                                                  "/a[@class='j-profile-username']"));
        userProfile.click();

        assertEquals("Мой профиль", getDriver().findElement(By.xpath(".//p[@class='page-menu__title']"))
                                                       .getText());

    }

    @Test
    @Tag("Positive")
    @DisplayName("Successful change first and last name in profile")
    public void changeFirstAndLastNameTest() throws InterruptedException {

        Thread.sleep(2000L);

        if (isDisplayed(By.xpath("//a[@class='j-profile-logout']"))) {
            WebElement logOut = getDriver().findElement(By.xpath("//a[@class='j-profile-logout']"));
            logOut.click();
        }

        WebElement logInAccount = getDriver().findElement(By.cssSelector(".username > span"));
        logInAccount.click();

        Assertions.assertTrue(getDriver().findElement(By.xpath(".//div[@class='login-form-shade j-auth j-auth-wrap']"))
                                         .isDisplayed(), "Menu for authorization doesn't work");


        Actions logIn = new Actions(getDriver());

        WebElement userLogin = getDriver().findElement(By.name("j_username"));
        WebElement UserPassword = getDriver().findElement(By.name("j_password"));
        WebElement buttonLogIn = getDriver().findElement(By.cssSelector(".login-input_wrap"));

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

        Thread.sleep(2000L);
        Cookie afterLogin = getDriver().manage()
                                       .getCookieNamed("LtpaToken2");
        assertThat(afterLogin.getValue(), is(not(nullValue())));

        WebElement userProfile = getDriver().findElement(By.xpath(".//*[contains(@class,'j-profile-links-loggedin')]" +
                                                                  "/a[@class='j-profile-username']"));
        userProfile.click();

        assertEquals("Мой профиль", getDriver().findElement(By.xpath(".//p[@class='page-menu__title']")).getText());

        Thread.sleep(2000L);

        Actions changeDateInProfile = new Actions(getDriver());

        WebElement fieldUserLastName = getDriver().findElement(By.name("lastName"));
        fieldUserLastName.clear();
        WebElement fieldUserFirstName = getDriver().findElement(By.name("firstName"));
        fieldUserFirstName.clear();
        WebElement buttonSave = getDriver().findElement(By.cssSelector(".active .btn-primary"));

        changeDateInProfile
                .moveToElement(fieldUserLastName).click().sendKeys("newLastName")
                .moveToElement(fieldUserFirstName).click().sendKeys("newFirstName")
                .moveToElement(buttonSave).click()
                .build()
                .perform();

        Assertions.assertTrue(getDriver().findElement(By.xpath(".//div[contains(@class, 'post-status__text')]"))
                                         .isDisplayed(), "There some problems with trying to change the data in profile");

        Assertions.assertEquals("newLastName", fieldUserLastName.getAttribute("value"),
                                "the expected LastName does not match the actual");
        Assertions.assertEquals("newFirstName", fieldUserFirstName.getAttribute("value"),
                                "the expected FirstName does not match the actual");


    }

    @Test
    @Tag("Positive")
    @DisplayName("Successful check arriving train")
    public void checkArrivingTrainTest() throws InterruptedException {
        Set<String> firstHandles = getDriver().getWindowHandles();

        Actions action = new Actions(getDriver());

        WebElement services = getDriver().findElement(By.cssSelector(".header_actions-item:nth-child(4) .j-menu-open use"));
        services.click();

        WebElement onlineScoreboardAtStations = getDriver().findElement(By.cssSelector(".menu-list:nth-child(2) li:nth-child(16) .menu-list-item_link"));
        ((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", onlineScoreboardAtStations);
        onlineScoreboardAtStations.click();

        Assertions.assertTrue(getDriver().findElement(By.cssSelector(".menu-list:nth-child(2) li:nth-child(16) .menu-list-item_link"))
                                         .isDisplayed(), "Searched service not found / Searched element not found");

        Thread.sleep(3000L);

        Set<String> secondHandles = getDriver().getWindowHandles();
        for (String handle : secondHandles) {

            if (!firstHandles.contains(handle)) {
                getDriver().switchTo().window(handle);
            }
        }

        WebElement chooseCity = getDriver().findElement(By.xpath("//*[contains(@name,'st_name')]"));
        action.moveToElement(chooseCity)
                .sendKeys("Москва")
                .build()
                .perform();

        Assertions.assertTrue(getDriver().findElement(By.xpath(".//li[@class='station-item' and @style='cursor: pointer;']"))
                .isDisplayed(), "There aren't anu cities with such name or that cities doesn't have train station");


        WebElement chooseStation = getDriver().findElement(By.xpath("//*[contains(@class,'station-item') and text()=" +
                                                                    "'МОСКВА ЯРОСЛАВСКАЯ (ЯРОСЛАВСКИЙ ВОКЗАЛ)']"));
        chooseStation.click();

        Assertions.assertEquals("МОСКВА ЯРОСЛАВСКАЯ (ЯРОСЛАВСКИЙ ВОКЗАЛ)", chooseCity.getAttribute("value"),
                                "You didn't choose the station ");


        WebElement buttonCheck = getDriver().findElement(By.xpath("//*[contains(@class,'btn btn-default')]"));
        buttonCheck.click();

        Thread.sleep(3000L);

        Assertions.assertTrue(getDriver().findElement(By.xpath(".//span[contains(@class,'train-num')]"))
                .isDisplayed(), "There aren't any trains or the site has some problems");

        WebElement buttonArrivingTrains = getDriver().findElement(By.cssSelector(".tabs_mode:nth-child(2) > .text-bold"));
        buttonArrivingTrains.click();

        Assertions.assertTrue(getDriver().findElement(By.xpath("//span[text()='Прибытие']/ancestor::div[contains(@class,'tabs_mode current text-bold')]"))
                .isDisplayed(), "There aren't any trains");

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30), Duration.ofSeconds(5));
        WebElement buttonTomorrow = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".link-date:nth-child(2)")));
        buttonTomorrow.click();

    }

    @Test
    @Tag("Positive")
    @DisplayName("Successful test my ticket list")
    public void myTicketListTest() throws InterruptedException {
        WebElement buttonLogInMyTickets = getDriver().findElement(By.cssSelector(".red-bottom-link:nth-child(1)"));
        buttonLogInMyTickets.click();

        Assertions.assertTrue(getDriver().findElement(By.xpath(".//div[@class='login-form-shade j-auth j-auth-wrap']"))
                .isDisplayed(), "Menu for authorization doesn't work");

        Actions logIn = new Actions(getDriver());

        WebElement userLogin = getDriver().findElement(By.name("j_username"));
        WebElement UserPassword = getDriver().findElement(By.name("j_password"));
        WebElement buttonLogIn = getDriver().findElement(By.cssSelector(".login-input_wrap"));

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

        Thread.sleep(2000L);
        Cookie afterLogin = getDriver().manage()
                .getCookieNamed("LtpaToken2");
        assertThat(afterLogin.getValue(), is(not(nullValue())));
        assertEquals("АРХИВ ПОЕЗДОК", getDriver().findElement(By.xpath("//div[@class='third-block__line-separated" +
                                                                               " j-login']/a[@class='red-bottom-link']"))
                                                         .getText());

        Thread.sleep(10000L);

        WebElement myTickets = getDriver().findElement(By.cssSelector(".block:nth-child(1) > .third-block_content use"));
        myTickets.click();

        assertEquals("Мои заказы", getDriver().findElement(By.xpath("//span[text()='Мои заказы']")).getText());

    }

    @Test
    @Tag("Positive")
    @DisplayName("Successful hotel search")
    public void searchHotelTest() throws InterruptedException {
        WebElement hotelAndExcursionBookingService = getDriver().findElement(By.cssSelector(".block-img-1060 .headerlink_text"));
        ((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", hotelAndExcursionBookingService);
        hotelAndExcursionBookingService.click();

        Thread.sleep(5000L);


        Actions chooseDirectionOrHotel = new Actions(getDriver());
        WebElement inputHotelOrDestination = getDriver().findElement(By.xpath("//input[starts-with(@id,'mui-')]"));
        chooseDirectionOrHotel
                .moveToElement(inputHotelOrDestination)
                .click()
                .sendKeys("Москва")
                .build()
                .perform();

        Assertions.assertTrue(getDriver().findElement(By.xpath("//input[contains(@aria-controls,'-popup')]"))
                .isDisplayed(), "There aren't any trains or the site has some problems");


        WebElement popUpList = getDriver().findElement(By.xpath("//input[contains(@aria-controls,'-popup')]"));
        WebElement choiceOption = getDriver().findElement(By.xpath("//span[contains(.,'Москва')]"));
        chooseDirectionOrHotel
                .moveToElement(popUpList)
                .click()
                .moveToElement(choiceOption)
                .click()
                .build()
                .perform();


        Actions chooseDate = new Actions(getDriver());
        WebElement dateOfDepartureAndReturn = getDriver().findElement(By.xpath("//button//*[contains(@class,'jss') and contains(@class,'jss')][1]/*[contains(@class,'MuiFormControl-root')]"));
        chooseDate
                .moveToElement(dateOfDepartureAndReturn)
                .click()
                .build()
                .perform();

        WebElement popupListOfDate = getDriver().findElement(By.xpath("//button//*[contains(@class,'jss') and contains(@class,'jss')]/div[1]/div"));
        WebElement dateFrom = getDriver().findElement(By.xpath("//*[contains(@class,'Root')][2]//*[contains(@class,'week')][2]/div[2]"));
        WebElement dateTill = getDriver().findElement(By.xpath("//*[contains(@class,'Root')][2]//*[contains(@class,'week')][2]/div[5]"));

        String checkDateF = dateFrom.getAttribute("value");
        String checkDateT = dateTill.getAttribute("value");


        chooseDate
                .moveToElement(popupListOfDate)
                .moveToElement(dateFrom)
                .click()
                .moveToElement(dateTill)
                .click()
                .build()
                .perform();


        Assertions.assertEquals(checkDateF, dateFrom.getAttribute("value"),
                               "Expected date does not match the actual one");
        Assertions.assertEquals(checkDateT, dateTill.getAttribute("value"),
                               "Expected date does not match the actual one");

        WebElement buttonSearchHotel = getDriver().findElement(By.xpath("//span[contains(@class,'MuiButton-label')]"));
        buttonSearchHotel.click();

        Thread.sleep(5000L);

        Assertions.assertTrue(getDriver().findElement(By.xpath("//div[@data-autotestid='SearchResultsCityName']"))
                .isDisplayed(), "The site page doesn't open");

    }

    @Test
    @Tag("Positive")
    @DisplayName("Successful ticket search")
    public void searchTicketTest() throws InterruptedException {
        Actions chooseCities = new Actions(getDriver());

        WebElement cityOfDeparture = getDriver().findElement(By.id("direction-from"));
        WebElement cityOfArrival = getDriver().findElement(By.id("direction-to"));

        chooseCities
                .moveToElement(cityOfDeparture)
                .click()
                .sendKeys("Москва")
                .moveToElement(cityOfArrival)
                .click()
                .sendKeys("Санкт-Петербург")
                .build()
                .perform();


        Actions chooseDates = new Actions(getDriver());

        WebElement dateOfDeparture = getDriver().findElement(By.id("datepicker-from"));
        chooseDates
                .moveToElement(dateOfDeparture)
                .click()
                .click()
                .perform();


        WebElement chooseDateOfDeparture = getDriver().findElement(By.xpath("//*[contains(@class,'rzd-datepicker-group-middle')]//tr[4]/td[2]"));
        chooseDates
                .moveToElement(chooseDateOfDeparture)
                .click()
                .perform();

        WebElement dateBackOfDeparture = getDriver().findElement(By.id("datepicker-to"));
        chooseDates
                .moveToElement(dateBackOfDeparture)
                .click()
                .build()
                .perform();

        WebElement chooseDateBackOfDeparture = getDriver().findElement(By.xpath("//*[contains(@class,'rzd-datepicker-group-middle')]//tr[4]/td[5]"));
        chooseDates
                .moveToElement(chooseDateBackOfDeparture)
                .click()
                .build()
                .perform();


        WebElement buttonSearchTrain = getDriver().findElement(By.cssSelector(".rzd-button"));
        buttonSearchTrain.click();

        Thread.sleep(5000L);

        Assertions.assertTrue(getDriver().findElement(By.xpath("//div[contains(@class,'searchresults__list')]"))
                .isDisplayed(), "The site page doesn't open");

    }


}
