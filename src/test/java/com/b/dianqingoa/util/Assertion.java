package com.b.dianqingoa.util;

/**
 * 该类的作用是：断言失败但是继续执行
 */
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

public class Assertion {

//	public static StringBuffer verificationErrors = new StringBuffer();
	public static boolean flag = true;
	public static List<Error> errors = new ArrayList<Error>();

	public static void verifyEquals(Object actual, Object expected) {
		try {
			Assert.assertEquals(actual, expected);
		} catch (Error e) {
			errors.add(e);
			flag = false;
		}
	}

	public static void verifyEquals(Object actual, Object expected, String message) {
		try {
			Assert.assertEquals(actual, expected, message);
		} catch (Error e) {
			errors.add(e);
			flag = false;
		}
	}

	public static void verifyTrue(boolean o) {
		try {
			Assert.assertTrue(o);
		} catch (Error e) {
//			verificationErrors.append(e.toString());
			errors.add(e);
			flag = false;
		}
	}

	public static void verifyFalse(boolean o) {
		try {
			Assert.assertFalse(o);
		} catch (Error e) {
//			verificationErrors.append(e.toString());
			errors.add(e);
			flag = false;
		}
	}

//	public static void verifyEquals(Object expected, Object actuals) {
//		try {
//			Assert.assertEquals(expected, actuals);
//		} catch (Error e) {
//			verificationErrors.append(e.toString());
//		}
//	}
//
//	public static void verifyEquals(Object expected, Object actuals, String message) {
//		try {
//			Assert.assertEquals(expected, actuals, message);
//		} catch (Error e) {
//			verificationErrors.append(e.toString());
//		}
//	}

}
