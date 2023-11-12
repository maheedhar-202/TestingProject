package com.dru;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Baseclass
{

	WebDriver driver;
	Alert alert; // alert class
	public ExtentTest test;
	public ExtentReports report;
	
	public void screenshots() throws IOException {
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			String formattedDate = myDateObj.format(myFormatObj);
		String ScreenshotsubFolder = formattedDate.toString();
		System.out.println(ScreenshotsubFolder);

				
				
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot,
	    new File("C:\\Users\\Lenovo\\santhisan\\screenshots\\hello.png"));
		report= new ExtentReports("C:\\Users\\Lenovo\\santhisan\\screenshots\\Extentresults.html", true);
		test=report.startTest("ExtentDemo");
	}
	public void report() {
		report= new ExtentReports("C:\\Users\\Lenovo\\santhisan\\screenshots\\Extentresults.html", true);
		test=report.startTest("ExtentDemo");
	}
	

	@Test
	public void initiliaze() throws IOException, InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://testautomationpractice.blogspot.com/");
		driver.manage().window().maximize();
		
//Scroll
	        
		WebElement submit = driver.findElement(By.xpath("//select//option[text()='Yellow']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");// scrolling using pixel
		js.executeScript("arguments[0].scrollIntoView();", submit);// scrooling page till we find element
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");// scrooling page till bottom
		
		//test.log(Status.PASS, "Test Step 1");
		
		
		
//print values in dropdown //we can create one generic method for handling dropdown and we can use it many times when needed
//Not only for drop down we can create for all functionalities and reuse
		
		List<WebElement> countryOptions = driver.findElements(By.xpath("//select[@id='country']/option"));
		int numberOfOptions = countryOptions.size();
		System.out.println("Number of options in the dropdown: " + numberOfOptions);// gives the no:of options
		for (WebElement option : countryOptions) {
			String optionText = option.getText();
			if (optionText.equalsIgnoreCase("Canada"))// if test equals canada click on canada
			{
				report();
				//test.log(LogStatus.PASS, "It's equall to canada");
				screenshots();
				option.click();
				break; // Exit the loop once "Canada" is found
			}

// print values in webtable

			List<WebElement> webtableOptions = driver.findElements(By.xpath("//table[@name='BookTable']"));
			for (WebElement woption : webtableOptions) {
				String woptionText = option.getText();
				System.out.println("Number of options in the dropdown: " + woptionText);

			}

//Popup
			

			driver.findElement(By.xpath("//button[text()='Alert']")).click();
			String text = driver.switchTo().alert().getText();// get text of popup
			System.out.println(text);
			driver.switchTo().alert().accept();// click on OK here in popup first we need to get text ,later click ok ,later dismiss
			// Becasue if we click on ok or dismiss we are unable to get text of popup

			
//Screenshot for failed Test Cases no over ridden screen shots 

		

			
			
//Cookies 
			

			Set<Cookie> c = driver.manage().getCookies();
			System.out.println(c.size());
			Cookie obj = new Cookie("santhi", "opp");
			driver.manage().addCookie(obj);

//Broken links
			

//		 List<WebElement> links = driver.findElements(By.tagName("a"));
//
//		 for (WebElement link : links) 
//		 {
//	            String href = link.getAttribute("href");
//	            if (href != null && !href.isEmpty()) {
//	                try {
//	                    URL url = new URL(href);
//	                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//	                    connection.setRequestMethod("GET");
//
//	                    connection.connect();
//
//	                    int responseCode = connection.getResponseCode();
//	                    if (responseCode == 200) {
//	                        System.out.println(href + " is a valid link.");
//	                    } else {
//	                        System.out.println(href + " is a broken link. Response Code: " + responseCode);
//	                    }
//	                    connection.disconnect();
//	                } catch (IOException e) {
//	                    System.err.println(href + " is a broken link. Exception: " + e.getMessage());
//	                }
//	            } else {
//	                System.out.println("Empty or null href. Skipping...");
//	            }
//	        }

			
// Windows

//			driver.findElement(By.xpath("//button[text()='New Browser Window']")).click();
//			Set<String> s = driver.getWindowHandles();
//			// driver.switchTo().window(s).getText();
//			// System.out.println(s);
//			for (String w : s) {
//				String z = driver.switchTo().window(w).getTitle();
//				System.out.println(z);
//
//				if (z.equals("Automation Testing Practice")) {
//					driver.switchTo().window(z);
//
//					driver.findElement(By.xpath("//input[@id='name']")).sendKeys("santhi");
//
//				}	
			
			// driver.get(text)
			// driver.switchTo().window("Your Store");

			
// Datepicker

			String month = "October";
			String date = "10";
			String year = "2023";
			driver.get("https://formy-project.herokuapp.com/datepicker");
			driver.findElement(By.xpath("//input[@id='datepicker']")).click();// open calendar
			String monthyear = driver.findElement(By.xpath("//th[@class='datepicker-switch']")).getText();// getting
																											// month
			System.out.println(monthyear);

			String arr[] = monthyear.split(" ");
			String mon = arr[0];
			String yr = arr[1];
			if (mon.equalsIgnoreCase(month) && yr.equalsIgnoreCase(year)) {
				screenshots();
				break;
			} else {
				driver.findElement(
						By.xpath("//div[@class='datepicker-days']//th[@class='next'][normalize-space()='»']")).click();
			}

			List<WebElement> alldates = driver.findElements(By.xpath("//table[@class='table-condensed']//tbody//tr"));
			System.out.println(alldates.size());

			for (WebElement w : alldates) {
				String s = w.getText();
				System.out.println(s);
				if (s.equals(date)) {
					w.click();
					break;
				}

				
				report.flush();
				// driver.switchTo().window(WindowType.TAB);
				// driver.close();

			}
		}
	}

	
	}

