package reports;

import base.ConstantGlobal;
import com.aventstack.extentreports.reporter.configuration.Theme;
import driver.DriverManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import helpers.CaptureHelpers;
import tech.grasshopper.reporter.ExtentPDFReporter;
import utils.DateUtils;

import java.io.File;
import java.io.IOException;

public class ExtentReportManager {
    private static ExtentReports extentReports = new ExtentReports();
    private static String path = ConstantGlobal.EXTENT_REPORT_PATH + File.separator + ConstantGlobal.EXTENT_REPORT_NAME;

    public synchronized static ExtentReports getExtentReports() {
        if (ConstantGlobal.OVERRIDE_REPORTS.trim().equals(ConstantGlobal.NO)) {
            path += "_" + DateUtils.getCurrentDateTimeCustom("_");
            System.out.println("OVERRIDE EXTENT REPORTS = " + ConstantGlobal.OVERRIDE_REPORTS);
            System.out.println("Link Extent Report: " + path);
        } else {
            System.out.println("OVERRIDE EXTENT REPORTS = " + ConstantGlobal.OVERRIDE_REPORTS);
            System.out.println("Link Extent Report: " + path);
        }

        //Generate PDF report
        ExtentPDFReporter pdf = new ExtentPDFReporter(path + ".pdf");
        try {
            pdf.loadJSONConfig(new File("src/test/resources/pdf-config.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pdf.config().setTitle(ConstantGlobal.REPORT_TITLE);
        pdf.config().setReportName(ConstantGlobal.REPORT_TITLE);
        extentReports.attachReporter(pdf);

        //Generate HTML report
        ExtentSparkReporter spark = new ExtentSparkReporter(path + ".html");
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle(ConstantGlobal.REPORT_TITLE);
        spark.config().setReportName(ConstantGlobal.REPORT_TITLE);
        extentReports.attachReporter(spark);
        extentReports.setSystemInfo("Framework Name", "Selenium Java Framework");
        extentReports.setSystemInfo("Author", ConstantGlobal.AUTHOR);
        return extentReports;
    }

    public static void removeTest(String testCaseName) {
        extentReports.removeTest(testCaseName);
    }

    /**
     * Adds the screenshot.
     *
     * @param message the message
     */
    public static void addScreenShot(String message) {
        String base64Image = "data:image/png;base64," + ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);

        //Base64 from Screenshot of Selenium
        //ExtentTestManager.getExtentTest().log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());

        //File Path from Screenshot of Java
        ExtentTestManager.getTest().log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(String.valueOf(CaptureHelpers.getScreenshotFile(message))).build());

    }

    /**
     * Adds the screenshot.
     *
     * @param status  the status
     * @param message the message
     */
    public static void addScreenShot(Status status, String message) {
        String base64Image = "data:image/png;base64," + ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);

        //Base64 from Screenshot of Selenium
        //ExtentTestManager.getExtentTest().log(status, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());

        //File Path from Screenshot of Java
        ExtentTestManager.getTest().log(status, MediaEntityBuilder.createScreenCaptureFromPath(CaptureHelpers.getScreenshotAbsolutePath(message)).build());

    }

    public static void pass(String message) {
        //System.out.println("ExtentReportManager class: " + ExtentTestManager.getExtentTest());
        ExtentTestManager.getTest().pass(message);
    }

    public static void pass(Markup message) {
        ExtentTestManager.getTest().pass(message);
    }

    public static void fail(String message) {
        ExtentTestManager.getTest().fail(message);
    }

    public static void fail(Object message) {
        ExtentTestManager.getTest().fail((String) message);
    }

    public static void fail(Markup message) {
        ExtentTestManager.getTest().fail(message);
    }

    public static void skip(String message) {
        ExtentTestManager.getTest().skip(message);
    }

    public static void skip(Markup message) {
        ExtentTestManager.getTest().skip(message);
    }

    public static void info(Markup message) {
        ExtentTestManager.getTest().info(message);
    }

    public static void info(String message) {
        ExtentTestManager.getTest().info(message);
    }

    public static void warning(String message) {
        ExtentTestManager.getTest().log(Status.WARNING, message);
    }

}
