package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class BackAdminFunctions {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    try {
      driver = new FirefoxDriver();
      baseUrl = "https://www.katalon.com/";
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testBackAdminFunctions() throws Exception {
    try {
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
      driver.findElement(By.linkText("用户信息")).click();
      driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='用户信息'])[1]/following::a[1]")).click();
      driver.findElement(By.linkText("用户信息")).click();
      driver.findElement(By.linkText("修改密码")).click();
      driver.findElement(By.id("userPass")).click();
      driver.findElement(By.id("userPass")).clear();
      driver.findElement(By.id("userPass")).sendKeys("12345678");
      driver.findElement(By.id("submit-btn")).click();
      assertEquals("修改成功", closeAlertAndGetItsText());
      driver.findElement(By.linkText("退出")).click();
      driver.findElement(By.id("user_login")).click();
      driver.findElement(By.id("user_login")).click();
      driver.findElement(By.id("user_login")).click();
      driver.findElement(By.id("user_login")).clear();
      driver.findElement(By.id("user_login")).sendKeys("admin");
      driver.findElement(By.id("user_pass")).clear();
      driver.findElement(By.id("user_pass")).sendKeys("12345678");
      driver.findElement(By.id("submit-btn")).click();
      driver.findElement(By.linkText("用户信息")).click();
      driver.findElement(By.linkText("修改密码")).click();
      driver.findElement(By.id("userPass")).click();
      driver.findElement(By.id("userPass")).clear();
      driver.findElement(By.id("userPass")).sendKeys("123456");
      driver.findElement(By.id("submit-btn")).click();
      assertEquals("修改成功", closeAlertAndGetItsText());
      driver.findElement(By.linkText("退出")).click();
      driver.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  @After
  public void tearDown() throws Exception {
    try {
      driver.quit();
      String verificationErrorString = verificationErrors.toString();
      if (!"".equals(verificationErrorString)) {
        fail(verificationErrorString);
      }
    } catch (Exception e) {
      e.printStackTrace();
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
    BackAdminFunctions test=new BackAdminFunctions();
    try {
      test.setUp();
      test.testBackAdminFunctions();
      test.tearDown();
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}
