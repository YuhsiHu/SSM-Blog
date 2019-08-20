package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class BackMenuFunctions {
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
  public void testBackMenuFunctions() throws Exception {
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
    driver.findElement(By.linkText("设置")).click();
    driver.findElement(By.linkText("菜单")).click();
    driver.findElement(By.name("menuName")).click();
    driver.findElement(By.name("menuName")).clear();
    driver.findElement(By.name("menuName")).sendKeys("新链接");
    driver.findElement(By.name("menuUrl")).click();
    driver.findElement(By.name("menuUrl")).click();
    driver.findElement(By.name("menuUrl")).clear();
    driver.findElement(By.name("menuUrl")).sendKeys("https://www.baidu.com");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='图标'])[1]/following::input[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='顶部菜单'])[2]/following::dd[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='主要菜单'])[2]/following::button[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='顶部菜单'])[3]/following::li[1]")).click();
    driver.findElement(By.linkText("前台")).click();
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
    BackMenuFunctions test=new BackMenuFunctions();
    try {
      test.setUp();
      test.testBackMenuFunctions();
      test.tearDown();
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}
