package com.c.dianqingoa.page;

import org.openqa.selenium.WebElement;

import com.a.dianqingoa.base.DriverBase;
import com.b.dianqingoa.util.ByMethod;

public class EmploymentClassesPage extends BasePage {

	public EmploymentClassesPage(DriverBase driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取“就业班”页面“就业班”图片元素
	 */
	public WebElement getEmploymentClassesImgElement() {
		return findElement(ByMethod.getLocator("employmentClassesImg"));
	}
}
