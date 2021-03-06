package com.example.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 字符类型显示装换工具类
 * @author leo
 *
 */
public class CamelToUnderlineUtil {
	
	public static final char UNDERLINE = '_';
	
	/**
	 * 驼峰转下划线(大写),并在后面加下划线
	 * @param param
	 * @return
	 */
	public static String camelToUnderlineAndUpperCase(String param){  
	       if (param==null||"".equals(param.trim())){  
	           System.out.println("");
	       }  
	       int len=param.length();  
	       StringBuilder sb=new StringBuilder(len);  
	       for (int i = 0; i < len; i++) {  
	           char c=param.charAt(i);  
	           if (Character.isUpperCase(c)){ 
	        	   //如果大写前面加一个_
	               sb.append(UNDERLINE);  
	               sb.append(c);  
	           }else{  
	        	   //如果小写,转为大写
	               sb.append(Character.toUpperCase(c));  
	           }  
	       }  
	       sb.append(UNDERLINE); 
	       return sb.toString();
	   }  

	public static String camelToUnderline(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(UNDERLINE);
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String underlineToCamel(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == UNDERLINE) {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String underlineToCamel2(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		StringBuilder sb = new StringBuilder(param);
		Matcher mc = Pattern.compile("_").matcher(param);
		int i = 0;
		while (mc.find()) {
			int position = mc.end() - (i++);
			// String.valueOf(Character.toUpperCase(sb.charAt(position)));
			sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(camelToUnderline("loginName"));
	}
}