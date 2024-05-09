package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AngularPaginator {
    private final WebElement angularEl;
    private final By currentPageUI = By.xpath("./div/span[contains(@class,'p-paginator-current')]");
//    private final By itemPerPageUI = By.xpath(".//div[contains(@class, 'p-paginator-rpp-options')]/span");
    private final By firstButtonUI = By.xpath("./div/button[contains(@class,'p-paginator-first')]");
    private final By lastButtonUI = By.xpath("./div/button[contains(@class,'p-paginator-last')]");
    private final By prevButtonUI = By.xpath("./div/button[contains(@class,'p-paginator-prev')]");
    private final By nextButtonUI = By.xpath("./div/button[contains(@class,'p-paginator-next')]");
    private final  By itemPageUI = By.xpath(".//button[contains(@class,'p-paginator-page')]");
    public AngularPaginator(By angularLocater){
        this.angularEl = WebUI.getWebElement(angularLocater);
    }
    public AngularPaginator(WebElement angularPanigator){this.angularEl = angularPanigator;}

    private enum PageInfo{HEAD_ITEM, TAIL_ITEM, TOTAL_ITEMS, ITEMS_PER_PAGE};

    private int[] getTotalPageInfo(){
        WebUI.scrollToElementAtBottom(angularEl);
        String span_PageTotal_Info = angularEl.findElement(currentPageUI).getText();

        //using  Regular Expression to find number
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(span_PageTotal_Info);

        //init array
        int[] pageInfo = new int[4];

        // fetch results and put it into array
        int index = 0;
        while (matcher.find()) {
            String number = matcher.group();
            pageInfo[index] = Integer.parseInt(number);
            index++;
        }

        //get items per page
        pageInfo[index] = 5;
        return pageInfo;
    }

    public void clickFirstPage(){
        WebUI.scrollToElementAtBottom(angularEl);
        if(WebUI.verifyElementClickable(firstButtonUI, 1))
            angularEl.findElement(firstButtonUI).click();
    }
    public void clickLastPage(){
        WebUI.scrollToElementAtBottom(angularEl);
        if(WebUI.verifyElementClickable(lastButtonUI, 1))
            angularEl.findElement(lastButtonUI).click();
    }
    public void clickNextPage(){
        WebUI.scrollToElementAtBottom(angularEl);
        if(WebUI.verifyElementClickable(nextButtonUI, 3))
            angularEl.findElement(nextButtonUI).click();
    }
    public void clickPrevPage(){
        WebUI.scrollToElementAtBottom(angularEl);
        if(WebUI.verifyElementClickable(prevButtonUI, 1))
            angularEl.findElement(prevButtonUI).click();
    }

    /*public void gotoPage(int page) {
        WebUI.scrollToElement(angularEl);
        this.clickFirstPage();

        boolean flag = false;
        do {
            List<WebElement> itemPageElemnets = angularEl.findElements(itemPageUI);
            if (!itemPageElemnets.isEmpty()) {
                for (WebElement element : itemPageElemnets) {
                    if (element.getText().equals(page)) {
                        element.click();
                        return;
                    }
                }
                itemPageElemnets.get(itemPageElemnets.size()-1).click();
            }
        } while (flag);
    }*/

    public int getItemsOfPage(){
        int[] arrPageInfo = getTotalPageInfo();
        return arrPageInfo[PageInfo.TAIL_ITEM.ordinal()] - arrPageInfo[PageInfo.HEAD_ITEM.ordinal()] + 1;
    }
    public int getItemsPerPage(){
        int[] arrPageInfo = getTotalPageInfo();
        return arrPageInfo[PageInfo.ITEMS_PER_PAGE.ordinal()];
    }
    public int getItemsCount(){
        int[] arrPageInfo = getTotalPageInfo();
        return arrPageInfo[PageInfo.TOTAL_ITEMS.ordinal()];
    }
    public int getPagesCount(){
        int[] arrPageInfo = getTotalPageInfo();
        int intPart = arrPageInfo[PageInfo.TOTAL_ITEMS.ordinal()] / 5;
        int modPart = ((arrPageInfo[PageInfo.TOTAL_ITEMS.ordinal()] % 5)>0)?1:0;
        return intPart + modPart;
    }

    public boolean verifyPageTotal(){
        int totalPage = this.getPagesCount();
        List<WebElement> elements = angularEl.findElements(itemPageUI);
        return totalPage == elements.size();
    }
}
