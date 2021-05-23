package com.flipcart.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.flipcart.qa.base.TestBase;
import com.flipcart.qa.pages.HomePage;
import com.flipcart.qa.pages.LoginPage;
import com.flipcart.qa.testdata.TestUtil;
public class HomePageTest extends TestBase{

	HomePage homePage;
	LoginPage loginPage;
	String sheetName = "Details";
	
	public HomePageTest()
	{
		super();
	}
	@BeforeMethod
	public void setUp() throws InterruptedException
	{
		initialization();
		getUrl();
		loginPage = new LoginPage();
		homePage = new HomePage();
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test(priority=1, groups= {"Title Tests"})
	public void verifyHomePageTitleTest()
	{
		String title = homePage.validateHomePageTitle();
		System.out.println(title);
		Assert.assertEquals(title, "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");
		System.out.println(Thread.currentThread().getId());
	}
	@Test(priority=2, groups= {"Home Page Tests"})
	public void verifyCartTest()
	{
		boolean cart = homePage.verifyCart();
		Assert.assertTrue(cart);
		System.out.println(Thread.currentThread().getId());
	}
	@Test(priority=3, groups= {"Home Page Tests"})
	public void getAllTextFromMyAccountDropdownTest()
	{
		Assert.assertEquals(homePage.origionalMyAccountDropdownList().size(), homePage.getAllTextFromMyAccountDropdown().size());
		System.out.println(Thread.currentThread().getId());
//		for (int i = 0; i < homePage.origionalMyAccountDropdownList().size(); i++) {
//		    Assert.assertEquals(homePage.origionalMyAccountDropdownList().get(i), homePage.getAllTextFromMyAccountDropdown().get(i));
//		}
	}
	@Test(priority=4, groups= {"Home Page Tests"})
	public void searchProductsTest() throws InterruptedException 
	{
		homePage.searchProducts();
		String ElementSearched = homePage.ShowingElement.getText();
		if(ElementSearched.contains("Showing"))
			Assert.assertTrue(true);
		System.out.println(Thread.currentThread().getId());
	}
	@Test(priority=5, groups= {"Home Page Tests"})
	public void addMultipleProductsIntoCartAndVerifyTest() throws InterruptedException
	{
		homePage.addMultipleProductsIntoCartAndVerify();
		System.out.println(Thread.currentThread().getId());
	}
	@Test(priority=6, groups= {"Home Page Tests"})
	public void removeCartItemsTest() throws InterruptedException
	{
		homePage.removeCartItems();	
		Assert.assertTrue(homePage.verifyCart.isDisplayed());
		System.out.println(Thread.currentThread().getId());
	}
	@DataProvider
	public Object[][] getTestData()
	{
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	@Test(priority=7, dataProvider="getTestData", groups= {"Data Tests"})
	public void addPersonalInfoOnMyProfileTest(String firstName, String lastName)
	{
		homePage.addPersonalInfoOnMyProfile(firstName,lastName);
		System.out.println(Thread.currentThread().getId());
	}
	
// for dependency feature of testNG we executed below tests
	
//	@Test(priority=8, groups= {"Home Page Tests"})
//	public void addMultipleProductsIntoCartAndVerifyTest1() throws InterruptedException
//	{
//		homePage.addMultipleProductsIntoCartAndVerify();
//		
//	}
//	@Test(priority=9, groups= {"Home Page Tests"},dependsOnMethods = {"addMultipleProductsIntoCartAndVerifyTest1"},alwaysRun=true)
//	public void removeCartItemsTest1() throws InterruptedException
//	{
//		homePage.removeCartItems();	
//		Assert.assertTrue(homePage.verifyCart.isDisplayed());
//	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
