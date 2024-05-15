package pages;

import base.AngularPaginator;
import base.AngularTable;
import base.WebUI;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.internal.StringUtil;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class HSoLTruPage extends CommonPage{
    By logOutButton = By.xpath("//a[@title='Logout']/i");
    public List<String> getListLinhVuc(){
        WebUI.moveToElement(logOutButton);
        WebUI.waitForPageLoaded(100);
        By table = By.xpath("//p-table");
        WebUI.waitForElementVisible(table, 100);
        AngularTable angularTable = new AngularTable(table, "tbody");
        AngularPaginator angularPaginator = angularTable.paginator;
        Integer pageCount = angularPaginator.getPagesCount();
        System.out.println("Page count: " + pageCount);
        List<String> options = new ArrayList<>();
        for (int i = 0; i < pageCount; i++){
            List<String> linhVuc = angularTable.getDisplayColumn(0);
            List<String> nhom = angularTable.getDisplayColumn(1);
            List<String> optionsTemp = angularTable.getDisplayColumn(0);
            List<Boolean> checkboxes = angularTable.getSelectedCheckboxColumn(2);
            System.out.println("Checkbox: " + checkboxes);
            for (int j = 0; j < linhVuc.size(); j++){
                if(nhom.get(j).isEmpty()){
                    optionsTemp.set(j, "-" + linhVuc.get(j));
                    System.out.println(optionsTemp.get(j));
                }
                else{
                    int index = linhVuc.indexOf(nhom.get(j));
                    int count = StringUtils.countMatches(optionsTemp.get(index), "-" );
                    System.out.println(optionsTemp.get(index));
                    System.out.println("Count: " + count);
                    for(int k = 0; k < count+1; k++){
                        optionsTemp.set(j, "-" + optionsTemp.get(j));
                    }
                    System.out.println(optionsTemp.get(j));
                }
                if (checkboxes.get(j)) {
                    optionsTemp.remove(j);
                    checkboxes.remove(j);
                }
            }
            System.out.println("Linh vuc: " + optionsTemp);
            if (!optionsTemp.isEmpty()){
                options.addAll(optionsTemp);
            }
            if (i < pageCount - 1) {
                WebUI.clickElement(By.xpath("(//button[contains(@class,'p-paginator-next')])[1]"));
            }
        }
        return options;
    }

    public List<String> getListTGianBQuan(){
        WebUI.moveToElement(logOutButton);
        WebUI.waitForPageLoaded(100);
        By table = By.xpath("//p-table");
        WebUI.waitForElementVisible(table, 100);
        AngularTable angularTable = new AngularTable(table, "tbody");
        AngularPaginator angularPaginator = angularTable.paginator;
        Integer pageCount = angularPaginator.getPagesCount();
        System.out.println("Page count: " + pageCount);
        List<String> tGianBQuan = new ArrayList<>();
        tGianBQuan.add("Không");
        for (int i = 0; i < pageCount; i++){
            List<String> tGianBQuanTemp = angularTable.getDisplayColumn(0);
            List<Boolean> checkboxes = angularTable.getSelectedCheckboxColumn(3);
            System.out.println("Checkbox: " + checkboxes);
            for (int j = 0; j < tGianBQuanTemp.size(); j++){
                if (checkboxes.get(j)){
                    tGianBQuanTemp.remove(j);
                    checkboxes.remove(j);
                }
            }
            System.out.println("Thoi gian bao quan: " + tGianBQuanTemp);
            if(!tGianBQuanTemp.isEmpty()){
                tGianBQuan.addAll(tGianBQuanTemp);
            }
            if (i < pageCount - 1) {
                WebUI.clickElement(By.xpath("(//button[contains(@class,'p-paginator-next')])[1]"));
            }
        }
        return tGianBQuan;
    }

    public List<String> getListTTrangHSo(){
        WebUI.moveToElement(logOutButton);
        WebUI.waitForPageLoaded(100);
        By table = By.xpath("//p-table");
        WebUI.waitForElementVisible(table, 100);
        AngularTable angularTable = new AngularTable(table, "tbody");
        AngularPaginator angularPaginator = angularTable.paginator;
        Integer pageCount = angularPaginator.getPagesCount();
        System.out.println("Page count: " + pageCount);
        List<String> tTrangHSo = new ArrayList<>();
        tTrangHSo.add("Không");
        for (int i = 0; i < pageCount; i++){
            List<String> tTrangHSoTemp = angularTable.getDisplayColumn(0);
            List<Boolean> checkboxes = angularTable.getSelectedCheckboxColumn(1);
            System.out.println("Checkbox: " + checkboxes);
            for (int j = 0; j < tTrangHSoTemp.size(); j++){
                if (checkboxes.get(j)){
                    tTrangHSoTemp.remove(j);
                    checkboxes.remove(j);
                }
            }
            System.out.println("Tinh trang ho so: " + tTrangHSoTemp);
            if (!tTrangHSoTemp.isEmpty()){
                tTrangHSo.addAll(tTrangHSoTemp);
            }
            if (i < pageCount - 1) {
                WebUI.clickElement(By.xpath("(//button[contains(@class,'p-paginator-next')])[1]"));
            }
        }
        return tTrangHSo;
    }
}
