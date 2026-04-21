import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

/*
проверить, что первый чекбокс unchecked, отметить
первый чекбокс, проверить что он checked. Проверить, что второй чекбокс
checked, сделать unheck, проверить, что он unchecked
Локатор: By.cssSelector("[type=checkbox]”)
 */
public class CheckBoxesTest {

    @Test
    public void checkBoxElementsTest() {
        //задаем опции для нашего драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        //определяем браузер с которым хотим работать
        WebDriver driver = new ChromeDriver(options);
        // ожидание
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //Объект SoftAssert
        SoftAssert softAssert = new SoftAssert();
        // открываем страницу
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        List<WebElement> checkboxes = driver.findElements(By.cssSelector("[type=checkbox]"));

        //проверяем что первый чекбокс unchecked
        softAssert.assertFalse(checkboxes.get(0).isSelected(), "Первый чекбокс unchecked");
        checkboxes.get(0).click();
        //проверяем что первый чекбокс checked
        softAssert.assertTrue(checkboxes.get(0).isSelected(), "Первый чекбокс checked");

        //проверяем что второй чекбокс checked
        softAssert.assertTrue(checkboxes.get(1).isSelected(), "Второй чекбокс checked");
        checkboxes.get(1).click();

        //проверяем что второй чекбокс unchecked
        softAssert.assertFalse(checkboxes.get(1).isSelected(), "Второй чекбокс unchecked");

        softAssert.assertAll();

        driver.quit();
    }
}
