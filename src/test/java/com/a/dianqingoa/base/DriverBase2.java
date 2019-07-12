package com.a.dianqingoa.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

/**
 * Setup1：Base内的封装
 * 
 * PageObject第二部： 封装driver
 * 
 * 作用：产生driver对象
 * 
 * 基于selenium进行二次开发的Web自动化测试常用方法封装的类, 包括浏览器操作, 元素操作, cookie操作,
 * 特殊情况弹框的处理以及优化测试的等待处理等方法
 * 
 * 浏览器的基类
 */
public class DriverBase2 {
	private static WebDriver driver = null;
	@SuppressWarnings("unused")
	private WebDriverWait wait = null;
	private long timeOutInSeconds = 10;
	private Select select = null;
	private Alert alert = null;

	// 有参构造 -- 创建对象时实例化driver
	public DriverBase2(String browser) {
		// 初始化浏览器选择类
		SelectDriver selectDriver = new SelectDriver();
		// 将SelectDriver中的driver对象赋值给"private WebDriver driver"中的driver 这样driver对象就有值了！！
		this.driver = selectDriver.selectBrowserDriver(browser);
	}

	// 构造方法
	public DriverBase2(long timeOutInSeconds) {
		this.timeOutInSeconds = timeOutInSeconds;
	}

	public DriverBase2(DriverBase driverBase) {
	}

	/*
	 * 获取driver
	 */
	public WebDriver getDriver() {
		return driver;
	}

