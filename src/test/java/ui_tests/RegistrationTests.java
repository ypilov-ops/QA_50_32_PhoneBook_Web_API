package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ContactPage;
import pages.HomePage;
import pages.LoginPage;

import java.util.Random;
import static utils.UserFactory.*;

public class RegistrationTests extends AppManager {
    LoginPage loginPage;

    @BeforeMethod
    public void goToRegistrationPage(){
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void registrationPositiveTest(){
        int i = new Random().nextInt(1000);
        User user = new User("D@NTeA"+i+"@gmail.com"
                , "H311is0th3r$");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertTrue(new ContactPage(getDriver())
                .isTextInContactPageMessagePresent("No Contacts here!"));
    }

    @Test
    public void registrationPositiveTest_WithFaker(){
        User user = positiveUser();
        System.out.println(user);
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertTrue(new ContactPage(getDriver())
                .isTextInContactPageMessagePresent("No Contacts here!"));
    }

    @Test
    public void registrationNegativeTest_WrongEmail() {
        User user = new User("dantehell.it", "314@PieHole!");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals
                (loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void registrationNegativeTest_WrongPassword() {
        User user = new User("dante@hell.it", "virgil");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals
                (loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    public void registrationNegativeTest_EmptyEmail() {
        User user = new User("", "314@PieHole!");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals
                (loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    public void registrationNegativeTest_EmptyPassword() {
        User user = new User("dante@hell.it", "");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals
                (loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    public void registrationNegativeTest_EmailWoDomain() {
        User user = new User("dantehell", "314@PieHole!");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals
                (loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    public void registrationNegativeTest_PasswordOnlyNumbers() {
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
    public void registrationNegativeTest_WithFakerEmail(){
        User user = negativeUserWrongEmail();
        System.out.println(user);
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertEquals
                (loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void registrationNegativeTest_WithFakerPassword(){
        User user = negativeUserWrongPassword();
        System.out.println(user);
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertEquals
                (loginPage.closeAlertReturnText(), "Wrong email or password");
    }







}
