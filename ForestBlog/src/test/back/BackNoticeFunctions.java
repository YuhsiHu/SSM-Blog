package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class BackNoticeFunctions {
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
  public void testBackNoticeFunctions() throws Exception {
    driver.get("http://localhost:8080/");
    driver.findElement(By.linkText("登录")).click();
    driver.findElement(By.id("user_login")).click();
    driver.findElement(By.id("user_login")).clear();
    driver.findElement(By.id("user_login")).sendKeys("admin");
    driver.findElement(By.id("loginForm")).click();
    driver.findElement(By.id("user_pass")).click();
    driver.findElement(By.id("user_pass")).clear();
    driver.findElement(By.id("user_pass")).sendKeys("123456");
    driver.findElement(By.id("submit-btn")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='添加链接'])[1]/following::a[1]")).click();
    driver.findElement(By.linkText("全部公告")).click();
    driver.findElement(By.linkText("这是一个公告测试")).click();
    driver.close();
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

  public static void main(String args[]){
    String url="http://localhost:8080/";
    System.setProperty("webdriver.gecko.driver", "src\\test\\geckodriver.exe");
    BackNoticeFunctions test=new BackNoticeFunctions();
    try {
      test.setUp();
      test.testBackNoticeFunctions();
      test.tearDown();
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}
