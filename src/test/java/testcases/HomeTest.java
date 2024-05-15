package testcases;

import base.BaseTest;
import base.WebUI;
import org.openqa.selenium.support.ui.Wait;
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
        homePage.openDuLieuMenu();
        String url = WebUI.getCurrentUrl();
        WebUI.waitUntilUrlContains(url, "http://10.170.210.202:7003/HTVPTH/#/hslt/hoso-tailieu", 100);
        Assert.assertEquals(WebUI.getCurrentUrl(), "http://10.170.210.202:7003/HTVPTH/#/hslt/hoso-tailieu");
    }
}
