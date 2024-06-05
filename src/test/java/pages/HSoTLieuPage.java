package pages;

import base.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.internal.invokers.InvokeMethodRunnable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.Keys.ENTER;

public class HSoTLieuPage extends CommonPage{
    By dropdownLoaiHSoFilter = By.xpath("//label[text()='Loại hồ sơ']/following-sibling::p-dropdown");
    By namLapHSoFilter = By.xpath("//label[contains(text(), 'Năm lập ')]/following-sibling::p-inputnumber//input");
    By dropdownLinhVucFilter = By.xpath("//label[text()='Lĩnh vực']/following-sibling::p-dropdown");
    By noiTaoHSoFilter = By.xpath("//label[text()='Nơi tạo hồ sơ']/following-sibling::input");
    By tieuDeFilter = By.xpath("(//label[text()='Tiêu đề']/following-sibling::input)[1]");
    By buttonNhapTuExcel = By.xpath("(//p-button[@label='Nhập từ excel']//button)[1]");
    By buttonDuyetFile = By.xpath("//p-fileupload[@chooselabel='Duyệt file']");
    By buttonXNhanNhapTuExcel = By.xpath("//button[@label='Xác nhận']/span");
    By messageAlert = By.xpath("//div[@role='alert']/div/div[2]");
    By buttonHuyBo = By.xpath("//span[text()='Hủy bỏ']/parent::button");
    By buttonDongY = By.xpath("//span[text()='Đồng ý']/parent::button");
    By buttonDong = By.xpath("//span[text()='Đóng']/parent::button");
    By buttonXuatExcel = By.xpath("//p-button[@label='Xuất excel']//button");
    By buttonThemMoi = By.xpath("//p-button[@label='Thêm mới']/button");
    By tableHSoTLieu = By.xpath("(//p-table)[1]");
    By trangThai = By.xpath("//div[text()=' Trạng thái: ']/h5");
    By maHSoThemMoi = By.xpath("//label[text()='Mã hồ sơ']/following-sibling::input");
    By soHSoThemMoi = By.xpath("//label[text()='Số hồ sơ']/following-sibling::input");
    By soHopThemMoi = By.xpath("//label[text()='Số hộp']/following-sibling::input");
    By soToThemMoi = By.xpath("//label[text()='Số tờ']/following-sibling::p-inputnumber//input");
    By tieuDeThemMoi = By.xpath("//label[text()='Tiêu đề']/following-sibling::textarea");
//    By tieuDeThemMoi = By.xpath("//label[text()='Tiêu đề']/following-sibling::input");
    By tuSoThemMoi = By.xpath("//label[text()='Từ số']/following-sibling::p-inputnumber//input");
    By denSoThemMoi = By.xpath("//label[text()='Đến số']/following-sibling::p-inputnumber//input");
    By tuNgayThemMoi = By.xpath("//label[text()='Từ ngày']/following-sibling::p-calendar");
    By denNgayThemMoi = By.xpath("//label[text()='Đến ngày']/following-sibling::p-calendar");
    By ngayLapThemMoi = By.xpath("//label[text()='Ngày lập']/following-sibling::p-calendar");
    By dropdownLinhVucThemMoi = By.xpath("//p-dropdown[@placeholder='Chọn lĩnh vực']");
    By dropdownTGianBQuan = By.xpath("//p-dropdown[@placeholder='Thời gian bảo quản']");
    By dropdownTinhTrang = By.xpath("//p-dropdown[@placeholder='Tình trạng']");
    By ghiChuThemMoi = By.xpath("//label[text()='Ghi chú']/following-sibling::textarea");
    By buttonLuu = By.xpath("//span[text()='Lưu']");
    By buttonNhapVBanTuExcel = By.xpath("//span[text()='Nhập từ excel văn bản']");
    By buttonXuatExcelDSachVBan = By.xpath("//span[text()='Xuất excel văn bản']");
    By buttonThemVBan = By.xpath("//span[text()='Thêm văn bản']");
    By soKyHieuVB = By.xpath("//label[text()='Số ký hiệu']/following-sibling::input");
    By noiBHanhVB = By.xpath("//label[text()='Nơi ban hành']/following-sibling::textarea");
    By ngayBanHanhVB = By.xpath("//label[text()='Ngày ban hành']/following-sibling::p-calendar");
    By soToVB = By.xpath("//label[text()='Tờ số']/following-sibling::input");
    By trichYeuVB = By.xpath("//label[text()='Trích yếu']/following-sibling::textarea");
    By ghichuVB = By.xpath("//label[text()='Trích yếu']/parent::div/following-sibling::div[1]/textarea");
    By buttonLuuVB = By.xpath("(//button/span[text()='Lưu'])[2]");
    By tableVBan = By.xpath("(//p-table)[2]");
    public void chonLoaiHSoFilter(String loaiHSo){
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(buttonXuatExcel, 5);
        WebUI.moveToElement(buttonXuatExcel);
        WebUI.moveToElement(dropdownLoaiHSoFilter);
        WebUI.scrollToElementAtBottom(dropdownLoaiHSoFilter);
        WebUI.waitForElementVisible(dropdownLoaiHSoFilter, 100);
        WebUI.waitForElementClickable(dropdownLoaiHSoFilter, 100);
        AngularDropdown chonLoaiHSo = new AngularDropdown(dropdownLoaiHSoFilter);
        chonLoaiHSo.setValues(loaiHSo);
    }

