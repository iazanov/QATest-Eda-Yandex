package yandex.eda;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SettingsWebDriver {
    public WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","D://chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://www.eda.yandex/");
    }
    /*@After
    public void close() {
        driver.quit();
    }*/
}
