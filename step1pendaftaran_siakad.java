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

public class step1pendaftaran_siakad {

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

        WebElement emailField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        emailField.sendKeys("9881905524310015");
        passwordField.sendKeys("310015");
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(1000);

        // captcha
        WebElement captchaText = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='captcha']")));
        captchaText.click();
        Thread.sleep(5000);
        
        // login
        WebElement loginButton = driver.findElement(By.xpath("//button[@type = 'submit' and @class = 'btn btn-red-arkatama ']"));
        loginButton.click();
        Thread.sleep(5000);

        // find pendaftaran step 1 button
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(1000);
        
        WebElement step1 = driver.findElement(By.xpath("//*[@id=\"panelsStayOpen-collapseOne\"]/div/div/div[2]/div/div/div[2]/a"));
        step1.click();
        Thread.sleep(2000);

        // input data
        js.executeScript("window.scrollBy(0,600)");
        Thread.sleep(3000);
        WebElement tempatLahirElement = driver.findElement(By.id("tempat_lahir"));
        WebElement genderElement = driver.findElement(By.id("gender2"));
        Select tanggalField = new Select(driver.findElement(By.name("tgl_lahir")));
        Select bulanField = new Select(driver.findElement(By.name("bln_lahir")));
        WebElement tahunField = driver.findElement(By.name("thn_lahir"));
        Select agamaField = new Select(driver.findElement(By.id("id_agama")));
        Select kwnField = new Select(driver.findElement(By.id("kewarganegaraan")));
        WebElement nikField = driver.findElement(By.id("nik"));
        WebElement alamatField = driver.findElement(By.id("alamat"));
        
        tempatLahirElement.sendKeys("Malang");
        genderElement.click();
        tanggalField.selectByVisibleText("24");
        bulanField.selectByVisibleText("Desember");
        tahunField.sendKeys("2002");
        agamaField.selectByVisibleText("Islam");
        kwnField.selectByVisibleText("WNI");
        nikField.sendKeys("3573042413020001");

        js.executeScript("window.scrollBy(0,200)");
        WebElement wniContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wilayah-indonesia")));
        Select provinsiField = new Select(wniContainer.findElement(By.id("provinsi")));
        Select kabupatenField = new Select(wniContainer.findElement(By.id("kabupaten")));
        Select kecamatanField = new Select(wniContainer.findElement(By.id("kecamatan")));

        provinsiField.selectByVisibleText("JAWA TIMUR");
        Thread.sleep(5000);
        kabupatenField.selectByVisibleText("KOTA MALANG");
        Thread.sleep(5000);
        kecamatanField.selectByVisibleText("LOWOKWARU");
        Thread.sleep(5000);
        alamatField.sendKeys("Perum Joyogrand A-20");

        // find the upload element
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(1000);
        WebElement uploadElement = driver.findElement(By.id("image"));
        uploadElement.sendKeys("D:\\MSIB\\arkatama.png"); 
        Thread.sleep(3000);

        WebElement modalContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crop_modal")));
        WebElement cropElement = modalContainer.findElement(By.id("crop-img"));
        cropElement.click();
        Thread.sleep(3000);

        WebElement savePendaftaran = driver.findElement(By.id("savependaftaran"));
        savePendaftaran.click();
        Thread.sleep(5000);
        
        WebElement confirmButton = driver.findElement(By.cssSelector("body > div.swal2-container.swal2-center.swal2-backdrop-show > div > div.swal2-actions > button.swal2-confirm.swal2-styled"));
        confirmButton.click();
        
        Thread.sleep(5000);
        js.executeScript("window.scrollTo(0,0)");
        Thread.sleep(5000);
            
        driver.quit();
    }
}
