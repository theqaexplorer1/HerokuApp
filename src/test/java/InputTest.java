import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

/*
Inputs - Проверить на возможность ввести различные цифровые и
нецифровые значения, используя Keys.ARROW_UP И
Keys.ARROW_DOWN
Локатор: By.tagName(“input”)
 */
public class InputTest {
    @Test
    public void checkInput() {
        //задаем опции для нашего драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        //определяем браузер с которым хотим работать
        WebDriver driver = new ChromeDriver(options);
        // ожидание
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //Soft Assert
        SoftAssert softAssert = new SoftAssert();
        // открываем страницу
        driver.get("https://the-internet.herokuapp.com/inputs");

        WebElement input = driver.findElement(By.tagName("input"));

        //Проверка ввода чисел через sendkeys
        input.sendKeys("5");
        softAssert.assertEquals(input.getAttribute("value"), "5", "Должно быть введено 5");
        System.out.printf("Проверка ввода чисел: %s%n", input.getAttribute("value"));

        //Проверка нажатия стрелки вверх 2 раза
        input.sendKeys(Keys.ARROW_UP);
        input.sendKeys(Keys.ARROW_UP);
        softAssert.assertEquals(input.getAttribute("value"), "7", "Должно быть введено 7");
        System.out.printf("Проверка ввода после Keys.ARROW_UP*2: %s%n", input.getAttribute("value"));

        //Проверка нажатия стрелки вниз 2 раза
        input.sendKeys(Keys.ARROW_DOWN);
        input.sendKeys(Keys.ARROW_DOWN);
        softAssert.assertEquals(input.getAttribute("value"), "5", "Должно быть введено 5");
        System.out.printf("Проверка ввода после Keys.ARROW_DOWN*2: %s%n", input.getAttribute("value"));

        //Проверка ввода букв
        input.clear();
        input.sendKeys("A");
        String result = input.getAttribute("value");
        softAssert.assertTrue(result.isEmpty() || !result.contains("A"), "Не должно быть букв в числовом поле");
        System.out.printf("Проверка ввода букв: %s%n", input.getAttribute("value"));

        //Проверка ввода отрицательных чисел
        input.clear();
        input.sendKeys("-5");
        softAssert.assertEquals(input.getAttribute("value"), "-5", "Должно быть введено -5");
        System.out.printf("Проверка ввода отрицательных чисел: %s%n", input.getAttribute("value"));

        //Проверка ввода дробных чисел
        input.clear();
        input.sendKeys("3.14");
        softAssert.assertEquals(input.getAttribute("value"), "3.14", "Должно быть введено 3.14");
        System.out.printf("Проверка ввода дробных чисел: %s%n", input.getAttribute("value"));

        softAssert.assertAll();

        driver.quit();
    }
}
