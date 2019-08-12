package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class BackOptionsFunctions {
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
  public void testBackOptionsFunctions() throws Exception {
    driver.get("http://localhost:8080/admin/options");
    driver.findElement(By.linkText("设置")).click();
    driver.findElement(By.linkText("主要选项")).click();
    driver.findElement(By.name("optionSiteTitle")).click();
    driver.findElement(By.name("optionSiteTitle")).clear();
    driver.findElement(By.name("optionSiteTitle")).sendKeys("测试个人博客系统");
    driver.findElement(By.name("optionSiteDescrption")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Github'])[1]/following::button[1]")).click();
    driver.findElement(By.linkText("前台")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_1 | ]]
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='×'])[1]/preceding::div[1]")).click();
    driver.close();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_local | ]]
    driver.findElement(By.name("optionSiteTitle")).click();
    driver.findElement(By.name("optionMetaKeyword")).click();
    driver.findElement(By.name("optionMetaKeyword")).clear();
    driver.findElement(By.name("optionMetaKeyword")).sendKeys("SSM博客,YuhsiHu,分享开发经验");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Github'])[1]/following::button[1]")).click();
    driver.findElement(By.linkText("前台")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_2 | ]]
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='阅读全文'])[10]/following::i[1]")).click();
    driver.close();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_local | ]]
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='基本信息'])[2]/following::li[1]")).click();
    driver.findElement(By.name("optionAboutsiteContent")).click();
    driver.findElement(By.name("optionAboutsiteContent")).click();
    driver.findElement(By.name("optionAboutsiteContent")).clear();
    driver.findElement(By.name("optionAboutsiteContent")).sendKeys("测试个人说明。");
    driver.findElement(By.name("optionAboutsiteWeibo")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Github'])[1]/following::button[1]")).click();
    driver.findElement(By.linkText("前台")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_3 | ]]
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='个人博客系统'])[1]/following::div[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='测试个人说明。'])[1]/following::a[3]")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_4 | ]]
    driver.close();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_3 | ]]
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='测试个人说明。'])[1]/following::a[4]")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_5 | ]]
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
