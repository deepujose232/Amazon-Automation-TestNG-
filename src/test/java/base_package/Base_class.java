package base_package;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Base_class {

	public static Properties prop= new Properties();
	public static WebDriver driver;
	public static JavascriptExecutor js;
	public static Select sel;
	
	public Base_class() {
		
		try {
		FileInputStream file= new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\environment_variables\\Config.properties");
	prop.load(file);
		}
		
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	catch(IOException a)
		{
		a.printStackTrace();
		}
	}
	
	public static void initiate()  {
		String browsername=prop.getProperty("browser");
		if(browsername.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver=new ChromeDriver();
		}
		else
			
		if(browsername.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver","geckodriver.exe");
			driver= new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.get(prop.getProperty("URL"));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
			}
	
     public static void screenshots(String filename) {
    	 File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	 
    	 try {
    		 FileUtils.copyFile(file, new File (System.getProperty("user.dir")+"\\src\\test\\java\\screenshots\\screenshots" +filename+ ".jpg"));
    		 }
    	 catch(IOException k) {
    		 k.printStackTrace();
    	 }

}

	
	
}
