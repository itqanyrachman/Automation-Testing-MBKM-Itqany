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

public class step2pendaftaran_siakad {

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

        // find pendaftaran step 2 button
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(2000);
        
        WebElement pendaftaran = driver.findElement(By.xpath("//*[@id=\"panelsStayOpen-collapseOne\"]/div/div/div[2]/div/div/div[2]/a"));
        pendaftaran.click();
        Thread.sleep(3000);

        WebElement step2 = driver.findElement(By.xpath("//*[@id=\"smartwizard\"]/ul/li[2]/a"));
        step2.click();
        Thread.sleep(3000);
        
        // input data
        WebElement nisn = driver.findElement(By.id("nomor"));
        Select jenisInstansi = new Select(driver.findElement(By.id("jenis_instansi")));
        WebElement namaInstansi = driver.findElement(By.id("nama_instansi"));
        Select provinsiSekolah = new Select(driver.findElement(By.id("kode_provinsi_instansi")));
        
        nisn.clear();
        namaInstansi.clear();

        nisn.sendKeys("0033344300");
        jenisInstansi.selectByVisibleText("SMAN");
        namaInstansi.sendKeys("SMAN 1 Kota Malang");
        provinsiSekolah.selectByVisibleText("JAWA TIMUR");
        Thread.sleep(5000);

        // scroll
        js.executeScript("window.scrollBy(0,500)");
        Select kabupatenSekolah = new Select(driver.findElement(By.id("kode_kabupaten_instansi")));
        Select kecamatanSekolah = new Select(driver.findElement(By.id("kode_kecamatan_instansi")));
        WebElement alamatSekolah = driver.findElement(By.id("alamat_instansi"));
        WebElement jurusan = driver.findElement(By.id("jurusan"));
        WebElement tahunLulus = driver.findElement(By.id("tahun_lulus"));

        kabupatenSekolah.selectByVisibleText("KOTA MALANG");
        Thread.sleep(5000);
        kecamatanSekolah.selectByVisibleText("KLOJEN");
        Thread.sleep(5000);

        alamatSekolah.clear();
        jurusan.clear();
        tahunLulus.clear();
        alamatSekolah.sendKeys("Jalan Kertanegara No 1");
        jurusan.sendKeys("IPA");
        tahunLulus.sendKeys("2022");

        // save operation
        String beforeUrl = driver.getCurrentUrl();
        WebElement next = driver.findElement(By.cssSelector("#form-instansi > div:nth-child(7) > div > button.btn.btn-success.float-end.bg-green.mx-2"));
        next.click();
        Thread.sleep(3000);
        
        WebElement saveButton = driver.findElement(By.cssSelector("body > div.swal2-container.swal2-center.swal2-backdrop-show > div > div.swal2-actions > button.swal2-confirm.swal2-styled"));
        saveButton.click();
        Thread.sleep(3000);

        WebElement okButton = driver.findElement(By.cssSelector("body > div.swal2-container.swal2-center.swal2-backdrop-show > div > div.swal2-actions > button.swal2-confirm.swal2-styled"));
        okButton.click();
        Thread.sleep(3000);
        
        String afterUrl = driver.getCurrentUrl();

        if (beforeUrl.equals(afterUrl)) {
            System.out.println("Pendaftaran step 2 test FAILED for user!");
            Thread.sleep(1000);
                
            driver.manage().deleteAllCookies();
            driver.navigate().refresh();
        } else {
              System.out.println("Pendaftaran step 2 test passed for user!");
              Thread.sleep(1000);
                  
              driver.manage().deleteAllCookies();
              driver.navigate().refresh();
              
        } 
            
        driver.quit();
    }
}
