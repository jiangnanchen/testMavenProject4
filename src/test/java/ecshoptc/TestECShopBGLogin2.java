package ecshoptc;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import ecshoppf.ECShopBGHeaderPage;
import ecshoppf.ECShopBGHomePage;
import ecshoppf.ECShopBGLoginPage;
import ecshoppf.ECShopBGSysInfoPage;
import ecshoputils.Log;
import ecshoputils.MyUtils;
import ecshoputils.MyUtils1;

import ecshoputils.ReadExcelPOI;
import com.thoughtworks.selenium.webdriven.commands.IsEditable;
import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;

import static org.testng.Assert.*;

public class TestECShopBGLogin2 {
 private WebDriver driver;
 private MyUtils utl;
 //定义alert动作值默认为true,点击确定/接受
 private boolean acceptNextAlert = true;
 
 //定义变量-登陆页面
 ECShopBGLoginPage 1lp;
 //变量-登录后主页
 ECShopBGHomePage hp;
 //变量-登陆后头部页面
 ECShopBGHeaderPage hdp;
 //变量-登录跳转的系统提示信息页
 ECShopBGSysInfoPage sip;
 

 @Test(dataProvider = "dp")
  public void testLogin(
		  String tdid,   		//数据编号
		  String tcid,			//用例编号
		  String un,			//用户名
		  String pw,			//密码
		  String cp,			//验证码
		  String expid			//预期结果编号
		  ) throws InterruptedException {
	//创建一个登录页面对象，进行登陆操作；赋值给lp。
	lp = new ECShopBGLoginPage(driver); 
	//打开ECShop登录的网页
	lp.get();
	
	
	//判断预期结果编号等于几，就可以做相应编号的断言
	switch(Integer.parseInt(expid)){
	case 1://断言登录ECShop登录成功
		hp = lp.loginSuccess(un, pw, cp);
		//断言头部子网页出现
		assertTrue(hp.isHeaderPresent());
		//切换到头部子网页
		hdp = hp.switchHeader();
		//断言“退出”按钮出现
		assertTrue(hdp.isLogoutPresent());
		Thread.sleep(2000);
		//点击退 出
		hdp.logout();
		Log.info("case1 finish!!!-chenjiangnan");
		break;
	case 2:
		//1.断言出现提示框
		
		lp.loginAlert(un, pw, cp);
		assertTrue(utl.isAlertPresent());
		//2.点击“确定”后关闭弹框并获取文本
		String actText2 = utl.closeAlertAndGetItsText();
		
		//3.断言提示包含信息“管理员用户名不能为空” 后面有一个换行符
		assertTrue(actText2.contains("- 管理员用户名不能为空!"));
		//4.点击“确定”后断言提示框消失
		assertFalse(utl.isAlertPresent());
		Log.info("case2 finish!!!-chenjiangnan");
		break;
	case 3:
		sip = lp.loginMessage(un, pw, cp);
		//1.断言跳转到信息页面（网页以“系统信息” 结尾）
		assertTrue(sip.isTitleCorrect());
		//2.断言网页源代码中包含“您输入的账号信息不正确”
		
		assertTrue(sip.isSourceContains("您输入的帐号信息不正确。"));
		//3.等待4秒
		lp = sip.autoDirect();
		//4.断言自动回到后台 登录页(用户名出现)
		assertTrue(lp.isUserNameInputPresent());
		//5.断言密码文本框内容被清除为空
		String pw1 = lp.getPassword();
		assertEquals(pw1,"");
		Log.info("case3 finish!!!-chenjiangnan");
		break;
	case 4:
		//创建一个登录弹出提示框对象，无返回值
		lp.loginAlert(un, pw, cp);
		
		//1.断言出现提示框
		assertTrue(utl.isAlertPresent());
		
		//2.点击“确定”后关闭弹框并获取文本
		String actText4 = utl.closeAlertAndGetItsText();
		
		//4.断言提示包含信息“您没有输入验证码” 
		assertTrue(actText4.contains("- 您没有输入验证码"));
		//5.断言提示包含信息“管理员用户名不能为空” 后面有一个换行符
		assertFalse(actText4.contains("- 管理员用户名不能为空!"));
		//6.断言提示框消失
		assertFalse(utl.isAlertPresent());
		Log.info("case4 finish!!!-chenjiangnan");
		break;
	case 5:
		//创建一个登录弹出提示框对象，无返回值
		lp.loginAlert(un, pw, cp);
		//1.断言出现提示框
		assertTrue(utl.isAlertPresent());
		
		//2.点击“确定”后关闭弹框并获取文本
		String actText5 = utl.closeAlertAndGetItsText();
		
		//4.断言提示包含信息“管理员用户名不能为空” 
		assertTrue(actText5.contains("- 管理员用户名不能为空"));
		//5.断言提示包含信息“您没有输入验证码” 
		assertTrue(actText5.contains("您没有输入验证码"));
		//6.断言提示框消失
		assertFalse(utl.isAlertPresent());
		Log.info("case5 finish!!!-chenjiangnan");
		break;
	case 6:
	  /*1.跳到系统信息页面“您输入的验证码不正确。”
		2.数秒后返回后台登录界面
		3.登录界面的输入为空*/
		sip = lp.loginMessage(un, pw, cp);
		//1.断言跳转到信息页面（网页以“系统信息” 结尾）
		assertTrue(sip.isTitleCorrect());
		
		//2.断言网页源代码中包含“您输入的账号信息不正确”
		assertTrue(sip.isSourceContains("您输入的验证码不正确。"));
		//3.等待4秒
		lp = sip.autoDirect();
		//4.断言自动回到后台 登录页(用户名出现)
		assertTrue(lp.isUserNameInputPresent());
		//5.断言密码文本框内容被清空
		String pw5 = lp.getPassword();
		assertEquals(pw5,"");
		Log.info("case6 finish!!!-chenjiangnan");
		break;
	
	}
  }
/* @BeforeMethod
   public void beforeMethod() {
 	
 	 

 	//调用工具类的启动浏览器方法
 	driver = MyUtils1.openBrowser("chrome");
 	
 	
 	 //工具类的实例化
 	 utl = new MyUtils(driver);

   }*/


@BeforeMethod
@Parameters({"browserType"})
  public void beforeMethod(String bt) {
	if(bt.equalsIgnoreCase("firefox")){
		
		//启动Firefox
	  System.setProperty("webdriver.firefox.bin",
	  "F:\\Program Files\\Fire\\firefox.exe");
	  driver = new FirefoxDriver();
	  Log.info("FireFox browser start 。。。。");
	} else if(bt.equalsIgnoreCase("chrome")){
		//启动Chrome
	  System.setProperty("webdriver.chrome.driver",
	  "E:\\selenium_Driver\\chromedriver2.48.2.exe");
	  driver = new ChromeDriver();
	  Log.info("Chrome browser start 。。。。");
	}
	 //工具类的实例化
	 utl = new MyUtils(driver);
  }

