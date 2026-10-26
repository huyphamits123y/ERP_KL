package Tests;

import Base.BaseTest;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    LoginPage loginPage;
    @BeforeClass
    public void setUp(){
        BaseTest.setDriver("chrome");
        loginPage = new LoginPage();
    }
    @BeforeMethod
    public void reloadPage(){
        loginPage.open();
    }
    @Test(priority = 1)
    public void LoginFailedWithEmptyUsernameTestcase() {
        BaseTest.waitForPageLoaded();
        Assert.assertTrue(loginPage.isLoginFailedWithEmptyUsername("", "secret"));
    }
    @Test(priority = 2)
    public void LoginFailedWithEmptyPasswordTestcase() {
        BaseTest.waitForPageLoaded();
        Assert.assertTrue(loginPage.isLoginFailedWithEmptyPassword("standard_user", ""));
    }
    @Test(priority = 3)
    public void LoginFailedWithInvalidCredentialsPasswordTestcase() {
        BaseTest.waitForPageLoaded();
        Assert.assertTrue(loginPage.isLoginFailedInvalid("pthau123123@gmail.com", "invalid_pass"));
    }
    @Test(priority = 4)
    public void LoginSuccessTestcase() {
        BaseTest.waitForPageLoaded();
        Assert.assertTrue(loginPage.isLoginSuccess("pthau123123@gmail.com", "123123123"));
    }



}
