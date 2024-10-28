package com.testng;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int a[]={10,20,30,40,50};
        //int Number=30;
        int even[]={};
        int odd[]={};
        for(int i=0;i<a.length;i++)
        {
            if(a[i]%2==0)
            {
                even[i]=a[i];
                
            }
            else{
                odd[i]=a[i];
            }

        }
        for(int i=0;i<even.length;i++)
        {
        System.out.println("even numbers :"+even[i]);   
        }

        for(int i=0;i<odd.length;i++)
        {
        System.out.println("odd numbers :"+odd[i]);   
        }
    
    }
}
