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
Context Menu https://the-internet.herokuapp.com/context_menu
- правый клик по элементу
- валидация текста на алерте
- закрытие алерта
 */
public class ContextMenuTest {

    @Test
    public void checkContextMenu() {
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
        driver.get("https://the-internet.herokuapp.com/context_menu");
        // Дополнительное
        SoftAssert softAssert = new SoftAssert();
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement hotSpot = driver.findElement(By.cssSelector("#hot-spot"));
        actions.contextClick(hotSpot).perform();
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        softAssert.assertEquals(alertText, "You selected a context menu",
                "Текст не совпадает");
        driver.switchTo().alert().accept();
        softAssert.assertTrue(hotSpot.isDisplayed(),
                "Элемент должен быть доступен после закрытия alert");

        softAssert.assertAll();
        driver.quit();
    }
}