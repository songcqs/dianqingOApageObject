package com.f.dianqingoa.testcase;

import com.a.dianqingoa.base.DriverBase;
import com.b.dianqingoa.util.HandleCookie;
import com.c.dianqingoa.page.HomePage;

//@Listeners({ TestngListener.class, RetryListener.class })
public class SuiteCookieLogin extends CaseBase {

	public DriverBase driverBase;
	public HandleCookie handleCookie;
	public HomePage homePage;

//	@BeforeClass
	public void cookieLogin() {
		this.driverBase = initDriverBase("chrome");
		// 为了得到我们添加到Set集合中的cookie，所以我们需要得到handlecookie对象
		handleCookie = new HandleCookie(driverBase);
		homePage = new HomePage(driverBase);
		driverBase.maxBrowser();
		// 直接进入到课程页面
//		driverBase.get("http://www.imooc.com/user/newlogin/from_url/");
		driverBase.get("http://www.imooc.com/");
		// 指定用户的cookie，相当于指定了谁登陆
		handleCookie.setCookies();
		// 设定完Cookie后,必须重新加载一下页面,否则会失败
//		driverBase.get("http://www.imooc.com/course/list");
		driverBase.refresh();

//		System.out.println("cookie登录！！！");
//		String text = driverBase.getText(homePage.getUserNameElement());
//		assertEquals(text, "weixin_常春藤_0", "登录失败！！");

	}

//	@Test(dependsOnMethods = { "com.f.dianqingoa.testcase.SuiteLogionTest.testLogin" })
//	@Test
//	public void cookieLogin() {
//		System.out.println("cookie登录！！！");
//		String text = driverBase.getText(homePage.getUserNameElement());
//		assertEquals(text, "weixin_常春藤_", "登录失败！！");
//	}
//
//	@AfterClass
//	public void afterClass() {
//		driverBase.sleep(2000);
//		driverBase.closeCurrentBrowser();
//	}
}
