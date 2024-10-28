package com.testng;
import java.time.Duration;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class pegaautomation {

    public static void main(String[] arg)
    {    
   
        productselection products= new productselection();
        
        products.Beverages();
        products.Seasonings();
        

       


    


}
}