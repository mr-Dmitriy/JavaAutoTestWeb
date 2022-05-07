package org.example.lesson06;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class CheckArrivingTrainPage extends AbstractPage{

    private final String LOCATOR_ButtonTomorrow = ".link-date:nth-child(2)";

    @FindBy(xpath = "//*[contains(@name,'st_name')]")
    private WebElement inputCity;

    @FindBy(xpath = "//*[contains(@class,'station-item') and text()='МОСКВА ЯРОСЛАВСКАЯ (ЯРОСЛАВСКИЙ ВОКЗАЛ)']")
    private WebElement chooseStation;

    @FindBy(xpath = "//*[contains(@class,'btn btn-default')]")
    private WebElement buttonCheck;

    @FindBy(css = ".tabs_mode:nth-child(2) > .text-bold")
    private WebElement buttonArrivingTrains;

    @FindBy(css = LOCATOR_ButtonTomorrow)
    private WebElement buttonTomorrow;

    public void clickButtonTomorrow() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30), Duration.ofSeconds(5));
        this.buttonTomorrow = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(LOCATOR_ButtonTomorrow)));
        buttonTomorrow.click();
    }

    public void checkArrivingTrainPage(String direction){

        Actions checkArrivingTrainPage = new Actions(getDriver());
        this.inputCity.click();
        this.inputCity.sendKeys(direction);
        checkArrivingTrainPage
                               .click(this.chooseStation)
                               .click(this.buttonCheck)
                               .click(this.buttonArrivingTrains)
                               .build()
                               .perform();

        clickButtonTomorrow();

    }

    public CheckArrivingTrainPage(WebDriver driver) {
        super(driver);
    }
}
