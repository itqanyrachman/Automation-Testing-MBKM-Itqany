// import java.io.BufferedReader;
// import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class step3pendaftaran_siakad {

    public static void main(String[] args) throws IOException, InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("use-fake-device-for-media-stream"); // use fake camera and microphone
        options.addArguments("use-fake-ui-for-media-stream"); // use fake UI for camera and microphone
		WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        options.addArguments("disable-extensions");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        JavascriptExecutor js = (JavascriptExecutor) driver;
       
        // open the login page
        driver.get("https://pmb.siakad.link/auth");
        Thread.sleep(3000);

        WebElement emailField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        emailField.sendKeys("9881905524310015");
        passwordField.sendKeys("310015");
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(3000);

        // captcha
        WebElement captchaText = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='captcha']")));
        captchaText.click();
        Thread.sleep(5000);
        
        // login
        WebElement loginButton = driver.findElement(By.xpath("//button[@type = 'submit' and @class = 'btn btn-red-arkatama ']"));
        loginButton.click();
        Thread.sleep(5000);

        // find pendaftaran step 3 button
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(2000);
        
        WebElement pendaftaran = driver.findElement(By.xpath("//*[@id=\"panelsStayOpen-collapseOne\"]/div/div/div[2]/div/div/div[2]/a"));
        pendaftaran.click();
        Thread.sleep(3000);

        WebElement step3 = driver.findElement(By.xpath("//*[@id=\"smartwizard\"]/ul/li[3]/a"));
        step3.click();
        Thread.sleep(3000);

        // next operation
        String beforeUrl = driver.getCurrentUrl();
        WebElement nextButton = driver.findElement(By.cssSelector("#form-prodi > div:nth-child(6) > div > button.btn.btn-success.float-end.btn-next.mx-2"));
        nextButton.click();
        Thread.sleep(3000);
        String afterUrl = driver.getCurrentUrl();

        if (beforeUrl.equals(afterUrl)) {
            System.out.println("Pendaftaran step 3 test FAILED for user!");
            Thread.sleep(1000);
                
            driver.manage().deleteAllCookies();
            driver.navigate().refresh();
        } else {
            System.out.println("Pendaftaran step 3 test passed for user!");
            Thread.sleep(1000);
                  
            driver.manage().deleteAllCookies();
            driver.navigate().refresh();
              
        } 
            
        driver.quit();
    }
}
