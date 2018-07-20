package cn.edu.lingnan.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class XmlParser {//解析器

	public static HashMap<String,String> parser(String xmlPath){
		HashMap<String,String> hm = new HashMap<String,String>();
		String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		xmlPath = basePath + xmlPath;
		System.out.println(xmlPath);
		try {
			//1.实例化一个解析工厂（SAXParserFactory对象）
			SAXParserFactory factory = SAXParserFactory.newInstance();		
			//2.通过这个工厂（factory）获得一个解析（SAXParser）对象，即SAX解析器
			SAXParser saxParser = factory.newSAXParser();		
			//3.saxParser对象调用parse方法解析XML文件（通过这个解析器去解析相应的东西）
			File f = new File(xmlPath);//事件源
			XmlHandler xh = new XmlHandler();//事件处理器
			saxParser.parse(f, xh);
			hm = xh.getHashMap();
//			System.out.println("---"+hm.get("user"));
//			System.out.println("---"+hm.get("driver"));
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return hm;
	}
	
	
//	public static void main(String[] args) {
//		String xmlPath = "database.conf.xml";
//		System.out.println(XmlParser.parser(xmlPath));
//
//	}

}
