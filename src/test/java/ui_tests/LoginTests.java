package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactPage;
import pages.HomePage;
import pages.LoginPage;
import utils.RetryAnalyser;

import static utils.UserFactory.negativeUserWrongEmail;
import static utils.UserFactory.negativeUserWrongPassword;

public class LoginTests extends AppManager {
    @Test(retryAnalyzer = RetryAnalyser.class)
    public void loginPositiveTest() {
        System.out.println("1st test");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationForm
                ("dante@hell.it", "314@PieHole");
        loginPage.clickBtnLoginForm();
        Assert.assertTrue
                (new ContactPage(getDriver()).isAddButtonPresent("ADD"));
    }

    @Test
    public void loginPositiveTestWithUser() {
        User user = new User("dante@hell.it", "314@PieHole");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertTrue
                (new ContactPage(getDriver()).isSignOutButtonPresent("Sign Out"));
    }

    @Test
    public void loginNegativeTest_WrongEmail() {
        User user = new User("dantehell.it", "314@PieHole!");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertFalse
                (new ContactPage(getDriver()).isSignOutButtonPresent("Sign Out"));
    }

    @Test
    public void loginNegativeTest_WrongPassword() {
        User user = new User("dante@hell.it", "virgil");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals
                (loginPage.closeAlertReturnText(), "Wrong email or password");
    }
    @Test
    public void loginNegativeTest_EmptyEmail() {
        User user = new User("", "314@PieHole!");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals
                (loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegativeTest_EmptyPassword() {
        User user = new User("dante@hell.it", "");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals
                (loginPage.closeAlertReturnText(), "Wrong email or password");
    }


    @Test
    public void loginNegativeTest_EmailWoDomain() {
        User user = new User("dantehell", "314@PieHole!");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals
                (loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegativeTest_PasswordOnlyNumbers() {
        User user = new User("dante@hell.it", "314456789");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals
                (loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegativeTest_WithFakerEmail(){
        User user = negativeUserWrongEmail();
        System.out.println(user);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertEquals
                (loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void loginNegativeTest_WithFakerPassword(){
        User user = negativeUserWrongPassword();
        System.out.println(user);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertEquals
                (loginPage.closeAlertReturnText(), "Wrong email or password");
    }


}
