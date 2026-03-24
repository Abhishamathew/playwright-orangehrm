package tests;

import com.abhisha.base.BaseTest;
import com.abhisha.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "TC01 - Login with valid credentials")
    public void loginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(page);

        loginPage.login("Admin", "admin123");

        Assert.assertTrue(loginPage.isDashboardDisplayed(),
                "Dashboard not displayed after valid login!");

        System.out.println("✅ TC01 Login with valid credentials passed!");
    }

    @Test(description = "TC02 - Login with invalid credentials")
    public void loginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(page);

        loginPage.login("wronguser", "wrongpassword");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error message not shown for invalid credentials!");

        System.out.println("Error message: " + loginPage.getErrorMessage());
        System.out.println("✅ TC02 Login with invalid credentials passed!");
    }

    @Test(description = "TC03 - Login with empty credentials")
    public void loginWithEmptyCredentials() {
        LoginPage loginPage = new LoginPage(page);

        loginPage.login("", "");

        Assert.assertTrue(loginPage.isRequiredMessageDisplayed(),
                "Required field message not shown for empty credentials!");

        System.out.println("✅ TC03 Login with empty credentials passed!");
    }
}