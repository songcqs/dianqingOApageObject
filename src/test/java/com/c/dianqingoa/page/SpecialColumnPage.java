package com.c.dianqingoa.page;

import org.openqa.selenium.WebElement;

import com.a.dianqingoa.base.DriverBase;
import com.b.dianqingoa.util.ByMethod;

public class SpecialColumnPage extends BasePage {

	public SpecialColumnPage(DriverBase driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/*
	 * 获取“专栏”页面“零基础实现微信电商小程序开发”文本链接元素
	 */
	public WebElement getProgramDevelopmentImgElement() {
		return findElement(ByMethod.getLocator("programDevelopment"));
	}
}
