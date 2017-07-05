package com.atguigu.springmvc.crud.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.springmvc.crud.dao.DepartmentDao;
import com.atguigu.springmvc.crud.dao.EmployeeDao;
import com.atguigu.springmvc.crud.entities.Employee;

@Controller
public class EmployeeHandler
{
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private DepartmentDao departmentDao;
	
	
	/**
	 * 跳转进入新建员工信息录入页面
	 * @return
	 */
	@RequestMapping(value="/emp",method=RequestMethod.GET)
	public String input(Map<String,Object> map)
	{
		//1	查询出全部部门；
		map.put("departments",departmentDao.getDepartments());
		//2	查询出性别，也保存进LOV (list of value)；
		map.put("genders",getGenderUtils());
		//3 新建承载的bean，实现和前台form表单的对应
		map.put("employee",new Employee());
		return "input";
	}
	/**
	 * 真正的往数据库insert记录
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/emp",method=RequestMethod.POST)	
	public String save(@Valid Employee employee,BindingResult bindingResult,Map<String,Object> map)	
	{
		System.out.println("save method:  "+employee.toString());
		if(bindingResult.getErrorCount()>0)
		{
			List<FieldError> list = bindingResult.getFieldErrors();
			for (FieldError fieldError : list)
			{
				System.out.println(fieldError.getField()+"\t"+fieldError.getDefaultMessage());
			}
			//1	查询出全部部门；
			map.put("departments",departmentDao.getDepartments());
			//2	查询出性别，也保存进LOV (list of value)；
			map.put("genders",getGenderUtils());
			return "input";			
		}
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	/**
	 * 删除数据delete操作
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id)
	{
		employeeDao.delete(id);
		return "redirect:/emps";
	}
	/**
	 * 跳转到编辑修改页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)	
	public String edit(@PathVariable("id") Integer id,Map<String,Object> map)
	{
		//1	查询出全部部门；
		map.put("departments",departmentDao.getDepartments());
		//2	查询出性别，也保存进LOV (list of value)；
		map.put("genders",getGenderUtils());
		//3 新建承载的bean，实现和前台form表单的对应
		map.put("employee",employeeDao.get(id));	
		return "edit";
	}
	
	@RequestMapping(value="/emp",method=RequestMethod.PUT)		
	public String update(Employee employee)
	{
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	@ModelAttribute
	public void getEmployeeById(@RequestParam(value="id",required=false) Integer id,Map<String,Object> map)
	{
		if(null !=id)
		{
			map.put("employee",employeeDao.get(id));
		}
	}
	
	
	/**
	 * 查询出所有员工的列表页面====================
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/emps",method=RequestMethod.GET)
	public String list(Map<String,Object> map)
	{
		map.put("employees",employeeDao.getAll());
		return "list";
	}
	
	//=========模拟从LOV数据库查询出gender值
	public Map<String,String> getGenderUtils()
	{
		Map<String,String> genders = new HashMap<String,String>();
		//1 male, 0 female
		genders.put("1","male");
		genders.put("0","female");
		
		return genders;
	}
	
	//========员工类型转换,String--Convert-->Employee
	@RequestMapping(value="/empConvert",method=RequestMethod.POST)
	public String empConvert(@RequestParam("employee") Employee employee)
	{
		employeeDao.save(employee);
		return "redirect:/emps";		
	}
	
	//========@InitBinder
	/*@InitBinder
	public void initBinder(WebDataBinder webDataBinder)
	{     
		webDataBinder.setDisallowedFields("email");
	}*/
}
