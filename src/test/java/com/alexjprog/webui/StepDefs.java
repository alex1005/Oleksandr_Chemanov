package com.alexjprog.webui;

import com.alexjprog.webui.pages.DashboardPage;
import com.alexjprog.webui.pages.LoginPage;
import com.alexjprog.webui.pages.WorkShiftsPage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class StepDefs {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private WorkShiftsPage workShiftsPage;

    private static ChromeDriver driver;

    static {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }


    @Given("^that I'm on Login page$")
    public void goToLoginPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage = new LoginPage(driver);
    }

    @When("^I enter username \"([^\"]*)\"$")
    public void enterUsername(String username) throws Exception {
        loginPage.typeUsername(username);
    }

    @When("^I enter password \"([^\"]*)\"$")
    public void enterPassword(String passwd) throws Exception {
        loginPage.typePassword(passwd);
    }

    @Then("^I get logged in$")
    public void isLoggedIn() throws Exception {
        dashboardPage = loginPage.submitLogin();
    }

    @Given("^that I'm on Work Shifts page$")
    public void goToWorkShiftsPage() throws Exception {
        driver.get("https://opensource-demo.orangehrmlive.com/index.php/admin/workShift");
        workShiftsPage = new WorkShiftsPage(driver);
    }

    @When("^I enter shift name \"([^\"]*)\"$")
    public void enterShiftName(String shiftName) throws Exception {
        workShiftsPage.enterName(shiftName);
    }

    @When("^enter time from \"([^\"]*)\" to \"([^\"]*)\"$")
    public void enterTimeFromTo(String from, String to) throws Exception {
        workShiftsPage.enterWorkTime(from, to);
    }

    @When("^assign employee \"([^\"]*)\"$")
    public void assignEmployee(String emp) throws Exception {
        workShiftsPage.addEmployee(emp);
    }

    @When("^press save button$")
    public void pressSaveButton() throws Exception {
        workShiftsPage.pressSaveButton();
    }

    @Then("^I see shift \"([^\"]*)\" in the table$")
    public void isNewRecordInTable(String shift) throws Exception {
        if(!workShiftsPage.isShiftPresent(workShiftsPage.getShiftIndex(shift)))
            throw new PendingException();
    }

    @When("^I choose \"([^\"]*)\" work shift$")
    public void chooseWorkShift(String shift) throws Exception {
        workShiftsPage.selectWorkShift(shift);
    }

    @When("^press delete button and submit deletion$")
    public void pressDeleteButton() throws Exception {
        workShiftsPage.deleteSelected();
    }

    @Then("^I see \"([^\"]*)\" record deleted$")
    public void isRecordDeleted(String shift) throws Exception {
        if(workShiftsPage.isShiftPresent(workShiftsPage.getShiftIndex(shift)))
            throw new PendingException();
    }

    @When("^I press add button$")
    public void pressAddButton() throws Exception {
        workShiftsPage.pressAddButton();
    }

}
