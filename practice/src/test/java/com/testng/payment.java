package com.testng;

import java.time.Duration;

import org.testng.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class payment extends login{

    //place your order 
    @Test
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


}
