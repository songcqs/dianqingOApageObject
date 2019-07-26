package com.b.dianqingoa.util;

import java.io.IOException;

import org.openqa.selenium.By;

/**
 * 根据属性文件中的元素自动进行By定位
 */
public class ByMethod {

	/**
	 * 封装by 静态方法 直接调用
	 * 
	 * @throws IOException
	 */
	public static By getLocator(String PropertiesKey) {

		String PropertiesPath = "element.properties";
		// 创建ReadProperties对象
		ReadProperties properties = new ReadProperties(PropertiesPath);

		String value = properties.getProperties(PropertiesKey);
		// 对value进行拆分
		String LocateMethon = value.split("<")[0];
		String LocateEle = value.split("<")[1];

		// System.out.println(LocateMethon+"========="+LocateEle);

		/*
		 * equalsignorecase 和equals的区别 equals方法来自于Object类 equalsIgnoreCase方法来自String类
		 * equals对象参数是Object 用于比较两个对象是否相等 equals在Object类中方法默然比较对象内存地址,所有我们应该重写这个方法
		 * equals在把对象放入HashMap中会被掉用 equalsIgnoreCase是String特有的方法
		 * equalsIgnoreCase方法的参数是String对象 equalsIgnoreCase 方法是比较两个String对象是否相等(并且忽略大小写)
		 */
		if (LocateMethon.equalsIgnoreCase("id")) {

			return By.id(LocateEle);
		} else if (LocateMethon.equalsIgnoreCase("name")) {

			return By.name(LocateEle);
		} else if (LocateMethon.equalsIgnoreCase("tagNmae")) {

			return By.tagName(LocateEle);
		} else if (LocateMethon.equalsIgnoreCase("linkText")) {

			return By.linkText(LocateEle);
		} else if (LocateMethon.equalsIgnoreCase("className")) {

			return By.className(LocateEle);
		} else if (LocateMethon.equalsIgnoreCase("xpath")) {

			return By.xpath(LocateEle);
		} else if (LocateMethon.equalsIgnoreCase("cssSelector")) {

			return By.cssSelector(LocateEle);
		} else {
			/*
			 * 与LinkText方法类似，通过给出的链接文本去定位，这个链接文本只要 '包含'在整个文本中即可。
			 * 如：“去付款吧”，By.partialLinkText(“付款”);
			 */
			return By.partialLinkText(LocateEle);
		}

	}

}
