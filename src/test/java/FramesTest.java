import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

/*
Frames https://the-internet.herokuapp.com/iframe
- Открыть iFrame
- Проверить, что текст внутри параграфа равен “Your content goes here.”
 */
public class FramesTest {

    @Test
    public void checkFrames() {
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
        driver.get("https://the-internet.herokuapp.com/iframe");
        // Дополнительное
        SoftAssert softAssert = new SoftAssert();
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("#mce_0_ifr")));
        WebElement content = driver.findElement(By.cssSelector("#tinymce p"));
        Assert.assertEquals(content.getText(), "Your content goes here.",
                "Текст в Iframe не совпадает с Your content goes here.");
        driver.switchTo().defaultContent();

        driver.quit();
    }
}