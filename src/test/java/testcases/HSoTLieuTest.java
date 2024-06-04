package testcases;

import base.BaseTest;
import base.WebUI;
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
        homePage.openDuLieuMenu();
        hSoTLieuPage = new HSoTLieuPage();
    }

    @Test(dataProvider = "objLoaiHSo", dataProviderClass = HSoTLieuDataProvider.class)
    public void testChonLoaiHSo(String loaiHSo){
        hSoTLieuPage.chonLoaiHSoFilter(loaiHSo);
        hSoTLieuPage.checkLoaiHSoFilter(loaiHSo);
    }

    @Test(dataProvider = "objNamLapHSo", dataProviderClass = HSoTLieuDataProvider.class)
    public void testSetNamLapHSo(String nam){
        hSoTLieuPage.setNamLapHSoFilter(nam);
        hSoTLieuPage.checkNamLapHSoFilter(nam);
    }

    @Test(dataProvider = "objLinhVuc", dataProviderClass = HSoTLieuDataProvider.class)
    public void testChonLinhVuc(String linhVuc){
        hSoTLieuPage.chonLinhVucFilter(linhVuc);
        hSoTLieuPage.checkLinhVucFilter(linhVuc);
    }

    @Test(dataProvider = "objNoiTaoHSo", dataProviderClass = HSoTLieuDataProvider.class)
    public void testSetNoiTaoHSo(String noiTaoHSo){
        hSoTLieuPage.setNoiTaoHSoFilter(noiTaoHSo);
        hSoTLieuPage.checkNoiTaoHSoFilter(noiTaoHSo);
    }

    @Test(dataProvider = "objTieuDe", dataProviderClass = HSoTLieuDataProvider.class)
    public void testSetTieuDe(String tieuDe){
        hSoTLieuPage.setTieuDeFilter(tieuDe);
        hSoTLieuPage.checkTieuDeFilter(tieuDe);
    }

    @Test(dataProvider = "objFilterHSo", dataProviderClass = HSoTLieuDataProvider.class)
    public void testFilterHSo(String loaiHSo, String nam, String linhVuc, String noiTaoHSo, String tieuDe){
        hSoTLieuPage.chonLoaiHSoFilter(loaiHSo);
        hSoTLieuPage.setNamLapHSoFilter(nam);
        hSoTLieuPage.chonLinhVucFilter(linhVuc);
        hSoTLieuPage.setNoiTaoHSoFilter(noiTaoHSo);
        hSoTLieuPage.setTieuDeFilter(tieuDe);
        hSoTLieuPage.checkLoaiHSoFilter(loaiHSo);
        hSoTLieuPage.checkNamLapHSoFilter(nam);
        hSoTLieuPage.checkLinhVucFilter(linhVuc);
        hSoTLieuPage.checkNoiTaoHSoFilter(noiTaoHSo);
        hSoTLieuPage.checkTieuDeFilter(tieuDe);
    }

    @Test
    public void testXuatExcel(){
        hSoTLieuPage.clickXuatExcel();
    }

    @Test(dataProvider = "objNhapTuExcel", dataProviderClass = HSoTLieuDataProvider.class)
    public void testNhapTuExcel(String filePath) throws Exception {
        hSoTLieuPage.nhapTuExcel(filePath);
        Assert.assertEquals(hSoTLieuPage.getMessageAlert(), "Tải dữ liệu lên hoàn tất");
    }

    @Test(dataProvider = "objThemMoiHSo", dataProviderClass = HSoTLieuDataProvider.class)
    public void testThemMoiHSo(String maHSo, String soHSo, String soHop, String soTo, String tieuDe, String tuSo, String denSo, String tuNgay, String denNgay, String ngayLap, String linhVuc, String thoiGianBQuan, String tinhTrang, String ghiChu){
        hSoTLieuPage.themMoiHSo(maHSo, soHSo, soHop, soTo, tieuDe, tuSo, denSo, tuNgay, denNgay, ngayLap, linhVuc, thoiGianBQuan, tinhTrang, ghiChu);
        if (soHSo != "" && tieuDe != ""){
            hSoTLieuPage.checkDSachHSoDaThem(maHSo, soHSo, tieuDe, ngayLap, linhVuc, soHop);
            hSoTLieuPage.checkHSoDaThem(maHSo, soHSo, soHop, soTo, tieuDe, tuSo, denSo, tuNgay, denNgay, ngayLap, linhVuc, thoiGianBQuan, tinhTrang, ghiChu);
        }
    }

    @Test(dataProvider = "objChinhSuaHSo", dataProviderClass = HSoTLieuDataProvider.class)
    public void testCSuaHSoCu(String maHSo, String soHSo, String soHop, String soTo, String tieuDe, String tuSo, String denSo, String tuNgay, String denNgay, String ngayLap, String linhVuc, String thoiGianBQuan, String tinhTrang, String ghiChu){
        hSoTLieuPage.chinhSuaHSoCu(maHSo, soHSo, soHop, soTo, tieuDe, tuSo, denSo, tuNgay, denNgay, ngayLap, linhVuc, thoiGianBQuan, tinhTrang, ghiChu);
        hSoTLieuPage.checkDSachHSoDaThem(maHSo, soHSo, tieuDe, ngayLap, linhVuc, soHop);
        hSoTLieuPage.checkHSoDaThem(maHSo, soHSo, soHop, soTo, tieuDe, tuSo, denSo, tuNgay, denNgay, ngayLap, linhVuc, thoiGianBQuan, tinhTrang, ghiChu);
    }

    @Test(dataProvider = "objNhapVBanTuExcel", dataProviderClass = HSoTLieuDataProvider.class)
    public void testNhapVBanTuExcel(String maHSo, String filePath) throws Exception {
        hSoTLieuPage.nhapVBanTuExcel(maHSo, filePath);
    }

    @Test(dataProvider = "objXuatExcelDSachVBan", dataProviderClass = HSoTLieuDataProvider.class)
    public void testXuatExcelDSachVBan(String maHSo){
        hSoTLieuPage.clickXuatExcelDSachVBan(maHSo);
    }

    @Test(dataProvider = "objThemVBan", dataProviderClass = HSoTLieuDataProvider.class)
    public void testThemVBan(String maHSo, String soKHieu, String noiBHanh, String ngayBHanh, String soTo, String trichYeu, String ghiChu, String fileupload){
        hSoTLieuPage.timHSoDaThem(maHSo);
        hSoTLieuPage.themVBan(soKHieu, noiBHanh, ngayBHanh, soTo, trichYeu, ghiChu, fileupload);
        hSoTLieuPage.checkVBanDaThem(soKHieu, noiBHanh, ngayBHanh, soTo, trichYeu);
        hSoTLieuPage.checkTTinVBan(soKHieu, noiBHanh, ngayBHanh, soTo, trichYeu, ghiChu, fileupload);
    }

    @Test(dataProvider = "objXoaVBan", dataProviderClass = HSoTLieuDataProvider.class)
    public void testXoaVBan(String maHSo, String soKHieu, String action){
        hSoTLieuPage.timHSoDaThem(maHSo);
        hSoTLieuPage.xoaVban(soKHieu, action);
//        hSoTLieuPage.xoaTatCaVBan();
        WebUI.reloadPage();
    }

    @Test(dataProvider = "objChinhSuaVBan", dataProviderClass = HSoTLieuDataProvider.class)
    public void testChinhSuaVBan(String maHSo,String soKHieuCu, String soKHieuMoi, String noiBHanhMoi, String ngayBHanhMoi, String soToMoi, String trichYeuMoi, String ghiChuMoi, String fileupload){
        hSoTLieuPage.timHSoDaThem(maHSo);
        hSoTLieuPage.chinhSuaVBan(soKHieuCu, soKHieuMoi, noiBHanhMoi, ngayBHanhMoi, soToMoi, trichYeuMoi, ghiChuMoi, fileupload);
        hSoTLieuPage.checkVBanDaThem(soKHieuMoi, noiBHanhMoi, ngayBHanhMoi, soToMoi, trichYeuMoi);
        hSoTLieuPage.checkTTinVBan(soKHieuMoi, noiBHanhMoi, ngayBHanhMoi, soToMoi, trichYeuMoi, ghiChuMoi, fileupload);
    }

    @Test(dataProvider = "objThemFileVBan", dataProviderClass = HSoTLieuDataProvider.class)
    public void testThemFileVBan(String maHSo, String soKHieu, String fileupload){
        hSoTLieuPage.timHSoDaThem(maHSo);
        hSoTLieuPage.themFileVBan(soKHieu, fileupload);
        hSoTLieuPage.checkThemFileVBan(soKHieu, fileupload);
    }

    @Test(dataProvider = "objTaiVBan", dataProviderClass = HSoTLieuDataProvider.class)
    public void testTaiVBan(String maHSo, String soKHieu){
        hSoTLieuPage.taiVBan(maHSo, soKHieu);
    }
}
