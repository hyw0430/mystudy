package com.atguigu.springmvc.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.atguigu.springmvc.crud.entities.Department;
import com.atguigu.springmvc.crud.entities.Employee;

@Component
public class EmployeeConverter implements Converter<String,Employee>
{
	@Override
	public Employee convert(String source)
	{
		Employee result = null;
		if(null != source)
		{
			String[] empInfos = source.split(";");
			if(null != empInfos && empInfos.length == 4)
			{
				result = new Employee();
				
				result.setLastName(empInfos[0]);
				result.setEmail(empInfos[1]);
				result.setGender(Integer.parseInt(empInfos[2]));
				
				Department department = new Department();
				department.setId(Integer.parseInt(empInfos[3]));
				result.setDepartment(department);
			}
		}
		return result;
	}
}
