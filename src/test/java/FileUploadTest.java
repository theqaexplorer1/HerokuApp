import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.time.Duration;

/*
File Upload https://the-internet.herokuapp.com/upload
- загрузить файл
- проверить, что имя файла на странице совпадает с именем загруженного файла
 */
public class FileUploadTest {

    @Test
    public void checkFileUpload() {
        //задаем опции для нашего драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        //определяем браузер с которым хотим работать
        WebDriver driver = new ChromeDriver(options);
        // неявное ожидание
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // открываем страницу
        driver.get("https://the-internet.herokuapp.com/upload");
        // Дополнительное
        SoftAssert softAssert = new SoftAssert();
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        File file = new File("src/test/resources/file_upload_test.txt");
        driver.findElement(By.cssSelector("#file-upload")).sendKeys(file.getAbsolutePath());
        driver.findElement(By.cssSelector("#file-submit")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uploaded-files")));
        String uploadedFileName = driver.findElement(By.id("uploaded-files")).getText();
        softAssert.assertEquals(uploadedFileName, "file_upload_test.txt", "Имя файла не совпадает");

        softAssert.assertAll();
        driver.quit();
    }
}