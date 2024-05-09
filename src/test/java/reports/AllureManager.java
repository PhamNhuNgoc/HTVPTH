package reports;

import base.ConstantGlobal;
import driver.DriverManager;
import com.google.common.io.Files;
import helpers.FileHelpers;
import helpers.Log;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class AllureManager {
    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    //HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    //Text attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG() {
        //byte[] screenshotAs = ((TakesScreenshot) BaseSetup.getDriver()).getScreenshotAs(OutputType.BYTES);
        //Allure.addAttachment("Screenshot", Arrays.toString(screenshotAs));
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static void addAttachmentVideoAVI() {
        if (ConstantGlobal.RECORD_VIDEO.toLowerCase().trim().equals("yes")) {
            try {
                //Get file Last Modified in folder
                File video = FileHelpers.getFileLastModified("./logs/test-recordings");
                if (video != null) {
                    Allure.addAttachment("Failed test Video record AVI", "video/avi", Files.asByteSource(video).openStream(), ".avi");
                } else {
                    Log.warn("Video record not found.");
                    Log.warn("Can not attachment Video in Allure report");
                }

            } catch (IOException e) {
                Log.error("Can not attachment Video in Allure report");
                e.printStackTrace();
            }
        }
    }

    public static void addAttachmentVideoMP4() {
        try {
            //Get file Last Modified in folder
            File video = FileHelpers.getFileLastModified("./logs/test-recordings");
            if (video != null) {
                Allure.addAttachment("Failed test Video record MP4", "video/mp4", Files.asByteSource(video).openStream(), ".mp4");
            } else {
                Log.warn("Video record not found.");
                Log.warn("Can not attachment Video in Allure report");
            }

        } catch (IOException e) {
            Log.error("Can not attachment Video in Allure report");
            e.printStackTrace();
        }
    }
}
