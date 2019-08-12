package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class BackCommentFunctions {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testBackCommentFunctions() throws Exception {
    driver.get("http://localhost:8080/admin/notice");
    driver.findElement(By.linkText("评论")).click();
    driver.findElement(By.linkText("回复")).click();
    driver.findElement(By.name("commentContent")).click();
    driver.findElement(By.name("commentContent")).clear();
    driver.findElement(By.name("commentContent")).sendKeys("谢谢化腾的鼓励！");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='我的回复'])[1]/following::button[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='回复'])[9]/following::a[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='昵称'])[1]/following::div[2]")).click();
    driver.findElement(By.name("commentAuthorEmail")).clear();
    driver.findElement(By.name("commentAuthorEmail")).sendKeys("zhangsan@qq.com");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='非常有用，感谢！'])[1]/following::button[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='删除'])[9]/following::a[1]")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_1 | ]]
    driver.findElement(By.linkText("回复")).click();
    driver.findElement(By.id("comment")).click();
    driver.findElement(By.id("comment")).clear();
    driver.findElement(By.id("comment")).sendKeys("谢谢");
    driver.findElement(By.id("submit")).click();
    driver.close();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_local | ]]
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='添加链接'])[1]/following::a[1]")).click();
    driver.findElement(By.linkText("评论")).click();
    driver.findElement(By.linkText("MySQL常用命令语句")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_2 | ]]
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='张三'])[1]/following::span[3]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='登出'])[1]/following::li[2]")).click();
    driver.close();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_local | ]]
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
