package pom_package;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base_package.Base_class;


public class Pom_searchpage2 extends Base_class {
	//List -"total_count2" is for all the products description in webelement format of page2
	@FindBy(xpath="//span[@class='a-size-medium a-color-base a-text-normal' or @class='a-size-base-plus a-color-base a-text-normal']")  List<WebElement> total_count2;
	//List-"element_description2" is for storing description of all the products in string format of page2
	public static List<String> element_description2=new ArrayList<String>();
	@FindBy(xpath="//input[@id='high-price']") WebElement max_price;
	@FindBy(xpath="//input[@id='low-price']") WebElement low_price;
	@FindBy(xpath="//input[@aria-labelledby='a-autoid-1-announce']") WebElement go;
	//List "prices" for storing price of all the products displayed in the page in webElement format
	@FindBy(xpath="//span[@class='a-price-whole']") List <WebElement> prices;
	//List-"Price_Element" for storing price of all the products displayed in the page in Integer format
	public static List<Integer> Price_Element=new ArrayList<Integer>();
	
	public Pom_searchpage2 () {
		PageFactory.initElements(driver,this);
	}
	//copy webelement values in List "total_count2" to List "element_description2" as text.
	public void get_text() {
		for(WebElement i:total_count2) {
			element_description2.add(i.getText());
			}
}
public void get_text_price() throws InterruptedException {
	
	//convert string value to integer
	int max=Integer.parseInt(prop.getProperty("maximumprice"));
	int min=Integer.parseInt(prop.getProperty("minimumprice"));
		//Copied values in "prices" List to "Price_Element" List after converting to integer 
		for(WebElement i:prices) {
			 String text = i.getText();
	            int intValue = Integer.parseInt(text);
	            Price_Element.add(intValue);
			}
		//comparing the prices in the List "Price_element" to find the products are in the price range specified.
		for(Integer i:Price_Element) {
			if ((i<=max)&&(i>=min)){
				
				
				}
			else {
				js.executeScript("window.scrollBy(0,1000)");
				Thread.sleep(2000);
				boolean Actual=false;
				screenshots("_SF_TC_79_Price");
				js.executeScript("window.scrollBy(0,3000)");
				screenshots("_SF_TC_79_Price1");
				Assert.assertEquals(Actual, true,"Products are not filtered under the given price range");
			}
		}
		System.out.println("Products filtered under the given price range");
}

public void filter_price() {
	max_price.sendKeys(prop.getProperty("maximumprice"));
	low_price.sendKeys(prop.getProperty("minimumprice"));
	go.click();
	
}
}
