package yandex.eda;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.naming.ldap.Control;
import javax.swing.text.Element;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.id;

public class FirstTest extends SettingsWebDriver {

    @Test
    public void testTitle() {
        String title = driver.getTitle();
        Assert.assertTrue(title.equals("Быстрая доставка еды из ресторанов в Санкт-Петербурге — Яндекс.Еда"));
    }

    @Test
    public void application () {
        String originalWindow = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        WebElement restaurants = driver.findElement(By.cssSelector("[class=\"DesktopHeader_restaurantsLink\"]"));
        restaurants.click();

        //Закрытие первоначального окна, переход на открывшуюся вкладку
        String newWindow = (new WebDriverWait(driver, 10))
                .until(new ExpectedCondition<String>() {
                           public String apply(WebDriver driver) {
                               Set<String> newWindowsSet = driver.getWindowHandles();
                               newWindowsSet.removeAll(oldWindowsSet);
                               return newWindowsSet.size() > 0 ?
                                       newWindowsSet.iterator().next() : null;
                           }
                       }
                );
        driver.switchTo().window(originalWindow);
        driver.close();
        driver.switchTo().window(newWindow);

        //Сверяем title
        String title = driver.getTitle();
        Assert.assertTrue(title.equals("Станьте партнёром Яндекс.Еды"));

        //Нажатие кнопки "Отправить заявку"
        WebDriverWait wait = new WebDriverWait(driver,40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"knopki-1\"]/div/div/div/div/a")));
        WebElement request = driver.findElement((By.xpath("//*[@id=\"knopki-1\"]/div/div/div/div/a")));
        request.click();
        //Переходим на фрэйм
        WebElement iframe = driver.findElement(By.xpath("//*[@id=\"forma-1\"]/div/div/div/iframe"));
        driver.switchTo().frame(iframe);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_answer_short_text_90483")));

        Random random = new Random();
        int n1 = random.nextInt(999)+ 000;
        int n2 = random.nextInt(999)+ 000;
        int n3 = random.nextInt(999)+ 000;

        String clientname = "Ilya Azanov";
        driver.findElement(By.id("id_answer_short_text_90483")).sendKeys(clientname);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("id_param_phone")));

        String phone = "+7(" + n1 + ")" + n2 + "-35-81";
        driver.findElement(By.id("id_param_phone")).sendKeys(phone);

        String email = "ilyaazanov"+ n3 + "@mail.ru";
        driver.findElement(By.id("id_answer_non_profile_email_90485")).sendKeys(email);

        String organization = "Рога и копыта";
        driver.findElement(By.id("id_answer_short_text_90481")).sendKeys(organization);
        String city = "Норильск, Красноярский край, Россия";
        driver.findElement(By.id("id_param_city2")).sendKeys(city);
        String address = "ул. Пушкина д.22 оф. 44/23";
        driver.findElement(By.id("id_answer_short_text_103814")).sendKeys(address);
        String site = "www.rogakopita.ru";
        driver.findElement(By.id("id_answer_url_114212")).sendKeys(site);
        String comment = "Тестирование формы отправки";
        driver.findElement(By.id("id_answer_short_text_90492")).sendKeys(comment);
        driver.findElement(By.id("id_answer_boolean_90493")).click();
        driver.findElement(By.cssSelector("[title=\"Отправить\"]")).click();

        /*WebElement element = driver.findElement(By.xpath("[/html/body/div[2]/h3/div/p]"));
        String element2 = element.getText();
        Assert.assertFalse(element2.equals("dfvdfvdvdvdfdfv"));
        System.out.print(element2);*/









    }



    }


