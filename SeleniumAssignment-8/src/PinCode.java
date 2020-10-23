

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PinCode {

	WebDriver d;
	String url="http://chennaiiq.com/chennai/pincode-by-name.php";
	
	@Before
	public void setUp() throws Exception {		
		System.setProperty("webdriver.chrome.driver","F:\\selenium\\Selenium New Jar Files\\chromedriver.exe");
		d=new ChromeDriver();
		d.manage().window().maximize();
		d.get(url);
		d.manage().timeouts().implicitlyWait(3000,TimeUnit.MILLISECONDS);
	}

	@After
	public void tearDown() throws Exception {
		d.quit();
	}

	@Test
	public void test() {
		WebElement table=d.findElement(By.xpath("/html/body/table/tbody/tr[3]/td[2]/table[1]"));
		List<WebElement> rows=table.findElements(By.tagName("tr"));
		
		for(int i=3;i<rows.size();i++)
		{
			List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
			String pin1 = cols.get(2).getText(); 
			
			for(int j=i;j<rows.size();j++) 
			{
				List<WebElement> cols1=rows.get(j).findElements(By.tagName("td"));
				String pin2 = cols.get(2).getText();
				Assert.assertFalse(pin1==pin2);  
			}
//			if(i==6) {			just for testing less set of data
//				break;   
//			}
		}
}
}