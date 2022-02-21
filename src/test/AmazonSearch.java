package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AmazonSearch {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root" , "WordPass");
		
		Statement stmt = con.createStatement();
		
		ResultSet result = stmt.executeQuery("select name from eproduct where ID = 7");
		
		while(result.next()) {
			
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		
		WebDriver driver = new ChromeDriver();
		//WebDriver driver = new FirefoxDriver();
		driver.get("https://www.amazon.in/");	
		
		//Maximize window
		driver.manage().window().maximize();
		
		//wait for browser to load - implicit
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		
		WebElement element = driver.findElement(By.id("twotabsearchtextbox"));	
		element.sendKeys(result.getString("name"));
		
		WebElement Search = driver.findElement(By.id("nav-search-submit-button"));	
		Search.click();
		
		List<WebElement> PhoneTypes = driver.findElements(By.xpath("//*[@class='a-size-medium a-color-base a-text-normal']"));
		List<WebElement> PhonePrices = driver.findElements(By.xpath("//*[@class='a-price-whole']"));
 		
		System.out.println("Number of all Phones displayed on page: " + PhoneTypes.size());
		
		//filter out iPhones
		System.out.println("List of all iPhones and Prices:");
		for (int i = 0 ; i < PhoneTypes.size() ; i++) {
			if (PhoneTypes.get(i).getText().contains("iPhone 12")) {
			
			System.out.println(PhoneTypes.get(i).getText() + "			Price: " + PhonePrices.get(i).getText());
			}		
		}
		
		//driver.close();
		
		}
		
		con.close();
	}

}
