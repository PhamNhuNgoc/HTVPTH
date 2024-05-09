package base;

import com.aventstack.extentreports.Status;
import helpers.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import reports.ExtentTestManager;

public class AngularCalendar {
    //WebDriver driver;
    private final WebElement angularEl;
    private final long SLEEP_STEP = 200;
    By monthButton = By.xpath(".//button[contains(@class, 'p-datepicker-month')]");
    By yearButton = By.xpath(".//button[contains(@class, 'p-datepicker-year')]");
    By prevButton = By.xpath(".//button[contains(@class, 'p-datepicker-prev')]");
    By nextButton = By.xpath(".//button[contains(@class, 'p-datepicker-next')]");

    //table[contains(@class, 'p-datepicker-calendar')]/tbody/tr/td/
    ////span[not(contains(@class, 'p-disabled')) and text()=26]

    public AngularCalendar(By angularLocater){
        this.angularEl = WebUI.getWebElement(angularLocater);
        WebUI.sleep(SLEEP_STEP);
    }

    private void findMonth(int targetMonth){
        //select month
        String xpath = ".//div[contains(@class, 'p-monthpicker')]/span[" + targetMonth + "]";
        angularEl.findElement(By.xpath(xpath)).click();
    }
    private void findYear(int targetMonth, int targetYear) {
        int firstYear = 0;
        int lastYear = 0;

        By firstYearUI = By.xpath(".//div[contains(@class, 'p-yearpicker')]/span[1]");
        By lastYearUI = By.xpath(".//div[contains(@class, 'p-yearpicker')]/span[last()]");

        do{
            WebElement firstYearEl = angularEl.findElement(firstYearUI);
            WebElement lastYearEl = angularEl.findElement(lastYearUI);
            firstYear = Integer.parseInt(firstYearEl.getText());
            lastYear = Integer.parseInt(lastYearEl.getText());

            if ((targetYear >= firstYear) && (targetYear <= lastYear)){
                //select year
                int idxItem = targetYear - firstYear + 1;
                By yearItemUI = By.xpath(".//div[contains(@class, 'p-yearpicker')]/span[" + idxItem + "]");
                angularEl.findElement(yearItemUI).click();
                WebUI.sleep(SLEEP_STEP);

                //select month
                this.findMonth(targetMonth);
            } else if (targetYear < firstYear) {
                angularEl.findElement(prevButton).click();
                WebUI.sleep(SLEEP_STEP);
            }else {
                angularEl.findElement(nextButton).click();
                WebUI.sleep(SLEEP_STEP);
            }
        } while ((targetYear < firstYear) || (targetYear > lastYear));
    }

    @Step("Set element to {0}")
    public void setValues(String itemValue) {
        String[] arrOfMonth = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        System.out.println(itemValue);
        String[] arrOfDate = itemValue.split("/");
        int targetYear = Integer.parseInt(arrOfDate[2]);
        int targetMonth = Integer.parseInt(arrOfDate[1]);
        int targetDate = Integer.parseInt(arrOfDate[0]);

        //click calendar object
        angularEl.click();
        Log.info("Click element " + angularEl);
        ExtentTestManager.logMessage(Status.PASS, "Click element " + angularEl);
        WebUI.sleep(SLEEP_STEP);

        WebUI.waitForElementVisible(yearButton);
        WebElement yearElement = angularEl.findElement(yearButton);
        int currYear = Integer.parseInt(yearElement.getText());

        WebUI.waitForElementVisible(monthButton);
        WebElement monthElement = angularEl.findElement(monthButton);

        //set year value
        if (currYear != targetYear){
            yearElement.click();
            findYear(targetMonth, targetYear);
        } else if (monthElement.getText() != arrOfMonth[targetMonth-1]) {
            monthElement.click();
            String xpath = ".//div[contains(@class, 'p-monthpicker')]/span[" + targetMonth + "]";
            angularEl.findElement(By.xpath(xpath)).click();
        }

        //set date value
        String xpath = ".//span[not(contains(@class, 'p-disabled')) and text()= '" + targetDate + "']";
        angularEl.findElement(By.xpath(xpath)).click();
        Log.info("Set value " + itemValue + " on " + angularEl);
        ExtentTestManager.logMessage(Status.PASS, "Set value " + itemValue + " on " + angularEl);
    }
}
