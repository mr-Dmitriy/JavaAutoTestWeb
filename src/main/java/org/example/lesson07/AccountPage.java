package org.example.lesson07;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends AbstractPage {

    private final String LOCATOR_LogOut = "//a[@class='j-profile-logout']";

    @FindBy(xpath = LOCATOR_LogOut)
    private WebElement buttonLogOut;

    public By buttonLogOutLocator() {
        return By.xpath(LOCATOR_LogOut);
    }

    public boolean isDisplayedButtonLogOut() {
        return isDisplayed(buttonLogOutLocator());
    }

    public void clickButtonLogOut() {
        this.buttonLogOut.click();
    }

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public void logOut() {
        if (new AccountPage(getDriver()).isDisplayedButtonLogOut()) {
            new AccountPage(getDriver()).clickButtonLogOut();
        }
    }


}