	/*
	 * 设置drive
	 */
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	/*
	 * 休眠 -- 强制等待
	 */
	public void sleep(int num) {
		try {
			Thread.sleep(num);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 封装Element方法 -- 定位Element/查找元素
	 */
	public WebElement findElement(By locator) {
		WebElement webElement = driver.findElement(locator);
		return webElement;
	}

	/*
	 * 封装/定位一组elements的方法
	 */
	public List<WebElement> findElements(By by) {
		List<WebElement> listWebElement = driver.findElements(by);
		return listWebElement;
	}

	/*
	 * 通过父节点定位一组elements
	 */
	public List<WebElement> findElements(WebElement element, By by) {
		return element.findElements(by);
	}

	/*
	 * 层级定位,通过父节点定位到子节点 需要传入父节点和子节点的By
	 */
	public WebElement getChildrenElement(By parentBy, By childrenBy) {
		WebElement ele = this.findElement(parentBy);
		return ele.findElement(childrenBy);
	}

	/*
	 * 层级定位传入element，以及子的by
	 */
	public WebElement getChildrenElement(WebElement element, By by) {
		return element.findElement(by);
	}

	/*
	 * get封装 -- 跳转到目标URL网页
	 */
	public void get(String url) {
		driver.get(url);
	}

	/*
	 * navigate封装 -- 跳转到目标URL网页,页面可以back返回
	 */
	public void navigateTo(String url) {
		driver.navigate().to(url);
	}

	/**
	 * 指定浏览器打开URL + 隱式等待
	 * 
	 * @param url
	 * @param browser
	 */
	public void openBrowser(String url, String browser) {
//		setDriver(initBrowser(browser));
		SelectDriver selectDriver = new SelectDriver();
		setDriver(selectDriver.selectBrowserDriver(browser));
		driver.manage().timeouts().implicitlyWait(timeOutInSeconds, TimeUnit.SECONDS);
//		driver.get(url);
		driver.navigate().to(url);
	}

	/**
	 * 关闭当前浏览器
	 */
	public void closeCurrentBrowser() {
		System.out.println("Stop Driver! ------------ 关闭当前浏览器驱动！");
		driver.close();
	}

	/**
	 * 关闭所有selenium驱动打开的浏览器
	 */
	public void closeAllBrowser() {
		System.out.println("Stop All Driver! ------------ 关闭所有浏览器驱动！");
		driver.quit();
	}

	/**
	 * 最大化浏览器/屏幕最大化
	 */
	public void maxBrowser() {
		driver.manage().window().maximize();
	}

	/**
	 * 自定义设置浏览器尺寸
	 * 
	 * @param width
	 * @param heigth
	 */
	public void setBrowserSize(int width, int heigth) {
		driver.manage().window().setSize(new Dimension(width, heigth));
	}

	/**
	 * 获取当前浏览器页面的URL的字符串
	 * 
	 * @return 85
	 */
	public String getURL() {
		String currentURL = driver.getCurrentUrl();
		return currentURL;
	}

	/**
	 * 获取当前浏览器页面的标题 title
	 * 
	 * @return 93
	 */
	public String getTitle() {
		String currentTitle = driver.getTitle();
		return currentTitle;
	}

	/**
	 * 返回or退回or回退or返回上一页 -- 在浏览器的历史中向后到上一个页面, 即点击浏览器返回
	 */
	public void returnToPreviousPage() {
		driver.navigate().back();
	}

	/**
	 * 前进or下一页 -- 在浏览器的历史中向前到下一个页面, 如果我们在最新的页面上看, 什么也不做, 即点击浏览器下一页
	 */
	public void forwardToNextPage() {
		driver.navigate().forward();
	}

	/**
	 * 刷新页面
	 */
	public void refreshPage() {
		driver.navigate().refresh();
	}

	/*
	 * 获取当前窗口
	 */
	public String getWindowHandle() {
		String currentWindow = driver.getWindowHandle();
		return currentWindow;
	}

	/*
	 * 获取当前系统窗口list
	 */
	public List<String> getWindowsHandles() {
		// Set里面不允许有重复的元素,检索元素效率低下，删除和插入效率高，插入和删除不会引起元素位置改变；
		Set<String> winHandels = driver.getWindowHandles();
		List<String> handles = new ArrayList<String>(winHandels);
		return handles;
	}

	/*
	 * 切换windows窗口
	 */
	public void switchWindows(String name) {
		driver.switchTo().window(name);
	}

	/**
	 * WebDriver切换到当前页面
	 */
	public void switchToCurrentPage() {
		String handle = driver.getWindowHandle();
		for (String tempHandle : driver.getWindowHandles()) {
			if (tempHandle.equals(handle)) {
				driver.close();
			} else {
				driver.switchTo().window(tempHandle);
			}
		}
	}

	/*
	 * 根据title切换新窗口
	 */
	public boolean switchToWindow_Title(String windowTitle) {
		boolean flag = false;
		try {
			String currentHandle = getWindowHandle();
			List<String> handles = getWindowsHandles();
			for (String s : handles) {
				if (s.equals(currentHandle))
					continue;
				else {
					switchWindows(s);
					if (driver.getTitle().contains(windowTitle)) {
						flag = true;
						System.out.println("切换windows成功: " + windowTitle);
						break;
					} else
						continue;
				}
			}
		} catch (NoSuchWindowException e) {
			System.out.println("Window: " + windowTitle + " 没找到!!!" + e.fillInStackTrace());
			flag = false;
		}
		return flag;
	}

	/**
	 * 输入文本字符串
	 * 
	 * @param locator
	 * @param text
	 */
	public void inputText(By locator, String text) {
		driver.findElement(locator).sendKeys(text);
	}

	/*
	 * 封装输入方法
	 * 
	 */
	public void sendKeys(WebElement element, String value) {
		if (element != null) {
			element.sendKeys(value);
		} else {
			System.out.println(element + "元素没有定位到,输入失败" + value);
		}
	}

	/*
	 * 封装click（点击）方法 需要传入一个WebElement类型的元素
	 */
	public void clickElement(WebElement element) {
		if (element != null) {
			element.click();
		} else {
			System.out.println("元素未定位到,定位失败");
		}
	}

	/**
	 * 点击元素
	 * 
	 * @param locator 定位器
	 */
	public void clickElement(By locator) {
		driver.findElement(locator).click();
	}

	/*
	 * 判断元素是否显示方法
	 */
	public boolean isDisplayed(WebElement element) {
		return element.isDisplayed();
	}

	/*
	 * 判断当前页面中是否存在某个期望查找的元素
	 */
	public boolean isExsit(By locator) {
		boolean bool = false;
		try {
			WebElement element = driver.findElement(locator);
			bool = null != element;
		} catch (NoSuchElementException e) {
			System.out.println("Element:" + locator.toString() + " is not exsit!");
		}
		return bool;
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
	 * 获取文本信息
	 * 
	 */
	public String getText(WebElement element) {
		return element.getText();
	}

	/**
	 * 获取该元素的可见（没有被CSS隐藏）内文, 包括子元素, 没有任何前导或尾随空白
	 * 
	 * @param locator
	 * @return 此元素的内部文本
	 */
	public String getElementText(By locator) {
		String text = driver.findElement(locator).getText();
		return text;
	}

	/**
	 * 下拉选择框根据选项文本值选择, 即当text="Bar", 那么这一项将会被选择: &lt;option
	 * value="foo"&gt;Bar&lt;/option&gt;
	 * 
	 * @param locator
	 * @param text
	 * @see org.openqa.selenium.support.ui.Select.selectByVisibleText(String text)
	 */
	public void selectByText(By locator, String text) {
		select = new Select(driver.findElement(locator));
		select.selectByVisibleText(text);
	}

	/**
	 * 下拉选择框根据索引值选择, 这是通过检查一个元素的“index”属性来完成的, 而不仅仅是通过计数
	 * 
	 * @param locator
	 * @param index
	 * @see org.openqa.selenium.support.ui.Select.selectByIndex(int index)
	 */
	public void selectByIndex(By locator, int index) {
		select = new Select(driver.findElement(locator));
		select.selectByIndex(index);
	}

	/**
	 * 下列选择框根据元素属性值(value)选择, 即value = "foo" , 那么这一项将会被选择: &lt;option
	 * value="foo"&gt;Bar&lt;/option&gt;
	 * 
	 * @param locator
	 * @param value
	 * @see org.openqa.selenium.support.ui.Select.selectByValue(String value)
	 */
	public void selectByValue(By locator, String value) {
		select = new Select(driver.findElement(locator));
		select.selectByValue(value);
	}

	/**
	 * 下拉选择框全选操作
	 * 
	 */
	public void selectAll(By locator) {
		select = new Select(driver.findElement(locator));
		select.getOptions();
	}

	/**
	 * 清空文本输入框
	 * 
	 * @param locator
	 * @see org.openqa.selenium.WebElement.clear()
	 */
	public void clearText(By locator) {
		driver.findElement(locator).clear();
	}

	/**
	 * 提交表单
	 * 
	 * @param locator
	 * @see org.openqa.selenium.WebElement.submit()
	 */
	public void submitForm(By locator) {
		driver.findElement(locator).submit();
	}

	/**
	 * 上传文件
	 * 
	 * @param locator
	 * @param filePath
	 */
	public void uploadFile(By locator, String filePath) {
		// 元素属性需为"input"
		driver.findElement(locator).sendKeys(filePath);
	}

	/*
	 * 上传文件(单个文件)，需要点击弹出上传照片的窗口才行
	 * 
	 * 通过 AutoIT 实现
	 * 
	 * @parambrower 使用的浏览器名称
	 * 
	 * @paramfile 需要上传的文件及文件名
	 */
//	public void handleUpload(String browser, File file) {
//		String filePath = file.getAbsolutePath();
	public void handleUpload(String browser, String filePath, String executeFile) {
//		String executeFile = "E:\\Javaworkspace\\mail163Send\\source\\uplod.exe"; // 定义了upload.exe文件的路径
		String cmd = "\"" + executeFile + "\"" + " " + "\"" + browser + "\"" + " " + "\"" + filePath + "\"";
		try {
			/*
			 * Runtime.getRuntime()返回当前应用程序的Runtime对象，该对象的exec()
			 * 方法指示Java虚拟机创建一个子进程执行指定的可执行程序, 并返回与该子进程对应的Process对象实例
			 */
			Process p = Runtime.getRuntime().exec(cmd);
			/*
			 * 方法将导致当前的线程等待，如果必要的话，直到由该Process对象表示的进程已经终止。 此方法将立即返回，如果子进程已经终止。 如果子进程尚未终止，
			 * 则调用线程将被阻塞，直到子进程退出。
			 */
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 上传文件(多个文件)，需要点击弹出上传照片的窗口才行
	 * 
	 * 通过 AutoIT 实现
	 * 
	 * @parambrower 使用的浏览器名称
	 * 
	 * @paramfile 需要上传的文件及文件名
	 */
	public void handleUploads(By locator, String browser, String[] filePaths, String executeFile) {
		for (String filePath : filePaths) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			clickElement(locator);// 点击上传按钮
			String cmd = "\"" + executeFile + "\"" + " " + "\"" + browser + "\"" + " " + "\"" + filePath + "\"";
			try {
				Process p = Runtime.getRuntime().exec(cmd);
				p.waitFor();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * 切换frame
	 * 
	 * @param ele
	 */
	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
	}

	/**
	 * 切换frame
	 * 
	 * @param locator
	 * @return 这个驱动程序切换到给定的frame
	 * @see org.openqa.selenium.WebDriver.TargetLocator.frame(WebElement
	 *      frameElement)
	 */
	public void switchToFrame(By locator) {
		driver.switchTo().frame(driver.findElement(locator));
	}

	/**
	 * 切换回父frame
	 * 
	 * @return 这个驱动程序聚焦在顶部窗口/第一个frame上
	 * @see org.openqa.selenium.WebDriver.TargetLocator.defaultContent()
	 */
	public void switchToParentFrame() {
		driver.switchTo().defaultContent();
	}

	/*
	 * 模态框切换
	 */
	public void switchToMode() {
		driver.switchTo().activeElement();
	}

	/*
	 * 切换alert窗口
	 */
	public void switchAlert() {
		driver.switchTo().alert();
	}

	/**
	 * 关闭或取消弹出对话框
	 */
	public void dismissAlert() {
		alert = driver.switchTo().alert();
		alert.dismiss();
	}

	/**
	 * 弹出对话框点击确定
	 */
	public void acceptAlert() {
		alert = driver.switchTo().alert();
		alert.accept();
	}

	/**
	 * 获取弹出对话框内容
	 * 
	 * @return259
	 */
	public String getAlertText() {
		alert = driver.switchTo().alert();
		return alert.getText();
	}

	/**
	 * 弹出对话框输入文本字符串
	 * 
	 * @param text
	 */
	public void inputTextToAlert(String text) {
		alert = driver.switchTo().alert();
		alert.sendKeys(text);
	}

	/**
	 * 根据cookie名称删除cookie
	 * 
	 * @param name cookie的name值
	 * @see org.openqa.selenium.WebDriver.Options.deleteCookieNamed(String name)
	 */
	public void deleteCookie(String name) {
		driver.manage().deleteCookieNamed(name);
	}

	/**
	 * 删除当前域的所有Cookie
	 * 
	 * @see org.openqa.selenium.WebDriver.Options.deleteAllCookies()
	 */
	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

	/**
	 * 根据名称获取指定cookie
	 * 
	 * @param name cookie名称
	 * @return Map&lt;String, String>, 如果没有cookie则返回空, 返回的Map的key值如下:
	 *         <ul>
	 *         <li><tt>name</tt> <tt>cookie名称</tt>
	 *         <li><tt>value</tt> <tt>cookie值</tt>
	 *         <li><tt>path</tt> <tt>cookie路径</tt>
	 *         <li><tt>domain</tt> <tt>cookie域</tt>
	 *         <li><tt>expiry</tt> <tt>cookie有效期</tt>
	 *         </ul>
	 * @see org.openqa.selenium.WebDriver.Options.getCookieNamed(String name)
	 */
	public Map<String, String> getCookieByName(String name) {
		Cookie cookie = driver.manage().getCookieNamed(name);
		if (cookie != null) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", cookie.getName());
			map.put("value", cookie.getValue());
			map.put("path", cookie.getPath());
			map.put("domain", cookie.getDomain());
			map.put("expiry", cookie.getExpiry().toString());
			return map;
		}
		return null;
	}

	/**
	 * 获取当前域所有的cookies
	 * 
	 * @return Set&lt;Cookie> 当前的cookies集合
	 * @see org.openqa.selenium.WebDriver.Options.getCookies()
	 */
	public Set<Cookie> getAllCookies() {
		return driver.manage().getCookies();
	}

	/*
	 * 设置cookie
	 */
	public void addCookie(Cookie cookie) {
		driver.manage().addCookie(cookie);
	}

	/**
	 * 用给定的name和value创建默认路径的Cookie并添加, 永久有效
	 * 
	 * @param name
	 * @param value
	 * @see org.openqa.selenium.WebDriver.Options.addCookie(Cookie cookie)
	 * @see org.openqa.selenium.Cookie.Cookie(String name, String value)
	 */
	public void addCookie(String name, String value) {
		driver.manage().addCookie(new Cookie(name, value));
	}

	/**
	 * 用给定的name和value创建指定路径的Cookie并添加, 永久有效
	 * 
	 * @param name  cookie名称
	 * @param value cookie值
	 * @param path  cookie路径
	 */
	public void addCookie(String name, String value, String path) {
		driver.manage().addCookie(new Cookie(name, value, path));
	}

	/**
	 * "显式等待" 指定时间 内等待直到页面包含文本字符串
	 * 
	 * @param text    期望出现的文本
	 * @param seconds 超时时间
	 * @return Boolean 检查给定文本是否存在于指定元素中, 超时则捕获抛出异常TimeoutException并返回false
	 * @see org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement(WebElement
	 *      element, String text)
	 */
	public Boolean waitUntilPageContainText(String text, long seconds) {
		try {
			return new WebDriverWait(driver, seconds)
					.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.tagName("body")), text));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * "显式等待" 默认时间 等待直到页面包含文本字符串
	 * 
	 * @param text 期望出现的文本
	 * @return Boolean 检查给定文本是否存在于指定元素中, 超时则捕获抛出异常TimeoutException并返回false
	 * @see org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement(WebElement
	 *      element, String text)
	 */
	public Boolean waitUntilPageContainText(String text) {
		try {
			return new WebDriverWait(driver, timeOutInSeconds)
					.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.tagName("body")), text));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

	/**
	 * "显式等待" 指定时间内等待直到元素存在于页面的DOM上并可见, 可见性意味着该元素不仅被显示, 而且具有大于0的高度和宽度
	 * 
	 * @param locator 元素定位器
	 * @param seconds 超时时间
	 * @return Boolean 检查给定元素的定位器是否出现, 超时则捕获抛出异常TimeoutException并返回false
	 * @see org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(By
	 *   locator)
	 */
	public Boolean waitUntilElementVisible(By locator, int seconds) {
		try {
			new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * "显式等待" 默认时间内等待直到元素存在于页面的DOM上并可见, 可见性意味着该元素不仅被显示, 而且具有大于0的高度和宽度
	 * 
	 * @param locator 元素定位器
	 * @return Boolean 检查给定元素的定位器是否出现, 超时则捕获抛出异常TimeoutException并返回false
	 * @see org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(By
	 *   locator)
	 */
	public Boolean waitUntilElementVisible(By locator) {
		try {
			new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * actionMoveElement 悬停 到更多按钮实现
	 */
	public void roverAction(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	/*
	 * actionMoveElement 悬停 到更多按钮实现
	 */
	public void roverElement(By locator) {
		WebElement linkElement = driver.findElement(locator);
		Actions actions = new Actions(driver);
		actions.moveToElement(linkElement).perform();
	}

	/*
	 * 将滚动条滚到适合的位置(垂直滚动)
	 * 
	 * @param height
	 */
	public void setScroll(int width, int height) {
		try {
//			String setscroll = "document.documentElement.scrollTop=" + height;
			String setscroll = "window.scrollBy(" + width + "," + height + ")";
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript(setscroll);
		} catch (Exception e) {
			System.out.println("Fail to set the scroll.");
		}
	}

	/*
	 * 滑动滑块
	 */
	public void slid(By locator) throws InterruptedException {
//		Actions action = new Actions(driver);
//		action.clickAndHold(driver.findElement(locator));
//		Thread.sleep(2000);
//		action.moveByOffset(258, 0).perform();
//		Thread.sleep(2000);
//		action.release();

//		Actions action = new Actions(driver);
//		long d1 = Math.round(100 * 0.8);
//		long d2 = Math.round(200 * 0.1);
//		long d3 = Math.round(1000 * 0.1);
//		action.moveByOffset((int) d1, 1);
//		Thread.sleep(new Random().nextInt(100) + 150);
//		action.moveByOffset((int) d2, 1);
//		Thread.sleep(new Random().nextInt(100) + 150);
//		action.moveByOffset((int) d3, 1);
//		action.moveByOffset(58, 1);
//		Thread.sleep(2000);
//		action.release(driver.findElement(locator));
//
//		Action actions = action.build();
//		actions.perform();
	}

	// ========================================截图方式(一)========================================//

	/*
	 * 自动截图
	 */
//	public void takeScreenShot() {
	public void takeScreenShot(String methodName) {
		// 获取时间，并且格式化，用来作为图片的名称
		SimpleDateFormat sf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		String dateStr = sf.format(date);
		// 获取当前运行的类名称和时间的组合一起命名图片
		String path = "screenshot2/" + this.getClass().getSimpleName() + "_" + dateStr + ".png";
		// 因为我们截图是需要用到driver的，所以这里需要获取driver，这个driver是获取的当前对象的driver
		takeScreenShot((TakesScreenshot) this.driver, path);
	}

	/*
	 * 传入参数截图
	 */
	public void takeScreenShot(TakesScreenshot drivername, String path) {
		String currentPath = System.getProperty("user.dir"); // get current work 当前工程路径
		File scrFile = drivername.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(currentPath + "\\" + path));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("截图成功");
		}
	}

	// ========================================截图方式(二)========================================//

	/**
	 * 自动截图
	 */
//	public static void takeScreenShot2() {
	public static void takeScreenShot2(ITestResult tr) {
//	public static void takeScreenShot2(ITestResult tr, String string) {
		// 获取时间，并且格式化，用来作为图片的名称
		SimpleDateFormat sf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		String dateStr = sf.format(date);
		// 获取当前运行的类名称和时间的组合一起命名图片
		String path = "screenshot2/" + tr.getClass().getSimpleName() + "_"
									 + tr.getMethod().getMethodName() + "_"
									 + dateStr + ".png";
//		String path = "screenshot2/" + tr.getClass().getSimpleName() + "_" 
//									 + string + "_" + dateStr + ".png";
		// 因为我们截图是需要用到driver的，所以这里需要获取driver，这个driver是获取的当前对象的driver
		takeScreenShot2((TakesScreenshot) driver, path);
	}

	/**
	 * 传入参数截图
	 */
	public static void takeScreenShot2(TakesScreenshot drivername, String path) {
		String currentPath = System.getProperty("user.dir"); // get current work 当前工程路径
		/*
		 * FileUtils 文件操作工具类,它支持很多文件操作，如 文件写入； 读取文件； 创建目录； 拷贝文件和目录； 删除文件和目录； 从URL转换；
		 * 基于统配和过滤查看文件和目录； 比较文件内容； 文件的更新时间；检查校验码。
		 */
		File scrFile = drivername.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(currentPath + "\\" + path));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("截图成功");
		}
	}

	// ========================================截图方式(三)========================================//

	private static Logger logger = Logger.getLogger(DriverBase.class);

	public static void takeScreenShot3(ITestResult tr) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String mDateTime = formatter.format(new Date());
//		String fileName = mDateTime + "_" + tr.getName();			// 图片名，时间加用例名(loginTest.png)
//		String fileName = mDateTime + "_" + tr.getTestName();		// 错(null.png)
//		String fileName = mDateTime + "_" + tr.getInstanceName();	// 对(com.test.demo.TestLogin.png)
//		String fileName = mDateTime + "_" + tr.getMethod().getMethodName();// 对(loginTest.png)
		/*
		 * String fileName = mDateTime + "_" + tr.getTestClass().getName() + "_" +
		 * tr.getMethod().getMethodName(); // 对(com.test.demo.TestLogin_loginTest.png)
		 */
		String fileName = mDateTime + "_" + tr.getClass().getSimpleName() + "_" + 
				tr.getMethod().getMethodName(); // 也对(TestResult_loginTest.png)
		String filePath = "";// 保存位置
		
		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			filePath = "screenshot2/" + fileName + ".png";
			File destFile = new File(filePath);
			FileUtils.copyFile(screenshot, destFile);
			logger.info("截图成功，保存在：" + "[" + filePath + "]");

			System.out.println("截图成功，保存在：" + "[" + filePath + "]");

		} catch (Exception e) {
			filePath = "截图失败" + e.getMessage();
			logger.error(filePath);

			System.out.println("截图失败" + e.getMessage());
		}

		if (!"".equals(filePath)) {
			Reporter.setCurrentTestResult(tr);
			Reporter.log(filePath);
			// 把截图写入到Html报告中方便查看
			Reporter.log("<imgsrc=\"../../" + filePath + "\"/>");
		}
	}
	
	// ========================================截图方式(四)========================================//

//	private final static String SCREEN_SHOT_PATH = "test-output/screenshot";
	private final static String SCREEN_SHOT_PATH = "screenshot2";
	private static String SCREEN_SHOT_NAME = null;

//	public static void takeScreenShot4() throws IOException {
	public static void takeScreenShot4(ITestResult tr) throws IOException {
		File screenShotDir = new File(SCREEN_SHOT_PATH);
		if (!screenShotDir.exists()) {
			screenShotDir.mkdirs();
		}

		SCREEN_SHOT_NAME = String.valueOf(new Date().getTime())
								+ tr.getMethod().getMethodName() + ".png";
//		FileUtils.copyFile(TestBase.getWebDriver().getScreenshotAs(OutputType.FILE),
		FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),
					new File(SCREEN_SHOT_PATH + "/" + SCREEN_SHOT_NAME));
	}

