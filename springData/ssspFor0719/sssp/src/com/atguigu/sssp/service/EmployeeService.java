package com.atguigu.sssp.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.sssp.entities.Employee;
import com.atguigu.sssp.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Transactional(readOnly=true)
	public Employee get(Integer id) {
		return employeeRepository.findOne(id);
	} 
	
	@Transactional
	public void save(Employee employee){
		//新添加的情况下需要设置 createTime. 
		//而修改的情况下则不需要设置该属性
		if(employee.getId() == null){
			employee.setCreateTime(new Date());
		}
		
		employeeRepository.saveAndFlush(employee);
	}
	
	@Transactional
	public void delete(Integer id){
		employeeRepository.delete(id);
	}
	
	@Transactional(readOnly=true)
	public Page<Employee> getAll(int pageNo, int pageSize){
		PageRequest pageable = new PageRequest(pageNo, pageSize);
		return employeeRepository.findAll(pageable);
	}

}
