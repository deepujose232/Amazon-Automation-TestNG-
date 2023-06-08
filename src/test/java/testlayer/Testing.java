package testlayer;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base_package.Base_class;
import pom_package.Pom_searchpage;
import pom_package.Pom_searchpage2;

public class Testing extends Base_class {
	Pom_searchpage obj;
	Pom_searchpage2 obj2;

	public Testing() {
		super();
	}
	
	@BeforeMethod
	
	public void launch() {
		initiate();
		obj=new Pom_searchpage();
		obj2=new Pom_searchpage2();
		
	}
	
@Test(priority=1)
	   //SF_TC_60_Keyword
public void SF_TC_60_Keyword() {
		obj.searching(prop.getProperty("productname"));
		
		screenshots("_SF_TC_60_Keyword");
		String text=obj.textresult();
		Assert.assertEquals(text, "Results");// confirming relevant products are showing.
		System.out.println("Relevant prouducts are shown "+" ''" +text+ "'' ");
	
			}
@Test(priority=2)
        //SF_TC_72_Next page button
public void SF_TC_72_Next_page_button() throws InterruptedException {
	obj.searching(prop.getProperty("productname"));//precondition
	Thread.sleep(2000);
	obj.Nextbutton();
	screenshots("_SF_TC_72_Next page button");
	
}

@Test(priority=3)
//SF_TC_70_Product_reviews
public void SF_TC_70_Product_reviews() throws InterruptedException {
	
	obj.searching(prop.getProperty("productname"));
	obj.validate_reviews();	
}
//SF_TC_71_No. of products per page
@Test(priority=4)
public void SF_TC_71_No_of_products_per_page() throws InterruptedException {
	obj.searching(prop.getProperty("productname"));
	obj.validate_total_count();
	
}

@Test(priority=5)
//SF_TC_73_Pagination
public void SF_TC_73_pagination() throws InterruptedException {
	obj.searching(prop.getProperty("productname"));
	obj.get_text();
	obj.Nextbutton();
	obj2.get_text();
	//Compare Lists for products uniqueness
	//boolean equal=compareLists(Pom_searchpage2.total_count2, Pom_searchpage.total_count);
	  boolean areEqual = Pom_searchpage2.element_description2.equals(Pom_searchpage.element_description);
	  //System.out.println(Pom_searchpage.element_description.get(0));
	  //System.out.println(Pom_searchpage2.element_description2.get(0));
	   Assert.assertEquals(areEqual, false,"Products are repeated");
	   System.out.println("Products are unique");
}

@Test(priority=6)
//SF_TC_75_Customer rating
public void SF_TC_75_Customer_rating() throws InterruptedException {
	obj.searching(prop.getProperty("productname"));
	obj.filter_review();
	}
@Test(priority=7)
//SF_TC_78_Brand
public void SF_TC_78_Brand() throws InterruptedException {
	obj.searching(prop.getProperty("productname"));
	obj.filter_brand();
	Thread.sleep(2000);
	}
@Test(priority=8)
//SF_TC_79_Price
public void SF_TC_79_Price() throws InterruptedException {
	obj.searching(prop.getProperty("productname"));
	obj2.filter_price();
	Thread.sleep(2000);
	obj2.get_text_price();
}

@Test(priority=9)
//SF_TC_82_Newest, SF_TC_83_Rating, SF_TC_84_Price, SF_TC_85_Popular

public void SF_TC_82_83_84_85() throws InterruptedException {
	obj.searching(prop.getProperty("productname"));
	obj.sort((prop.getProperty("sorting_option")));
	
}

@Test(priority=10)
//SF_TC_86_Sorting and Filtering, SF_TC_87_Relevant Info
public void SF_TC_86_87() throws InterruptedException {
	obj.searching(prop.getProperty("productname"));
	obj.sort((prop.getProperty("sorting_option")));
	obj.filter_brand();
	js.executeScript("window.scrollBy(0,500)");
	screenshots("_SF_TC_87_Relevant Info");
	
}
@Test(priority=11)
//SF_TC_88_Sort,filter,pagination
public void SF_TC_88_Sort_filter_pagination() throws InterruptedException {
	obj.searching(prop.getProperty("productname"));
	obj.sort((prop.getProperty("sorting_option")));
	obj.filter_brand();
	obj.get_text();
	obj.Nextbutton();
	obj2.get_text();
	boolean areEqual = Pom_searchpage2.element_description2.equals(Pom_searchpage.element_description);
	Assert.assertEquals(areEqual, false,"Products are repeated");
	System.out.println("Products are unique");
}

@Test(priority=12)
//SF_TC_89_No. of items on page
public void SF_TC_89_No_of_items_on_page() throws InterruptedException {
	
	obj.searching(prop.getProperty("productname"));
	obj.sort((prop.getProperty("sorting_option")));
	obj.filter_brand();
	obj.get_text();
	obj.Nextbutton();
	obj2.get_text();
	boolean areEqual = Pom_searchpage2.element_description2.equals(Pom_searchpage.element_description);
	Assert.assertEquals(areEqual, false,"Products are repeated");
	System.out.println("Products are unique");
	obj.validate_total_count();
	}

@AfterMethod

public void close_browser() throws InterruptedException {
	
	driver.quit();
}
	
}

