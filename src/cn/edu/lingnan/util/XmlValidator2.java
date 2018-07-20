package cn.edu.lingnan.util;

import java.io.File;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

public class XmlValidator2 {

	public static boolean validator(String xmlPath, String xsdPath){
		boolean flag = false;
		SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		File f = new File(xsdPath);
		try {
			Schema s = sf.newSchema(f);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return flag;
	}

	public static void main(String[] args) {
		String xmlPath = "src//database.conf.xml";
		String xsdPath = "src//database.conf.xsd";
		System.out.println(XmlValidator2.validator(xmlPath, xsdPath));
	}

}
