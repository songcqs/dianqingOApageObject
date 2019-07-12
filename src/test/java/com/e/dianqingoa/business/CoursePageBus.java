package com.e.dianqingoa.business;

import com.a.dianqingoa.base.DriverBase;
import com.d.dianqingoa.handles.CoursePageHandle;

public class CoursePageBus {

	public CoursePageHandle coursePageHandle;

	public CoursePageBus(DriverBase driverBase) {
		super();
		this.coursePageHandle = new CoursePageHandle(driverBase);
	}

}
