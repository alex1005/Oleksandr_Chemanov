package com.alexjprog.webui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WorkShiftsPage {

    By addButton = By.id("btnAdd");
    By workShiftName = By.id("workShift_name");
    By workHoursFrom = By.id("workShift_workHours_from");
    By workHoursTo = By.id("workShift_workHours_to");
    By availableEmployees = By.id("workShift_availableEmp");
    By assignEmployeeButton = By.id("btnAssignEmployee");
    By saveButton = By.id("btnSave");
    By workShiftsTable = By.id("resultTable");
    By deleteButton = By.id("btnDelete");
    By modalDeleteButton = By.id("dialogDeleteBtn");

    private final WebDriver driver;

    public WorkShiftsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void pressAddButton() {
        driver.findElement(addButton).click();
    }

    public void pressDeleteButton() {
        driver.findElement(deleteButton).click();
    }

    public void pressModalDeleteButton() {
        driver.findElement(modalDeleteButton).click();
    }

    public void enterWorkTime(String from, String to) {
        driver.findElement(workHoursFrom).sendKeys(from);
        driver.findElement(workHoursTo).sendKeys(to);
    }

    public void enterName(String name) {
        driver.findElement(workShiftName).sendKeys(name);
    }

    public void addEmployee(String name) {
        driver.findElement(availableEmployees).sendKeys(name);
        driver.findElement(assignEmployeeButton).click();
    }

    public void pressSaveButton() {
        driver.findElement(saveButton).submit();
    }

    public void addWorkShift(List<String> shift, String ... emp) {
        pressAddButton();
        enterName(shift.get(0));

        enterWorkTime(shift.get(1), shift.get(2));

        for (String s: emp) {
            addEmployee(s);
        }
        pressSaveButton();
    }

    public int getShiftIndex(String shift) {
        List<WebElement> rows = driver.findElement(workShiftsTable).findElements(By.tagName("tr"));
        int rowIndex = 0;
        for(WebElement row : rows) {
            List<String> cols = row.findElements(By.tagName("td")).stream().map(val -> val.getText()).toList();
            if (cols.size() > 0 && cols.get(1).equals(shift)) {
                break;
            }
            rowIndex++;
        }
        
        return rowIndex;
    }

    public boolean isShiftPresent(int index) {
        return index < driver.findElement(workShiftsTable).findElements(By.tagName("tr")).size();
    }

    public void selectWorkShift(String shift) {
        int index = getShiftIndex(shift);
        if(!isShiftPresent(index))
            throw new IllegalStateException("Unable to delete nonexistent record");
        driver.findElement(workShiftsTable).findElements(By.tagName("tr"))
                .get(index).findElement(By.tagName("input")).click();
    }

    public void deleteSelected() {
        pressDeleteButton();
        pressModalDeleteButton();
    }

    public List<String> getAvailableEmployees() {
        return driver.findElement(availableEmployees)
                .findElements(By.tagName("option")).stream().map(val -> val.getText()).toList();
    }
}
