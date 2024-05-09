package base;

import helpers.SystemHelpers;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;

public class AngularFileUpload {
    private final WebElement angularEl;
    private final By messageSumaryUI = By.xpath(".//span[contains(@class, 'p-message-summary')]");
    private final By messageDetailUI = By.xpath(".//span[contains(@class, 'p-message-detail')]");
    private final By fileNameUI = By.xpath(".//div[@class='p-fileupload-filename']");

    public AngularFileUpload(By angularLocater){
        //this.driver = driver;
        this.angularEl = WebUI.getWebElement(angularLocater);
    }

    @Step("Click element {0}")
    public void upload(String filePath) throws Exception {
        //System.out.print(angularEl.getAttribute("accept"));
        //System.out.print(angularEl.getAttribute("outerHTML"));
        angularEl.findElement(By.xpath(".//span[contains(@class, 'p-fileupload-choose')]")).click();
        Robot rb = new Robot();

        //Get project path (using absolute path)
        String projectDir = SystemHelpers.getCurrentDir(); //System.getProperty("project.path");
        //System.out.println(projectDir);

        // Copy File path into clipboard
        StringSelection str = new StringSelection(projectDir + filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

        Thread.sleep(500);

        // Press Control+V to paste
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);

        //Release Control V
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);

        Thread.sleep(500);

        //Press Enter
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);

        Thread.sleep(1000);
    }

    public String getMessage(){
        WebUI.waitForElementVisible(messageSumaryUI);
        WebElement messageSumary = angularEl.findElement(messageSumaryUI);

        WebUI.waitForElementVisible(messageDetailUI);
        WebElement messageDetail = angularEl.findElement(messageDetailUI);

        return messageSumary.getText() + " " + messageDetail.getText();
    }

    public boolean isMessageContains(String expectedValue){
        WebUI.waitForElementVisible(messageSumaryUI);
        WebElement messageSumary = angularEl.findElement(messageSumaryUI);
        return messageSumary.getText().contains(expectedValue);
    }

    public List<String> getFileNames(){
        WebUI.waitForElementVisible(fileNameUI);
        List<WebElement> fileNameElements = angularEl.findElements(fileNameUI);

        List<String> results = new ArrayList<String>();
        if(!fileNameElements.isEmpty()){
            for(WebElement element : fileNameElements)
                results.add(element.getText().trim());
        }
        else {
            results.add("");
        }
        return results;
    }
}
