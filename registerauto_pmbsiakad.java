import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
// import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
// import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.openqa.selenium.support.ui.WebDriverWait;

public class registerauto_pmbsiakad {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		ChromeOptions options = new ChromeOptions();
        options.addArguments("use-fake-device-for-media-stream"); // use fake camera and microphone
        options.addArguments("use-fake-ui-for-media-stream"); // use fake UI for camera and microphone
		WebDriver driver = new ChromeDriver(options);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        options.addArguments("disable-extensions");
        driver.manage().window().maximize();
		
		String csvFile = "D:/MSIB/ARKATAMA/data-pmb-siakad.csv";
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(";");
            String nama = data[0];
            String nomor_hp = data[1];
            String email = data[2];
            String pilihan1 = data[3];
            String pilihan2 = data[4];

            // open the login page
            driver.get("https://pmb.siakad.link/registrasi/mandiri/5f5b066a-d6ff-4898-a397-44400341a334");

            // start time
            long start = System.nanoTime();
            
            // find the email and password fields and enter values
            WebElement namaField = driver.findElement(By.id("nama"));
            WebElement nomorHpField = driver.findElement(By.id("no_hp"));
            WebElement emailField = driver.findElement(By.id("email"));
            Select pilihan1field = new Select(driver.findElement(By.id("pilihan1")));
            Select pilihan2field = new Select(driver.findElement(By.id("pilihan2")));
            WebElement daftarButton = driver.findElement(By.xpath("//button[@type = 'submit' and text() = ' Daftar']"));
            
            // field nama
            if(nama.equals("null")){
                namaField.sendKeys("");
                Thread.sleep(1000);
            } else {
                namaField.sendKeys(nama);
                Thread.sleep(1000);
            }

            // field nomor hp
            if(nomor_hp.equals("null")){
                nomorHpField.sendKeys("");
                Thread.sleep(1000);
            } else {
                nomorHpField.sendKeys(nomor_hp);
                Thread.sleep(1000);
            }
            
            // field email
            if(email.equals("null")){
                emailField.sendKeys("");
                Thread.sleep(1000);
            } else {
                emailField.sendKeys(email);
                Thread.sleep(1000);
            }
            
            // field prodi 1
            if(pilihan1.equals("null")){
                Thread.sleep(1000);
            } else {
                pilihan1field.selectByVisibleText(pilihan1);
                Thread.sleep(4000);
            }
            
            // scroll bottom
            js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
            Thread.sleep(1000);

            // field pilihan 2
            if(pilihan2.equals("null")){
                // Scrolling down the page till the element is found		
                js.executeScript("arguments[0].scrollIntoView();", daftarButton);

                // click the register button
                daftarButton.click();
                Thread.sleep(3000);
            } else {
                try {
                    pilihan2field.selectByVisibleText(pilihan2);
                    Thread.sleep(3000);
                    // Scrolling down the page till the element is found		
                    js.executeScript("arguments[0].scrollIntoView();", daftarButton);

                    // click the register button
                    daftarButton.click();
                    Thread.sleep(3000);
                } catch (Exception e) {
                    
                }
            }     

            try{
                WebElement okButton = driver.findElement(By.xpath("//button[@type = 'button' and text() = 'OK']"));
                okButton.click();
                Thread.sleep(3000);
                long finish = System.nanoTime();
                long timeElapsed = (finish - start)/1000000;
            	System.out.println("Test Daftar berhasil pada peserta: " + nama + " || Execution time : " + timeElapsed);
                      
                driver.manage().deleteAllCookies();
                driver.navigate().refresh();
            } catch(Exception e){
                long finish = System.nanoTime();
                long timeElapsed = (finish - start)/1000000;
            	System.out.println("Test Daftar GAGAL pada peserta : " + nama + " || Execution time : " + timeElapsed);
                Thread.sleep(3000);
                    
                driver.manage().deleteAllCookies();
                driver.navigate().refresh();
            }
            
        }
        br.close();
        driver.quit();
	}
}
