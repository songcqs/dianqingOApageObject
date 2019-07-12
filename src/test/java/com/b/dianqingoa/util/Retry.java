package com.b.dianqingoa.util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

/**
 *
 * 失败重跑
 *
 * @author Administrator
 *
 */
public class Retry implements IRetryAnalyzer {

	public static Logger logger = Logger.getLogger(Retry.class);

	public int retryCount=0;
    private static int maxRetryCount ;

	static {
        //外围文件配置最大运行次数，失败后重跑maxRetryCount+1次
 
		maxRetryCount = 2;//也就是失败后重跑3次
		logger.info("maxRunCount=" + (maxRetryCount));
	}
	
    /**
     * 读取配置文件的重跑次数
     */
   /* static {
        ConfigReader config = ConfigReader.getInstance();
        maxRetryCount = config.getRetryCount();
        logger.info("retrycount=" + maxRetryCount);
        logger.info("sourceCodeDir=" + config.getSourceCodeDir());
        logger.info("sourceCodeEncoding=" + config.getSrouceCodeEncoding());
    }
    */

	/*
	 * private static int maxRetryCount = 3;// #控制失败跑几次
	 * private int retryCount = 0;// 设置当前失败执行次数
	 * 
	 * @Override
	 * public boolean retry(ITestResult iTestResult) {
	 *	if (retryCount < maxRetryCount) {
	 *		retryCount++;
	 *		return true;
	 *	}
	 *	return false;
	 *}
	 * 
	 * ITestResult是TestNG提供的一个接口 结合@AfterMethod使用类似监听器
	 * 可以监听@Test方法的执行状态等信息。
	 */
	@Override
	public boolean retry(ITestResult result) {
		System.out.println(result);
		if (retryCount <= maxRetryCount) {
			String message = "Running retry for '" + result.getName() + "' on class "
					 			+ this.getClass().getName() + " Retrying " + retryCount + " times";
			logger.info(message);
			Reporter.setCurrentTestResult(result);
			Reporter.log("RunCount=" + (retryCount + 1));
			retryCount++;
			return true;
		}
		return false;
	}
	
	
}
