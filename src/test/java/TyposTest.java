import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

/*
https://the-internet.herokuapp.com/typos
Проверить соответствие параграфа орфографии
Локатор: By.tagName(“p”)
или по xpath
 */

public class TyposTest {
    @Test
    public void checkTypos() {
        //задаем опции для нашего драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        //определяем браузер с которым хотим работать
        WebDriver driver = new ChromeDriver(options);
        //Soft Assert
        SoftAssert softAssert = new SoftAssert();
        // ожидание
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // открываем страницу
        driver.get("https://the-internet.herokuapp.com/typos");

        // Проверяем первый тег P
        String typosText1 = driver.findElement(By.xpath("(//p)[1]")).getText();
        System.out.println(typosText1);
        softAssert.assertEquals(typosText1, "This example demonstrates a typo being introduced. It does it randomly on each page load.", "Ошибка в первом абзаце");

        //Проверяем второй тег P
        String typosText2 = driver.findElement(By.xpath("(//p)[2]")).getText();
        System.out.println(typosText2);
        softAssert.assertEquals(typosText2, "Sometimes you'll see a typo, other times you won't.", "Ошибка во втором абзаце");

        softAssert.assertAll();
        driver.quit();


    }
}
