package com.alexjprog.webui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        WebDriverManager.chromedriver().setup();

        ChromeDriver driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/");


        WebElement username = driver.findElement(By.id("txtUsername"));
        WebElement password = driver.findElement(By.id("txtPassword"));
        username.sendKeys("Admin");
        password.sendKeys("admin123");
        password.submit();

        // 1
        driver.findElement(By.id("menu_admin_viewAdminModule")).click();
        driver.findElement(By.id("menu_admin_Job")).click();
        driver.findElement(By.id("menu_admin_workShift")).click();

        driver.findElement(By.id("btnAdd")).click();
        driver.findElement(By.id("workShift_name")).sendKeys("Bernoulli");

        driver.findElement(By.id("workShift_workHours_from")).sendKeys("06:00");
        driver.findElement(By.id("workShift_workHours_to")).sendKeys("18:00");

        driver.findElement(By.id("workShift_availableEmp")).sendKeys("Alice Duval");
        driver.findElement(By.id("btnAssignEmployee")).click();
        driver.findElement(By.id("workShift_availableEmp")).sendKeys("Chenzira Chuki");
        driver.findElement(By.id("btnAssignEmployee")).click();
        driver.findElement(By.id("workShift_availableEmp")).sendKeys("Lisa Andrews");
        driver.findElement(By.id("btnAssignEmployee")).click();

        driver.findElement(By.id("btnSave")).click();

        // 2
        List<WebElement> rows = driver.findElement(By.id("resultTable")).findElements(By.tagName("tr"));
        System.out.println(rows);
        List<String> addedVals = Arrays.asList("", "Bernoulli", "06:00", "18:00", "12.00");
        int addedRowNumber = 0;
        for(WebElement row : rows) {
            List<String> cols = row.findElements(By.tagName("td")).stream().map(val -> val.getText()).toList();
            if (cols.equals(addedVals)) {
                break;
            }
            addedRowNumber++;
        }

        System.out.println("Added row was found at position " + addedRowNumber);

        // 3
        rows.get(addedRowNumber).findElement(By.tagName("input")).click();
        driver.findElement(By.id("btnDelete")).click();
        driver.findElement(By.id("dialogDeleteBtn")).click();
    }
}
