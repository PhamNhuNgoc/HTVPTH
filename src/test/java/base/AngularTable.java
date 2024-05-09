package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AngularTable {
    private final WebElement angularEl;
    private final WebElement wrapperTable;
    private String tbodyTag = "";
    public AngularPaginator paginator;

    public AngularTable(By angularLocater, String tbodyTag){
        this.angularEl = WebUI.getWebElement(angularLocater);
        this.tbodyTag = tbodyTag;
        wrapperTable = angularEl.findElement(By.tagName("table"));

        By panigatorUI = By.tagName("p-paginator");
        List<WebElement> panigators = angularEl.findElements(panigatorUI);
        if (!panigators.isEmpty())
            this.paginator = new AngularPaginator(angularEl.findElement(panigatorUI));
    }
    public int getSize(){
        List<WebElement> htmlRows = wrapperTable.findElements(By.xpath("./" + tbodyTag + "/tr"));
        return htmlRows.size();
    }
    public List<WebElement> getDisplayRows(){
        return wrapperTable.findElements(By.xpath("./" + tbodyTag + "/tr"));
    }

    public List<String> getDisplayRow(int i){
        List<WebElement> trTags = wrapperTable.findElements(By.xpath("./" + tbodyTag + "/tr"));
        List<String> results = new ArrayList<String>();
        if (!trTags.isEmpty()) {
            List<WebElement> tdTags = trTags.get(i).findElements(By.xpath("./td"));
            for(WebElement tdTag: tdTags)
                results.add(tdTag.getText());
        }
        return results;
    }

    public List<String> getDisplayColumn(int i){
        List<WebElement> trTags = wrapperTable.findElements(By.xpath("./" + tbodyTag + "/tr"));
        List<String> results = new ArrayList<String>();
        if (!trTags.isEmpty())
            for(WebElement trTag: trTags)
                results.add(trTag.findElements(By.xpath("./td")).get(i).getText());
        return results;
    }

    public List<String> getLastRow(){
        List<WebElement> trTags = wrapperTable.findElements(By.xpath("./" + tbodyTag + "/tr"));
        List<String> results = new ArrayList<String>();
        if (!trTags.isEmpty()) {
            List<WebElement> tdTags = trTags.get(trTags.size()-1).findElements(By.xpath("./td"));
            for(WebElement tdTag: tdTags)
                results.add(tdTag.getText());
        }
        return results;
    }

    public boolean VerifyColumnAscendingSort(Integer col) {
        List<WebElement> htmlRows = getDisplayRows();

        ArrayList<String> actualValues = new ArrayList<String>();
        ArrayList<String> expectedValues = new ArrayList<String>();

        for (WebElement htmlRow : htmlRows) {
            List<WebElement> tdElements = htmlRow.findElements(By.tagName("td"));
            String strValue = tdElements.get(col).getText().toLowerCase();

            actualValues.add(strValue);
            expectedValues.add(strValue);
        }

        Collections.sort(expectedValues);
        return actualValues.equals(expectedValues);
    }

    public boolean VerifyColumnDescendingSort(Integer col) {
        List<WebElement> htmlRows = getDisplayRows();

        ArrayList<String> actualValues = new ArrayList<String>();
        ArrayList<String> expectedValues = new ArrayList<String>();

        for(int i = 0; i < htmlRows.size(); i++) {
            List<WebElement> tdElements = htmlRows.get(i).findElements(By.tagName("td"));
            String strValue = tdElements.get(col).getText().toLowerCase();

            actualValues.add(strValue);
            expectedValues.add(strValue);
        }

        expectedValues.sort(new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                return str2.compareTo(str1);
            }
        });

        return actualValues.equals(expectedValues);
    }

    /*public boolean VerifyCellValue(Integer rowIndex, Integer colIndex, String expectedValue){
        List<WebElement> trTags = getDisplayRows();
        List<WebElement> tdTags = trTags.get(rowIndex).findElements(By.xpath("./td"));
        return tdTags.get(colIndex).getText().equals(expectedValue);
    }

    public boolean VerifyLastRowsValue(Integer colIndex, String expectedValue){
        List<WebElement> trTags = getDisplayRows();
        List<WebElement> tdTags = trTags.get(trTags.size()-1).findElements(By.xpath("./td"));
        return tdTags.get(colIndex).getText().equals(expectedValue);
    }*/

}
