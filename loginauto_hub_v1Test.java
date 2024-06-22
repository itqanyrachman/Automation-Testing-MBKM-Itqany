import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class loginauto_hub_v1Test {
    @Test
    void testMain() throws IOException, InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("use-fake-device-for-media-stream"); // use fake camera and microphone
        options.addArguments("use-fake-ui-for-media-stream"); // use fake UI for camera and microphone
		WebDriver driver = new ChromeDriver(options);
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        options.addArguments("disable-extensions");
		
		String csvFile = "D:/MSIB/ARKATAMA/data-hub.csv";
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(";");
            String email = data[0];
            String password = data[1];
            
            // open the login page
            driver.get("https://hub.arkatama.id/auth");

            // find the email and password fields and enter values
            WebElement emailField = driver.findElement(By.name("username"));
            WebElement passwordField = driver.findElement(By.name("password"));
            emailField.sendKeys(email);
            passwordField.sendKeys(password);

            // click the login button
            String beforeUrl = driver.getCurrentUrl();
            WebElement loginButton = driver.findElement(By.xpath("//button[@type = 'submit' and span='Login']"));
            loginButton.click();
                
            Thread.sleep(2500);
            
            String afterUrl = driver.getCurrentUrl();

            if (beforeUrl.equals(afterUrl)) {
            	System.out.println("Login test FAILED for user: " + email);
                Thread.sleep(1000);
                    
                driver.manage().deleteAllCookies();
                driver.navigate().refresh();
            } else {
            	  System.out.println("Login test passed for user: " + email);
                  Thread.sleep(1000);
                      
                  driver.manage().deleteAllCookies();
                  driver.navigate().refresh();
                  
            } 
            
        }
        br.close();
        driver.quit();
    }
}
