package pages;

import base.ConstantGlobal;
import base.WebUI;
import org.openqa.selenium.By;

public class CommonPage {
    //Khai báo đối tượng cho Class chung
    public LoginPage loginPage;

    public HomePage homePage;
    public HSoTLieuPage hSoTLieuPage;
    public HSoLTruPage hSoLTruPage;

    //Main menu
    By shopMenu = By.xpath("//a[text() = 'Shop']");
    By myAccountMenu = By.xpath("//a[text() = 'My Account']");




    //Click menu

    public LoginPage clickLogin(){
        WebUI.clickElement(myAccountMenu);
        return new LoginPage();
    }

    //Function chung
//    public LoginPage dangXuat() {
//        WebUI.clickElement(buttonLogOut);
//        return new LoginPage();
//    }
//    public String getPageTitle(){return WebUI.getTextElement(pageTitle);}
//    public String getDisplayName(){return WebUI.getTextElement(displayName);}



    //Khởi tạo giá trị cho tất cả các đối tượng class ở trên
    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

//    public AccountPage getAccountPage() {
//        if (accountPage == null) {
//            accountPage = new AccountPage();
//        }
//        return accountPage;
//    }
//
//    public ShopPage getShopPage() {
//        if (shopPage == null) {
//            shopPage = new ShopPage();
//        }
//        return shopPage;
//    }

    public void navigateToLoginPage() {
        try {
            WebUI.navigate(ConstantGlobal.URL + "/login");
        } catch (Exception e) {
            System.out.println("Error navigating to URL: " + e.getMessage());
        }

    }

    public void navigateToHomePage() {
        try {
            WebUI.navigate("http://10.170.215.7/#/");
        } catch (Exception e) {
            System.out.println("Error navigating to URL: " + e.getMessage());
        }
    }

    public String getShopURL() {
        try {
            return WebUI.getCurrentURL();
        } catch (Exception e) {
            return "Error getting URL: " + e.getMessage();
        }
    }
}
