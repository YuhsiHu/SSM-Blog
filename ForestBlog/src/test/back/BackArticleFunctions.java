package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class BackArticleFunctions {
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
  public void testBackArticleFunctions() throws Exception {
    driver.get("http://localhost:8080/");
    driver.findElement(By.linkText("登录")).click();
    driver.findElement(By.id("user_login")).click();
    driver.findElement(By.id("user_login")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [doubleClick | id=user_login | ]]
    driver.findElement(By.id("loginForm")).click();
    driver.findElement(By.id("submit-btn")).click();
    driver.findElement(By.linkText("退出")).click();
    driver.findElement(By.id("articleTitle")).click();
    driver.findElement(By.id("articleTitle")).clear();
    driver.findElement(By.id("articleTitle")).sendKeys("文章草稿");
    driver.findElement(By.id("articleContent")).click();
    driver.findElement(By.id("articleContent")).clear();
    driver.findElement(By.id("articleContent")).sendKeys("这是一个文章草稿。");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='删除'])[5]/following::button[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='退出'])[1]/following::a[1]")).click();
    driver.findElement(By.linkText("全部文章")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='退出'])[1]/following::a[1]")).click();
    driver.findElement(By.linkText("查找文章")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='查找方式'])[1]/following::input[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='标题'])[2]/following::dd[1]")).click();
    driver.findElement(By.name("cons")).click();
    driver.findElement(By.name("cons")).clear();
    driver.findElement(By.name("cons")).sendKeys("SQL");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='输入条件'])[1]/following::button[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='退出'])[1]/following::a[1]")).click();
    driver.findElement(By.linkText("写文章")).click();
    driver.findElement(By.id("title")).click();
    driver.findElement(By.id("title")).clear();
    driver.findElement(By.id("title")).sendKeys("新建的文章");
    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | index=0 | ]]
    driver.findElement(By.xpath("//body")).click();
    // ERROR: Caught exception [unknown command [editContent]]
    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | relative=parent | ]]
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='*'])[3]/following::input[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='计算机科学'])[2]/following::dd[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='其他技术'])[2]/following::input[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='缓存服务'])[2]/following::dd[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='设计模式'])[1]/following::i[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='草稿'])[1]/following::button[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='退出'])[1]/following::a[1]")).click();
    driver.findElement(By.linkText("全部分类")).click();
    driver.findElement(By.name("categoryName")).click();
    driver.findElement(By.name("categoryName")).clear();
    driver.findElement(By.name("categoryName")).sendKeys("测试分类");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='*'])[2]/following::input[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='*'])[2]/following::dd[1]")).click();
    driver.findElement(By.name("categoryDescription")).click();
    driver.findElement(By.name("categoryDescription")).clear();
    driver.findElement(By.name("categoryDescription")).sendKeys("新建的测试分类");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='图标样式'])[1]/following::button[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='退出'])[1]/following::a[1]")).click();
    driver.findElement(By.linkText("全部标签")).click();
    driver.findElement(By.name("tagName")).click();
    driver.findElement(By.name("tagName")).clear();
    driver.findElement(By.name("tagName")).sendKeys("测试标签");
    driver.findElement(By.name("tagDescription")).click();
    driver.findElement(By.name("tagDescription")).clear();
    driver.findElement(By.name("tagDescription")).sendKeys("新建的测试标签");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("(//a[contains(text(),'文章')])[2]")).click();
    driver.findElement(By.linkText("全部文章")).click();
    driver.findElement(By.linkText("编辑")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='测试标签'])[1]/following::i[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='*'])[3]/following::input[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='其他技术'])[2]/following::dd[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='草稿'])[1]/following::button[1]")).click();
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
