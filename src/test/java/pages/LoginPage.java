package pages;

import base.AngularDropdown;
import base.WebUI;
import org.openqa.selenium.By;

public class LoginPage extends CommonPage{
    By loginDropDown = By.xpath("//p-dropdown");
    By inputUserID = By.xpath("//input[@placeholder='Tài khoản']");
    By inputPassword = By.xpath("//input[@type='password']");
    By buttonLogin = By.xpath("//button");

    void chooseTypeLogin(String typeLogin){
        AngularDropdown loginDropDownEl = new AngularDropdown(loginDropDown);
        loginDropDownEl.setValues(typeLogin);
    }

    public void setInfo(String userID, String password){
        WebUI.setText(inputUserID, userID);
        WebUI.setText(inputPassword, password);
        WebUI.clickElement(buttonLogin);
    }

    public void logIn (String typeLogin, String userID, String password){
        chooseTypeLogin(typeLogin);
        setInfo(userID, password);
    }
}
