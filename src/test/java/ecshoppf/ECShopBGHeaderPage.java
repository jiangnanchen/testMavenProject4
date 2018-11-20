package ecshoppf;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import ecshoputils.MyUtils;

/**
 * ECShop后台登录后进入的主页内的头部子网页
 * 
 * @author tarena
 * 
 */
public class ECShopBGHeaderPage {
	private WebDriver driver;
	private MyUtils utl;
	
	//“退出”链接
	@FindBy(how = How.LINK_TEXT, using = "退出")
	@CacheLookup
	private WebElement logoutLink;	

	public ECShopBGHeaderPage() {
		super();
	}

	public ECShopBGHeaderPage(WebDriver driver) {
		super();
		this.driver = driver;
		//初始化所有页面元素
		PageFactory.initElements(driver, this);
		utl = new MyUtils(driver);
	}
	
	//退出
	public ECShopBGLoginPage logout(){
		logoutLink.click();
		return new ECShopBGLoginPage(driver);
	}
	
	//判断是否出现“退出”
	public boolean isLogoutPresent(){
		return utl.isElementPresent(
				  By.linkText("退出"));
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



