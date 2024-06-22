import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class loginauto_pusdiktan_v1 {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		ChromeOptions options = new ChromeOptions();
        options.addArguments("use-fake-device-for-media-stream"); // use fake camera and microphone
        options.addArguments("use-fake-ui-for-media-stream"); // use fake UI for camera and microphone
		WebDriver driver = new ChromeDriver(options);
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        options.addArguments("disable-extensions");
		
		String csvFile = "D:/MSIB/ARKATAMA/data-cat.csv";
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(";");
            String email = data[0];
            String password = data[1];
            

            // open the login page
            driver.get("https://pmb.pusdiktan.id/index.php/auth/login");

            // start time
            long start = System.nanoTime();
            
            // find the email and password fields and enter values
            WebElement emailField = driver.findElement(By.name("username"));
            WebElement passwordField = driver.findElement(By.name("password"));
            emailField.sendKeys(email);
            passwordField.sendKeys(password);
            
            // Delay for captcha
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // Switch to the iframe
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='reCAPTCHA']")));
            WebElement captchaCheckbox = driver.findElement(By.cssSelector(".recaptcha-checkbox-border"));
            Thread.sleep(2000);
            captchaCheckbox.click();
            Thread.sleep(3000);

            // Switch back to the default content
            driver.switchTo().defaultContent();
            Thread.sleep(3000);

            // click the captcha button
            try{
                if(driver.findElement(By.xpath("//iframe[@title='recaptcha challenge expires in two minutes']")) != null){
                    WebElement captchaImage1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='rc-imageselect-instructions']")));
                    captchaImage1.click();
                    // try{
                    //     if(driver.findElement(By.xpath("//iframe[@title='recaptcha challenge expires in two minutes']")) != null){
                    //         WebElement captchaImage2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='rc-imageselect-instructions']")));
                    //         captchaImage2.click();
                    //     }
                    // } catch(Exception e){
                    //     System.out.println("Exception " + e.getMessage() + " found!");
                    // }
                }
            }catch(Exception e){

            }
           
            // click the login button
            String beforeUrl = driver.getCurrentUrl();
            // WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type = 'submit' and @value = 'MASUK']")));
            WebElement loginButton = driver.findElement(By.xpath("//input[@type = 'submit' and @value = 'MASUK']"));
            loginButton.click();
            // Thread.sleep(5000);
            String afterUrl = driver.getCurrentUrl();

            if (beforeUrl.equals(afterUrl)) {
                long finish = System.nanoTime();
                long timeElapsed = (finish - start)/1000000;
            	System.out.println("Login test FAILED for user: " + email + " || Execution time : " + timeElapsed);
                // Thread.sleep(1000);
                    
                driver.manage().deleteAllCookies();
                // driver.navigate().refresh();
                
            } else {
                long finish = System.nanoTime();
                long timeElapsed = (finish - start)/1000000;
            	System.out.println("Login test passed for user: " + email + " || Execution time : " + timeElapsed);
                // Thread.sleep(1000);
                      
                driver.manage().deleteAllCookies();
                // driver.navigate().refresh();
                // Thread.sleep(1000);
                driver.get("https://pmb.pusdiktan.id/index.php/auth/login");  
            } 
            // cek per user berapa lama dan keseluruhan berapa
            // cek report grade, seperti image
        }
        br.close();
        driver.quit();
	}
}