    public void checkLoaiHSoFilter(String loaiHSo){
        WebUI.waitForPageLoaded();
        AngularTable table = new AngularTable(tableHSoTLieu, "tbody");
        WebUI.scrollToElementAtBottom(tableHSoTLieu);
        AngularPaginator paginator = table.paginator;
        int pageCount = paginator.getPagesCount();
        System.out.println(pageCount);
        for (int i = 0; i < pageCount; i++){
            List<String> loaiHSoList = table.getDisplayColumn(8);
            System.out.println(loaiHSoList);
            for (String loaiHSoItem: loaiHSoList){
                System.out.println(loaiHSoItem);
                if (loaiHSo == "Tất cả"){
                    Assert.assertTrue(loaiHSoItem == "Mới tạo" || loaiHSoItem == "Lãnh đạo xét duyệt" || loaiHSoItem == "Trả lại" || loaiHSoItem == "Chuyển tổ văn thư" || loaiHSoItem != "Đã lưu kho");
                }
                else {
                    Assert.assertEquals(loaiHSoItem, loaiHSo);
                }
            }
            if (i < pageCount - 1) {
                WebUI.clickElement(By.xpath("(//button[contains(@class,'p-paginator-next')])[1]"));
            }
        }
        if (pageCount > 1){
            WebUI.clickElement(By.xpath("(//button[contains(@class,'p-paginator-first')])[1]"));
        }
    }

    public void setNamLapHSoFilter(String nam){
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(buttonXuatExcel, 5);
        WebUI.moveToElement(buttonXuatExcel);
        WebUI.scrollToElementAtBottom(namLapHSoFilter);
        WebUI.moveToElement(namLapHSoFilter);
        WebUI.waitForElementVisible(namLapHSoFilter, 100);
        WebUI.clearText(namLapHSoFilter);
        WebUI.setText(namLapHSoFilter, nam, ENTER);
        WebUI.waitForTextToBeChanged(tableHSoTLieu);
    }

    public void checkNamLapHSoFilter(String nam) {
        WebUI.waitForPageLoaded();
        AngularTable table = new AngularTable(tableHSoTLieu, "tbody");
        WebUI.scrollToElementAtBottom(tableHSoTLieu);
        AngularPaginator paginator = table.paginator;
        int pageCount = paginator.getPagesCount();
        System.out.println(pageCount);
        for (int i = 0; i < pageCount; i++){
            List<String> namLapHSoList = table.getDisplayColumn(3);
            System.out.println(namLapHSoList);
            for (String namLapHSoItem: namLapHSoList){
                System.out.println(namLapHSoItem);
                Assert.assertTrue(namLapHSoItem.contains(nam));
            }
            if (i < pageCount - 1) {
                WebUI.clickElement(By.xpath("(//button[contains(@class,'p-paginator-next')])[1]"));
            }
        }
        if (pageCount > 1){
            WebUI.clickElement(By.xpath("(//button[contains(@class,'p-paginator-first')])[1]"));
        }
    }

    public void chonLinhVucFilter(String linhVuc){
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(buttonXuatExcel, 5);
        WebUI.moveToElement(buttonXuatExcel);
        WebUI.scrollToElementAtBottom(dropdownLinhVucFilter);
        WebUI.moveToElement(dropdownLinhVucFilter);
        WebUI.waitForElementVisible(dropdownLinhVucFilter, 100);
        AngularDropdown chonLinhVuc = new AngularDropdown(dropdownLinhVucFilter);
        chonLinhVuc.setValues(linhVuc);
    }

    public void checkLinhVucFilter(String linhVuc) {
        WebUI.waitForPageLoaded();
        AngularTable table = new AngularTable(tableHSoTLieu, "tbody");
        WebUI.scrollToElementAtBottom(tableHSoTLieu);
        AngularPaginator paginator = table.paginator;
        int pageCount = paginator.getPagesCount();
        System.out.println(pageCount);
        for (int i = 0; i < pageCount; i++){
            List<String> linhVucList = table.getDisplayColumn(4);
            System.out.println(linhVucList);
            for (String linhVucItem: linhVucList){
                System.out.println(linhVucItem);
                if (linhVuc == "Tất cả"){
                    Assert.assertTrue(linhVucItem != "");
                }
                else Assert.assertEquals(linhVucItem, linhVuc);
            }
            if (i < pageCount - 1) {
                WebUI.clickElement(By.xpath("(//button[contains(@class,'p-paginator-next')])[1]"));
            }
        }
        if (pageCount > 1){
            WebUI.clickElement(By.xpath("(//button[contains(@class,'p-paginator-first')])[1]"));
        }
    }

    public void setNoiTaoHSoFilter(String noiTao){
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(buttonXuatExcel, 5);
        WebUI.moveToElement(buttonXuatExcel);
        WebUI.moveToElement(noiTaoHSoFilter);
        WebUI.waitForElementVisible(noiTaoHSoFilter, 100);
        WebUI.clearText(noiTaoHSoFilter);
        WebUI.setText(noiTaoHSoFilter, noiTao);
    }

