package pom_package;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import base_package.Base_class;


public class Pom_searchpage extends Base_class {
	
@FindBy(xpath="//input[@id='twotabsearchtextbox']") WebElement search;
@FindBy(xpath="//input[@id='nav-search-submit-button']")WebElement searchbtn;
@FindBy(linkText="Next") WebElement nextbtn;
@FindBy(xpath="//span[contains(text(),'Results')]") WebElement results;
@FindBy(xpath="//span[@aria-label='Current page, page 2']") WebElement page2;
//List-"reviews" is for all the products which has review rating given
@FindBy(xpath="//span[contains(text(),'out of 5 stars')]") List<WebElement> reviews;
//List -"total_count" is for all the products description in webelement format of page1
@FindBy(xpath="//span[@class='a-size-medium a-color-base a-text-normal' or @class='a-size-base-plus a-color-base a-text-normal']")List<WebElement> total_count;
//List-"element_description" is for storing description of all the products in string format of page1
public static List<String> element_description=new ArrayList<String>();
@FindBy(xpath="//section[@aria-label='4 Stars & Up']") WebElement rating;
@FindBy(xpath="//span[@class='s-back-arrow aok-inline-block']") WebElement clear;
@FindBy(xpath="//span[@class='a-list-item'] // i[@class='a-icon a-icon-checkbox']") WebElement brandcheckbox;
@FindBy(xpath="//select[@id='s-result-sort-select']") WebElement sortbtn;
@FindBy(xpath="//span[@class='a-dropdown-prompt']") WebElement sort_check;



public Pom_searchpage () {
	PageFactory.initElements(driver,this);
}
	
public void searching(String name)  {
	search.sendKeys(name);
	searchbtn.click();
	js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,1000)");
	System.out.println("Knowledge");
}

public String textresult() {
	return results.getText();
}

public void Nextbutton() throws InterruptedException {
	
	Thread.sleep(2000);
	js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].scrollIntoView();",nextbtn);
	Thread.sleep(2000);
	nextbtn.click();
	//For 2nd page view to next button to confirm 2nd page is loaded
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
	js.executeScript("arguments[0].scrollIntoView();",page2);
	Thread.sleep(2000);
	Boolean page=page2.isDisplayed();
	Assert.assertEquals(true, page, "Page 2 not displayed");
	System.out.println("Page 2 is displayed");
	
}
public void validate_reviews () {
	
	int r=reviews.size();
	int tc=total_count.size();
	Assert.assertEquals(tc, r,"Reviews are not displayed for all products");
	}

public void validate_total_count() {
	int c=total_count.size();
	Assert.assertEquals(c, 60,"Total number of products is not 60, it is "+c+".");
	
}
//copy webelement values in List "total_count" to List "element_description" as text.
public void get_text() {
	
	for(WebElement i:total_count) {
		element_description.add(i.getText());
		}
	}

public void filter_review() throws InterruptedException {
	rating.click();
	Thread.sleep(2000);
	boolean filter=clear.isDisplayed();
	Assert.assertEquals(filter, true,"Filtering not done on reviews");
	System.out.println("Products are filtered on review of 4 and Up");
	}
public void filter_brand() throws InterruptedException {
	brandcheckbox.click();
	clear.isDisplayed();
	boolean filter=clear.isDisplayed();
	Thread.sleep(2000);
	Assert.assertEquals(filter, true,"Filtering not done on brand");
	System.out.println("Products are filtered on selected brand");
	}

public void sort(String sortby) throws InterruptedException {
	sel=new Select(sortbtn);
	sel.selectByVisibleText(sortby);
	String Actual=sort_check.getText();
	Assert.assertEquals(Actual, sortby,"Sorting not done as per selection");
	
}


}
