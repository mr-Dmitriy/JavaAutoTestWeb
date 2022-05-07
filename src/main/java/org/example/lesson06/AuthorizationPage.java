package org.example.lesson06;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AuthorizationPage extends AbstractPage{

    @FindBy(css = ".username > span")
    private WebElement logInAccount;

    @FindBy(name = "j_username")
    private WebElement userLogin;

    @FindBy(name = "j_password")
    private WebElement UserPassword;

    @FindBy(css = ".login-input_wrap")
    private WebElement buttonLogIn;


    public void loginIn(){
        this.buttonLogIn.click();
    }

    public AuthorizationPage setLogin(String login){
        this.logInAccount.click();
        this.logInAccount.sendKeys(login);
        return this;
    }

    public AuthorizationPage setPassword(String password){
        this.UserPassword.click();
        this.UserPassword.sendKeys(password);
        return this;
    }

    public void clickLogInAccount(){
        this.logInAccount.click();
    }

    public void loginIn(String login, String password){

        Actions loginIn = new Actions(getDriver());
        loginIn
                .click(this.userLogin)
                .sendKeys(login)
                .click(this.UserPassword)
                .sendKeys(password)
                .click(this.buttonLogIn)
                .build()
                .perform();
    }

    public AuthorizationPage(WebDriver driver) {
        super(driver);
    }
}
