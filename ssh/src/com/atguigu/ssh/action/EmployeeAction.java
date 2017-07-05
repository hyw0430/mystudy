package com.atguigu.ssh.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.ssh.domains.Employee;
import com.atguigu.ssh.service.DepartmentService;
import com.atguigu.ssh.service.EmployeeService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

@Scope("prototype")
@Controller
public class EmployeeAction extends ActionSupport 
	implements RequestAware, ModelDriven<Employee>, Preparable{

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DepartmentService departmentService;
	
	private Integer id;
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void prepareEdit(){
		model = employeeService.get(id);
	}
	
	public String edit(){
		this.request.put("departments", departmentService.getAll());
		return "edit";
	}
	
	public String input(){
		this.request.put("departments", departmentService.getAll());
		return "emp-input";
	}
	
	public void prepareSave(){
		if(id == null){
			model = new Employee();
		}else{
			model = employeeService.get(id);
		}
	}
	
	public String save(){
		employeeService.save(model);
		return "success";
	}
	
	public String list(){
		this.request.put("employees", employeeService.getAll());
		return "list";
	}
	
	private Map<String, Object> request;
	
	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;
	}
	
	private Employee model;

	@Override
	public void prepare() throws Exception {}

	@Override
	public Employee getModel() {
		return model;
	}

	
	
}
