package tests;

import com.abhisha.base.BaseTest;
import com.abhisha.pages.LoginPage;
import com.abhisha.pages.DashboardPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {

    private void loginAsAdmin() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.login("Admin", "admin123");
        page.waitForSelector(".oxd-topbar-header-breadcrumb");
        System.out.println("Logged in as Admin");
    }

    @Test(description = "TC07 - Verify all dashboard widgets are displayed")
    public void verifyDashboardWidgets() {
        loginAsAdmin();

        DashboardPage dashboardPage = new DashboardPage(page);
        dashboardPage.navigateToDashboard();

        Assert.assertTrue(dashboardPage.isTimeAtWorkWidgetVisible(),
                "Time at Work widget not visible!");
        Assert.assertTrue(dashboardPage.isMyActionsWidgetVisible(),
                "My Actions widget not visible!");
        Assert.assertTrue(dashboardPage.isQuickLaunchWidgetVisible(),
                "Quick Launch widget not visible!");
        Assert.assertTrue(dashboardPage.isBuzzWidgetVisible(),
                "Buzz Latest Posts widget not visible!");
        Assert.assertTrue(dashboardPage.isEmployeesOnLeaveWidgetVisible(),
                "Employees on Leave Today widget not visible!");
        Assert.assertTrue(dashboardPage.isEmployeeDistSubUnitWidgetVisible(),
                "Employee Distribution by Sub Unit widget not visible!");
        Assert.assertTrue(dashboardPage.isEmployeeDistLocationWidgetVisible(),
                "Employee Distribution by Location widget not visible!");

        System.out.println("✅ TC07 All dashboard widgets verified!");
    }

    @Test(description = "TC08 - Verify Quick Launch has 6 buttons")
    public void verifyQuickLaunchButtons() {
        loginAsAdmin();

        DashboardPage dashboardPage = new DashboardPage(page);
        dashboardPage.navigateToDashboard();

        int count = dashboardPage.getQuickLaunchButtonCount();
        Assert.assertEquals(count, 6,
                "Quick Launch should have 6 buttons!");

        System.out.println("✅ TC08 Quick Launch buttons count verified!");
    }

    @Test(description = "TC09 - Verify My Actions widget has items")
    public void verifyMyActionsItems() {
        loginAsAdmin();

        DashboardPage dashboardPage = new DashboardPage(page);
        dashboardPage.navigateToDashboard();

        int count = dashboardPage.getMyActionsCount();
        Assert.assertTrue(count > 0,
                "My Actions should have at least one item!");

        System.out.println("✅ TC09 My Actions items verified - count: " + count);
    }

    @Test(description = "TC10 - Verify Quick Launch Assign Leave navigation")
    public void verifyQuickLaunchAssignLeave() {
        loginAsAdmin();

        DashboardPage dashboardPage = new DashboardPage(page);
        dashboardPage.navigateToDashboard();

        dashboardPage.clickQuickLaunchButton("Assign Leave");
        page.waitForSelector(".oxd-topbar-header-breadcrumb");

        String url = dashboardPage.getCurrentUrl();
        Assert.assertTrue(url.contains("leave"),
                "Should navigate to leave page!");

        System.out.println("✅ TC10 Quick Launch Assign Leave navigation verified!");
        System.out.println("Current URL: " + url);
    }

    @Test(description = "TC11 - Verify Quick Launch Timesheets navigation")
    public void verifyQuickLaunchTimesheets() {
        loginAsAdmin();

        DashboardPage dashboardPage = new DashboardPage(page);
        dashboardPage.navigateToDashboard();

        dashboardPage.clickQuickLaunchButton("Assign Leave");
        page.waitForSelector(".oxd-topbar-header-breadcrumb");

        String url = dashboardPage.getCurrentUrl();
        Assert.assertTrue(url.contains("leave"),
                "Should navigate to timesheets page!");

        System.out.println("✅ TC11 Quick Launch Timesheets navigation verified!");
        System.out.println("Current URL: " + url);
    }
}
