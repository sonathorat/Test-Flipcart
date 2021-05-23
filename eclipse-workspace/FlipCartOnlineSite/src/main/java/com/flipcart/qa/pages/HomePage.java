package com.flipcart.qa.pages;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flipcart.qa.base.TestBase;

public class HomePage extends TestBase{

	JavascriptExecutor javascript = (JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver,40);
	Actions actions = new Actions(driver);
	LoginPage loginPage = new LoginPage();
	
	@FindBy(xpath = "//span[text()='Cart']")
	WebElement cart;
	
	@FindBy(xpath = "//*[contains(text(),'Your cart is empty!')]")
	public WebElement verifyCart;
	
	@FindBy(xpath = "//*[@class='_2NOVgj']//a")
	List<WebElement> MyAccountLinks;
	
	@FindBy(xpath = "//a[contains(text(),'Mouse')]")
	List<WebElement> SearchedItems;
	
	@FindBy(name = "q")
	WebElement searchProductsTextBox;
	
	@FindBy(xpath = "//button[@class='_2KpZ6l _2U9uOA _3v1-ww']")
	WebElement AddToCartButton;
	
	@FindBy(xpath="//a[contains(text(),'Mouse')]")
	List<WebElement> AddedItemIntoCart;
	
	@FindBy(xpath = "//*[text()='Remove']")
	WebElement removeButton;
	
	@FindBy(xpath = "//*[@class='_3dsJAO _24d-qY FhkMJZ']")
	WebElement ConfirmRemove;
	
	@FindBy(xpath="//span[contains(text(),'Showing')]")
	public
	WebElement ShowingElement;
	
	@FindBy(xpath="//*[contains(text(),'My Profile')]//parent::a//parent::li")
	WebElement MyProfileLink;
	
	@FindBy(xpath="//span[contains(text(),'Edit')]")
	WebElement EditBtnOnMyProfile;
	
	@FindBy(name="firstName")
	WebElement FirstName;
	
	@FindBy(name="lastName")
	WebElement LastName;
	
	@FindBy(xpath="//button[@type='submit' and contains(., 'SAVE')]")
	WebElement SaveButton;
	
	
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyCart()
	{
		javascript.executeScript("arguments[0].click()",cart);
		if(verifyCart.isDisplayed())
		{
			System.out.println("You have no items in cart");
		}
		else
		{
			System.out.println("You have items in cart");
		}
		return verifyCart.isDisplayed();
		
	}
	public String validateHomePageTitle()
	{
		return driver.getTitle();
	}
	public List<String> origionalMyAccountDropdownList()
	{
		List<String> origionaldropDownList = new LinkedList<String>();
		origionaldropDownList.add("My Profile");
		origionaldropDownList.add("SuperCoin Zone");
		origionaldropDownList.add("Flipkart Plus Zone");
		origionaldropDownList.add("Orders");
		origionaldropDownList.add("Wishlist");
		origionaldropDownList.add("My Chats");
		origionaldropDownList.add("Coupons");
		origionaldropDownList.add("Gift Cards");
		origionaldropDownList.add("Notifications");
		origionaldropDownList.add("Logout");
		return origionaldropDownList;
	}
	public List<String> getAllTextFromMyAccountDropdown()
	{
		actions.moveToElement(loginPage.MyAccountText).perform();
		
		List<WebElement> links = MyAccountLinks;
		int total_links_count = links.size();
		System.out.println("Total count of links is : "+total_links_count);
		
		List<String> getdropDownList = new LinkedList<String>();
		for(int i=0;i<links.size();i++)
		{
			WebElement element = links.get(i);
			String text = element.getText();
			getdropDownList.add(text);
			System.out.println("Link is : " + text);
		}
		return getdropDownList;
	}
	public void searchProducts() throws InterruptedException 
	{
		searchProductsTextBox.click();
		searchProductsTextBox.sendKeys("Mouse");
		Thread.sleep(2000);
		searchProductsTextBox.sendKeys(Keys.ENTER);
	}
	public void addMultipleProductsIntoCartAndVerify() throws InterruptedException
	{
		searchProducts();
		Thread.sleep(3000);
		String ParentWindow = driver.getWindowHandle();
		List<WebElement> items = SearchedItems;
		int total_number_of_searched_items = items.size();
		System.out.println("Total count of products are : "+total_number_of_searched_items);
		List<String> AddedElementnames=new LinkedList<String>();
		for(int i=0;i<items.size()-35;i++)
		{
			WebElement element = items.get(i);
			element.click();
			AddedElementnames.add(element.getText());
			
			Set<String> set = driver.getWindowHandles();
			Iterator<String> I1= set.iterator();
			while(I1.hasNext())
			{
				String child_window=I1.next();
				if(!ParentWindow.equalsIgnoreCase(child_window))
				{
					driver.switchTo().window(child_window);
				}
			}
			Thread.sleep(5000);
			AddToCartButton.click();
			Thread.sleep(3000);
			driver.switchTo().window(ParentWindow);
			Thread.sleep(3000);
		}
			
			//verify Code here
			driver.switchTo().window(ParentWindow);
			Thread.sleep(3000);
			cart.click();
			Thread.sleep(3000);
			List<WebElement> AddedElementnames1 = AddedItemIntoCart;
			System.out.println("Added Items count: "+AddedElementnames1.size());
			
			for(int i=0;i<AddedElementnames1.size();i++)
			{
				String CartEle = AddedElementnames1.get(i).getText();
				for(int j=0;j<AddedElementnames.size();j++)
				{
					String AddedEle = AddedElementnames.get(i);
				    if(AddedEle==CartEle)
				    {
				    	System.out.println("Item Added Successfully");
				    }  
				}
			}
	}
	public void removeCartItems() throws InterruptedException
	{
		cart.click();
		System.out.println("Added element count"+AddedItemIntoCart.size());
		for(int i=0;i<=AddedItemIntoCart.size();i++)
		{
			javascript.executeScript("arguments[0].click()",removeButton);
			Thread.sleep(5000);
//			removeButton.click();
			ConfirmRemove.click();
			Thread.sleep(5000);
		}
	}
	public void addPersonalInfoOnMyProfile(String firstName, String lastName) 
	{
		actions.moveToElement(loginPage.MyAccountText).perform();
		MyProfileLink.click();
		EditBtnOnMyProfile.click();
		FirstName.clear();
		FirstName.sendKeys(firstName);
		LastName.clear();
		LastName.sendKeys(lastName);
		SaveButton.click();
	}

	

}
