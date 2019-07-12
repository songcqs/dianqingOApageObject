package com.b.dianqingoa.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.log4testng.Logger;

import com.a.dianqingoa.base.DriverBase;

/*
 * 有人中提到是去实现ITestListener接口， IestListenerAdapter 已经实现 ITestListener，并且提供了一些有用的方法，
 * 比如分别获取所有成功失败跳过三种测试结果的测试方法的方法，并且 ITestListner 中有很多方法而 TestListenerAdapter 已给出了默认实现。
 * 因此，继承 TestListenerAdapter 后，便只需关注需要修改的方法。
 */
public class TestngListener extends TestListenerAdapter {

	private static Logger logger = Logger.getLogger(TestngListener.class);

	@Override
	// ITestResult是TestNG提供的一个接口 结合@AfterMethod使用类似监听器 可以监听@Test方法的执行状态等信息。
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		logger.info(tr.getName() + " Success!------------测试用例执行成功！");
	}

	@Override
	// 主要是用到这个方法了，当你报错时他会监听到，然后就会执行截图操作，这里的 ITestresult tr是testng里的，获取到的是当前运行对象，
	// 你这样理解，他就是我当前运行的类这个对象
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		System.out.println("ITestResult tr: " + tr);
		takeScreenShot(tr);
		logger.info(tr.getName() + " Failure!------------测试用例执行失败！");
	}

	/**
	 * @param tr         TestNG的监听接口
	 * 
	 * @param methodName 产生异常的类方法名
	 */
	/*
	 * ITestResult是TestNG提供的一个接口 结合@AfterMethod使用类似监听器 可以监听@Test方法的执行状态等信息。
	 * 所以这里我执行截图的时候就获取到了我运行过程中实力对象的driver了，懂了吧。所以当我再回去调用我基类的对象时，那么driver就是同一个了
	 */
	// private void takeScreenShot(ITestResult tr, String methodName) {// 第二个参数表示哪个类产生的异常
	private void takeScreenShot(ITestResult tr) {
		/*
		 * 单实例模式，层次间调用,需要间接使用new，即getInstance方法,getInstance往往是static的
		 * 单实例模式:保证系统中一个类只有一个实例，不能在其它类中实例化
		 * 
		 * 疑问：为啥driver被封装后就不能用截图方式一了？
		 */
		// ======================================截图(一)不可行======================================//
		// 以下不可行(ClassCastException强制类型转换错误,原因=====搞不懂=====)
//		DriverBase b = (DriverBase) tr.getInstance();
//		TestLogin2 b = (TestLogin2) tr.getInstance();
//		driver = b.driver;
		// 调用截图方法
//		b.takeScreenShot();截图方式一

		// 以下不可行(ClassCastException强制类型转换错误,原因=====搞不懂=====,与上边一样)
//		LogManager.getLogger(this.getClass()).info("In failed method");
//		Object currentClass = tr.getInstance();
//		((DriverBase) currentClass).takeScreenShot();// 截图方式一
//		LogManager.getLogger(this.getClass()).info("Out failed method");

		// ======================================截图(二)可行======================================//
//		LogManager.getLogger(this.getClass()).info("In failed method");
//		DriverBase.takeScreenShot2(tr);// 截图方式二
//		LogManager.getLogger(this.getClass()).info("Out failed method");

		// ======================================截图(三)可行======================================//
//		DriverBase.takeScreenShot3(tr);// 截图方式三
//		System.out.println(tr.getMethod().getMethodName() + " failed, the screenshot saved in "
//				+ DriverBase2.getScreenShotPath() + " screenshot name : " 
//				+ DriverBase2.getScreenShotName());

		// ======================================截图(四)可行======================================//
		try {
			DriverBase.takeScreenShot4(tr);// 截图方式四
//			System.out.println(tr.getMethod().getMethodName() + " failed, the screenshot saved in "
//					+ DriverBase.getScreenShotPath() + " screenshot name : " 
			System.out.println(tr.getMethod().getMethodName() + " 运行失败, 截图保存在: "
								+ DriverBase.getScreenShotPath() + " 截图名称 : " 
								+ DriverBase.getScreenShotName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		logger.info(tr.getName() + " Skipped!------------测试用例由于某些原因被跳过！");
	}

	@Override
	public void onTestStart(ITestResult tr) {
		super.onTestStart(tr);
		logger.info(tr.getName() + " Start!------------测试用例开始执行！");
	}

	@Override
	/*
	 * 如果一个数据提供者在方法签名中声名了一个ITestContext类型的参数，TestNG就会将当前的测试上下文设置给它，
	 * 这使得数据提供者能够知道当前测试执行的运行时刻参数。
	 */
	public void onStart(ITestContext testContext) {
		super.onStart(testContext);
	}
/*
	@Override
	public void onFinish(ITestContext testContext) {
		super.onFinish(testContext);
		// 失败后重跑，记录最后一次结果
		Iterator<ITestResult> listOfFailedTests = testContext.getFailedTests().getAllResults().iterator();
		while (listOfFailedTests.hasNext()) {
			ITestResult failedTest = listOfFailedTests.next();
			ITestNGMethod method = failedTest.getMethod();
			if (testContext.getFailedTests().getResults(method).size() > 1) {
				listOfFailedTests.remove();
			} else {
				if (testContext.getPassedTests().getResults(method).size() > 0) {
					listOfFailedTests.remove();
				}
			}
		}
	}
*/
	@Override
	public void onFinish(ITestContext testContext) {
		super.onFinish(testContext);

		// List of test results which we will delete later 稍后要删除的测试结果列表
		ArrayList<ITestResult> testsToBeRemoved = new ArrayList<ITestResult>();
		// collect all id's from passed test 收集通过测试的所有ID
		Set<Integer> passedTestIds = new HashSet<Integer>();
		for (ITestResult passedTest : testContext.getPassedTests().getAllResults()) {
			logger.info("PassedTests 通过用例: = " + passedTest.getName());
			passedTestIds.add(getId(passedTest));
		}

		// Eliminate the repeat methods 消除重复方法
		Set<Integer> skipTestIds = new HashSet<Integer>();
		for (ITestResult skipTest : testContext.getSkippedTests().getAllResults()) {
			logger.info("skipTest 跳过用例: = " + skipTest.getName());
			// id = class + method + dataprovider
			int skipTestId = getId(skipTest);

			if (skipTestIds.contains(skipTestId) || passedTestIds.contains(skipTestId)) {
				testsToBeRemoved.add(skipTest);
			} else {
				skipTestIds.add(skipTestId);
			}
		}

		// Eliminate the repeat failed methods 消除重复失败的方法
		Set<Integer> failedTestIds = new HashSet<Integer>();
		for (ITestResult failedTest : testContext.getFailedTests().getAllResults()) {
			logger.info("failedTest 失败用例: = " + failedTest.getName());
			// id = class + method + dataprovider
			int failedTestId = getId(failedTest);

			/*
			 * if we saw this test as a failed test before we mark as to be deleted or
			 * delete this failed test if there is at least one passed version
			 * 如果我们在标记为删除之前将此测试视为失败的测试，或者如果至少有一个已通过的版本，则删除此失败的测试
			 */
//			if (failedTestIds.contains(failedTestId) || passedTestIds.contains(failedTestId)) {
			if (failedTestIds.contains(failedTestId) || passedTestIds.contains(failedTestId)
					|| skipTestIds.contains(failedTestId)) {
				testsToBeRemoved.add(failedTest);
			} else {
				failedTestIds.add(failedTestId);
			}
		}

		// finally delete all tests that are marked 最后删除所有标记的测试
		for (Iterator<ITestResult> iterator = testContext.getFailedTests().getAllResults().iterator(); 
				iterator.hasNext();) {
			ITestResult testResult = iterator.next();
			if (testsToBeRemoved.contains(testResult)) {
				logger.info("Remove repeat Fail Test 删除重复失败测试: " + testResult.getName());
				iterator.remove();
			}
		}
		/*
		 * 其实我们就是重写了onTestFailure() 和 onTestSkipped() 方法，添加上截图方法，当然上面我们还重写了onFinish()方法，
		 * 目的是最后生成report时，移除重跑的结果，避免report 生成多余数据。
		 */
	}

	private int getId(ITestResult result) {
		int id = result.getTestClass().getName().hashCode();
		id = id + result.getMethod().getMethodName().hashCode();
		id = id + (result.getParameters() != null ? Arrays.hashCode(result.getParameters()) : 0);
		return id;
	}
}
