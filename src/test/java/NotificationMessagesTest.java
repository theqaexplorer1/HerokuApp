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
https://the-internet.herokuapp.com/notification_message_rendered
Notification Messages - кликнуть на кнопку, дождаться появления
нотификации, проверить соответствие текста ожиданиям
 */
public class NotificationMessagesTest {
    @Test
    public void checkNotificationMessage() {
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
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");

        //Находим ссылку и кликаем по ней
        WebElement link = driver.findElement(By.xpath("//a[contains(text(), 'Click here')]"));
        String linkText = link.getText();
        System.out.printf("Кликнули по ссылке: %s %n", linkText);
        link.click();

        //Находим и проверяем нотификацию
        WebElement notification = driver.findElement(By.xpath("//div[@id='flash']"));
        String notificationText = notification.getText().toLowerCase();
        Assert.assertTrue(notification.isDisplayed(), "Нотификация должна отображаться");
        System.out.printf("Нотификация появилась %s%n", notificationText);

        //Проверяем, что текст содержит ожидаемую фразу (независимо от регистра и лишних символов)
        Assert.assertTrue(
                notificationText.contains("action succesful") ||
                notificationText.contains("action unsuccesful"),
                "Текст нотификации должен содержать 'Action succesful' или 'Action unsuccesful'"
        );

        driver.quit();

    }
}
