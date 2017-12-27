package com.yichangapp.utils.dateConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/*
 * 接口的泛型指示了将String转换成Date
 * 使用与表单日期字符串的封装
 */
public class CustomDateConverter implements Converter<String, Date> {
	
	@Override
	public Date convert(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
}
