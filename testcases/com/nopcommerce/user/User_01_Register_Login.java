package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class User_01_Register_Login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	private String email = "";

	@BeforeClass
	public void beforeClass() {
		email = "thanh" + generateNumber() + "@gmail.com";
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void Login_01_Register_Empty_Data() {
		driver.get("https://demo.nopcommerce.com/");
		
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span#FirstName-error")).getText(), "First name is required.");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#LastName-error")).getText(), "Last name is required.");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Email is required.");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(), "Password is required.");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(), "Password is required.");
	}
	
	@Test
	public void Login_02_Register_Invalid_Data() {
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Le");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Thanh");
		driver.findElement(By.cssSelector("input#Email")).sendKeys("abc@abc@abc");
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Wrong email");
	}
	
	@Test
	public void Login_03_Register_Success() {
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Le");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Thanh");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		
		driver.findElement(By.cssSelector("a.button-1")).click();
	}
	
	@Test
	public void Login_04_Register_Email_Existing() {
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Le");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Thanh");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.message-error li")).getText(), "The specified email already exists");
	}
	
	@Test
	public void Login_05_Register_Password_Less_Than_6_Chars() {
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Le");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Thanh");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123");
		
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(), "Password must meet the following rules:\nmust have at least 6 characters");
	}	
	
	@Test
	public void Login_06_Register_Invalid_Confirm_Password() {
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Le");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Thanh");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("456123");
		
		driver.findElement(By.cssSelector("button#register-button")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(), "The password and confirmation password do not match.");
	}
	
	private int generateNumber() {
		Random random = new Random();
		return random.nextInt(9999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
