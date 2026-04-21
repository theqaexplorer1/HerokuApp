import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.Scanner;

public class AddRemoveElementTest {

    @Test
    public void checkAddRemoveElement() {
        //задаем опции для нашего драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");

        //определяем браузер с которым хотим работать
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        SoftAssert softAssert = new SoftAssert();

        //открывает страницу по указанному url
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");

        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();

        int size = driver.findElements(By.xpath("//button[text()='Delete']")).size();
        softAssert.assertEquals(size, 2);

        driver.findElement(By.xpath("//button[text()='Delete']")).click();

        int size1 = driver.findElements(By.xpath("//button[text()='Delete']")).size();
        softAssert.assertEquals(size1, 1);

        softAssert.assertAll();

        //закрывает браузер
        driver.quit();
    }
}