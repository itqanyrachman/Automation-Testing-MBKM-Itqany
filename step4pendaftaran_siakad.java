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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class step4pendaftaran_siakad {

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

        // find pendaftaran step 4 button
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(2000);
        
        WebElement pendaftaran = driver.findElement(By.xpath("//*[@id=\"panelsStayOpen-collapseOne\"]/div/div/div[2]/div/div/div[2]/a"));
        pendaftaran.click();
        Thread.sleep(3000);

        WebElement step4 = driver.findElement(By.xpath("//*[@id=\"smartwizard\"]/ul/li[4]/a"));
        step4.click();
        Thread.sleep(3000);

        // input data prestasi
        WebElement tambahPrestasi = driver.findElement(By.id("btn-tambah"));
        tambahPrestasi.click();
        Thread.sleep(3000);

        WebElement modalPrestasi = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modal-prestasi")));
        WebElement namaPrestasi = modalPrestasi.findElement(By.id("nama_prestasi"));
        Select juara = new Select(modalPrestasi.findElement(By.id("juara")));
        Select tingkat = new Select(modalPrestasi.findElement(By.id("tingkat")));
        Select jenis = new Select(modalPrestasi.findElement(By.id("jenis")));
        WebElement tahunPrestasi = driver.findElement(By.id("tahun"));

        namaPrestasi.sendKeys("FIA FUTSAL CUP");
        juara.selectByVisibleText("2");
        tingkat.selectByVisibleText("PROVINSI");
        jenis.selectByVisibleText("KELOMPOK");
        tahunPrestasi.sendKeys("2022");
        
        // input file prestasi
        WebElement uploadElement = driver.findElement(By.id("dokumen"));
        uploadElement.sendKeys("D:\\MSIB\\arkatama.png"); 
        Thread.sleep(3000);

        // save operation
        WebElement saveButton = driver.findElement(By.cssSelector("#form-prestasi > div.modal-footer > button.btn.btn-success"));
        saveButton.click();
        Thread.sleep(3000);

        WebElement confirmButton = driver.findElement(By.cssSelector("body > div.swal2-container.swal2-center.swal2-backdrop-show > div > div.swal2-actions > button.swal2-confirm.swal2-styled"));
        confirmButton.click();
        Thread.sleep(3000);

        WebElement okButton = driver.findElement(By.cssSelector("body > div.swal2-container.swal2-center.swal2-backdrop-show > div > div.swal2-actions > button.swal2-confirm.swal2-styled"));
        okButton.click();
        Thread.sleep(3000);

        // next operation
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        String beforeUrl = driver.getCurrentUrl();
        WebElement nextButton = driver.findElement(By.cssSelector("#step-4 > div:nth-child(3) > div > button.btn.btn-success.float-end.btn-next.mx-2"));
        nextButton.click();
        Thread.sleep(3000);
        String afterUrl = driver.getCurrentUrl();

        if (beforeUrl.equals(afterUrl)) {
            System.out.println("Pendaftaran step 4 test FAILED for user!");
            Thread.sleep(1000);
                
            driver.manage().deleteAllCookies();
            driver.navigate().refresh();
        } else {
            System.out.println("Pendaftaran step 4 test passed for user!");
            Thread.sleep(1000);
                  
            driver.manage().deleteAllCookies();
            driver.navigate().refresh();
              
        } 
            
        driver.quit();
    }
}
