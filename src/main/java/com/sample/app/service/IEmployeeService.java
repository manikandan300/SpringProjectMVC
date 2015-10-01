package com.sample.app.service;

import com.sample.app.model.EmployeeModel;



public interface IEmployeeService {

	public boolean addEmployee(EmployeeModel employee);
	public boolean deleteEmployee(EmployeeModel employee);
	public boolean updateEmployee(EmployeeModel employee);
	public EmployeeModel findEmployeeByID(int empid);
	
	
	
}