    public void checkNoiTaoHSoFilter(String noiTaoHSo) {
        WebUI.waitForPageLoaded();
        AngularTable table = new AngularTable(tableHSoTLieu, "tbody");
        WebUI.scrollToElementAtBottom(tableHSoTLieu);
        AngularPaginator paginator = table.paginator;
        int pageCount = paginator.getPagesCount();
        System.out.println(pageCount);
        for (int i = 0; i < pageCount; i++){
            List<String> noiTaoHSoList = table.getDisplayColumn(5);
            System.out.println(noiTaoHSoList);
            for (String noiTaoHSoItem: noiTaoHSoList){
                System.out.println(noiTaoHSoItem);
                if (noiTaoHSo == ""){
                    Assert.assertTrue(noiTaoHSoItem != "");
                }
                else Assert.assertTrue(noiTaoHSoItem.contains(noiTaoHSo));
            }
            if (i < pageCount - 1) {
                WebUI.waitForElementClickable(By.xpath("(//button[contains(@class,'p-paginator-next')])[1]"));
                WebUI.clickElement(By.xpath("(//button[contains(@class,'p-paginator-next')])[1]"));
            }
        }
        if (pageCount > 1){
            WebUI.clickElement(By.xpath("(//button[contains(@class,'p-paginator-first')])[1]"));
        }
    }

    public void setTieuDeFilter(String tieuDeHSo){
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(buttonXuatExcel, 5);
        WebUI.moveToElement(buttonXuatExcel);
        WebUI.moveToElement(tieuDeFilter);
        WebUI.waitForElementVisible(tieuDeFilter, 100);
        WebUI.clearText(tieuDeFilter);
        WebUI.setText(tieuDeFilter, tieuDeHSo);
        WebUI.waitForTextToBeChanged(tableHSoTLieu);
    }

    public void checkTieuDeFilter(String tieuDeHSo) {
        WebUI.waitForPageLoaded(100);
        WebUI.waitForPeriod(10);
        WebUI.waitForElementVisible(tableHSoTLieu, 100);
        AngularTable table = new AngularTable(tableHSoTLieu, "tbody");
        WebUI.scrollToElementAtBottom(tableHSoTLieu);
        AngularPaginator paginator = table.paginator;
        int pageCount = paginator.getPagesCount();
        System.out.println(pageCount);
        for (int i = 0; i < pageCount; i++){
            List<String> tieuDeList = table.getDisplayColumn(2);
            System.out.println(tieuDeList);
            for (String tieuDeItem: tieuDeList){
                System.out.println(tieuDeItem);
                if (tieuDeHSo == ""){
                    Assert.assertTrue(tieuDeItem != "");
                }
                else Assert.assertTrue(tieuDeItem.contains(tieuDeHSo));
            }
            if (i < pageCount - 1) {
                WebUI.clickElement(By.xpath("(//button[contains(@class,'p-paginator-next')])[1]"));
            }
        }
        if (pageCount > 1){
            WebUI.clickElement(By.xpath("(//button[contains(@class,'p-paginator-first')])[1]"));
        }
    }

    public void clickXuatExcel(){
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(buttonXuatExcel, 100);
        WebUI.moveToElement(buttonXuatExcel);
        WebUI.clickElement(buttonXuatExcel);
        WebUI.waitForPeriod(30);
        Assert.assertTrue(WebUI.verifyFileDownloadedWithJS_Contains("DSHoSo"));
    }

