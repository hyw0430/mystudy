package com.atguigu.ssh.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.ssh.dao.EmployeeDao;
import com.atguigu.ssh.domains.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Transactional
	public Employee get(Integer id){
		return employeeDao.get(id);
	}
	
	@Transactional
	public void save(Employee employee){
		//需要指定 createTime
		if(employee.getId() == null){
			employee.setCreateTime(new Date());
		}
		
		employeeDao.save(employee);
	}
	
	@Transactional(readOnly=true)
	public List<Employee> getAll(){
		return employeeDao.getAll();
	}
}
