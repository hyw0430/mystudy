package com.atguigu.convert.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.atguigu.convert.bean.Address;

public class AddressConverter extends StrutsTypeConverter{

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		
		//1.values参数传入的是要进行转换的字符串数组，我们可以从中获取第一个元素
		String addressStr = values[0];
		
		//2.使用“,”作为分隔符，拆分字符串
		String[] split = addressStr.split(",");
		String country = split[0];
		String province = split[1];
		String city = split[2];
		String street = split[3];
		
		//3.将拆分得到的数据封装为address对象
		Address address = new Address(country, province, city, street);
		
		//4.返回address对象
		return address;
	}

	@Override
	public String convertToString(Map context, Object o) {
		return null;
	}

}
