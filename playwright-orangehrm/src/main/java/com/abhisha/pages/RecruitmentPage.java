package com.abhisha.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class RecruitmentPage {

    private Page page;

    // Vacancy list locators
    private String addButton = "button:has-text('Add')";
    private String searchButton = "button:has-text('Search')";
    private String tableRows = ".oxd-table-row--with-border";
    private String noRecordsFound = ".oxd-text:has-text('No Records Found')";

    // Add Vacancy form locators
    private String vacancyNameInput = ".oxd-input.oxd-input--active >> nth=0";
    private String jobTitleDropdown = ".oxd-select-text-input >> nth=0";
    private String descriptionTextarea = ".oxd-textarea";
    private String hiringManagerInput = ".oxd-autocomplete-text-input input";
    private String numberOfPositionsInput = ".oxd-input.oxd-input--active >> nth=1";
    private String saveButton = "button[type='submit']";
    private String autocompleteOption = ".oxd-autocomplete-dropdown .oxd-autocomplete-option";

    // Success locator
    private String vacanciesHeader = ".oxd-text:has-text('Vacancies')";

    public RecruitmentPage(Page page) {
        this.page = page;
    }

    public void navigateToVacancies() {
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewJobVacancy");
        page.waitForSelector(vacanciesHeader);
        System.out.println("Navigated to Vacancies");
    }

    public void clickAdd() {
        page.click(addButton);
        page.waitForSelector(saveButton);
        System.out.println("Clicked Add button");
    }

    public void fillVacancyName(String name) {
        page.waitForSelector(vacancyNameInput);
        page.fill(vacancyNameInput, name);
        System.out.println("Filled vacancy name: " + name);
    }

    public void selectJobTitle(String title) {
        page.click(jobTitleDropdown);
        page.waitForSelector(".oxd-select-dropdown");
        page.locator(".oxd-select-dropdown .oxd-select-option")
                .filter(new Locator.FilterOptions().setHasText(title))
                .click();
        System.out.println("Selected job title: " + title);
    }

    public void fillDescription(String description) {
        page.fill(descriptionTextarea, description);
        System.out.println("Filled description");
    }

    public void fillHiringManager(String manager) {
        page.fill(hiringManagerInput, manager);
        try {
            page.waitForSelector(autocompleteOption,
                    new Page.WaitForSelectorOptions().setTimeout(5000));
            page.click(autocompleteOption);
            System.out.println("Selected hiring manager: " + manager);
        } catch (Exception e) {
            System.out.println("No autocomplete found for: " + manager);
        }
    }

    public void fillNumberOfPositions(String number) {
        page.fill(numberOfPositionsInput, number);
        System.out.println("Filled number of positions: " + number);
    }

    public void clickSave() {
        page.click(saveButton);
        System.out.println("Clicked Save");
    }

    public boolean isVacancyListDisplayed() {
        try {
            page.waitForSelector(vacanciesHeader);
            return page.isVisible(vacanciesHeader);
        } catch (Exception e) {
            return false;
        }
    }

    public int getVacancyCount() {
        try {
            page.waitForSelector(tableRows,
                    new Page.WaitForSelectorOptions().setTimeout(5000));
            int count = page.locator(tableRows).count();
            System.out.println("Vacancy count: " + count);
            return count;
        } catch (Exception e) {
            return 0;
        }
    }

    public void searchByJobTitle(String title) {
        page.click(jobTitleDropdown);
        page.waitForSelector(".oxd-select-dropdown");
        page.locator(".oxd-select-dropdown .oxd-select-option")
                .filter(new Locator.FilterOptions().setHasText(title))
                .click();
        page.click(searchButton);
        System.out.println("Searched by job title: " + title);
    }
}