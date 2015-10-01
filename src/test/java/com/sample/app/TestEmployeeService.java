package com.sample.app;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sample.app.model.EmployeeModel;
import com.sample.app.service.IEmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestEmployeeService {

	@Autowired
	IEmployeeService employeeService;
	public static int employeeID;

	@Test
	public void A_AddEmployee() {
		EmployeeModel emp = new EmployeeModel();
		EmployeeModel searchEmployee = null;
		emp.setFirstName("Test");
		emp.setLastName("A");
		emp.setAge(20);

		employeeService.addEmployee(emp);

		employeeID = emp.getEmployeeID();

		searchEmployee = employeeService.findEmployeeByID(emp.getEmployeeID());
		assert (searchEmployee != null && emp.getEmployeeID() != searchEmployee
				.getEmployeeID());

	}

	@Test
	public void B_EditEmployee() {
		
		EmployeeModel searchEmployee = null;
		searchEmployee=employeeService.findEmployeeByID(employeeID);
		assert(searchEmployee!=null);
		
		searchEmployee.setLastName("B");
		
		employeeService.updateEmployee(searchEmployee);
		
		searchEmployee=employeeService.findEmployeeByID(employeeID);
		
		assert(searchEmployee.getLastName().equals("B"));
		
		

	}

	@Test
	public void C_DeleteEmployee() {

		EmployeeModel searchEmployee = null;
		searchEmployee=employeeService.findEmployeeByID(employeeID);
		employeeService.deleteEmployee(searchEmployee);
		searchEmployee=employeeService.findEmployeeByID(employeeID);
		assert(searchEmployee==null);
		
		
	}

}
