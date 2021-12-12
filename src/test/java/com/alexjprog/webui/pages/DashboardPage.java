package com.alexjprog.webui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage {
    By adminMenu = By.id("menu_admin_viewAdminModule");
    By adminJobsSubmenu = By.id("menu_admin_Job");
    By workShiftsMenu = By.id("menu_admin_workShift");

    private final WebDriver driver;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public WorkShiftsPage goToWorkShifts() {
        driver.findElement(adminMenu).click();
        driver.findElement(adminJobsSubmenu).click();
        driver.findElement(workShiftsMenu).click();
        return new WorkShiftsPage(driver);
    }
}
