package com.atguigu.springmvc.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.springmvc.crud.dao.EmployeeDao;
import com.atguigu.springmvc.crud.entities.Employee;

@Controller
public class Test01
{
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@RequestMapping(value="/testInit",method=RequestMethod.GET)
	public String testInit()
	{
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping(value="/testJson",method=RequestMethod.POST)
	public Collection<Employee> qryAllEmployee()
	{
		return employeeDao.getAll();
	}

	@ResponseBody
	@RequestMapping(value="/testRequestBody",method=RequestMethod.POST)
	public String testRequestBody(@RequestBody String content)
	{
		System.out.println("content:===>  "+content);
		return "ok";
	}
	//ResponseEntity<byte[]>
	
	@RequestMapping(value="/testDownLoad",method=RequestMethod.GET)
	public ResponseEntity<byte[]> testDownLoad() throws IOException
	{
		byte[] body = null;
		FileInputStream input = new FileInputStream(new File("D:\\44\\b.txt"));
		body = new byte[input.available()];
		input.read(body);
		input.close();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=b.txt");
		
		HttpStatus statusCode = HttpStatus.OK;
		
		ResponseEntity<byte[]> result = new ResponseEntity<byte[]>(body, headers, statusCode);
		return result;
	}
	
	@RequestMapping(value="/i18n",method=RequestMethod.GET)	
	public String i18n()
	{
		return "ok";
	}
	
	@RequestMapping(value="/i18n2",method=RequestMethod.GET)	
	public String i18n2(Locale locale)
	{
		String v1 = messageSource.getMessage("i18n.username",null, locale);
		String v2 = messageSource.getMessage("i18n.password",null, locale);
		System.out.println(v1+"\t"+v2);
		return "ok";
	}
	
	@RequestMapping(value="/i18n3",method=RequestMethod.GET)	
	public String i18n3()
	{
		return "ok";
	}	
}
