package com.d.dianqingoa.handles;

import com.a.dianqingoa.base.DriverBase;
import com.c.dianqingoa.page.HomePage;

public class HomePageHandle {

	public DriverBase driver;
	public HomePage homePage;

	public HomePageHandle(DriverBase driver) {
		super();
		this.driver = driver;
		homePage = new HomePage(driver);
	}

	/**
	 * 点击退出按钮
	 */
	public void clickLogout() {
		homePage.clickElement(homePage.getLogoutElement());
	}

	/**
	 * 点击实战按钮
	 */
	public void clickCoding() {
		homePage.clickElement(homePage.getCodingElement());
	}

	/**
	 * 获取用户名
	 */
	public String getUserName() {
		String username = homePage.getText(homePage.getUserNameElement());
		return username;
	}

	/**
	 * 点击 首页右上角"课程"元素
	 */
	public void clickCourseElement() {
		homePage.clickElement(homePage.getCourseElement());
	}

	/**
	 * 点击 首页右上角"实战课程"按钮元素element
	 */
	public void clickPracticalCourseElement() {
		homePage.clickElement(homePage.getPracticalCourseElement());
	}

	/**
	 * 点击 首页右上角"就业班"元素
	 */
	public void clickEmploymentClassesElement() {
		homePage.clickElement(homePage.getEmploymentClassesElement());
	}

	/**
	 * 点击 首页右上角"专栏"元素
	 */
	public void clickSpecialColumnElement() {
		homePage.clickElement(homePage.getSpecialColumnElement());
	}

	/**
	 * 点击 首页右上角"猿问"元素
	 */
	public void clickApeAskElement() {
		homePage.clickElement(homePage.getApeAskElement());
	}

	/**
	 * 点击 首页右上角"手记"元素
	 */
	public void clickNotesElement() {
		homePage.clickElement(homePage.getNotesElement());
	}
}
