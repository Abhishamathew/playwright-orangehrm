package com.abhisha.pages;

import com.microsoft.playwright.Page;

public class EmployeePage {

    private Page page;

    // Employee List locators
    private String addButton = "button:has-text('Add')";
//    private String searchNameInput = ".oxd-input.oxd-input--active >> nth=1";
//    private String searchButton = "button:has-text('Search')";
    private String employeeTable = ".oxd-table-body";
//    private String tableRows = ".oxd-table-row--with-border";
//    private String noRecordsFound = ".oxd-text:has-text('No Records Found')";
    private String searchNameInput = ".oxd-autocomplete-text-input input";
    private String autocompleteOption = ".oxd-autocomplete-dropdown .oxd-autocomplete-option";
    private String searchButton = "button:has-text('Search')";
    private String tableRows = ".oxd-table-row--with-border";
    private String noRecordsFound = ".oxd-text:has-text('No Records Found')";

    // Add Employee form locators
    private String firstNameInput = "input[name='firstName']";
    private String middleNameInput = "input[name='middleName']";
    private String lastNameInput = "input[name='lastName']";
    private String saveButton = "button[type='submit']";
    private String employeeIdInput = ".oxd-input.oxd-input--active >> nth=0";

    // Success/header locator after save
    private String personalDetailsHeader = ".orangehrm-edit-employee-name";

    public EmployeePage(Page page) {
        this.page = page;
    }

    public void navigateToEmployeeList() {
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");
        System.out.println("Navigated to Employee List");
    }

    public void clickAddEmployee() {
        page.click(addButton);
        System.out.println("Clicked Add Employee button");
    }

    public void fillEmployeeDetails(String firstName, String middleName, String lastName) {
        page.waitForSelector(firstNameInput);
        page.fill(firstNameInput, firstName);
        page.fill(middleNameInput, middleName);
        page.fill(lastNameInput, lastName);
        System.out.println("Filled employee details: " + firstName + " " + lastName);
    }

    public void clickSave() {
        page.click(saveButton);
        System.out.println("Clicked Save button");
    }

    public boolean isPersonalDetailsPageDisplayed() {
        try {
            page.waitForSelector(personalDetailsHeader);
            return page.isVisible(personalDetailsHeader);
        } catch (Exception e) {
            System.out.println("Personal details page not displayed!");
            return false;
        }
    }

    public void searchEmployee(String name) {
        // Type in autocomplete field
        page.fill(searchNameInput, name);
        System.out.println("Typed employee name: " + name);

        // Wait for dropdown and click first option
        try {
            page.waitForSelector(autocompleteOption,
                    new Page.WaitForSelectorOptions().setTimeout(5000));
            page.click(autocompleteOption);
            System.out.println("Selected from autocomplete: " + name);
        } catch (Exception e) {
            System.out.println("No autocomplete option found for: " + name);
        }

        // Click search button
        page.click(searchButton);

        // Wait for results to load
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("Searched for employee: " + name);
    }

    public boolean isEmployeeFound() {
        try {
            page.waitForSelector(tableRows,
                    new Page.WaitForSelectorOptions().setTimeout(5000));
            int count = page.locator(tableRows).count();
            System.out.println("Employee rows found: " + count);
            return count > 0;
        } catch (Exception e) {
            System.out.println("No employees found!");
            return false;
        }
    }

    public boolean isNoRecordsFound() {
        try {
            page.waitForSelector(noRecordsFound,
                    new Page.WaitForSelectorOptions().setTimeout(5000));
            return page.isVisible(noRecordsFound);
        } catch (Exception e) {
            // If no records text not found, check if table is empty
            int count = page.locator(tableRows).count();
            return count == 0;
        }
    }
}