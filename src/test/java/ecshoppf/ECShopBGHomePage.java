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
 * ECShop后台登录后的主页
 * 
 * @author tarena
 * 
 */
public class ECShopBGHomePage {
	private WebDriver driver;
	private MyUtils utl;
	//头部子网页Frame元素
	@FindBy(how = How.ID, using = "header-frame")
	@CacheLookup
	private WebElement headerFrame;
	

	public ECShopBGHomePage() {
		super();
	}

	public ECShopBGHomePage(WebDriver driver) {
		super();
		this.driver = driver;
		//初始化所有页面元素
		PageFactory.initElements(driver, this);
		utl = new MyUtils(driver);
	}
	
	//切换到头部子网页
	public ECShopBGHeaderPage switchHeader(){
		driver.switchTo().defaultContent();
		driver.switchTo().frame(headerFrame);
		return new ECShopBGHeaderPage(driver);
	}
	
	//判断是否包含头部子网页
	public boolean isHeaderPresent(){
		return utl.isElementPresent(
				  By.id("header-frame"));
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


