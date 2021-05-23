package com.flipcart.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flipcart.qa.base.TestBase;

public class LoginPage extends TestBase{

	JavascriptExecutor javascript = (JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver,40);
	
	@FindBy(xpath = "//a[contains(text(),'Login')]")
	WebElement FirstLoginButton;
	
	@FindBy(xpath="//span[contains(text(),'Enter Email/Mobile number')]/../..//input")
	WebElement UserName;
	
	@FindBy(xpath="//span[contains(text(),'Enter Password')]/../..//input")
	WebElement Password;
	
	@FindBy(xpath = "//span[contains(text(),'Login')]/../..//button")
	WebElement LoginButton;
	
//	@FindBy(xpath = "//*[contains(text(),'My Account')]/../..//div")
	@FindBy(xpath = "//*[text()='Tom']")
	WebElement MyAccountText;
	
	@FindBy(xpath = "//img[@title='Flipkart']")
	WebElement FlipCartLogo;
	
	@FindBy(xpath = "//span[text()='Cart']")
	WebElement cart;
	
	@FindBy(xpath = "//span[text()='Your username or password is incorrect']")
	public
	WebElement LoginErrorMessage;
	
 
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	public String validateLoginPageTitle()
	{
		return driver.getTitle();
	}
	public boolean validateLogoImage()
	{
		return FlipCartLogo.isDisplayed();
	}
	public void login(String un, String pdw) throws InterruptedException
	{
//		javascript.executeScript("arguments[0].click()",CloseLoginPopup);
//		javascript.executeScript("arguments[0].click()",FirstLoginButton);
//		javascript.executeScript("arguments[0].value='un'",UserName);
//		javascript.executeScript("arguments[0].value='pdw'",Password);
		UserName.sendKeys(un);
		Password.sendKeys(pdw);
		javascript.executeScript("arguments[0].click()",LoginButton);
		Thread.sleep(5000);
	}
	public boolean validateLogin()
	{
		return MyAccountText.isDisplayed();
	}
	
	
}
