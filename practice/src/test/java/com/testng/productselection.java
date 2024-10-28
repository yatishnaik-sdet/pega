package com.testng;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class productselection extends login {

    @Test(dependsOnMethods = {"testLogin"})
    public void Beverages() {    
            
             try {
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
        } catch (Exception e) {
            System.out.println("Error in Beverages test: " + e.getMessage());
            throw e;
        }
    }  

    @Test(dependsOnMethods = {"testLogin"})
    public void Seasonings() {
        try {
            // Click on seasonings image
            driver.findElement(By.xpath("//*[@id=\"my-seasonings-table\"]/tbody/tr[1]/td/a/img")).click();
            
            // Click on specific seasoning
            driver.findElement(By.linkText("Aniseed Syrup")).click();
            driver.findElement(By.name("Order")).click();
            driver.findElement(By.name("edit_your_cart")).click();
        } catch (Exception e) {
            System.out.println("Error in Seasonings test: " + e.getMessage());
            throw e;
        }
    }
}