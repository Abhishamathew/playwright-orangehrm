package tests;

import com.abhisha.base.BaseTest;
import com.abhisha.pages.LoginPage;
import com.abhisha.pages.EmployeePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmployeeTest extends BaseTest {

    private void loginAsAdmin() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.login("Admin", "admin123");
        // Wait for dashboard to load instead of URL
        page.waitForSelector(".oxd-topbar-header-breadcrumb");
        System.out.println("Logged in as Admin");
    }

    private void navigateToPIM() {
        page.click("a[href='/web/index.php/pim/viewPimModule']");
        page.waitForSelector(".oxd-topbar-header-breadcrumb");
        System.out.println("Navigated to PIM");
    }

    @Test(description = "TC04 - Add new employee")
    public void addNewEmployee() {
        loginAsAdmin();
        navigateToPIM();

        EmployeePage employeePage = new EmployeePage(page);
        employeePage.navigateToEmployeeList();
        employeePage.clickAddEmployee();

        employeePage.fillEmployeeDetails("Abhisha", "Test", "Playwright");
        employeePage.clickSave();

        Assert.assertTrue(employeePage.isPersonalDetailsPageDisplayed(),
                "Personal details page not displayed after saving employee!");

        System.out.println("✅ TC04 Add new employee passed!");
    }

    @Test(description = "TC05 - Search existing employee")
    public void searchExistingEmployee() {
        loginAsAdmin();
        navigateToPIM();

        EmployeePage employeePage = new EmployeePage(page);
        employeePage.navigateToEmployeeList();
        employeePage.searchEmployee("Abhisha");

        Assert.assertTrue(employeePage.isEmployeeFound(),
                "Employee not found in search results!");

        System.out.println("✅ TC05 Search existing employee passed!");
    }

    @Test(description = "TC06 - Search non-existing employee")
    public void searchNonExistingEmployee() {
        loginAsAdmin();
        navigateToPIM();

        EmployeePage employeePage = new EmployeePage(page);
        employeePage.navigateToEmployeeList();
        employeePage.searchEmployee("XYZNONEXISTENT123");

        Assert.assertTrue(employeePage.isNoRecordsFound(),
                "Should show no records for non-existing employee!");

        System.out.println("✅ TC06 Search non-existing employee passed!");
    }
}