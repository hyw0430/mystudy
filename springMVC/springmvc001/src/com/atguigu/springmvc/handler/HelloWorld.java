package com.atguigu.springmvc.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
//@RequestMapping(value="/crm")
public class HelloWorld
{
	@RequestMapping(value="/helloworld",method=RequestMethod.GET)
	public String helloworld()
	{
		System.out.println("-----helloworld");
		return "ok";
	}
}
