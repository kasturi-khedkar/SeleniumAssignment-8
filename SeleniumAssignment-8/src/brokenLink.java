import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class brokenLink {

	private static WebDriver driver=null;

	@Test
	public void f() 
	{
		String website="http://www.zlti.com";
		String url="";
		HttpURLConnection httpCon = null;
		int StatusCode = 200;

		System.setProperty("webdriver.chrome.driver", "F:\\selenium\\Selenium New Jar Files\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(website);

		List<WebElement> li = driver.findElements(By.tagName("a"));
		Iterator<WebElement> iterator = li.iterator();

		while (iterator.hasNext()) {
			url = iterator.next().getAttribute("href");

			System.out.println(url);
			if (url == null || url.isEmpty()) {
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}

			if (!url.startsWith(website)) {
				System.out.println("URL belongs to another domain, skipping it.");
				continue;
			}

			try {
				httpCon = (HttpURLConnection) (new URL(url).openConnection());
				httpCon.setRequestMethod("HEAD");
				httpCon.connect();
				StatusCode = httpCon.getResponseCode();

				if (StatusCode >= 400) {
					System.out.println(url + " is a broken link");
				} else {
					System.out.println(url + " is a valid link");
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		driver.quit();
	}
}
