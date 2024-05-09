package pages;

import base.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends CommonPage {
    By sideBar = By.xpath("//div[@class='layout-sidebar']");
    By HSoTLieu = By.xpath("//span[text()='Hồ sơ tài liệu']");
    By DuLieuMenu = By.xpath("//a[@href='#/hslt/hoso-tailieu']");

    public void openHSoTLieu(){
        WebUI.moveToElement(sideBar);
        WebUI.clickElement(HSoTLieu);
        WebUI.clickElement(DuLieuMenu);
    }

    public boolean checkSideBarAppear(){
        return WebUI.isElementVisible(sideBar, 3);
    }
}
