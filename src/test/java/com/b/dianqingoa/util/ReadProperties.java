package com.b.dianqingoa.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 该类的作用是：读取属性文件
 */
public class ReadProperties {

	private Properties properties;
	private String filePath = "element.properties";

	/**
	 * 构造方法 创建对象时自动返回pro对象 在new该对象的时候会自动加载readProperties()方法
	 */
	// 初始化new ProUtil是从外部传回一个properties文件的路径
	public ReadProperties(String filePath) {
		// 外部文件路径赋值给当前类的文件路径
		this.filePath = filePath;
		// 在new该对象的时候会自动加载readProperties()方法
		this.properties = readProperties();
	}

	/**
	 * 读取配置文件 - 返回已经加载properties文件的pro对象
	 * 
	 */
	private Properties readProperties() {
		// 创建一个Properties对象(Properties 类表示了一个持久的属性集,可保存在流中或从流中加载,主要用于读取Java的配置文件)
		Properties prop = new Properties();
		// 读取properties文件到缓存
		try {
			/*
			 * BufferedInputStream字节缓冲输入流
			 * 
			 * public BufferedInputStream(InputStream in,int size) 指定缓冲区大小构造缓冲输入流对象 in -
			 * 底层输入流。 size - 缓冲区大小。 IllegalArgumentException 如果 size <= 0，则抛出这个异常。
			 * 
			 * JAVA中针对文件的读写操作设置了一系列的流，其中主要有FileInputStream,FileOutputStream,FileReader,
			 * FileWriter四种最为常用的流
			 * 
			 * FileInputStream流被称为文件字节输入流，意思指对文件数据以字节的形式进行读取操作如读取图片视频等
			 */
			// 使用文件读取并且使用properties文件进行加载出来
			InputStream inputStream = new FileInputStream(filePath);
			BufferedInputStream bis = new BufferedInputStream(inputStream);
			// 加载缓存到pro对象
			prop.load(bis);
			/*
			 * BufferedInputStream in = new BufferedInputStream(new
			 * FileInputStream(filePath)); 加载缓存到pro对象 prop.load(in)这么写不能读取properties配置文件中的中文
			 * InputStreamReader 是字节流通向字符流的桥梁,它将字节流转换为字符流. pro.load(new
			 * InputStreamReader(in, "utf-8"));
			 */
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 返回pro对象
		return prop;
	}

	/**
	 * 使用全局的properties对象获取key对应的value值
	 * 
	 * 封装配置文件中的getProperty方法
	 * 
	 *	public String getValue(String key){
	 *		return properties.getProperty(key);
	 *	}
	 */
	public String getProperties(String key) {
		// 判断properties文件中是否包含key信息
		if (properties.containsKey(key)) {
			String username = properties.getProperty(key);// 用指定的键在属性列表中搜索属性。
			return username;
		} else {
			System.out.println("你获取的值不存在");
			return "";
		}
	}

	/**
	 * 写入内容 --- 把取得的cookie写入
	 */
	public void writeProperties(String key, String value) {
		Properties pro = new Properties();
		try {
			FileOutputStream file = new FileOutputStream(filePath);
			pro.setProperty(key, value);
			/*
			 * 	store(OutputStream out,String comments) 以适合使用 load(InputStream) 
			 * 方法加载到 Properties 表中的格式，将此 Properties 表中的属性列表（键和元素对）写入输出流 ;
			 * 	store(Writer writer,String comments) 以适合使用 load(Reader) 方法的格式，
			 * 将此 Properties 表中的属性列表（键和元素对）写入输出字符;
			 * 	store方法是写入输出流，“只有”把properties对象设置键值对之后，“才能”去写入输出流以实现真正的
			 * 输出到文件内
			 */
			pro.store(file, key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
