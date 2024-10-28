package com.testng;

//import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;



//import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;

public class login {

    protected WebDriver driver;

     @BeforeSuite
     public void setup() {
        
        WebDriverManager.chromedriver().setup();

          // Create ChromeOptions to start Chrome without a user profile
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito"); // Use incognito mode to avoid cache

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        driver.manage().deleteAllCookies();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }
 
    @Test(priority = 1)
    public void testLogin() {

        
        driver.get("https://training.openspan.com/login");
        driver.findElement(By.id("user_name")).sendKeys("DummyUser1");
        driver.findElement(By.name("user_pass")).sendKeys("Test123");
        driver.findElement(By.id("login_button")).click();
        
        String expectedtitle ="Home | Pega Studio Training Web Application";
        String originaltitle = driver.getTitle();

        Assert.assertEquals(originaltitle,expectedtitle);
        // You can add assertions here to verify successful login, if needed.
    }

    @Test(priority = 2,dependsOnMethods = {"testLogin"})
    public void Beverages() {    
            
             
            // Add explicit wait for better reliability
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            // Select product type
            WebElement dropdown = driver.findElement(By.name("productType"));
            Select dropdown1 = new Select(dropdown);  
            dropdown1.selectByVisibleText("Beverages");
            
            // Select a specific beverage
            Select dropdown2 = new Select(driver.findElement(By.id("productsList")));
            dropdown2.selectByVisibleText("Chai");
            
            // Click buttons for viewing and ordering
            driver.findElement(By.id("viewButton")).click();
            driver.findElement(By.name("Order")).click();
            driver.findElement(By.name("continue_shopping")).click();
    }
    @Test(priority = 3,dependsOnMethods = {"testLogin"})
    public void Seasonings() {
    
            // Click on seasonings image
            driver.findElement(By.xpath("//*[@id=\"my-seasonings-table\"]/tbody/tr[1]/td/a/img")).click();
            
            // Click on specific seasoning
            driver.findElement(By.linkText("Aniseed Syrup")).click();
            driver.findElement(By.name("Order")).click();
            driver.findElement(By.name("edit_your_cart")).click();
    }
      
    //payment process 
    @Test(priority = 4,dependsOnMethods = {"testLogin"})
    public void paymentmethod()
    {

    
    driver.findElement(By.name("next")).click();  //click next

    //step 2 order :tell us who you are 
    driver.findElement(By.name("bfirst_name")).sendKeys("John"); //first name
    driver.findElement(By.name("blast_name")).sendKeys("Smith");  //last name
    driver.findElement(By.name("bcompany_name")).sendKeys("ABC Solutions"); // company 
    driver.findElement(By.name("bstreet_address")).sendKeys("123 Main Street-New York, NY");   //street address
    driver.findElement(By.name("bzip_code")).sendKeys("10001");     //zip code 
    driver.findElement(By.name("barea_code")).sendKeys("212");      //area code
    driver.findElement(By.name("bprimary_phone")).sendKeys("555-0123");  // primary phone 
    driver.findElement(By.linkText("Ship to Billing Address")).click();   // ship to billing address
    
    driver.findElement(By.name("email_address")).sendKeys("john.smith123@example.com");  // Email address 
    
    driver.findElement(By.id("next2_button")).click();  //next button

    //Step 3; tell us how will you pay 
    Boolean varTest1 = driver.findElement(By.id("credit_card")).isSelected();
    if(varTest1 == false)
    {
        driver.findElement(By.id("credit_card")).click();
    }
           //dropdown 
            WebElement dropdowncard = driver.findElement(By.name("card_type"));
            Select dropdown1 = new Select(dropdowncard);  
            dropdown1.selectByVisibleText("Visa");

            driver.findElement(By.name("security_code")).sendKeys("123");  //cvv
            
            driver.findElement(By.name("card_number")).sendKeys("5555 5555 5555 4444");  //cvv
            
            //expiration month of card
            WebElement dropdownmonth = driver.findElement(By.name("expiry_month"));
            Select dropdownmonthSelect = new Select(dropdownmonth);  
            dropdownmonthSelect.selectByVisibleText("December");
            
            //expiration date of card
            WebElement dropdownyear = driver.findElement(By.name("expiry_year"));
            Select dropdownyearSelect = new Select(dropdownyear);  
            dropdownyearSelect.selectByVisibleText("2020");

            driver.findElement(By.name("submit")).click();

         String orderNumber =  driver.findElement(By.xpath("//*[@id=\"load_content\"]/div/div/div/p[1]/b")).getText();

         String expectedtext = "Thank you for placing an order with ACME!";
        String actualtext= driver.findElement(By.xpath("//*[@id=\"load_content\"]/div/div/div/p[2]")).getText();

        Assert.assertEquals(expectedtext, actualtext);


    }


    @AfterSuite
    public void teardown() {
        if (driver != null) {
            driver.quit(); // Close the browser
        }
    } 
}
