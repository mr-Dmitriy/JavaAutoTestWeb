package org.example.lesson07;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.Set;

public class ServicesPage extends AbstractPage {

    @FindBy(css = ".header_actions-item:nth-child(4) .j-menu-open use")
    private WebElement services;

    @FindBy(css = ".menu-list:nth-child(2) li:nth-child(16) .menu-list-item_link")
    private WebElement onlineScoreboardAtStations;


    public void clickOnlineScoreboardAtStations() {

        Set<String> firstHandles = getDriver().getWindowHandles();

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", onlineScoreboardAtStations);
        onlineScoreboardAtStations.click();

        Set<String> secondHandles = getDriver().getWindowHandles();
        for (String handle : secondHandles) {

            if (!firstHandles.contains(handle)) {
                getDriver().switchTo().window(handle);
            }
        }
    }

    public void openOnlineScoreboardAtStations() throws InterruptedException {

        Actions openOnlineScoreboardAtStations = new Actions(getDriver());
        openOnlineScoreboardAtStations
                .click(this.services)
                .build()
                .perform();

        clickOnlineScoreboardAtStations();
    }


    public ServicesPage(WebDriver driver) {
        super(driver);
    }
}
