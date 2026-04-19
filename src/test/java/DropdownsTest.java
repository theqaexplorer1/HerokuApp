import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

/*
https://the-internet.herokuapp.com/dropdown
Взять все элементы дроп-дауна и проверить их наличие.
Выбрать первый, проверить, что он выбран, выбрать второй, проверить, что
он выбран
Локатор: By.id(“dropdown”)
 */
public class DropdownsTest {

    @Test
    public void checkDropdowns() {
        //задаем опции для нашего драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        //определяем браузер с которым хотим работать
        WebDriver driver = new ChromeDriver(options);
        // ожидание
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // открываем страницу
        driver.get("https://the-internet.herokuapp.com/dropdown");

        WebElement dropdown = driver.findElement(By.id("dropdown"));
        List<WebElement> dropdownOptions = dropdown.findElements(By.tagName("option"));
        System.out.printf("Найдено опций внутри dropdowns %s", dropdownOptions.size());
        //проверяем наличие всех элементов в dropdown
        Assert.assertEquals(dropdownOptions.size(), 3);
        //кликаем первую опцию и проверяем, что она выбрана
        dropdownOptions.get(1).click();
        Assert.assertTrue(dropdownOptions.get(1).isSelected(), "Опция 1 должна быть выбрана");
        // кликаем вторую опцию и проверяем, что она выбрана
        dropdownOptions.get(2).click();
        Assert.assertTrue(dropdownOptions.get(2).isSelected(), "Опция 2 должна быть выбрана");

        driver.quit();


    }

}
