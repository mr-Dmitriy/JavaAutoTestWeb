package org.example.lesson06;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static org.bouncycastle.cms.RecipientId.password;

public class SearchHotelPage extends AbstractPage{


    @FindBy(css = ".block-img-1060 .headerlink_text")
    private WebElement hotelAndExcursionBookingService;

    @FindBy(xpath = "//input[starts-with(@id,'mui-')]")
    private WebElement inputHotelOrDestination;

    @FindBy(xpath = "//input[contains(@aria-controls,'-popup')]")
    private WebElement popUpList;

    @FindBy(xpath = "//span[contains(.,'Москва')]")
    private WebElement choiceOption;

    @FindBy(xpath = "//button//*[contains(@class,'jss') and contains(@class,'jss')][1]/*[contains(@class,'MuiFormControl-root')]")
    private WebElement dateOfDepartureAndReturn;

    @FindBy(xpath = "//button//*[contains(@class,'jss')][1]/div[1]/div[1]")
    private WebElement popupListOfDate;

    @FindBy(xpath = "//*[contains(@class,'Root')][2]//*[contains(@class,'week')][2]/div[2]")
    private WebElement dateFrom;

    @FindBy(xpath = "//*[contains(@class,'Root')][2]//*[contains(@class,'week')][2]/div[5]")
    private WebElement dateTill;

    @FindBy(xpath = "//span[contains(@class,'MuiButton-label')]")
    private WebElement buttonSearchHotel;


    public void clickHotelAndExcursionBookingService(){
        ((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);", this.hotelAndExcursionBookingService);
        this.hotelAndExcursionBookingService.click();
    }

    public void findHotel(String direction) throws InterruptedException {

        clickHotelAndExcursionBookingService();
        this.inputHotelOrDestination.click();
        this.inputHotelOrDestination.sendKeys(direction);
        this.popUpList.click();
        this.choiceOption.click();
        this.dateOfDepartureAndReturn.click();

        Actions chooseDate = new Actions(getDriver());
        chooseDate
                .moveToElement(this.popupListOfDate)
                .moveToElement(this.dateFrom)
                .click()
                .moveToElement(this.dateTill)
                .click()
                .build()
                .perform();

        Thread.sleep(5000L);
        this.buttonSearchHotel.click();


    }


    public SearchHotelPage(WebDriver driver) {
        super(driver);
    }

}
