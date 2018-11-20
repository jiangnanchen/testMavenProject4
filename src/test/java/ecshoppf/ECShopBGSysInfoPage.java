package ecshoppf;

import org.openqa.selenium.WebDriver;

import ecshoputils.Constants;

/**
 * ECShop后台系统信息页
 * 
 * @author tarena
 * 
 */
public class ECShopBGSysInfoPage {
	WebDriver driver;
//	String title1 = "系统信息"; //标题的结尾部分

	public ECShopBGSysInfoPage() {
		super();
	}

	public ECShopBGSysInfoPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	//自动跳转回登录页
	public ECShopBGLoginPage autoDirect(){
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new ECShopBGLoginPage(driver);
	}
	
	//判断网页标题是否正确
	public boolean isTitleCorrect(){
		return driver.getTitle().endsWith(Constants.title);
	}
	
	//判断网页源代码是否包含info
	public boolean isSourceContains(
			String info){
		return driver.getPageSource()
			.contains(info);
	}
}
