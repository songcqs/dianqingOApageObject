package com.b.dianqingoa.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

/**
 * 重跑监听
 * 
 * @author Administrator
 *
 */
public class RetryListener implements IAnnotationTransformer {
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, 
			Method testMethod) {
		// 对当前运行的用例先通过getRetryAnalyzer()获取重跑的次数的属性
		IRetryAnalyzer retry = annotation.getRetryAnalyzer();
		// 如果属性为空
		if (retry == null) {
			// 那么就设置我们自己设置的重跑次数（失败重跑类.class）
			annotation.setRetryAnalyzer(Retry.class);// 注意这里的类名一定要写对
		}
	}
}
