package testcases;

import base.BaseTest;
import dataprovider.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    @Test(dataProvider = "objLoginData", dataProviderClass = LoginDataProvider.class)
    public void LoginWithValidData(String typeLogin, String userID, String password){
        loginPage = new LoginPage();
        navigateToLoginPage();
        loginPage.logIn(typeLogin, userID, password);
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.checkSideBarAppear());
    }
}