    public void nhapTuExcel(String filePath) throws Exception {
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(buttonNhapTuExcel, 100);
        WebUI.moveToElement(buttonNhapTuExcel);
        WebUI.clickElement(buttonNhapTuExcel);
        WebUI.waitForElementVisible(buttonDuyetFile, 100);
        AngularFileUpload fileUpload = new AngularFileUpload(buttonDuyetFile);
        try {
            fileUpload.upload(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        WebUI.waitForElementVisible(buttonXNhanNhapTuExcel, 100);
        WebUI.clickElement(buttonXNhanNhapTuExcel);
        WebUI.waitForElementVisible(buttonDongY, 100);
        WebUI.clickElement(buttonDongY);
    }

    public String getMessageAlert(){
        WebUI.waitForElementVisible(messageAlert, 100);
        return WebUI.getTextElement(messageAlert);
    }

    public void clickButtonThemMoi(){
        WebUI.waitForPageLoaded(500);
        WebUI.waitForElementVisible(buttonThemMoi, 100);
        WebUI.moveToElement(buttonThemMoi);
        WebUI.clickElement(buttonThemMoi);
    }

    public void nhapTTinHSo(String soHSo, String soHop, String soTo, String tieuDe, String tuSo, String denSo, String tuNgay, String denNgay, String ngayLap, String linhVuc, String thoiGianBQuan, String tinhTrang, String ghiChu){
        WebUI.setText(soHSoThemMoi, soHSo);
        WebUI.setText(soHopThemMoi, soHop);
        WebUI.setText(soToThemMoi, soTo);
        WebUI.setText(tieuDeThemMoi, tieuDe);
        WebUI.setText(tuSoThemMoi, tuSo);
        WebUI.setText(denSoThemMoi, denSo);
        WebUI.scrollToElementAtBottom(tuNgayThemMoi);
        AngularCalendar tuNgayCalendar = new AngularCalendar(tuNgayThemMoi);
        tuNgayCalendar.setValues(tuNgay);
        AngularCalendar denNgayCalendar = new AngularCalendar(denNgayThemMoi);
        denNgayCalendar.setValues(denNgay);
        AngularCalendar ngayLapCalendar = new AngularCalendar(ngayLapThemMoi);
        ngayLapCalendar.setValues(ngayLap);
        WebUI.waitForElementClickable(dropdownLinhVucThemMoi, 100);
        AngularDropdown linhVucDropdown = new AngularDropdown(dropdownLinhVucThemMoi);
        linhVucDropdown.setOptions(linhVuc);
        AngularDropdown thoiGianBQuanDropdown = new AngularDropdown(dropdownTGianBQuan);
        thoiGianBQuanDropdown.setValues(thoiGianBQuan);
        AngularDropdown tinhTrangDropdown = new AngularDropdown(dropdownTinhTrang);
        tinhTrangDropdown.setValues(tinhTrang);
        WebUI.setText(ghiChuThemMoi, ghiChu);
    }

    public void xoaTTinHSo(){
        WebUI.clearText(soHSoThemMoi);
        WebUI.clearText(soHopThemMoi);
        WebUI.clearText(soToThemMoi);
        WebUI.clearText(tieuDeThemMoi);
        WebUI.clearText(tuSoThemMoi);
        WebUI.clearText(denSoThemMoi);
        WebUI.clearText(ghiChuThemMoi);
    }

    public void clickLuuThemMoi(String soHSo, String tieuDe){
        WebUI.clickElement(buttonLuu);
        if(soHSo == ""){
            System.out.println("Không tìm thấy tiền tố Đơn vị hoặc Phòng ban của người dùng");
            WebUI.waitForElementVisible(buttonDongY, 100);
            WebUI.clickElement(buttonDongY);
            String message = getMessageAlert();
            Assert.assertTrue(message.contains("Không tìm thấy tiền tố Đơn vị hoặc Phòng ban của người dùng"));
        } else if (tieuDe == ""){
            System.out.println("Bạn chưa nhập tiêu đề");
            String message = getMessageAlert();
            Assert.assertTrue(message.contains("Bạn chưa nhập tiêu đề"));
        } else{
            System.out.println("Ghi nhận dữ liệu thành công");
            WebUI.waitForElementVisible(buttonDongY, 100);
            WebUI.waitForElementClickable(buttonDongY, 100);
            WebUI.clickElement(buttonDongY);
            String message = getMessageAlert();
            Assert.assertTrue(message.contains("Ghi nhận dữ liệu thành công"));
        }
    }

    public void themMoiHSo(String maHSo, String soHSo, String soHop, String soTo, String tieuDe, String tuSo, String denSo, String tuNgay, String denNgay, String ngayLap, String linhVuc, String thoiGianBQuan, String tinhTrang, String ghiChu){
        clickButtonThemMoi();
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(trangThai, 100);
        WebUI.getTextElement(trangThai);
        Assert.assertTrue(WebUI.getTextElement(trangThai).contains("THÊM MỚI"));
        WebUI.setText(maHSoThemMoi, maHSo);
        nhapTTinHSo(soHSo, soHop, soTo, tieuDe, tuSo, denSo, tuNgay, denNgay, ngayLap, linhVuc, thoiGianBQuan, tinhTrang, ghiChu);
        clickLuuThemMoi(soHSo, tieuDe);
        WebUI.reloadPage();
    }

    public void timHSoDaThem(String maHSo){
        WebUI.waitForPageLoaded(100);
        WebUI.waitForElementVisible(buttonThemMoi, 100);
        WebUI.moveToElement(buttonThemMoi);
        WebUI.waitForElementVisible(tableHSoTLieu, 100);
        AngularTable table = new AngularTable(tableHSoTLieu, "tbody");
        WebUI.scrollToElementAtBottom(tableHSoTLieu);
        AngularPaginator paginator = table.paginator;
        int pageCount = paginator.getPagesCount();
        System.out.println(pageCount);
        outerloop:
        for (int i = 0; i < pageCount; i++){
            List<String> maHSoList = table.getDisplayColumn(0);
            for (String maHSoItem: maHSoList){
                if (maHSoItem.contains("SPCIT.CNPM." + maHSo)){
                    WebUI.clickElement(By.xpath("//td[contains(text(), 'SPCIT.CNPM." + maHSo + "')]/following-sibling::td[9]//button"));
                    break outerloop;
                }
            }
            if (i < pageCount - 1) {
                WebUI.clickElement(By.xpath("(//button[contains(@class,'p-paginator-next')])[1]"));
            }
        }
    }

    public void chinhSuaHSoCu(String maHSo, String soHSo, String soHop, String soTo, String tieuDe, String tuSo, String denSo, String tuNgay, String denNgay, String ngayLap, String linhVuc, String thoiGianBQuan, String tinhTrang, String ghiChu){
        timHSoDaThem(maHSo);
        WebUI.waitForElementVisible(trangThai, 100);
        WebUI.getTextElement(trangThai);
        Assert.assertTrue(WebUI.getTextElement(trangThai).contains("CẬP NHẬT"));
        xoaTTinHSo();
        nhapTTinHSo(soHSo, soHop, soTo, tieuDe, tuSo, denSo, tuNgay, denNgay, ngayLap, linhVuc, thoiGianBQuan, tinhTrang, ghiChu);
        WebUI.moveToElement(buttonLuu);
        WebUI.waitForElementClickable(buttonLuu, 100);
        WebUI.clickElement(buttonLuu);
        String message = getMessageAlert();
        Assert.assertTrue(message.contains("Ghi nhận dữ liệu thành công"));
    }

    public void checkDSachHSoDaThem(String maHSo, String soHSo, String tieuDe, String ngayLap, String linhVuc, String soHop){
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(buttonThemMoi, 100);
        WebUI.moveToElement(buttonThemMoi);
        WebUI.waitForElementVisible(tableHSoTLieu, 100);
        AngularTable table = new AngularTable(tableHSoTLieu, "tbody");
        WebUI.scrollToElementAtBottom(tableHSoTLieu);
        AngularPaginator paginator = table.paginator;
        int pageCount = paginator.getPagesCount();
        System.out.println(pageCount);
        List<String> maHSoList = table.getDisplayColumn(0);
        Integer index = maHSoList.indexOf("SPCIT.CNPM." + maHSo);
        System.out.println(index);
        List<String> thongTinHSo = table.getDisplayRow(index);
        outerloop:
        for (int i = 0; i < pageCount; i++){
            if (thongTinHSo.get(0).contains("SPCIT.CNPM." + maHSo)){
                Assert.assertEquals(thongTinHSo.get(1), soHSo);
                Assert.assertEquals(thongTinHSo.get(2), tieuDe);
                Assert.assertEquals(thongTinHSo.get(3), ngayLap);
                Assert.assertEquals(thongTinHSo.get(4), linhVuc.replace("-", ""));
                Assert.assertEquals(thongTinHSo.get(6), soHop);
                Assert.assertEquals(thongTinHSo.get(8), "Mới tạo");
                System.out.println("Hồ sơ đã được thêm mới thành công");
                break outerloop;
            }
            if (i < pageCount - 1) {
                WebUI.clickElement(By.xpath("(//button[contains(@class,'p-paginator-next')])[1]"));
            }
        }
    }

    public void checkHSoDaThem(String maHSo, String soHSo, String soHop, String soTo, String tieuDe, String tuSo, String denSo, String tuNgay, String denNgay, String ngayLap, String linhVuc, String thoiGianBQuan, String tinhTrang, String ghiChu){
        timHSoDaThem(maHSo);
        WebUI.waitForElementVisible(trangThai, 100);
        Assert.assertTrue(WebUI.getTextElement(trangThai).contains("CẬP NHẬT"));
        Assert.assertEquals(WebUI.getAttributeElement(maHSoThemMoi, "value"), "SPCIT.CNPM." + maHSo);
        Assert.assertEquals(WebUI.getAttributeElement(soHSoThemMoi, "value"), soHSo);
        Assert.assertEquals(WebUI.getAttributeElement(soHopThemMoi, "value"), soHop);
        Assert.assertEquals(WebUI.getAttributeElement(soToThemMoi, "value"), soTo);
        Assert.assertEquals(WebUI.getAttributeElement(tieuDeThemMoi, "value"), tieuDe);
        Assert.assertEquals(WebUI.getAttributeElement(tuSoThemMoi, "value"), tuSo);
        Assert.assertEquals(WebUI.getAttributeElement(denSoThemMoi, "value"), denSo);
        WebUI.waitForElementVisible(tuNgayThemMoi, 100);
        AngularCalendar tuNgayCalendar = new AngularCalendar(tuNgayThemMoi);
        Assert.assertEquals(tuNgayCalendar.getSelectedValue(), tuNgay);
        WebUI.waitForElementVisible(denNgayThemMoi, 100);
        AngularCalendar denNgayCalendar = new AngularCalendar(denNgayThemMoi);
        Assert.assertEquals(denNgayCalendar.getSelectedValue(), denNgay);
        WebUI.waitForElementVisible(ngayLapThemMoi, 100);
        AngularCalendar ngayLapCalendar = new AngularCalendar(ngayLapThemMoi);
        Assert.assertEquals(ngayLapCalendar.getSelectedValue(), ngayLap);
        WebUI.waitForElementVisible(dropdownLinhVucThemMoi, 100);
        AngularDropdown linhVucDropdown = new AngularDropdown(dropdownLinhVucThemMoi);
        Assert.assertEquals(linhVucDropdown.getSelectedOption(), linhVuc);
        WebUI.waitForElementVisible(dropdownTGianBQuan, 100);
        AngularDropdown thoiGianBQuanDropdown = new AngularDropdown(dropdownTGianBQuan);
        Assert.assertEquals(thoiGianBQuanDropdown.getSelectedValue(), thoiGianBQuan);
        WebUI.waitForElementVisible(dropdownTinhTrang, 100);
        AngularDropdown tinhTrangDropdown = new AngularDropdown(dropdownTinhTrang);
        Assert.assertEquals(tinhTrangDropdown.getSelectedValue(), tinhTrang);
        Assert.assertEquals(WebUI.getAttributeElement(ghiChuThemMoi, "value"), ghiChu);
    }

    public boolean compareDropdownLVuc(List<String> listOptions){
        clickButtonThemMoi();
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(dropdownLinhVucThemMoi, 100);
        AngularDropdown linhVucDropdown = new AngularDropdown(dropdownLinhVucThemMoi);
        List<String> options = linhVucDropdown.getOptions();
        System.out.println(options);
        System.out.println(listOptions);
        return options.equals(listOptions);
    }

    public boolean compareDropdownTGianBQuan(List<String> listOptions){
        clickButtonThemMoi();
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(dropdownTGianBQuan, 100);
        AngularDropdown tGianBQuanDropdown = new AngularDropdown(dropdownTGianBQuan);
        List<String> options = tGianBQuanDropdown.getValues();
        System.out.println(options);
        System.out.println(listOptions);
        return options.equals(listOptions);
    }

    public boolean compareDropdownTTrangHSo(List<String> listOptions){
        clickButtonThemMoi();
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(dropdownTinhTrang, 100);
        AngularDropdown tinhTrangDropdown = new AngularDropdown(dropdownTinhTrang);
        List<String> options = tinhTrangDropdown.getValues();
        System.out.println(options);
        System.out.println(listOptions);
        return options.equals(listOptions);
    }

    public void nhapVBanTuExcel(String maHSo, String filePath){
        timHSoDaThem(maHSo);
        WebUI.waitForElementVisible(buttonNhapVBanTuExcel, 100);
        WebUI.moveToElement(buttonNhapVBanTuExcel);
        WebUI.clickElement(buttonNhapVBanTuExcel);
        WebUI.waitForElementVisible(buttonDuyetFile, 100);
        AngularFileUpload fileUpload = new AngularFileUpload(buttonDuyetFile);
        try {
            fileUpload.upload(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        WebUI.waitForElementVisible(buttonXNhanNhapTuExcel, 100);
        WebUI.clickElement(buttonXNhanNhapTuExcel);
        WebUI.waitForElementVisible(buttonDongY, 100);
        WebUI.clickElement(buttonDongY);
        WebUI.clickElement(buttonDong);
        WebUI.waitForElementVisible(messageAlert, 100);
        Assert.assertTrue(getMessageAlert().contains("Tải dữ liệu lên hoàn tất"));
    }

    public void clickXuatExcelDSachVBan(String maHSo){
        timHSoDaThem(maHSo);
        WebUI.waitForElementVisible(buttonXuatExcelDSachVBan, 100);
        WebUI.moveToElement(buttonXuatExcelDSachVBan);
        WebUI.clickElement(buttonXuatExcelDSachVBan);
        Assert.assertTrue(WebUI.verifyFileDownloadedWithJS_Contains("DSVanBan"));
    }

    public void nhapTTinVBan(String soKHieu, String noiBHanh, String ngayBHanh, String soTo, String trichYeu, String ghiChu, String fileupload){
        WebUI.waitForElementVisible(soKyHieuVB, 100);
        WebUI.setText(soKyHieuVB, soKHieu);
        WebUI.setText(noiBHanhVB, noiBHanh);
        WebUI.scrollToElementAtTop(buttonDuyetFile);
        AngularCalendar ngayBHanhVB = new AngularCalendar(ngayBanHanhVB);
        ngayBHanhVB.setValues(ngayBHanh);
        WebUI.setText(soToVB, soTo);
        WebUI.setText(trichYeuVB, trichYeu);
        WebUI.setText(ghichuVB, ghiChu);
        AngularFileUpload fileUpload = new AngularFileUpload(buttonDuyetFile);
        try {
            fileUpload.upload(fileupload);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> fileNames = fileUpload.getFileNames();
        File file = new File(fileupload);
        String fileName = file.getName();
        Assert.assertTrue(fileNames.contains(fileName));
    }

    public void themVBan(String soKHieu, String noiBHanh, String ngayBHanh, String soTo, String trichYeu, String ghiChu, String fileupload){
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(buttonThemVBan, 100);
        WebUI.moveToElement(buttonThemVBan);
        WebUI.clickElement(buttonThemVBan);
        nhapTTinVBan(soKHieu, noiBHanh, ngayBHanh, soTo, trichYeu, ghiChu, fileupload);
        WebUI.waitForPageLoaded(100);
        WebUI.waitForElementVisible(buttonLuuVB, 100);
        WebUI.clickElement(buttonLuuVB);
        while (!getMessageAlert().contains("Ghi nhận dữ liệu thành công")){
            WebUI.waitForPeriod(30);
            WebUI.clickElement(buttonLuuVB);
        }
        Assert.assertTrue(getMessageAlert().contains("Ghi nhận dữ liệu thành công"));
    }

    public void xoaTTinVBan(){
        WebUI.clearText(soKyHieuVB);
        WebUI.clearText(noiBHanhVB);
        WebUI.clearText(soToVB);
        WebUI.clearText(trichYeuVB);
        WebUI.clearText(ghichuVB);
    }

    public void xoaFileDinhKem(){
        List<WebElement> deleteFileButtonsElements = WebUI.findElements(By.xpath("//p-fileupload/following-sibling::div//button"));
        for (WebElement deleteFileButton: deleteFileButtonsElements){
            deleteFileButton.click();
        }
        WebUI.clickElement(buttonDongY);
        Assert.assertTrue(getMessageAlert().contains("Quá trình xóa file thành công"));
    }

    public void checkVBanDaThem(String soKHieu, String noiBHanh, String ngayBHanh, String soTo, String trichYeu){
        Assert.assertEquals(getMessageAlert(), "Ghi nhận dữ liệu thành công");
        WebUI.waitForPageLoaded();
        AngularTable table = new AngularTable(tableVBan, "tbody");
        WebUI.waitForElementVisible(tableVBan, 100);
        AngularPaginator paginator = table.paginator;
        int pageCount = paginator.getPagesCount();
        System.out.println(pageCount);
        for (int i = 0; i < pageCount; i++){
            List<String> soKHieuList = table.getDisplayColumn(0);
            System.out.println(soKHieuList);
            for (String soKHieuItem: soKHieuList){
                System.out.println(soKHieuItem);
                if (soKHieuItem.equals(soKHieu)){
                    List<String> thongTinVBan = table.getDisplayRow(soKHieuList.indexOf(soKHieuItem));
//                    String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
//                    String[] ngayBHanhArr = ngayBHanh.split("/");
//                    if (ngayBHanhArr[0].startsWith("0")){
//                        ngayBHanhArr[0] = ngayBHanhArr[0].substring(1);
//                    }
//                    ngayBHanh = months[Integer.parseInt(ngayBHanhArr[1]) - 1] + " " + ngayBHanhArr[0] + ", " + ngayBHanhArr[2];
                    Assert.assertTrue(thongTinVBan.get(1).contains(ngayBHanh));
                    Assert.assertEquals(thongTinVBan.get(2), noiBHanh);
                    Assert.assertEquals(thongTinVBan.get(3), trichYeu);
                    Assert.assertEquals(thongTinVBan.get(4), soTo);
                    break;
                }
            }
            if (i < pageCount - 1) {
                WebUI.clickElement(By.xpath("(//button[contains(@class,'p-paginator-next')])[1]"));
            }
        }
    }

    public void checkTTinVBan(String soKHieu, String noiBHanh, String ngayBHanh, String soTo, String trichYeu, String ghiChu, String fileupload){
        timVBanDaThem(soKHieu);
        WebUI.waitForElementVisible(soKyHieuVB, 100);
        Assert.assertEquals(WebUI.getAttributeElement(soKyHieuVB, "value"), soKHieu);
        Assert.assertEquals(WebUI.getAttributeElement(noiBHanhVB, "value"), noiBHanh);
        WebUI.scrollToElementAtTop(soKyHieuVB);
        AngularCalendar ngayBHanhVB = new AngularCalendar(ngayBanHanhVB);
        Assert.assertEquals(ngayBHanhVB.getSelectedValue(), ngayBHanh);
        WebUI.clickElement(soToVB);
        Assert.assertEquals(WebUI.getAttributeElement(soToVB, "value"), soTo);
        Assert.assertEquals(WebUI.getAttributeElement(trichYeuVB, "value"), trichYeu);
        Assert.assertEquals(WebUI.getAttributeElement(ghichuVB, "value"), ghiChu);
        AngularFileUpload fileUpload = new AngularFileUpload(buttonDuyetFile);
        List<WebElement> listFiles = WebUI.findElements(By.xpath("//div[@class='listfile-upload']//a"));
        List<String> fileNames = new ArrayList<>();
        for (WebElement file: listFiles){
            fileNames.add(file.getText());
        }
        File file = new File(fileupload);
        String fileName = file.getName();
        Assert.assertTrue(fileNames.contains(fileName));
    }

    public void xoaVban(String soKHieu, String action){
        WebUI.waitForPageLoaded();
        AngularTable table = new AngularTable(tableVBan, "tbody");
        WebUI.waitForElementVisible(tableVBan, 100);
        AngularPaginator paginator = table.paginator;
        int pageCount = paginator.getPagesCount();
        System.out.println(pageCount);
        outterloop:
        for (int i = 0; i < pageCount; i++){
            List<String> soKHieuList = table.getDisplayColumn(0);
            System.out.println(soKHieuList);
            for (String soKHieuItem: soKHieuList){
                System.out.println(soKHieuItem);
                if (soKHieuItem.contains(soKHieu)){
                    WebUI.clickElement(By.xpath("//td[contains(text(), '" + soKHieu + "')]/following-sibling::td[5]//p-button[@icon='pi pi-times']//button"));
                    if (action.equals("Xác nhận")) {
                        WebUI.waitForElementVisible(buttonDongY, 100);
                        WebUI.clickElement(buttonDongY);
//                        Assert.assertEquals(getMessageAlert(), "Ghi nhận dữ liệu thành công");
                        WebUI.waitForTextToBeChanged(tableVBan);
                        Assert.assertFalse(table.getDisplayColumn(0).contains(soKHieu));
                    } else{
                        WebUI.waitForElementVisible(buttonHuyBo, 100);
                        WebUI.clickElement(buttonHuyBo);
                        Assert.assertTrue(table.getDisplayColumn(0).contains(soKHieu));
                    }
                    break outterloop;
                }
            }
            if (i < pageCount - 1) {
                WebUI.clickElement(By.xpath("(//button[contains(@class,'p-paginator-next')])[1]"));
            }
        }
    }

    public void xoaTatCaVBan(){
        WebUI.waitForPageLoaded();
        AngularTable table = new AngularTable(tableVBan, "tbody");
        WebUI.waitForElementVisible(tableVBan, 100);
        AngularPaginator paginator = table.paginator;
        int pageCount = paginator.getPagesCount();
        System.out.println(pageCount);
        for (int i = 0; i < pageCount; i++){
            List<String> soKHieuList = table.getDisplayColumn(0);
            System.out.println(soKHieuList);
            for (String soKHieuItem: soKHieuList){
                System.out.println(soKHieuItem);
                WebUI.clickElement(By.xpath("//td[contains(text(), '" + soKHieuItem + "')]/following-sibling::td[5]//p-button[@icon='pi pi-times']//button"));
                WebUI.waitForElementVisible(buttonDongY, 100);
                WebUI.clickElement(buttonDongY);
                WebUI.waitForTextToBeChanged(tableVBan);
            }
        }
    }

    public void timVBanDaThem(String soKHieu){
        WebUI.waitForPageLoaded();
        AngularTable table = new AngularTable(tableVBan, "tbody");
        WebUI.waitForElementVisible(tableVBan, 100);
        AngularPaginator paginator = table.paginator;
        int pageCount = paginator.getPagesCount();
        System.out.println(pageCount);
        outterloop:
        for (int i = 0; i < pageCount; i++){
            List<String> soKHieuList = table.getDisplayColumn(0);
            System.out.println(soKHieuList);
            for (String soKHieuItem: soKHieuList){
                System.out.println(soKHieuItem);
                if (soKHieuItem.contains(soKHieu)){
                    WebUI.clickElement(By.xpath("//td[text()='" + soKHieu + "']/following-sibling::td[5]//p-button[@icon='pi pi-pencil']//button"));
                    break outterloop;
                }
            }
            if (i < pageCount - 1) {
                WebUI.clickElement(By.xpath("(//button[contains(@class,'p-paginator-next')])[1]"));
            }
        }
    }

    public Boolean checkVBanDaThem(String soKHieu){
        WebUI.waitForPageLoaded();
        AngularTable table = new AngularTable(tableVBan, "tbody");
        WebUI.waitForElementVisible(tableVBan, 100);
        AngularPaginator paginator = table.paginator;
        int pageCount = paginator.getPagesCount();
        System.out.println(pageCount);
        for (int i = 0; i < pageCount; i++){
            List<String> soKHieuList = table.getDisplayColumn(0);
            System.out.println(soKHieuList);
            for (String soKHieuItem: soKHieuList){
                System.out.println(soKHieuItem);
                if (soKHieuItem.equals(soKHieu)){
                    return true;
                }
            }
            if (i < pageCount - 1) {
                WebUI.clickElement(By.xpath("(//button[contains(@class,'p-paginator-next')])[1]"));
            }
        }
        return false;
    }

    public void chinhSuaVBan(String soKHieuCu, String soKHieuMoi, String noiBHanh, String ngayBHanh, String soTo, String trichYeu, String ghiChu, String fileupload){
        timVBanDaThem(soKHieuCu);
        WebUI.waitForElementVisible(soKyHieuVB, 100);
        xoaTTinVBan();
        xoaFileDinhKem();
        nhapTTinVBan(soKHieuMoi, noiBHanh, ngayBHanh, soTo, trichYeu, ghiChu, fileupload);
        WebUI.clickElement(buttonLuuVB);
        WebUI.waitForPageLoaded(100);
        WebUI.waitForTextToBeChanged(tableVBan);
        Assert.assertTrue(checkVBanDaThem(soKHieuMoi));
        Assert.assertFalse(checkVBanDaThem(soKHieuCu));
    }

    public void themFileVBan(String soKHieu, String filePath){
        AngularTable table = new AngularTable(tableVBan, "tbody");
        WebUI.waitForElementVisible(tableVBan, 100);
        AngularPaginator paginator = table.paginator;
        int pageCount = paginator.getPagesCount();
        System.out.println(pageCount);
        outterloop:
        for (int i = 0; i < pageCount; i++){
            List<String> soKHieuList = table.getDisplayColumn(0);
            System.out.println(soKHieuList);
            for (String soKHieuItem: soKHieuList){
                System.out.println(soKHieuItem);
                if (soKHieuItem.contains(soKHieu)){
                    By buttonDuyetFile = By.xpath("//td[text()='" + soKHieu + "']/following-sibling::td[5]//p-fileupload");
                    AngularFileUpload fileUpload = new AngularFileUpload(buttonDuyetFile);
                    try {
                        fileUpload.upload(filePath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Assert.assertTrue(getMessageAlert().contains("Ghi nhận dữ liệu thành công"));
                    break outterloop;
                }
            }
            if (i < pageCount - 1) {
                WebUI.clickElement(By.xpath("(//button[contains(@class,'p-paginator-next')])[1]"));
            }
        }
    }
    public void checkThemFileVBan(String soKHieu, String filePath){
        timVBanDaThem(soKHieu);
        AngularFileUpload fileUpload = new AngularFileUpload(buttonDuyetFile);
        WebUI.scrollToElementAtBottom(buttonDuyetFile);
        List<WebElement> listFiles = WebUI.findElements(By.xpath("//div[@class='listfile-upload']//a"));
        List<String> fileNames = new ArrayList<>();
        for (WebElement file: listFiles){
            fileNames.add(file.getText());
        }
        File file = new File(filePath);
        String fileName = file.getName();
        Assert.assertTrue(fileNames.contains(fileName));
    }

    public void taiVBan(String mHSo, String soKHieu){
        timHSoDaThem(mHSo);
        WebUI.scrollToElementAtBottom(tableVBan);
        AngularTable table = new AngularTable(tableVBan, "tbody");
        WebUI.waitForElementVisible(tableVBan, 100);
        AngularPaginator paginator = table.paginator;
        int pageCount = paginator.getPagesCount();
        System.out.println(pageCount);
        outterloop:
        for (int i = 0; i < pageCount; i++){
            List<String> soKHieuList = table.getDisplayColumn(0);
            System.out.println(soKHieuList);
            for (String soKHieuItem: soKHieuList){
                System.out.println(soKHieuItem);
                if (soKHieuItem.equals(soKHieu)){
                    WebUI.clickElement(By.xpath("//td[text()='" + soKHieu + "']/following-sibling::td[5]//p-button[@icon='pi pi-download']//button"));
                    break outterloop;
                }
            }
            if (i < pageCount - 1) {
                WebUI.clickElement(By.xpath("(//button[contains(@class,'p-paginator-next')])[1]"));
            }
        }
        WebUI.waitForPeriod(5);
        System.out.println(soKHieu + ".zip");
        Assert.assertTrue(WebUI.verifyFileDownloadedWithJS_Contains(soKHieu + ".zip"));
    }
}
