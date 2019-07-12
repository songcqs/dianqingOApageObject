package com.c.dianqingoa.page;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.a.dianqingoa.base.DriverBase;

/**
 * Setup1：Base内的封装
 * 
 * PageObject第三步：BasePage的封装
 * 
 */
public class BasePage {

	private DriverBase driver;

	/**
	 * 构造方法
	 */
	public BasePage(DriverBase driver) {
		super();
		this.driver = driver;
	}

	/**
	 * 封装Element
	 */
	public WebElement findElement(By by) {
		WebElement element = driver.findElement(by);
		return element;
	}

	/**
	 * 层级定位，通过父节点定位到子节点 需要传入父节点和子节点的by
	 */
	public WebElement nodeElement(By by, By nodeby) {
		WebElement el = this.findElement(by);
		return el.findElement(nodeby);
	}

	/**
	 * actionMoveElement 悬停 到更多按钮实现
	 */
	public void roverAction(WebElement element) {
		driver.roverAction(element);
	}

	/**
	 * 封装click操作
	 */
	public void clickElement(WebElement element) {
		// 判断element是否为空 null和非null的处理方式
		if (element != null) {
			element.click();
		} else {
			System.out.println("你要点击的元素不存在");
		}
	}

	/**
	 * 封装SendKeys操作
	 */
	public void sendKeys(WebElement element, String context) {
		// 判断element是否为空 null和非null的处理方式
		if (element != null) {
			element.clear();
			element.sendKeys(context);
		} else {
			System.out.println("你要输入的元素不存在 输入内容失败");
		}
	}

	/*
	 * 获取文本信息
	 * 
	 */
	public String getText(WebElement element) {
		return element.getText();
	}

	/*
	 * 判断元素是否显示方法
	 * 
	 */
	public boolean assertElementIs(WebElement element) {
		return element.isDisplayed();
	}

	/*
	 * 关闭当前浏览器
	 */
	public void closeCurrentBrowser() {
		System.out.println("Stop Driver! ------------ 关闭当前浏览器驱动！");
		driver.close();
	}

	/*
	 * 关闭所有selenium驱动打开的浏览器
	 */
	public void closeAllBrowser() {
		System.out.println("Stop All Driver! ------------ 关闭所有浏览器驱动！");
		driver.quit();
	}

	/*
	 * 判断当前页面中是否存在某个期望查找的元素
	 */
	public boolean isExsit(WebElement element) {
		boolean bool = false;
		try {
			bool = null != element;
		} catch (NoSuchElementException e) {
			System.out.println("Element:" + element.toString() + " is not exsit!");
		}
		return bool;
	}

	/*
	 * 传入参数截图
	 */
	public void takeScreenShot(TakesScreenshot drivername, String path) {
		String currentPath = System.getProperty("user.dir"); // get current work
		File scrFile = drivername.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(currentPath + "\\" + path));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("截图成功");
		}
	}

	/*
	 * 自动截图
	 */
//	public void takeScreenShot() {
	public void takeScreenShot(String methodName) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		String dateStr = sf.format(date);
		// 上面几行代码的意思都是获取时间，并且格式化，用来作为图片的名称
		String path = "screenshot/" + this.getClass().getSimpleName() + "_" + dateStr + ".png";
		takeScreenShot((TakesScreenshot) this.driver, path);
		// takeScreenShot((TakesScreenshot) driver, path);
	}

}
