package org.example.lesson06;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CookiesPage extends AbstractPage{

    private final String LOCATOR_COOCKIES = ".//button[@class='cookie-alert__btn btn-primary']";


    @FindBy(xpath = LOCATOR_COOCKIES)
    private WebElement buttonAcceptCoockies;

    public By buttonAcceptCoockiesLocator() {
        return By.xpath(LOCATOR_COOCKIES);
    }

    public boolean isDisplayedCookies() {
        return isDisplayed(buttonAcceptCoockiesLocator());
    }

    public void clickButtonAcceptCoockies(){
        this.buttonAcceptCoockies.click();
    }


    public CookiesPage(WebDriver driver) {
        super(driver);
    }
}
