package com.f.dianqingoa.testcase;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.a.dianqingoa.base.DriverBase;
import com.b.dianqingoa.util.Assertion;
import com.b.dianqingoa.util.HandleCookie;
import com.b.dianqingoa.util.ReadProperties;
import com.b.dianqingoa.util.RetryListener;
import com.b.dianqingoa.util.TestngListener;
import com.c.dianqingoa.page.HomePage;
import com.e.dianqingoa.business.HomePageBus;
import com.e.dianqingoa.business.LoginPageBus;

@Listeners({ TestngListener.class, RetryListener.class })
public class SuiteTest001_Login extends CaseBase {
	public DriverBase driver;
	public ReadProperties properties;
	public LoginPageBus loginbus;
	public HomePageBus homePageBus;
	public HandleCookie handleCookie;

	public HomePage homePage;

	// 相当于构造方法
	@BeforeClass
	public void beforeClass() {
		this.driver = initDriverBase("chrome");
		properties = new ReadProperties("loginTest.properties");
		handleCookie = new HandleCookie(driver);
		// 设置系统响应时间为10秒钟
		driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		loginbus = new LoginPageBus(driver);
		homePageBus = new HomePageBus(driver);
		driver.get(properties.getProperties("url"));
		homePage = new HomePage(driver);
		driver.maxBrowser();
	}

//	@Test(priority = 1) // priority 设置测试优先级 priority = 0, 1, 2, 3, 4 ....
	@Test
	@Parameters({ "username", "password" })
//	public void testLogin(@Optional("1357863545@qq.com") String username, @Optional("0108152535") String password) {
	public void testLogin(String username, String password) {

//		driver.clickElement(By.id("switchAccountLogin"));// 选择用户密码登录
//		driver.switchToFrame(By.xpath("//div[@id='loginDiv']/iframe"));// 进入登录元素所在的frame

		loginbus.login(username, password);

		String text = driver.getText(homePage.getUserNameElement());
//		assertEquals(text, "weixin_常春藤_0", "登录失败！！");
		Assertion.verifyEquals(text, "weixin_常春藤_", "验证是否登录成功！！");

		driver.sleep(1000);

		if (homePageBus.AssertLogin(properties.getProperties("user"))) {
			System.out.println("登陆成功" + username);
			// 确定登录成功后,保存我们当前用户的Cookie
			handleCookie.saveCookie();
		}
	}

	@AfterClass
	public void afterClass() {
//		driver.getDriver().close();
		driver.sleep(1000);
		driver.closeCurrentBrowser();
	}

}
