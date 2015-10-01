package com.sample.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import com.sample.app.dao.EmployeeDAO;
import com.sample.app.domain.EmployeeDomain;
import com.sample.app.model.EmployeeModel;

@Service("employeeService")
public class EmployeeService implements IEmployeeService {

	
	@Autowired
	private PlatformTransactionManager transactionManager;

	@Autowired
	EmployeeDAO employeeDAO;
	
	/*
	 * Adds the Employee
	 * (non-Javadoc)
	 * @see com.sample.app.service.IEmployeeService#addEmployee(com.sample.app.model.EmployeeModel)
	 */

	@Transactional
	public boolean addEmployee(EmployeeModel employee) {
		// TODO Auto-generated method stub
		EmployeeDomain employeeDomain = new EmployeeDomain();
		employeeDomain.setFirstName(employee.getFirstName());
		employeeDomain.setLastName(employee.getLastName());
		employeeDomain.setAge(employee.getAge());

		employeeDAO.create(employeeDomain);

		employee.setEmployeeID(employeeDomain.getEmployeeID());

		return true;
	}
	

	/*
	 * Delete the Employee
	 * (non-Javadoc)
	 * @see com.sample.app.service.IEmployeeService#deleteEmployee(com.sample.app.model.EmployeeModel)
	 */
	@Transactional
	public boolean deleteEmployee(EmployeeModel employee) {
		// TODO Auto-generated method stub

		employeeDAO.delete(new Integer(employee.getEmployeeID()));
		return true;
	}
	
	/* Update Employee
	 * (non-Javadoc)
	 * @see com.sample.app.service.IEmployeeService#updateEmployee(com.sample.app.model.EmployeeModel)
	 */

	@Transactional
	public boolean updateEmployee(EmployeeModel employee) {
		// TODO Auto-generated method stub
		EmployeeDomain employeeDomain = new EmployeeDomain();
		employeeDomain.setEmployeeID(employee.getEmployeeID());
		employeeDomain.setFirstName(employee.getFirstName());
		employeeDomain.setLastName(employee.getLastName());
		employeeDomain.setAge(employee.getAge());

		employeeDAO.update(employeeDomain);
		return true;
	}

	/* Find the Employee
	 * (non-Javadoc)
	 * @see com.sample.app.service.IEmployeeService#findEmployeeByID(int)
	 */
	@Transactional(readOnly=true)
	public EmployeeModel findEmployeeByID(int empid) {
		// TODO Auto-generated method stub
		
		EmployeeDomain employeeDomain=  employeeDAO.find(new Integer(empid));
		if(employeeDomain !=null)
		{
			EmployeeModel employeeModel=new EmployeeModel();
			employeeModel.setEmployeeID(employeeDomain.getEmployeeID());
			employeeModel.setFirstName(employeeDomain.getFirstName());
			employeeModel.setLastName(employeeDomain.getLastName());
			return employeeModel;
		}
		return null;
	}

}
