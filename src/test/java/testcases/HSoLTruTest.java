package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HSoLTruPage;
import pages.HSoTLieuPage;
import pages.HomePage;

import java.util.List;

public class HSoLTruTest extends BaseTest {
    @BeforeClass
    public void setUp(){
        homePage = new HomePage();
        Assert.assertTrue(homePage.checkSideBarAppear());
    }

    @Test
    public void testListLinhVuc(){
        homePage.openDMucLVucMenu();
        HSoLTruPage hSoLTruPage = new HSoLTruPage();
        List<String> linhVuc = hSoLTruPage.getListLinhVuc();
        System.out.println("Linh vuc: " + linhVuc);
        homePage.openDuLieuMenu();
        HSoTLieuPage hSoTLieuPage = new HSoTLieuPage();
        Assert.assertTrue(hSoTLieuPage.compareDropdownLVuc(linhVuc));
    }

    @Test
    public void testListTGianBQuan(){
        homePage.openDMucTGianBQuanMenu();
        HSoLTruPage hSoLTruPage = new HSoLTruPage();
        List<String> tGianBQuan = hSoLTruPage.getListTGianBQuan();
        System.out.println("Thoi gian bao quan: " + tGianBQuan);
        homePage.openDuLieuMenu();
        HSoTLieuPage hSoTLieuPage = new HSoTLieuPage();
        Assert.assertTrue(hSoTLieuPage.compareDropdownTGianBQuan(tGianBQuan));
    }

    @Test
    public void testListTTrangHSo(){
        homePage.openDMucTTrangHSo();
        HSoLTruPage hSoLTruPage = new HSoLTruPage();
        List<String> tTrangHSo = hSoLTruPage.getListTTrangHSo();
        System.out.println("Tinh trang ho so: " + tTrangHSo);
        homePage.openDuLieuMenu();
        HSoTLieuPage hSoTLieuPage = new HSoTLieuPage();
        Assert.assertTrue(hSoTLieuPage.compareDropdownTTrangHSo(tTrangHSo));
    }
}
