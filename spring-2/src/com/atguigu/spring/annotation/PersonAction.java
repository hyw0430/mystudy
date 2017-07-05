package com.atguigu.spring.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//默认的 id 为类名第一个字母小写. 即 personAction. 也可以使用 value 属性去指定. 
@Controller
public class PersonAction {

	//在成员变量上使用 @Autowired 注解来装配 IOC 容器中对应类型的 bean
	@Autowired
	private PersonService personService;
	
	public void execute(){
		System.out.println("[PersonAction]#execute");
		personService.save();
	}
	
}
