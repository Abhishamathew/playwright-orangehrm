package com.abhisha.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    private Page page;

    // Locators
    private String usernameInput = "input[name='username']";
    private String passwordInput = "input[name='password']";
    private String loginButton = "button[type='submit']";
    private String dashboardHeader = ".oxd-topbar-header-breadcrumb";
    private String errorMessage = ".oxd-alert-content-text";
    private String requiredMessage = ".oxd-input-field-error-message";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void enterUsername(String username) {
        page.fill(usernameInput, username);
    }

    public void enterPassword(String password) {
        page.fill(passwordInput, password);
    }

    public void clickLogin() {
        page.click(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        System.out.println("Login attempted with: " + username);
    }

    public boolean isDashboardDisplayed() {
        try {
            page.waitForSelector(dashboardHeader);
            return page.isVisible(dashboardHeader);
        } catch (Exception e) {
            System.out.println("Dashboard not displayed!");
            return false;
        }
    }

    public boolean isErrorDisplayed() {
        try {
            page.waitForSelector(errorMessage);
            return page.isVisible(errorMessage);
        } catch (Exception e) {
            System.out.println("Error message not displayed!");
            return false;
        }
    }

    public boolean isRequiredMessageDisplayed() {
        try {
            page.waitForSelector(requiredMessage);
            return page.isVisible(requiredMessage);
        } catch (Exception e) {
            System.out.println("Required message not displayed!");
            return false;
        }
    }

    public String getErrorMessage() {
        return page.textContent(errorMessage);
    }
}