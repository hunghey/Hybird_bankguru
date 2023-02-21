package com.bankguru.login;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login_01_Resgister_And_Login {
	WebDriver driver;
	
	String projectPath = System.getProperty("user.dir");
	String username, password, loginURL;
	

	@BeforeClass
	public void initBrower() {
//		System.setProperty("webdriver.geckodriver.driver", projectPath + "\\browerDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.get("https://demo.guru99.com/v4/index.php");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		loginURL = driver.getCurrentUrl();
	}

	@Test
	public void Login_01_Resgister() {
//		driver.get("https://demo.guru99.com/v4/index.php");
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(getRandomEmail());
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		username = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

	}

	@Test
	public void Login_02_Login_To_System() {
		driver.get(loginURL);
		
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(username);;
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		String welcomMes = driver.findElement(By.cssSelector("marquee.heading3")).getText();
		Assert.assertEquals(welcomMes, "Welcome To Manager's Page of Guru99 Bank");
	}
	
	public String getRandomEmail() {
		Random rand = new Random();
		return "testing"+ rand.nextInt(9999)+"@gmail.com";
	}
}
