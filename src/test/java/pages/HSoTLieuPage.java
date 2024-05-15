package pages;

import base.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.internal.invokers.InvokeMethodRunnable;

import java.io.File;
import java.util.List;

import static org.openqa.selenium.Keys.ENTER;

public class HSoTLieuPage extends CommonPage{
    By dropdownLoaiHSoFilter = By.xpath("//label[text()='Loại hồ sơ']/following-sibling::p-dropdown");
    By namLapHSoFilter = By.xpath("//label[text()='Năm lập hồ sơ']/following-sibling::p-inputnumber//input");
    By dropdownLinhVucFilter = By.xpath("//label[text()='Lĩnh vực']/following-sibling::p-dropdown");
    By noiTaoHSoFilter = By.xpath("//label[text()='Nơi tạo hồ sơ']/following-sibling::input");
    By tieuDeFilter = By.xpath("(//label[text()='Tiêu đề']/following-sibling::input)[1]");
    By buttonNhapTuExcel = By.xpath("(//p-button[@label='Nhập từ excel']//button)[1]");
    By fileuploadExcel = By.xpath("//p-fileupload[@chooselabel='Duyệt file']");
    By buttonXNhanNhapTuExcel = By.xpath("//button[@label='Xác nhận']/span");
    By messageAlert = By.xpath("//div[@role='alert']/div/div[2]");
    By buttonDongY = By.xpath("//span[text()='Đồng ý']/parent::button");
    By buttonXuatExcel = By.xpath("//p-button[@label='Xuất excel']//button");
    By buttonThemMoi = By.xpath("//p-button[@label='Thêm mới']/button");
    By tableHSoTLieu = By.xpath("(//p-table)[1]");
    By trangThai = By.xpath("//div[text()=' Trạng thái: ']/h5");
    By maHSoThemMoi = By.xpath("//label[text()='Mã hồ sơ']/following-sibling::input");
    By soHSoThemMoi = By.xpath("//label[text()='Số hồ sơ']/following-sibling::input");
    By soHopThemMoi = By.xpath("//label[text()='Số hộp']/following-sibling::input");
    By soToThemMoi = By.xpath("//label[text()='Số tờ']/following-sibling::p-inputnumber//input");
//    By tieuDeThemMoi = By.xpath("//label[text()='Tiêu đề']/following-sibling::textarea");
    By tieuDeThemMoi = By.xpath("//label[text()='Tiêu đề']/following-sibling::input");
    By tuSoThemMoi = By.xpath("//label[text()='Từ số']/following-sibling::p-inputnumber//input");
    By denSoThemMoi = By.xpath("//label[text()='Đến số']/following-sibling::p-inputnumber//input");
    By tuNgayThemMoi = By.xpath("//label[text()='Từ ngày']/following-sibling::p-calendar");
    By denNgayThemMoi = By.xpath("//label[text()='Đến ngày']/following-sibling::p-calendar");
    By ngayLapThemMoi = By.xpath("//label[text()='Ngày lập']/following-sibling::p-calendar");
    By dropdownLinhVucThemMoi = By.xpath("//p-dropdown[@placeholder='Chọn lĩnh vực']");
    By dropdownTGianBQuan = By.xpath("//p-dropdown[@placeholder='Thời gian bảo quản']");
    By dropdownTinhTrang = By.xpath("//p-dropdown[@placeholder='Tình trạng']");
    By ghiChuThemMoi = By.xpath("//label[text()='Ghi chú']/following-sibling::textarea");
    By buttonLuuThemMoi = By.xpath("//span[text()='Lưu']");

    public void chonLoaiHSoFilter(String loaiHSo){
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(buttonXuatExcel, 5);
        WebUI.moveToElement(buttonXuatExcel);
        WebUI.moveToElement(dropdownLoaiHSoFilter);
        WebUI.waitForElementVisible(dropdownLoaiHSoFilter, 100);
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
    }

    public void checkTieuDeFilter(String tieuDeHSo) {
        WebUI.waitForPageLoaded(100);
        WebUI.waitForTextToBeChanged(tableHSoTLieu);
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
    }

    public void checkExcelFile(){
        String downloadPath = System.getProperty("user.dir") + "/src/test/resources/downloadedFiles";
        File file = new File(downloadPath);
        File[] files = file.listFiles();
        for (File f: files){
            Assert.assertTrue(f.getName().contains("DSHoSo"));
            f.delete();
        }
    }

