package com.a.dianqingoa.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
public class DriverBase {

	public static WebDriver driver;

	/**
	 * 构造方法 创建对象时实例化driver
	 */
	public DriverBase(String browersName) {
		SelectDriver selectDriver = new SelectDriver();
		// 将SelectDriver中的driver对象赋值给"private WebDriver driver"中的driver 这样driver对象就有值了！！
		driver = selectDriver.selectBrowserDriver(browersName);
	}

	/**
	 * 封装Element方法
	 */
	public WebElement findElement(By by) {

		return driver.findElement(by);
	}

	/**
	 * 封装get方法
	 */
	public void get(String url) {

		driver.get(url);
	}

	/**
	 * 返回
	 */
	public void back() {
		driver.navigate().back();
	}

	/**
	 * 点击
	 */
	public void click(WebElement element) {
		element.click();
	}

	/**
	 * 获取当前url
	 */
	public String getUrl() {
		return driver.getCurrentUrl();
	}

	/**
	 * 获取title
	 */
	public String getTitle() {
		return driver.getTitle();
	}

	/**
	 * 关闭浏览器
	 */
	public void close() {
		driver.close();
	}

	/**
	 * 关闭浏览器
	 */
	public void quit() {
		driver.quit();
	}

	/**
	 * 获取driver
	 */
	public WebDriver getDriver() {
		return driver;
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

	/**
	 * 设置cookie
	 */
	public void addCookie(Cookie cookie) {
		driver.manage().addCookie(cookie);
	}

	/**
	 * 获取元素文本值
	 */
	public String getText(WebElement element) {
		// TODO Auto-generated method stub
		return element.getText();
	}

	/**
	 * 强制等待
	 */
	public void sleep(int i) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 关闭浏览器当前页面
	 */
	public void closeCurrentBrowser() {
		// TODO Auto-generated method stub
		driver.quit();
	}

	/**
	 * 最大化浏览器
	 */
	public void maxBrowser() {
		// TODO Auto-generated method stub
		driver.manage().window().maximize();
	}

	/**
	 * 悬浮/悬停
	 */
	public void roverAction(WebElement element) {
		// TODO Auto-generated method stub
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}
	
	/**
	 * 刷新页面
	 */
	public void refresh() {
		driver.navigate().refresh();
	}

	/**
	 * 返回or退回or回退or返回上一页 -- 在浏览器的历史中向后到上一个页面, 即点击浏览器返回
	 */
	public void returnToPreviousPage() {
		driver.navigate().back();
	}

	// ========================================截图方式(一)========================================//
	/**
	 * 自动截图
	 */
//	public void takeScreenShot() {
	public void takeScreenShot(ITestResult tr) {
		// 获取时间，并且格式化，用来作为图片的名称
        SimpleDateFormat sf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
		String dateStr = sf.format(date);
		// 获取当前运行的类名称和时间的组合一起命名图片
		String path = "screenshot/" + this.getClass().getSimpleName() + "_" + dateStr + ".png";
		// 因为我们截图是需要用到driver的，所以这里需要获取driver，这个driver是获取的当前对象的driver
        takeScreenShot((TakesScreenshot) this.getDriver(), path);
    }

	/**
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
		String path = "screenshot/" + tr.getClass().getSimpleName() + "_"
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

	public static void takeScreentShot3(ITestResult tr) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String mDateTime = formatter.format(new Date());
		String fileName = mDateTime + "_" + tr.getName();// 图片名，时间加用例名
		String filePath = "";// 保存位置
		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			filePath = "screenshot/" + fileName + ".png";
			File destFile = new File(filePath);
			FileUtils.copyFile(screenshot, destFile);
			logger.info("截图成功，保存在：" + "[" + filePath + "]");

		} catch (Exception e) {
			filePath = "截图失败" + e.getMessage();
			logger.error(filePath);
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
	private final static String SCREEN_SHOT_PATH = "screenshot";
	private static String SCREEN_SHOT_NAME = null;

//	public static void takeScreenShot4() throws IOException {
	public static void takeScreenShot4(ITestResult tr) throws IOException {
		File screenShotDir = new File(SCREEN_SHOT_PATH);
		if (!screenShotDir.exists()) {
			screenShotDir.mkdirs();
		}

		// 获取时间，并且格式化，用来作为图片的名称
		SimpleDateFormat sf = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss");
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		String dateStr = sf.format(date);

//		SCREEN_SHOT_NAME = String.valueOf(new Date().getTime()) 
		SCREEN_SHOT_NAME = String.valueOf(tr.getClass().getSimpleName() + "_"
										+ tr.getMethod().getMethodName() + "_"
										+ dateStr + ".png");
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
}
