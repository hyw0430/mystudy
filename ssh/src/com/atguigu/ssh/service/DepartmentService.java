package com.atguigu.ssh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.ssh.dao.DepartmentDao;
import com.atguigu.ssh.domains.Department;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;
	
	@Transactional(readOnly=true)
	public List<Department> getAll(){
		return departmentDao.getAll();
	}
}
