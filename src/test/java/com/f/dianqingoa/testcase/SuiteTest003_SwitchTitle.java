package com.f.dianqingoa.testcase;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.a.dianqingoa.base.DriverBase;
import com.b.dianqingoa.util.HandleCookie;
import com.b.dianqingoa.util.ReadProperties;
import com.b.dianqingoa.util.RetryListener;
import com.b.dianqingoa.util.TestngListener;
import com.c.dianqingoa.page.ApeAskPage;
import com.c.dianqingoa.page.CoursePage;
import com.c.dianqingoa.page.EmploymentClassesPage;
import com.c.dianqingoa.page.HomePage;
import com.c.dianqingoa.page.NotesPage;
import com.c.dianqingoa.page.PracticalCoursePage;
import com.c.dianqingoa.page.SpecialColumnPage;
import com.d.dianqingoa.handles.CoursePageHandle;
import com.e.dianqingoa.business.CoursePageBus;
import com.e.dianqingoa.business.HomePageBus;
import com.e.dianqingoa.business.LoginPageBus;

@Listeners({ TestngListener.class, RetryListener.class })
public class SuiteTest003_SwitchTitle {

	public SuiteCookieLogin suiteCookieLogin;

	public DriverBase driver;
	public ReadProperties properties;

	public LoginPageBus loginbus;
	public HandleCookie handleCookie;

	public HomePage homePage;
	public HomePageBus homePageBus;

	public CoursePage coursePage;
	public CoursePageHandle coursePageHandle;
	public CoursePageBus coursePageBus;

	public PracticalCoursePage practicalCoursePage;

	public EmploymentClassesPage employmentClassesPage;

	public SpecialColumnPage specialColumnPage;

	public ApeAskPage apeAskPage;

	public NotesPage notesPage;


	@BeforeClass
//	@BeforeTest
	public void beforeClass() {
		suiteCookieLogin = new SuiteCookieLogin();
		suiteCookieLogin.cookieLogin();
		this.driver = suiteCookieLogin.driverBase;
		homePageBus = new HomePageBus(driver);
		homePage = new HomePage(driver);
		coursePage = new CoursePage(driver);
		practicalCoursePage=new PracticalCoursePage(driver);
		employmentClassesPage = new EmploymentClassesPage(driver);
		specialColumnPage = new SpecialColumnPage(driver);
		apeAskPage = new ApeAskPage(driver);
		notesPage = new NotesPage(driver);
	}

	// 从"首页"跳转到"课程"
	@Test
	public void test01_switchCourse() {
		homePageBus.clickCourseElement();
		boolean bool = homePage.isExsit(coursePage.getCourseImgElement());
		assertTrue(bool, "跳转转失败！！！");
		driver.returnToPreviousPage();
	}

	// 从"首页"跳转到"实战课程"
	@Test
	public void test02_switchPracticalCourse() {
		homePageBus.clickPracticalCourse();
		boolean bool = homePage.isExsit(practicalCoursePage.getPracticalCourseImgElement());
		assertTrue(bool, "跳转转失败！！！");
		driver.returnToPreviousPage();
	}

	// 从"首页"跳转到"就业班"
	@Test
	public void test03_switchEmploymentClasses() {
		homePageBus.clickEmploymentClassesElement();
		boolean bool = homePage.isExsit(employmentClassesPage.getEmploymentClassesImgElement());
		assertTrue(bool, "跳转转失败！！！");
		driver.returnToPreviousPage();
	}

	// 从"首页"跳转到"专栏"
	@Test
	public void test04_switchSpecialColumn() {
		homePageBus.clickSpecialColumnElement();
		boolean bool = homePage.isExsit(specialColumnPage.getProgramDevelopmentImgElement());
		assertTrue(bool, "跳转转失败！！！");
		driver.returnToPreviousPage();
	}

	// 从"首页"跳转到"猿问"
	@Test
	public void test05_switchApeAsk() {
		homePageBus.clickApeAskElement();
		boolean bool = homePage.isExsit(apeAskPage.getApeAskImgElement());
		assertTrue(false, "跳转转失败！！！");
		driver.returnToPreviousPage();
	}

	// 从"首页"跳转到"手记"
	@Test
	public void test06_switchNotes() {
		homePageBus.clickNotesElement();
		boolean bool = homePage.isExsit(notesPage.getNotesImgElement());
		assertTrue(false, "跳转转失败！！！");
		driver.returnToPreviousPage();
	}

	@AfterClass
//	@AfterTest
	public void afterClass() {
		driver.sleep(2000);
		driver.closeCurrentBrowser();
	}
}
