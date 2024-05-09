package testcases;

import base.BaseTest;
import base.WebUI;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomeTest extends BaseTest {
    @BeforeClass
    public void setUp(){
        homePage = new HomePage();
        Assert.assertTrue(homePage.checkSideBarAppear());
    }

    @Test
    public void testNavigateToHSoTLieu(){
        homePage.openHSoTLieu();
        WebUI.waitForPageLoaded();
        Assert.assertEquals(WebUI.getCurrentUrl(), "http://10.170.210.202:7003/HTVPTH/#/hslt/hoso-tailieu");
    }
}
