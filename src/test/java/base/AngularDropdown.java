package base;

import com.aventstack.extentreports.Status;
import driver.DriverManager;
import helpers.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import reports.ExtentTestManager;

import java.util.List;
public class AngularDropdown {
    WebDriver driver;
    WebElement angularEl;
    private final long STEP_TIME;
    public AngularDropdown(By angularLocator){
        this.driver = DriverManager.getDriver();
        this.angularEl = WebUI.getWebElement(angularLocator);
        this.STEP_TIME = ConstantGlobal.STEP_TIME;
    }
    @Step("Click element {0}")
    public void setValues(String itemValue)
    {
        //Click angular multiselect element
        angularEl.click();
        Log.info("Click element " + angularEl);
        ExtentTestManager.logMessage(Status.PASS, "Click element " + angularEl);
        WebUI.sleep(500);

        if (angularEl.getTagName().contains("p-dropdown")){
            //Find p-dropdownitem element
            By itemElementUI = By.xpath(".//p-dropdownitem/li/span[contains(text(), '" + itemValue + "')]");
            WebUI.waitForElementVisible(itemElementUI);
            WebUI.waitForElementClickable(itemElementUI);
            WebUI.moveToElement(itemElementUI);
            WebUI.scrollToElementAtBottom(itemElementUI);
            WebUI.clickElement(itemElementUI);
            WebUI.sleep(STEP_TIME);
            Log.info("Choose element " + itemElementUI);
            ExtentTestManager.logMessage(Status.PASS, "Choose element " + itemElementUI);
        }
        else if (angularEl.getTagName().contains("p-multiselect")){
            //Find outer tag of p-multiselectitem element
            By outerItems = By.xpath("//ul[contains(@class, 'p-multiselect-items')]");
            WebElement outerElement = angularEl.findElement(outerItems);

            //Find p-multiselectitem element
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String[] strItems = itemValue.split(", ");
            for(int i=0; i < strItems.length; i++){
                String strXpath = "//p-multiselectitem[@ng-reflect-label='" + strItems[i].trim() + "']";
                WebElement itemElement = outerElement.findElement(By.xpath(strXpath));

                js.executeScript("arguments[0].scrollIntoView();", itemElement);
                itemElement.click();
            }
        }
    }

    public String getValues()
    {
        angularEl.click();

        //String tagName = angularEl.getTagName();
        //tagName = tagName.replaceAll("[^a-zA-Z0-9-]", "");

        String strSelected = "";
        if (angularEl.getTagName().contains("p-dropdown")){
            By labelPath = By.xpath("//div/span");
            strSelected = angularEl.findElement(labelPath).getText();
        }
        else if(angularEl.getTagName().contains("p-multiselect")){
            By listItemPath = By.xpath("//div[contains(@class, 'p-multiselect-items-wrapper')]/ul/p-multiselectitem[@ng-reflect-selected='true']");
            List<WebElement> lstItem = angularEl.findElements(listItemPath);

            for(int i=0; i< lstItem.size(); i++)
            {
                WebElement item = lstItem.get(i);
                String strItemText = item.getAttribute("ng-reflect-label");
                if (strSelected == "")
                    strSelected = strItemText;
                else
                    strSelected = String.join(", ", strSelected, strItemText);
            }
        }

        return strSelected;
    }
}