	public static String getScreenShotPath() {
		return SCREEN_SHOT_PATH;
	}

	public static String getScreenShotName() {
		return SCREEN_SHOT_NAME;
	}
	
	/**
	 * 初始化浏览器
	 * 
	 * @param browser 参数值为ie, ff, chrome
	 * @return WebDriver
	 */
/*	
	 private WebDriver initBrowser(String browser) { 
		 switch (browser) {
		case "ie":
			System.setProperty("webdriver.ie.driver", ".\\source\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
		case "ff":
		case "firefox":
		case "Firefox":
		case "FireFox":
			// FireFox版本小于48
//	        System.setProperty("webdriver.firefox.marionette", ".\\Tools\\geckodriver.exe");
			// FireFox版本大于48，默认安装时可以试试，应该可以
//	        System.setProperty("webdriver.gecko.driver", ".\\Tools\\geckodriver.exe");
			// 自定义安装要这么写，使用上面的会报错找不到Firefox浏览器
			System.setProperty("webdriver.firefox.bin", "E:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
			driver = new FirefoxDriver();
			break;
		case "chrome":
		case "Chrome":
//				System.setProperty("webdriver.chrome.driver", ".\\Tools\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver",
					"E:/Program Files/Google/Chrome/Application/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		default:
			try {
				throw new Exception("浏览器错误!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return driver;
	}
*/

}



