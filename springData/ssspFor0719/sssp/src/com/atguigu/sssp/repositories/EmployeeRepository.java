package com.atguigu.sssp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atguigu.sssp.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
