package com.d.dianqingoa.handles;

import com.a.dianqingoa.base.DriverBase;
import com.c.dianqingoa.page.LoginPage;

public class LoginPageHandle {

	public DriverBase driverBase;

	// 由于是操作的登录，所以要实例化LoginPage
	public LoginPage loginPage;

	public LoginPageHandle(DriverBase driverBase) {
		this.driverBase = driverBase;
		loginPage = new LoginPage(driverBase);
	}

	/*
	 * 输入用户名
	 */
	public void sendKeysUserName(String userName) {
		loginPage.sendKeys(loginPage.getUserElement(), userName);
	}

	/*
	 * 输入密码
	 */
	public void sendKeysPassword(String password) {
		loginPage.sendKeys(loginPage.getPasswordElement(), password);
	}

	/*
	 * 点击登录
	 */
	public void clickLoginButton() {
		loginPage.clickElement(loginPage.getLoginButtonElement());
	}

	/*
	 * 点击自动登录
	 */
	public void clickAutoSigin() {
		loginPage.clickElement(loginPage.getAutoLoginElement());
	}

	/*
	 * 判断是否是登录页面 判断标准:只要能够识别登录页面的一个输入框即可，这里使用用户名
	 */
	public boolean assertLoginPage() {
		return loginPage.assertElementIs(loginPage.getUserElement());
	}
}
