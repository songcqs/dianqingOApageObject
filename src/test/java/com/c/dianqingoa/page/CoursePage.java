package com.c.dianqingoa.page;

import org.openqa.selenium.WebElement;

import com.a.dianqingoa.base.DriverBase;
import com.b.dianqingoa.util.ByMethod;

public class CoursePage extends BasePage {

	public CoursePage(DriverBase driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取“课程”页面“课程”图片元素
	 */
	public WebElement getCourseImgElement() {
		return findElement(ByMethod.getLocator("courseImg"));
	}
}
