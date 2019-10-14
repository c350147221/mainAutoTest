package com.itrip.main.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

//@SpringBootTest
public class AddOrder/* extends AbstractTestNGSpringContextTests */ {

    private WebDriver driver;

    @BeforeClass
    public void login() throws Exception {

        System.setProperty("webdriver.chrome.driver", "D://TortoiseSVN-1.9.4.27285-x64-svn-1.9.4/SVNcentOS/mainAutoTest/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.itrip.com/");
        driver.manage().window().maximize();

        // 关闭导航栏
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/a[1]")).click();
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]")).click();

        // 点击登录
        driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/div/div[2]/span[3]/span[1]/a")).click();

        // 切换至邮箱登录
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div[2]/div/span[2]")).click();

        // 输入邮箱
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).sendKeys("eric.chen@aoliday.com");

        // 输入密码
        driver.findElement(By.id("emailPwd")).clear();
        driver.findElement(By.id("emailPwd")).click();
        driver.findElement(By.id("emailPwd")).sendKeys("111111");

        // 点击登录
        driver.findElement(By.id("emailLogin")).click();

        String name = driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/div/div[2]/span[4]/span/a")).getText();
        System.out.println(name);
        if (!"Eric".equals(name)) {

            throw new Exception("登录信息错误");

        }

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/a[1]")).click();
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]")).click();
//        driver.close();

    }

    @Test
    public void joinProductDetail() throws IOException {

        driver.findElement(By.linkText("悉尼外海三小时观鲸之旅(开放式三层甲板游船)")).click();
        // 获取当前句柄
        String currentHandle = driver.getWindowHandle();
        // 获取所有窗口句柄
        Set<String> handles = driver.getWindowHandles();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String date = format.format(new Date());

        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        ImageIO.write(screenshot.getImage(), "PNG",
                new File("D:/ideaIU 2019.1.1/IntelliJ IDEA 2019.1.1/mainAutoTest/" + date + ". png "));

        for (String handle : handles) {
            // 如果当前窗口不是浏览器最初打开的窗口 则切换至新窗口
            if (!handle.equals(currentHandle)) {
                driver.switchTo().window(handle);
            } else {
                // 关闭切换后的窗口
                driver.close();
            }
        }
        // 关闭浏览器
        driver.close();

    }

//    @Test
//    public void dispatcherLogin() throws Exception {
//
//        System.setProperty("webdriver.chrome.driver", "C:/Software/Selenium/ChromeDriver/chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
//        driver.get("https://car.itrip.com/");
//        driver.manage().window().maximize();
//
//        driver.findElement(By.name("userName")).clear();
//        driver.findElement(By.name("userName")).click();
//        driver.findElement(By.name("userName")).sendKeys("alan");
//
//        driver.findElement(By.name("password")).clear();
//        driver.findElement(By.name("password")).click();
//        driver.findElement(By.name("password")).sendKeys("123321");
//
//        driver.findElement(By.xpath("//*[@id=\"app\"]/div/form/button")).click();
//        Thread.sleep(3000);
//
//        String name = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[1]/span")).getText();
//        System.out.println(name);
//        if (!"你好，alan_test".equals(name)) {
//
//            throw new Exception("登录信息错误");
//
//        }
//
//        driver.close();
//
//    }

}