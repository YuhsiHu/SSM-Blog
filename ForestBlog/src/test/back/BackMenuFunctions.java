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
    driver.get("http://localhost:8080/admin");
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
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_1 | ]]
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='开发利器'])[1]/following::span[2]")).click();
    driver.close();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_local | ]]
    driver.findElement(By.name("menuName")).click();
    driver.findElement(By.name("menuName")).clear();
    driver.findElement(By.name("menuName")).sendKeys("顶部新链接");
    driver.findElement(By.name("menuUrl")).click();
    driver.findElement(By.name("menuUrl")).clear();
    driver.findElement(By.name("menuUrl")).sendKeys("https://www.jd.com");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='图标'])[1]/following::input[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='图标'])[1]/following::dl[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='主要菜单'])[2]/following::button[1]")).click();
    driver.findElement(By.linkText("前台")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_2 | ]]
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='个人博客系统'])[1]/preceding::span[2]")).click();
    driver.close();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_local | ]]
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='顶部菜单'])[3]/following::li[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='https://www.baidu.com'])[1]/following::a[1]")).click();
    driver.findElement(By.name("menuName")).click();
    driver.findElement(By.name("menuName")).clear();
    driver.findElement(By.name("menuName")).sendKeys("主要菜单新链接");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Order'])[1]/following::button[1]")).click();
    driver.findElement(By.linkText("前台")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_3 | ]]
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='开发利器'])[1]/following::span[2]")).click();
    driver.close();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_local | ]]
    acceptNextAlert = true;
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='编辑'])[4]/following::a[1]")).click();
    assertEquals("您确定要删除吗？", closeAlertAndGetItsText());
    driver.findElement(By.linkText("前台")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_4 | ]]
    driver.findElement(By.id("top-header")).click();
    driver.findElement(By.xpath("//nav[@id='top-header']/div")).click();
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
