package com.atguigu.validation.myvalidator;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

public class IDCardValidator extends FieldValidatorSupport{

	@Override
	public void validate(Object object) throws ValidationException {
		
		System.out.println(object);
		
		//获取被验证的字段的值
		String idCardStr = (String) getFieldValue(getFieldName(), object);
		
		//创建IDCard对象
		IDCard idCard = new IDCard();
		
		//执行验证
		boolean result = idCard.Verify(idCardStr);		
		
		//检查身份证号是否正确
		if(!result) {
			
			//在目标Action类中添加错误信息
			addFieldError(getFieldName(), object);
			
		}
		
	}

}
