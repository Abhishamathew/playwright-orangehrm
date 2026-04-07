package com.abhisha.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class DashboardPage {

    private Page page;

    // Widget locators
    private String timeAtWorkWidget = ".oxd-text:has-text('Time at Work')";
    private String myActionsWidget = ".oxd-text:has-text('My Actions')";
    private String quickLaunchWidget = ".oxd-text:has-text('Quick Launch')";
    private String buzzWidget = ".oxd-text:has-text('Buzz Latest Posts')";
    private String employeesOnLeaveWidget = ".oxd-text:has-text('Employees on Leave Today')";
    private String employeeDistSubUnitWidget = ".oxd-text:has-text('Employee Distribution by Sub Unit')";
    private String employeeDistLocationWidget = ".oxd-text:has-text('Employee Distribution by Location')";

    // Quick launch buttons
    private String assignLeaveButton = "button[title='Assign Leave']";
    private String leaveListButton = "button[title='Leave List']";
    private String timesheetsButton = "button[title='Timesheets']";
    private String applyLeaveButton = "button[title='Apply Leave']";
    private String myLeaveButton = "button[title='My Leave']";
    private String myTimesheetButton = "button[title='My Timesheet']";

    // My Actions
    private String myActionItems = ".orangehrm-todo-list-item";

    public DashboardPage(Page page) {
        this.page = page;
    }

    public void navigateToDashboard() {
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        page.waitForSelector(".orangehrm-dashboard-grid");
        System.out.println("Navigated to Dashboard");
    }

    public boolean isWidgetVisible(String widgetName) {
        try {
            page.waitForSelector(".oxd-text:has-text('" + widgetName + "')");
            return page.isVisible(".oxd-text:has-text('" + widgetName + "')");
        } catch (Exception e) {
            System.out.println("Widget not found: " + widgetName);
            return false;
        }
    }

    public boolean isTimeAtWorkWidgetVisible() {
        return isWidgetVisible("Time at Work");
    }

    public boolean isMyActionsWidgetVisible() {
        return isWidgetVisible("My Actions");
    }

    public boolean isQuickLaunchWidgetVisible() {
        return isWidgetVisible("Quick Launch");
    }

    public boolean isBuzzWidgetVisible() {
        return isWidgetVisible("Buzz Latest Posts");
    }

    public boolean isEmployeesOnLeaveWidgetVisible() {
        return isWidgetVisible("Employees on Leave Today");
    }

    public boolean isEmployeeDistSubUnitWidgetVisible() {
        return isWidgetVisible("Employee Distribution by Sub Unit");
    }

    public boolean isEmployeeDistLocationWidgetVisible() {
        return isWidgetVisible("Employee Distribution by Location");
    }

    public int getQuickLaunchButtonCount() {
        page.locator(".orangehrm-quick-launch").waitFor();

        Locator cards = page.locator(".orangehrm-quick-launch-card");
        return cards.count();
    }

    public int getMyActionsCount() {
        try {
            page.waitForSelector(myActionItems,
                    new Page.WaitForSelectorOptions().setTimeout(5000));
            int count = page.locator(myActionItems).count();
            System.out.println("My Actions count: " + count);
            return count;
        } catch (Exception e) {
            System.out.println("No actions found");
            return 0;
        }
    }

    public void clickQuickLaunchButton(String title) {
        page.click("button[title='" + title + "']");
        System.out.println("Clicked Quick Launch: " + title);
    }

    public String getCurrentUrl() {
        return page.url();
    }
}
