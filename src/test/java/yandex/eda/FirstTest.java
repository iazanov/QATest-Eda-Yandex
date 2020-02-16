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
        Assert.assertEquals(title, "Быстрая доставка еды из ресторанов в Санкт-Петербурге — Яндекс.Еда");
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
        Assert.assertEquals(title,"Станьте партнёром Яндекс.Еды");

        //Нажатие кнопки "Отправить заявку"
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"knopki-1\"]/div/div/div/div/a")));
        WebElement request = driver.findElement((By.xpath("//*[@id=\"knopki-1\"]/div/div/div/div/a")));
        request.click();
        //Переходим на фрэйм
        WebElement iframe = driver.findElement(By.xpath("//*[@id=\"forma-1\"]/div/div/div/iframe"));
        driver.switchTo().frame(iframe);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_answer_short_text_90483")));

        Random random = new Random();
        int n1 = random.nextInt(999);
        int n2 = random.nextInt(999);
        int n3 = random.nextInt(999);

        String clientname = "Ilya Azanov";
        driver.findElement(By.id("id_answer_short_text_90483")).sendKeys(clientname);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("id_param_phone")));

        String phone = "+7(" + n1 + ")" + n2 + "-35-81";
        driver.findElement(By.id("id_param_phone")).sendKeys(phone);

        String email = "ilyaazanov" + n3 + "@mail.ru";
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

        WebElement element = driver.findElement(By.cssSelector("[class=\"safe-content\"]"));
        String element2 = element.getText();
        Assert.assertEquals(element2,"Спасибо! С Вами свяжется представитель команды Яндекс.Еда!");
        System.out.print(element2);
    }
    @Test
    public void failApplication () {
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
        Assert.assertEquals(title,"Станьте партнёром Яндекс.Еды");

        //Нажатие кнопки "Отправить заявку"
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"knopki-1\"]/div/div/div/div/a")));
        WebElement request = driver.findElement((By.xpath("//*[@id=\"knopki-1\"]/div/div/div/div/a")));
        request.click();
        //Переходим на фрэйм
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"forma-1\"]/div/div/div/iframe")));
        WebElement iframe = driver.findElement(By.xpath("//*[@id=\"forma-1\"]/div/div/div/iframe"));
        driver.switchTo().frame(iframe);
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("id_answer_boolean_90493")).click();
        driver.findElement(By.id("id_answer_boolean_90493")).click();
        WebElement button = driver.findElement(By.cssSelector("[title=\"Отправить\"]"));
        button.click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[class=\"survey-validation__message survey-validation__message_from_client survey-validation__message_type_required survey-validation__message_visibility_visible\"]")));

        WebElement namefirst = driver.findElement(id("id_answer_short_text_90483"));
        WebElement namefirstParrent = namefirst.findElement(By.xpath("../../.."));
        String namefirstError = namefirstParrent.findElement(By.cssSelector("div")).getText();
        Assert.assertEquals(namefirstError,"Это поле обязательно");

        WebElement phonefirst = driver.findElement(id("id_param_phone"));
        WebElement phonefirstParrent = phonefirst.findElement(By.xpath("../../.."));
        String phonefirstError = phonefirstParrent.findElement(By.cssSelector("div")).getText();
        Assert.assertEquals(phonefirstError,"Это поле обязательно");

        WebElement emailfirst = driver.findElement(id("id_answer_short_text_90483"));
        WebElement emailfirstParrent = emailfirst.findElement(By.xpath("../../.."));
        String emailfirstError = emailfirstParrent.findElement(By.cssSelector("div")).getText();
        Assert.assertEquals(emailfirstError,"Это поле обязательно");

        WebElement orgfirst = driver.findElement(id("id_answer_short_text_90481"));
        WebElement orgfirstParrent = orgfirst.findElement(By.xpath("../../.."));
        String orgfirstError = orgfirstParrent.findElement(By.cssSelector("div")).getText();
        Assert.assertEquals(orgfirstError,"Это поле обязательно");

        WebElement cityfirst = driver.findElement(id("id_param_city2"));
        WebElement cityfirstParrent = cityfirst.findElement(By.xpath("../../.."));
        String cityfirstError = cityfirstParrent.findElement(By.cssSelector("div")).getText();
        Assert.assertEquals(cityfirstError,"Это поле обязательно");

        WebElement addressfirst = driver.findElement(id("id_answer_short_text_103814"));
        WebElement addressfirstParrent = addressfirst.findElement(By.xpath("../../.."));
        String addressfirstError = addressfirstParrent.findElement(By.cssSelector("div")).getText();
        Assert.assertEquals(addressfirstError,"Это поле обязательно");

        WebElement checkfirst = driver.findElement(By.id("id_answer_boolean_90493"));
        WebElement checkfirstParrent = checkfirst.findElement(By.xpath("../../.."));
        String checkfirstError = checkfirstParrent.findElement(By.cssSelector("[class=\"survey-validation__message survey-validation__message_from_client survey-validation__message_type_required survey-validation__message_visibility_visible\"]")).getText();
        Assert.assertEquals(checkfirstError,"Это поле обязательно");








    }



    }


