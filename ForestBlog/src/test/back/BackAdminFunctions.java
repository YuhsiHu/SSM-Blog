package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class BackAdminFunctions {
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
  public void testBackAdminFunctions() throws Exception {
    driver.get("http://localhost:8080/admin/comment");
    driver.findElement(By.linkText("用户信息")).click();
    driver.findElement(By.id("userName")).click();
    driver.findElement(By.id("userName")).clear();
    driver.findElement(By.id("userName")).sendKeys("admin2");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='*'])[1]/following::div[3]")).click();
    driver.findElement(By.id("userPass")).clear();
    driver.findElement(By.id("userPass")).sendKeys("12345678");
    driver.findElement(By.name("userNickname")).click();
    driver.findElement(By.name("userNickname")).clear();
    driver.findElement(By.name("userNickname")).sendKeys("YuhsiH");
    driver.findElement(By.id("submit-btn")).click();
    assertEquals("保存成功", closeAlertAndGetItsText());
    driver.findElement(By.linkText("退出")).click();
    driver.findElement(By.id("user_login")).click();
    driver.findElement(By.id("user_login")).clear();
    driver.findElement(By.id("user_login")).sendKeys("admin2");
    driver.findElement(By.id("user_pass")).click();
    driver.findElement(By.id("user_pass")).clear();
    driver.findElement(By.id("user_pass")).sendKeys("12345678");
    driver.findElement(By.id("submit-btn")).click();
    driver.findElement(By.linkText("用户信息")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='上传图片'])[1]/following::div[1]")).click();
    driver.findElement(By.id("userName")).clear();
    driver.findElement(By.id("userName")).sendKeys("admin");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='*'])[1]/following::div[3]")).click();
    driver.findElement(By.id("userPass")).clear();
    driver.findElement(By.id("userPass")).sendKeys("123456");
    driver.findElement(By.name("userNickname")).click();
    driver.findElement(By.name("userNickname")).clear();
    driver.findElement(By.name("userNickname")).sendKeys("YuhsiHu");
    driver.findElement(By.id("submit-btn")).click();
    assertEquals("保存成功", closeAlertAndGetItsText());
    driver.findElement(By.linkText("退出")).click();
    driver.findElement(By.id("user_login")).click();
    driver.findElement(By.id("user_login")).clear();
    driver.findElement(By.id("user_login")).sendKeys("admin");
    driver.findElement(By.id("loginForm")).click();
    driver.findElement(By.xpath("//body")).click();
    driver.findElement(By.id("user_pass")).clear();
    driver.findElement(By.id("user_pass")).sendKeys("123456");
    driver.findElement(By.id("submit-btn")).click();
    driver.findElement(By.linkText("用户信息")).click();
    driver.findElement(By.id("test1")).click();
    driver.findElement(By.name("file")).clear();
    driver.findElement(By.name("file")).sendKeys("C:\\fakepath\\收款码.png");
    driver.findElement(By.id("submit-btn")).click();
    assertEquals("保存成功", closeAlertAndGetItsText());
    driver.findElement(By.linkText("退出")).click();
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
