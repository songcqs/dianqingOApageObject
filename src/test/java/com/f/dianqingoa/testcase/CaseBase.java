package com.f.dianqingoa.testcase;

import com.a.dianqingoa.base.DriverBase;

public class CaseBase {
	public DriverBase initDriverBase(String browser) {
		return new DriverBase(browser);
	}
}
