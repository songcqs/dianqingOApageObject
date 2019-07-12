package com.c.dianqingoa.page;

import org.openqa.selenium.WebElement;

import com.a.dianqingoa.base.DriverBase;
import com.b.dianqingoa.util.ByMethod;

public class HomePage extends BasePage {

	public HomePage(DriverBase driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取实战element
	 */
	public WebElement getCodingElement() {
		return nodeElement(ByMethod.getLocator("tophead"), ByMethod.getLocator("coding"));
	}

	/**
	 * 获取已登录用户名信息element
	 */
	public WebElement getUserNameElement() {
		// 悬浮使元素显现
		roverAction(findElement(ByMethod.getLocator("header")));
		// 返回用户名
		return findElement(ByMethod.getLocator("nameInfo"));
	}
	
	/**
	 * 获取安全退出按钮元素element
	 */
	public WebElement getLogoutElement() {
		// 悬浮使元素显现
		roverAction(findElement(ByMethod.getLocator("header")));
		// 返回用户名
		return findElement(ByMethod.getLocator("logout"));
	}

	/**
	 * 获取登录按钮元素element
	 */
	public WebElement getLoginButtonElement() {
		// TODO Auto-generated method stub
		return findElement(ByMethod.getLocator("mainLoginButton"));
	}

	/**
	 * 获取 首页右上角"课程"元素
	 */
	public WebElement getCourseElement() {
		return findElement(ByMethod.getLocator("mainCourse"));
	}

	/**
	 * 获取 首页右上角"实战课程"按钮元素element
	 */
	public WebElement getPracticalCourseElement() {
		return findElement(ByMethod.getLocator("mainPracticalCourse"));
	}

	/**
	 * 获取“实战课程”页面“前端开发”元素
	 */
//	public WebElement getUIdevelopmentElement() {
//		return findElement(ByMethod.getLocator("UIdevelopment"));
//	}

	/**
	 * 获取首页右上角"就业班"元素
	 */
	public WebElement getEmploymentClassesElement() {
		return findElement(ByMethod.getLocator("mainEmploymentClasses"));
	}

	/**
	 * 获取 首页右上角"专栏"元素
	 */
	public WebElement getSpecialColumnElement() {
		return findElement(ByMethod.getLocator("mainSpecialColumn"));
	}

	/**
	 * 获取 首页右上角"猿问"元素
	 */
	public WebElement getApeAskElement() {
		return findElement(ByMethod.getLocator("mainApeAsk"));
	}

	/**
	 * 获取 首页右上角"手记"元素
	 */
	public WebElement getNotesElement() {
		return findElement(ByMethod.getLocator("mainNotes"));
	}
}
