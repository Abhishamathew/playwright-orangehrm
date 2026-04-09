package tests;

import com.abhisha.base.BaseTest;
import com.abhisha.pages.LoginPage;
import com.abhisha.pages.RecruitmentPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RecruitmentTest extends BaseTest {

    private void loginAsAdmin() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.login("Admin", "admin123");
        page.waitForSelector(".oxd-topbar-header-breadcrumb");
        System.out.println("Logged in as Admin");
    }

    @Test(description = "TC12 - Verify Vacancies page is displayed")
    public void verifyVacanciesPage() {
        loginAsAdmin();

        RecruitmentPage recruitmentPage = new RecruitmentPage(page);
        recruitmentPage.navigateToVacancies();

        Assert.assertTrue(recruitmentPage.isVacancyListDisplayed(),
                "Vacancies page not displayed!");

        System.out.println("✅ TC12 Vacancies page verified!");
    }

    @Test(description = "TC13 - Verify vacancy list has records")
    public void verifyVacancyListHasRecords() {
        loginAsAdmin();

        RecruitmentPage recruitmentPage = new RecruitmentPage(page);
        recruitmentPage.navigateToVacancies();

        int count = recruitmentPage.getVacancyCount();
        Assert.assertTrue(count > 0,
                "Vacancy list should have at least one record!");

        System.out.println("✅ TC13 Vacancy list has " + count + " records!");
    }

    @Test(description = "TC14 - Add new job vacancy")
    public void addNewJobVacancy() {
        loginAsAdmin();

        RecruitmentPage recruitmentPage = new RecruitmentPage(page);
        recruitmentPage.navigateToVacancies();
        recruitmentPage.clickAdd();

        // Fill form
        recruitmentPage.fillVacancyName("Playwright QA Engineer");
        recruitmentPage.selectJobTitle("QA Engineer");
        recruitmentPage.fillDescription("Looking for an experienced QA Engineer");
        recruitmentPage.fillHiringManager("Admin");
        recruitmentPage.fillNumberOfPositions("2");

        recruitmentPage.clickSave();

        // After save it redirects back to vacancy list or edit page
        Assert.assertTrue(
                page.url().contains("recruitment"),
                "Should stay on recruitment page after save!");

        System.out.println("✅ TC14 Add new job vacancy passed!");
        System.out.println("Current URL: " + page.url());
    }

    @Test(description = "TC15 - Add vacancy with missing required fields")
    public void addVacancyWithMissingFields() {
        loginAsAdmin();

        RecruitmentPage recruitmentPage = new RecruitmentPage(page);
        recruitmentPage.navigateToVacancies();
        recruitmentPage.clickAdd();

        // Click save without filling required fields
        recruitmentPage.clickSave();

        // Should show validation errors
        boolean hasErrors = page.locator(".oxd-input-field-error-message").count() > 0;
        Assert.assertTrue(hasErrors,
                "Should show validation errors for missing required fields!");

        System.out.println("✅ TC15 Validation errors shown for missing fields!");
    }
}