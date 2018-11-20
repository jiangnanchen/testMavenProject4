package ecshoputils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

	


public class MyUtils {
	private WebDriver driver;
	public boolean acceptNextAlert = true;

	/**
	 * 无参的构造方法
	 */
	public MyUtils() {
		super();
	}
	/**
	 * 有参构造方法
	 */
	public MyUtils(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * 判断页面元素是否出现
	 * @param by
	 * @return
	 */
	public  boolean isElementPresent(By by){
		  try {
			  driver.findElement(by);
			  return true;
		} catch (NoSuchElementException e) {
			  e.printStackTrace();
			  return false;
		}
	  }
	
	/**
	 * 判断提示框是否出现
	 * @return
	 */
	public  boolean isAlertPresent(){
		  try {
			  driver.switchTo().alert();
			  return true;
		} catch (NoAlertPresentException e) {
//			  e.printStackTrace();
			  return false;
		}
		  
	 }
	
	/**
	 * 关闭提示框 并获取提示框文本内容    数据
	 * @return
	 */
	public String closeAlertAndGetItsText() {
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
	
/*	因为怕抛出异常，所以进行封装sleep方法
	时间等待的方法sleep*/
	public void sleep(long ms){
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
