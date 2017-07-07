package com.atguigu.sssp.handlers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.sssp.entities.Employee;
import com.atguigu.sssp.service.DepartmentService;
import com.atguigu.sssp.service.EmployeeService;

@Controller
public class EmployeeHandler {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping(value="/emp/{id}")
	public String input(@PathVariable("id") Integer id, Map<String, Object> map){
		map.put("employee", employeeService.get(id));
		map.put("departments", departmentService.getAll());
		return "input";
	}
	
	@ModelAttribute
	public void getEmployee(@RequestParam(value="id",required=false) Integer id,
			Map<String, Object> map){
		if(id != null){
			Employee employee = employeeService.get(id);
			employee.setDepartment(null);
			map.put("employee", employee);
		}
	}
	
	@RequestMapping(value="/emp", method={RequestMethod.POST, RequestMethod.PUT})
	public String save(Employee employee){
		employeeService.save(employee);
		return "redirect:/emps";
	}
	
	@RequestMapping(value="/emp",method=RequestMethod.GET)
	public String input(Map<String, Object> map){
		map.put("departments", departmentService.getAll());
		map.put("employee", new Employee());
		return "input";
	}
	
	@RequestMapping(value="/emp/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id){
		employeeService.delete(id);
		return "redirect:/emps";
	}
	
	@RequestMapping(value="/emps")
	public String list(@RequestParam(value="pageNo",required=false,defaultValue="1") String pageNoStr, 
			Map<String, Object> map){
		int pageNo = 0;
		try {
			pageNo = Integer.parseInt(pageNoStr) - 1;
			if(pageNo < 0){
				pageNo = 0;
			}
		} catch (NumberFormatException e) {}
		map.put("page", employeeService.getAll(pageNo, 5));
		return "list";
	}
	
}
