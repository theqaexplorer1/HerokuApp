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

import java.time.Duration;

/*
Dynamic Controls https://the-internet.herokuapp.com/dynamic_controls
- нажать на кнопку Remove около чекбокса
- дождаться надписи “It’s gone”
- проверить, что чекбокса нет
- найти инпут
- проверить, что он disabled
- нажать на кнопку
- дождаться надписи “It's enabled!”
- проверить, что инпут enabled
 */
public class DynamicControlsTest {

    @Test
    public void  checkDynamicControls() {
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
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        // Дополнительное
        SoftAssert softAssert = new SoftAssert();
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Часть 1 задания, нажать Remove, чекбокс исчезает,
        // дождаться надписи It’s gone и проверить, что чекбокса нет
        WebElement removeCheckBoxButton = driver.findElement(By.cssSelector("#checkbox-example button"));
        removeCheckBoxButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#message")));
        String message = driver.findElement(By.cssSelector("#message")).getText();
        softAssert.assertEquals(message, "It's gone!", "Текст #message не совпадает");
        int checkBoxCount = driver.findElements(By.cssSelector("#checkbox")).size();
        softAssert.assertEquals(checkBoxCount, 0, "Не должно быть элемента #checkbox");

        //Часть 2 задания - найти input, проверить, что он disabled
        // нажать на кнопку, дождаться надписи “It's enabled!” и проверить, что инпут enabled
        WebElement input = driver.findElement(By.cssSelector("#input-example input"));
        boolean isInputDisabled = !input.isEnabled();
        softAssert.assertTrue(isInputDisabled, "Input должен быть Disabled");
        WebElement enableInput = driver.findElement(By.cssSelector("#input-example button"));
        enableInput.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#message")));
        String message2 = driver.findElement(By.cssSelector("#message")).getText();
        softAssert.assertEquals(message2, "It's enabled!", "Текст #message не совпадает");
        boolean isInputEnabled = driver.findElement(By.cssSelector("#input-example input")).isEnabled();
        softAssert.assertTrue(isInputEnabled, "Input должен быть Enabled");

        softAssert.assertAll();
        driver.quit();
    }
}