package com.c.dianqingoa.page;

import org.openqa.selenium.WebElement;

import com.a.dianqingoa.base.DriverBase;
import com.b.dianqingoa.util.ByMethod;

public class ApeAskPage extends BasePage {

	public ApeAskPage(DriverBase driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/*
	 * 获取“猿问”页面“猿问”图片元素
	 */
	public WebElement getApeAskImgElement() {
		return findElement(ByMethod.getLocator("apeAskImg"));
	}
}
