package cn.edu.lingnan.util;

import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlHandler extends DefaultHandler{//事件处理器

	private StringBuffer sb = new StringBuffer();//可变字符串
	private HashMap<String,String> hm = new HashMap<String,String>();
	
    public void startElement (String uri, String localName,
                              String qName, Attributes attributes)
        throws SAXException
    {
        // 清空一个可变字符串
    	sb.delete(0, sb.length());
    }
 
    public void endElement (String uri, String localName, String qName)
        throws SAXException
    {
        // 将可变字符串的内容存入某个介质当中
    	hm.put(qName.toLowerCase(), sb.toString().trim());//元素名（转小写），内容（去空格）
    }
 
    public void characters (char ch[], int start, int length)
        throws SAXException
    {
        // 把读到的ch字符数组中的内容存入可变字符串
    	sb.append(ch,start,length);
    }
    
    public HashMap<String,String> getHashMap(){
    	return hm;
    }  
}
