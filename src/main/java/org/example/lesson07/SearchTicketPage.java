package org.example.lesson07;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchTicketPage extends AbstractPage {

    @FindBy(id = "direction-from")
    private WebElement cityOfDeparture;

    @FindBy(id = "direction-to")
    private WebElement cityOfArrival;

    @FindBy(id = "datepicker-from")
    private WebElement dateOfDeparture;

    @FindBy(xpath = "//*[contains(@class,'rzd-datepicker-group-middle')]//tr[4]/td[2]")
    private WebElement chooseDateOfDeparture;

    @FindBy(id = "datepicker-to")
    private WebElement dateBackOfDeparture;

    @FindBy(xpath = "//*[contains(@class,'rzd-datepicker-group-middle')]//tr[4]/td[5]")
    private WebElement chooseDateBackOfDeparture;

    @FindBy(css = ".rzd-button")
    private WebElement buttonSearchTrain;


    public void findTicket(String cityFrom, String cityTo){

        this.cityOfDeparture.click();
        this.cityOfDeparture.sendKeys(cityFrom);
        this.cityOfArrival.click();
        this.cityOfArrival.sendKeys(cityTo);
        this.dateOfDeparture.click();
        this.chooseDateOfDeparture.click();
        this.dateBackOfDeparture.click();
        this.chooseDateBackOfDeparture.click();
        this.buttonSearchTrain.click();
    }

    public SearchTicketPage(WebDriver driver) {
        super(driver);
    }



}
