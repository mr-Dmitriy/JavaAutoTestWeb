package org.example.lesson07;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends AbstractPage {

    @FindBy(xpath = ".//*[contains(@class,'j-profile-links-loggedin')]/a[@class='j-profile-username']")
    private WebElement userProfile;

    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement fieldUserLastName;

    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement fieldUserFirstName;

    @FindBy(css = ".active .btn-primary")
    private WebElement buttonSave;

    public void clickUserProfile(){
        this.userProfile.click();
    }
    public void clearFieldUserLastName(){
        this.fieldUserLastName.clear();
    }

    public WebElement getFieldUserLastName(){
        return fieldUserLastName;
    }

    public WebElement getFieldUserFirstName(){
        return fieldUserFirstName;
    }


    public void clearFieldUserFirstName(){
        this.fieldUserFirstName.clear();
    }

    public void changeDateInProfile(String lastName, String firstName){

        clickUserProfile();
        clearFieldUserLastName();
        clearFieldUserFirstName();

        Actions changeDateInProfile = new Actions(getDriver());
        changeDateInProfile
                .click(this.fieldUserLastName)
                .sendKeys(lastName)
                .click(this.fieldUserFirstName)
                .sendKeys(firstName)
                .pause(10000L)
                .click(this.buttonSave)
                .build()
                .perform();
    }


    public ProfilePage(WebDriver driver) {
        super(driver);
    }
}
