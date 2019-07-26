package com.b.dianqingoa.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Set;
import java.util.StringTokenizer;

import org.openqa.selenium.Cookie;

import com.a.dianqingoa.base.DriverBase;

public class HandleCookie {

	public DriverBase driverBase;

	// 要读取cookie.properties配置文件,所以需要Proutil工具类
	public ReadProperties properties;

	public HandleCookie(DriverBase driverBase) {
		this.driverBase = driverBase;
		properties = new ReadProperties("cookie.properties");
	}

	/**
	 * 设置cookie方法(单个)
	 * 
	 */
	public void setCookie() {
		String value = properties.getProperties("apsid");
		/*
		 * 五个参数:
		 * 第一个参数:cookie的名称，任意取 
		 * 第二个参数:cookie的值
		 * 第三个参数:path:域名地址-代表我们只要是我们所测网站的域名，都表示有效 
		 * 第四个参数:代表全部路径下 
		 * 第五个参数:日期
		 */
		Cookie cookie = new Cookie("apsid", value, "imooc.com", "/", null);
		// 把当前的cookie成功添加到Set<Cookie>集合中,在DriverBase类中封装了setCookie方法
		driverBase.addCookie(cookie);
	}

	/*
	 * 设置全部cookie方法(多个)---将文件中的cookie值写入driver
	 * 
	 */
	public void setCookies() {
		BufferedReader bufferedReader;
//		webDriver.get("https://passport.csdn.net/account/login?from=http://my.csdn.net/my/mycsdn");

		try {
			File cookieFile = new File("cookies.txt");
			FileReader fileReader = new FileReader(cookieFile);
			bufferedReader = new BufferedReader(fileReader);

			String line;

			while ((line = bufferedReader.readLine()) != null) {
				// StringTokenizer用于分隔字符串,用来分隔String的应用类,相当于VB的split函数。
				StringTokenizer stringTokenizer = new StringTokenizer(line, ";");

				// hasMoreTokens() 测试此 tokenizer 的字符串中是否还有更多的可用标记。
				while (stringTokenizer.hasMoreTokens()) {

					String name = stringTokenizer.nextToken();// 名
					String value = stringTokenizer.nextToken();// 值
					String domain = stringTokenizer.nextToken();// 域
					String path = stringTokenizer.nextToken();// 路径
					Date expiry = null;// 日期
					String dt;

					// 日期不为空时
					if (!(dt = stringTokenizer.nextToken()).equals("null")) {
						expiry = new Date(dt);

//用这个cookie登录就不行了？？？     SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
//						 SimpleDateFormat中的parse方法可以把String型的字符串转换成特定格式的date类型,返回一个Date类型数据
//						 format返回的是一个StringBuffer类型的数据
//						expiry = simpleDateFormat.parse(dt);
					}

					// booleanValue() 将此 Boolean 对象的值作为基本布尔值返回。
//	也可以			boolean isSecure = new Boolean(stringTokenizer.nextToken()).booleanValue();
					boolean isSecure = Boolean.valueOf(stringTokenizer.nextToken()).booleanValue();

					Cookie cookie = new Cookie(name, value, domain, path, expiry, isSecure);
					driverBase.addCookie(cookie);
//					driver.manage().addCookie(cookie);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取cookie方法 原理:
	 * 
	 */
	public void writeCookie() {
		Set<Cookie> cookies = driverBase.getAllCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("apsid")) {
				properties.writeProperties(cookie.getName(), cookie.getValue());
			}
		}
	}

	/**
	 * 获取所有cookie并将其写入一个文件中
	 */
	public void saveCookie() {
		File cookieFile = new File("cookies.txt");

		try {
			cookieFile.delete();// 删除抽象路径名表示的文件或目录(此处删除cookie文件)。
			cookieFile.createNewFile();
			FileWriter fileWriter = new FileWriter(cookieFile);// FileWriter 用于写入字符流。
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);// 类将文本写入字符输出流,缓冲字符。

//			for (Cookie cookie : driver.manage().getCookies()) {
			for (Cookie cookie : driverBase.getAllCookies()) {
				bufferedWriter.write((cookie.getName() + ";"// 名
						+ cookie.getValue() + ";"// 值
						+ cookie.getDomain() + ";"// 域
						+ cookie.getPath() + ";"// 路径
						+ cookie.getExpiry() + ";"// 日期
						+ cookie.isSecure()));// 安全
				bufferedWriter.newLine();// 写入一个行分隔符。
			}
			bufferedWriter.flush();// 刷新该流的缓冲。
			bufferedWriter.close();// 关闭此流，但要先刷新它。
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
