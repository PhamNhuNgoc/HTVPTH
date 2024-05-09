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
    By dropdownLoaiHSo = By.xpath("//label[text()='Loại hồ sơ']/following-sibling::p-dropdown");
    By namLapHSo = By.xpath("//label[text()='Năm lập hồ sơ']/following-sibling::p-inputnumber//input");
    By dropdownLinhVuc = By.xpath("//label[text()='Lĩnh vực']/following-sibling::p-dropdown");
    By noiTaoHSo = By.xpath("//label[text()='Nơi tạo hồ sơ']/following-sibling::input");
    By tieuDe = By.xpath("(//label[text()='Tiêu đề']/following-sibling::input)[1]");
    By buttonNhapTuExcel = By.xpath("(//p-button[@label='Nhập từ excel']//button)[1]");
    By fileuploadExcel = By.xpath("//input[@type='file']");
    By buttonXuatExcel = By.xpath("//p-button[@label='Xuất excel']//button");
    By tableHSoTLieu = By.xpath("(//p-table)[1]");

    public void chonLoaiHSo(String loaiHSo){
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(buttonXuatExcel, 5);
        WebUI.moveToElement(buttonXuatExcel);
        WebUI.moveToElement(dropdownLoaiHSo);
        WebUI.waitForElementVisible(dropdownLoaiHSo, 100);
        AngularDropdown chonLoaiHSo = new AngularDropdown(dropdownLoaiHSo);
        chonLoaiHSo.setValues(loaiHSo);
    }

    public void checkLoaiHSo(String loaiHSo){
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

    public void setNamLapHSo(String nam){
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(buttonXuatExcel, 5);
        WebUI.moveToElement(buttonXuatExcel);
        WebUI.moveToElement(namLapHSo);
        WebUI.waitForElementVisible(namLapHSo, 100);
        WebUI.clearText(namLapHSo);
        WebUI.setText(namLapHSo, nam, ENTER);
        WebUI.waitForTextToBeChanged(tableHSoTLieu);
    }

    public void checkNamLapHSo(String nam) {
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

    public void chonLinhVuc(String linhVuc){
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(buttonXuatExcel, 5);
        WebUI.moveToElement(buttonXuatExcel);
        WebUI.moveToElement(dropdownLinhVuc);
        WebUI.waitForElementVisible(dropdownLinhVuc, 100);
        AngularDropdown chonLinhVuc = new AngularDropdown(dropdownLinhVuc);
        chonLinhVuc.setValues(linhVuc);
    }

    public void checkLinhVuc(String linhVuc) {
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

    public void setNoiTaoHSo(String noiTao){
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(buttonXuatExcel, 5);
        WebUI.moveToElement(buttonXuatExcel);
        WebUI.moveToElement(noiTaoHSo);
        WebUI.waitForElementVisible(noiTaoHSo, 100);
        WebUI.clearText(noiTaoHSo);
        WebUI.setText(noiTaoHSo, noiTao);
    }

    public void checkNoiTaoHSo(String noiTaoHSo) {
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

    public void setTieuDe(String tieuDeHSo){
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(buttonXuatExcel, 5);
        WebUI.moveToElement(buttonXuatExcel);
        WebUI.moveToElement(tieuDe);
        WebUI.waitForElementVisible(tieuDe, 100);
        WebUI.clearText(tieuDe);
        WebUI.setText(tieuDe, tieuDeHSo);
    }

    public void checkTieuDe(String tieuDeHSo) {
        WebUI.waitForPageLoaded();
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
        fileUpload.upload(filePath);
    }
}
