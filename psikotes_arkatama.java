// import java.io.BufferedReader;
// import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
// import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.openqa.selenium.support.ui.Select;
// import org.openqa.selenium.support.ui.WebDriverWait;

public class psikotes_arkatama {

    public static void main(String[] args) throws IOException, InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("use-fake-device-for-media-stream"); // use fake camera and microphone
        options.addArguments("use-fake-ui-for-media-stream"); // use fake UI for camera and microphone
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        options.addArguments("disable-extensions");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        JavascriptExecutor js = (JavascriptExecutor) driver;
       
        // open the login page
        driver.get("https://psikotest.arkatama.id/");
        Thread.sleep(3000);

        WebElement emailField = driver.findElement(By.id("user_email"));
        WebElement passwordField = driver.findElement(By.id("user_password"));
        emailField.sendKeys("9881905524310015");
        passwordField.sendKeys("password");
        Thread.sleep(3000);
        
        // login
        WebElement loginButton = driver.findElement(By.xpath("//button[@type = 'submit' and @class = 'btn btn-lg btn-block text-light btn-login mt-5']"));
        loginButton.click();
        Thread.sleep(5000);

        // Klik tombol simpan
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(2000);
            
        WebElement simpanButton = driver.findElement(By.xpath("//*[@id=\"form-profile\"]/div[2]/div/button"));
        simpanButton.click();
        Thread.sleep(3000);

        // Klik tombol mulai
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(2000);

        WebElement mulaiButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[3]/div[2]/div/div/main/a"));
        mulaiButton.click();
        Thread.sleep(3000);

        // Pilih jawaban
        for(int i = 0; i<90; i++){
            WebElement optionA = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[3]/div[2]/div/div/main/a"));
            WebElement optionB = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[3]/div[2]/div/div/main/b"));

            if(i%2==0){
                optionA.click();
            } else {
                optionB.click();
            }

            WebElement nextButton = driver.findElement(By.xpath("//button[@type = 'submit' and @class = 'next-question btn btn-primary px-5']"));
            nextButton.click();
            Thread.sleep(3000);
        }

        // next operation
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        String beforeUrl = driver.getCurrentUrl();
        WebElement nextButton = driver.findElement(By.cssSelector("#step-4 > div:nth-child(3) > div > button.btn.btn-success.float-end.btn-next.mx-2"));
        nextButton.click();
        Thread.sleep(3000);
        String afterUrl = driver.getCurrentUrl();

        if (beforeUrl.equals(afterUrl)) {
            System.out.println("");
            Thread.sleep(1000);
                
            driver.manage().deleteAllCookies();
            driver.navigate().refresh();
        } else {
            System.out.println("");
            Thread.sleep(1000);
                  
            driver.manage().deleteAllCookies();
            driver.navigate().refresh();
        } 
            
        driver.quit();
    }
}
