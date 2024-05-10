package testcases;

import base.BaseTest;
import dataprovider.HSoTLieuDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HSoTLieuPage;
import pages.HomePage;

public class HSoTLieuTest extends BaseTest {
    @BeforeClass
    public void setUp(){
        homePage = new HomePage();
        Assert.assertTrue(homePage.checkSideBarAppear());
        homePage.openHSoTLieu();
        hSoTLieuPage = new HSoTLieuPage();
    }

    @Test(dataProvider = "objLoaiHSo", dataProviderClass = HSoTLieuDataProvider.class)
    public void testChonLoaiHSo(String loaiHSo){
        hSoTLieuPage.chonLoaiHSo(loaiHSo);
        hSoTLieuPage.checkLoaiHSo(loaiHSo);
    }

    @Test(dataProvider = "objNamLapHSo", dataProviderClass = HSoTLieuDataProvider.class)
    public void testSetNamLapHSo(String nam){
        hSoTLieuPage.setNamLapHSo(nam);
        hSoTLieuPage.checkNamLapHSo(nam);
    }

    @Test(dataProvider = "objLinhVuc", dataProviderClass = HSoTLieuDataProvider.class)
    public void testChonLinhVuc(String linhVuc){
        hSoTLieuPage.chonLinhVuc(linhVuc);
        hSoTLieuPage.checkLinhVuc(linhVuc);
    }

    @Test(dataProvider = "objNoiTaoHSo", dataProviderClass = HSoTLieuDataProvider.class)
    public void testSetNoiTaoHSo(String noiTaoHSo){
        hSoTLieuPage.setNoiTaoHSo(noiTaoHSo);
        hSoTLieuPage.checkNoiTaoHSo(noiTaoHSo);
    }

    @Test(dataProvider = "objTieuDe", dataProviderClass = HSoTLieuDataProvider.class)
    public void testSetTieuDe(String tieuDe){
        hSoTLieuPage.setTieuDe(tieuDe);
        hSoTLieuPage.checkTieuDe(tieuDe);
    }

    @Test(dataProvider = "objFilterHSo", dataProviderClass = HSoTLieuDataProvider.class)
    public void testFilterHSo(String loaiHSo, String nam, String linhVuc, String noiTaoHSo, String tieuDe){
        hSoTLieuPage.chonLoaiHSo(loaiHSo);
        hSoTLieuPage.setNamLapHSo(nam);
        hSoTLieuPage.chonLinhVuc(linhVuc);
        hSoTLieuPage.setNoiTaoHSo(noiTaoHSo);
        hSoTLieuPage.setTieuDe(tieuDe);
        hSoTLieuPage.checkLoaiHSo(loaiHSo);
        hSoTLieuPage.checkNamLapHSo(nam);
        hSoTLieuPage.checkLinhVuc(linhVuc);
        hSoTLieuPage.checkNoiTaoHSo(noiTaoHSo);
        hSoTLieuPage.checkTieuDe(tieuDe);
    }

    @Test
    public void testXuatExcel(){
        hSoTLieuPage.clickXuatExcel();
        hSoTLieuPage.checkExcelFile();
    }

    @Test(dataProvider = "objNhapTuExcel", dataProviderClass = HSoTLieuDataProvider.class)
    public void testNhapTuExcel(String filePath) throws Exception {
        hSoTLieuPage.nhapTuExcel(filePath);
        Assert.assertEquals(hSoTLieuPage.getMessageAlert(), "Tải dữ liệu lên hoàn tất");
    }

    @Test(dataProvider = "objThemMoiHSo", dataProviderClass = HSoTLieuDataProvider.class)
    public void testThemMoiHSo(String maHSo, String soHSo, String soHop, String soTo, String tieuDe, String tuSo, String denSo, String tuNgay, String denNgay, String ngayLap, String linhVuc, String thoiGianBQuan, String tinhTrang, String ghiChu){
        hSoTLieuPage.themMoiHSo(maHSo, soHSo, soHop, soTo, tieuDe, tuSo, denSo, tuNgay, denNgay, ngayLap, linhVuc, thoiGianBQuan, tinhTrang, ghiChu);
        hSoTLieuPage.checkHSoDaThem(maHSo, soHSo, tieuDe, ngayLap, linhVuc, soHop);
    }

}
