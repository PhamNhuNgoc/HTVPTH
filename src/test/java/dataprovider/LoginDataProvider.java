package dataprovider;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {
    @DataProvider
    public Object[][] objLoginData(){
        return new  Object[][]{
                {"Đăng nhập bằng mã nhân viên", "28224", "Spcit@123"}
        };
    };
}
