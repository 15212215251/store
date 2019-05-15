package com.itheima.conventer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class MyConventer implements Converter {

	@Override
	public Object convert(Class clazz, Object value) {
		// TODO Auto-generated method stub
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = simpleDateFormat.parse((String) value);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
