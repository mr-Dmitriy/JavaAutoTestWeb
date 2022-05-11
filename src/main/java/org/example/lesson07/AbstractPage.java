package org.example.lesson07;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public abstract class AbstractPage {

    private static WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected static WebDriver getDriver() {
        return driver;
    }

    protected boolean isDisplayed(By by) {
        try {
            return getDriver().findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