  @AfterMethod
  public void afterMethod() {
	  //关闭浏览器
	  driver.quit();
  }

  @DataProvider
  public Object[][] dp() {
	  return ReadExcelPOI.getTestData("H:\\Selenium", "数据_ECShop_后台登录.xls","后台登录");
    
  }
//  /**
//	 * 判断页面元素是否出现
//	 * @param by
//	 * @return
//	 */
//	public static boolean isElementPresent(By by){
//		  try {
//			  driver.findElement(by);
//			  return true;
//		} catch (NoSuchElementException e) {
//			  e.printStackTrace();
//			  return false;
//		}
//		  
//	 }
//	
//	/**
//	 * 判断提示框是否出现
//	 * @return
//	 */
//	public static boolean isAlertPresent(){
//		  try {
//			  driver.switchTo().alert();
//			  return true;
//		} catch (NoAlertPresentException e) {
////			  e.printStackTrace();
//			  return false;
//		}
//		  
//	 }
//	/**
//	 * 关闭提示框 并获取提示框文本内容    数据
//	 * @return
//	 */
//	private String closeAlertAndGetItsText() {
//	    try {
//	      Alert alert = driver.switchTo().alert();
//	      String alertText = alert.getText();
//	      if (acceptNextAlert) {
//	        alert.accept();
//	      } else {
//	        alert.dismiss();
//	      }
//	      return alertText;
//	    } finally {
//	      acceptNextAlert = true;
//	    }
//	  }

}