    public void nhapTuExcel(String filePath) throws Exception {
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(buttonNhapTuExcel, 100);
        WebUI.moveToElement(buttonNhapTuExcel);
        WebUI.clickElement(buttonNhapTuExcel);
        WebUI.waitForElementVisible(fileuploadExcel, 100);
        AngularFileUpload fileUpload = new AngularFileUpload(fileuploadExcel);
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

    public void clickThemMoiButton(){
        WebUI.waitForPageLoaded(500);
        WebUI.waitForElementVisible(buttonThemMoi, 100);
        WebUI.moveToElement(buttonThemMoi);
        WebUI.clickElement(buttonThemMoi);
    }

    public void themMoiHSo(String maHSo, String soHSo, String soHop, String soTo, String tieuDe, String tuSo, String denSo, String tuNgay, String denNgay, String ngayLap, String linhVuc, String thoiGianBQuan, String tinhTrang, String ghiChu){
        clickThemMoiButton();
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(trangThai, 100);
        WebUI.getTextElement(trangThai);
        Assert.assertTrue(WebUI.getTextElement(trangThai).contains("THÊM MỚI"));
        WebUI.setText(maHSoThemMoi, maHSo);
        WebUI.setText(soHSoThemMoi, soHSo);
        WebUI.setText(soHopThemMoi, soHop);
        WebUI.setText(soToThemMoi, soTo);
        WebUI.setText(tieuDeThemMoi, tieuDe);
        WebUI.setText(tuSoThemMoi, tuSo);
        WebUI.setText(denSoThemMoi, denSo);
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
        WebUI.clickElement(buttonLuuThemMoi);
        if(soHSo == ""){
            System.out.println("Không tìm thấy tiền tố Đơn vị hoặc Phòng ban của người dùng");
            WebUI.waitForElementVisible(buttonDongY, 100);
            WebUI.clickElement(buttonDongY);
            String message = getMessageAlert();
//            Assert.assertTrue(message.contains("Không tìm thấy tiền tố Đơn vị hoặc Phòng ban của người dùng"));
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
        WebUI.reloadPage();
    }

    public void checkHSoDaThem(String maHSo, String soHSo, String tieuDe, String ngayLap, String linhVuc, String soHop){
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
        for (int i = 0; i < pageCount; i++){
            if (thongTinHSo.get(0).contains("SPCIT.CNPM." + maHSo)){
                Assert.assertEquals(thongTinHSo.get(1), soHSo);
                Assert.assertEquals(thongTinHSo.get(2), tieuDe);
                Assert.assertEquals(thongTinHSo.get(3), ngayLap);
                Assert.assertEquals(thongTinHSo.get(4), linhVuc);
                Assert.assertEquals(thongTinHSo.get(6), soHop);
                Assert.assertEquals(thongTinHSo.get(8), "Mới tạo");
                System.out.println("Hồ sơ đã được thêm mới thành công");
            }
            if (i < pageCount - 1) {
                WebUI.clickElement(By.xpath("(//button[contains(@class,'p-paginator-next')])[1]"));
            }
        }
    }

    public boolean compareDropdownLVuc(List<String> listOptions){
        clickThemMoiButton();
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(dropdownLinhVucThemMoi, 100);
        AngularDropdown linhVucDropdown = new AngularDropdown(dropdownLinhVucThemMoi);
        List<String> options = linhVucDropdown.getOptions();
        System.out.println(options);
        System.out.println(listOptions);
        return options.equals(listOptions);
    }

    public boolean compareDropdownTGianBQuan(List<String> listOptions){
        clickThemMoiButton();
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(dropdownTGianBQuan, 100);
        AngularDropdown tGianBQuanDropdown = new AngularDropdown(dropdownTGianBQuan);
        List<String> options = tGianBQuanDropdown.getValues();
        System.out.println(options);
        System.out.println(listOptions);
        return options.equals(listOptions);
    }

    public boolean compareDropdownTTrangHSo(List<String> listOptions){
        clickThemMoiButton();
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(dropdownTinhTrang, 100);
        AngularDropdown tinhTrangDropdown = new AngularDropdown(dropdownTinhTrang);
        List<String> options = tinhTrangDropdown.getValues();
        System.out.println(options);
        System.out.println(listOptions);
        return options.equals(listOptions);
    }
}
