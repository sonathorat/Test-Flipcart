package com.flipcart.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.flipcart.qa.base.TestBase;
import com.flipcart.qa.pages.HomePage;
import com.flipcart.qa.pages.LoginPage;
public class LoginPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	
	public LoginPageTest()
	{
		super();
	}
	@BeforeMethod
	public void setUp()
	{
		initialization();
		getUrl();
		loginPage = new LoginPage();
	}
	@Test(priority=1, groups= {"Title Tests"})
	public void loginPageTitleTest()
	{
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");
		System.out.println(Thread.currentThread().getId());
	}
	@Test(priority=2, groups= {"LogoTests"})
	public void logoImageTest()
	{
		boolean image = loginPage.validateLogoImage();
		Assert.assertTrue(image);
		System.out.println(Thread.currentThread().getId());
	}
	@Test(priority=3, groups= {"LoginPageTests"})
	public void loginTest() throws InterruptedException
	{
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		boolean confirmLogin = loginPage.validateLogin();
		Assert.assertTrue(confirmLogin);
		System.out.println(Thread.currentThread().getId());
	}
	
	@Test(priority=4, groups= {"LoginPageTests"},enabled=false)
	public void loginTestwithinvalidusername() throws InterruptedException
	{
		loginPage.login(prop.getProperty("invalidusername"),prop.getProperty("password"));
		String text = loginPage.LoginErrorMessage.getText();
		Assert.assertEquals(text,"Your username or password is incorrect");
		System.out.println(Thread.currentThread().getId());
	}
	
	@Test(priority=5, groups= {"LoginPageTests"},enabled=false)
	public void loginTestwithinvalidpassword() throws InterruptedException
	{
		loginPage.login(prop.getProperty("username"),prop.getProperty("invalidpassword"));
		String text = loginPage.LoginErrorMessage.getText();
		Assert.assertEquals(text,"Your username or password is incorrect");
		System.out.println(Thread.currentThread().getId());
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
