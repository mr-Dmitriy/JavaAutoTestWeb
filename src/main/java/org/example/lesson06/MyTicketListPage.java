package org.example.lesson06;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class MyTicketListPage extends AbstractPage{

    @FindBy(xpath = "//div[@class='red-bottom-link j-auth-open']")
    private WebElement buttonLogInMyTickets;


    @FindBy(css = ".block:nth-child(1) > .third-block_content use")
    private WebElement myTickets;

    public void loginInMyTickets(){
        this.buttonLogInMyTickets.click();
    }

    public void openMyTickets(){
        this.myTickets.click();
    }

    public void myTicketList(String login, String password) throws InterruptedException {

        loginInMyTickets();
        Thread.sleep(2000L);
        new AuthorizationPage(getDriver()).loginIn(login,password);
        Thread.sleep(2000L);
        openMyTickets();
    }


    public MyTicketListPage(WebDriver driver) {
        super(driver);
    }





}
