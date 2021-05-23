package com.flipcart.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	
	public TestBase()
	{
		try
		{
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:/Users/sthorat2/eclipse-workspace/FlipCartOnlineSite/src/main/java/com/flipcart/qa/config/config.properties");
			prop.load(ip);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void initialization()
	{
		System.setProperty("webdriver.chrome.driver","D:\\Automations\\CromeDriver\\chromedriver.exe");
	    driver = new ChromeDriver();
	    
	    driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	public void getUrl()
	{
		driver.get(prop.getProperty("url"));
	}
	public void navigateBack()
	{
		driver.navigate().back();
	}
	public void navigateForword()
	{
		driver.navigate().forward();
	}
	public void navigateRefresh()
	{
		driver.navigate().refresh();
	}
	public void closeDriver()
	{
		driver.close();
	}

}