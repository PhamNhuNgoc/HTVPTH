package pages;

import base.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends CommonPage {
    By sideBar = By.xpath("//div[@class='layout-sidebar']");
    By HSoLTru = By.xpath("//span[text()='Hồ sơ Lưu trữ']");
    By dMucLVucMenu = By.xpath("//span[text()='Danh mục Lĩnh vực']");
    By dMucTGianBQuanMenu = By.xpath("//span[text()='Danh mục Thời gian bảo quản']");
    By dMucTTrangHSoMenu = By.xpath("//span[text()='Danh mục Tình trạng hồ sơ']");
    By HSoTLieu = By.xpath("//span[text()='Hồ sơ tài liệu']");
    By DuLieuMenu = By.xpath("//a[@href='#/hslt/hoso-tailieu']");

    public void openDuLieuMenu(){
        WebUI.waitForPageLoaded(100);
        WebUI.moveToElement(sideBar);
        WebUI.waitForElementVisible(HSoLTru, 100);
        WebUI.clickElement(HSoTLieu);
        WebUI.clickElement(DuLieuMenu);
    }

    public void openDMucLVucMenu(){
        WebUI.waitForPageLoaded(100);
        WebUI.moveToElement(sideBar);
        WebUI.waitForElementVisible(HSoLTru, 100);
        WebUI.waitForElementClickable(HSoLTru, 100);
        WebUI.clickElement(HSoLTru);
        WebUI.clickElement(dMucLVucMenu);
    }

    public void openDMucTGianBQuanMenu(){
        WebUI.waitForPageLoaded(100);
        WebUI.moveToElement(sideBar);
        WebUI.waitForElementVisible(HSoLTru, 100);
        WebUI.waitForElementClickable(HSoLTru, 100);
        WebUI.clickElement(HSoLTru);
        WebUI.clickElement(dMucTGianBQuanMenu);
    }

    public void openDMucTTrangHSo(){
        WebUI.waitForPageLoaded(100);
        WebUI.moveToElement(sideBar);
        WebUI.waitForElementVisible(HSoLTru, 100);
        WebUI.waitForElementClickable(HSoLTru, 100);
        WebUI.clickElement(HSoLTru);
        WebUI.clickElement(dMucTTrangHSoMenu);
    }

    public boolean checkSideBarAppear(){
        return WebUI.isElementVisible(sideBar, 3);
    }
}
