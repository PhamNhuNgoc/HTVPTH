package base;

import com.aventstack.extentreports.Status;
import driver.DriverManager;
import driver.TargetFactory;
import helpers.PropertiesHelpers;
import listeners.AnnotationTransformer;
import listeners.ReportListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.*;
import pages.CommonPage;
import pages.LoginPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import reports.ExtentTestManager;

@Listeners(ReportListener.class)
public class BaseTest extends CommonPage {

    @Parameters({"browser"})
    @BeforeClass
    public void createDriver(String browser) {
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);
        driver.manage().window().maximize();
    }

    protected LoginPage loginPage;
    protected ExtentReports extent;
    protected ExtentTest test;
    @BeforeClass
    @Parameters({"typeLogin", "userID", "password"})
    public void setUp(String typeLogin, String userID, String password){
        extent = new ExtentReports();
        test = extent.createTest("My Test");
        ExtentTestManager.saveToReport("My Test", "Test Description");

        loginPage = new LoginPage();
        navigateToLoginPage();
        loginPage.logIn(typeLogin, userID, password);

    }

    @AfterClass
    public void quit() throws Exception{
        Thread.sleep(3000);
        if (DriverManager.getDriver() != null) {
            DriverManager.quit();
        }
    }

    public WebDriver createBrowser(@Optional("chrome") String browser) {
        PropertiesHelpers.loadAllFiles();
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        driver.manage().window().maximize();
        DriverManager.setDriver(driver);
        return DriverManager.getDriver();
    }
}
