package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class BackLinkFunctions {
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
  public void testBackLinkFunctions() throws Exception {
    driver.get("http://localhost:8080/admin/page");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='添加页面'])[1]/following::a[1]")).click();
    driver.findElement(By.linkText("全部链接")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='添加页面'])[1]/following::a[1]")).click();
    driver.findElement(By.linkText("添加链接")).click();
    driver.findElement(By.name("linkName")).click();
    driver.findElement(By.name("linkName")).clear();
    driver.findElement(By.name("linkName")).sendKeys("百度");
    driver.findElement(By.name("linkUrl")).click();
    driver.findElement(By.name("linkUrl")).clear();
    driver.findElement(By.name("linkUrl")).sendKeys("https://www.baidu.com");
    driver.findElement(By.name("linkOrder")).click();
    driver.findElement(By.name("linkOrder")).clear();
    driver.findElement(By.name("linkOrder")).sendKeys("1");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Order'])[1]/following::button[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='添加页面'])[1]/following::a[1]")).click();
    driver.findElement(By.linkText("全部链接")).click();
    driver.findElement(By.linkText("前台")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_1 | ]]
    driver.findElement(By.linkText("百度")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_2 | ]]
    driver.close();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_1 | ]]
    driver.close();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_local | ]]
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='显示'])[2]/following::a[1]")).click();
    driver.findElement(By.name("linkUrl")).click();
    driver.findElement(By.name("linkUrl")).clear();
    driver.findElement(By.name("linkUrl")).sendKeys("https://www.jd.com");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='隐藏'])[1]/following::button[1]")).click();
    driver.findElement(By.linkText("前台")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_3 | ]]
    driver.findElement(By.linkText("百度")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_4 | ]]
    driver.close();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_3 | ]]
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
