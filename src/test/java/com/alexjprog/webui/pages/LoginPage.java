package com.alexjprog.webui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    By usernameLocator = By.id("txtUsername");
    By passwordLocator = By.id("txtPassword");
    By loginButtonLocator = By.id("btnLogin");
    By loginPanel = By.id("logInPanelHeading");

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage typeUsername(String username) {
        driver.findElement(usernameLocator).sendKeys(username);
        return this;
    }

    public LoginPage typePassword(String password) {
        driver.findElement(passwordLocator).sendKeys(password);
        return this;
    }

    public DashboardPage submitLogin() {
        driver.findElement(loginButtonLocator).submit();
        return new DashboardPage(driver);
    }

    public DashboardPage loginAs(String username, String password) {
        typeUsername(username);
        typePassword(password);
        return submitLogin();
    }
}
