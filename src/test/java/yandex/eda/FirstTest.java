package yandex.eda;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {
    public ChromeDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","D://chromedriver.exe");
        driver = new ChromeDriver();
        System.out.println("Test started");
    }

    @Test
    public void firstTest() {
        driver.get("http://eda.yandex/");
        String title = driver.getTitle();
        Assert.assertTrue(title.equals("Быстрая доставка еды из ресторанов в Санкт-Петербурге — Яндекс.Еда"));
    }

    @Test
    public void secondTest() {
        driver.get("http://eda.yandex/");
        String title = driver.getTitle();
        Assert.assertFalse(title.equals("Хорошая доставка еды из ресторанов в Санкт-Петербурге — Яндекс.Еда"));
    }

    @After
    public void close() {
        driver.quit();
        System.out.println("Test closed");
    }
}
