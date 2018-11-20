package ecshoppf;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import ecshoputils.Constants;
import ecshoputils.MyUtils;

/**
 * ECShop后台登录页
 * 
 * @author tarena
 * 
 */
public class ECShopBGLoginPage {
	private WebDriver driver;
	private MyUtils utl;
	private String url = Constants.ECSHOP_BASE_URL+"/upload/admin/privilege.php?act=login";

	//用户名文本框
	@FindBy(how = How.NAME, using = "username")
	@CacheLookup
	private WebElement unInput;
	
	//密码文本框
	@FindBy(how = How.NAME, using = "password")
	@CacheLookup
	private WebElement pwInput;
	
	//验证码文本框
	@FindBy(how = How.NAME, using = "captcha")
	@CacheLookup
	private WebElement cpInput;
	
	//“进入管理中心”按钮
	@FindBy(how = How.XPATH, using = "//input[@value='进入管理中心']")
	@CacheLookup
	private WebElement loginButton;	
	
	public ECShopBGLoginPage() {
		super();
	}

	public ECShopBGLoginPage(WebDriver driver) {
		super();
		this.driver = driver;
		//初始化当前网页内所有的页面元素
		PageFactory.initElements(driver, this);
		utl = new MyUtils(driver);
	}
	
	//打开后台登录页
	public void get(){
		driver.get(url);
	}
	
	//使用正确的数据登录，进入后台主页
	public ECShopBGHomePage loginSuccess(
			String username,
			String password,
			String captcha){
		unInput.sendKeys(username);
		pwInput.sendKeys(password);
		cpInput.sendKeys(captcha);
		loginButton.click();
		return new ECShopBGHomePage(driver);
	}
	
	//登录页上出现弹出框，网页未跳转
	public void loginAlert(
			String username,
			String password,
			String captcha){
		unInput.sendKeys(username);
		pwInput.sendKeys(password);
		cpInput.sendKeys(captcha);
		loginButton.click();
	}
	
	//登录时跳转到系统信息页
	public ECShopBGSysInfoPage loginMessage(
			String username,
			String password,
			String captcha){
		unInput.sendKeys(username);
		pwInput.sendKeys(password);
		cpInput.sendKeys(captcha);
		loginButton.click();
		return new ECShopBGSysInfoPage(driver);
	}
	
	//获取密码文本框的内容
	public String getPassword(){
		return pwInput.getAttribute("value");
	}
	
	//判断是否存在用户名文本框
	public boolean isUserNameInputPresent(){
		return utl.isElementPresent(
				  By.name("username"));
	}
	
//	  private boolean isElementPresent(By by) {
//		    try {
//		      driver.findElement(by);
//		      return true;
//		    } catch (NoSuchElementException e) {
//		      return false;
//		    }
//		  }
}



