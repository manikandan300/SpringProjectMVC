package com.sample.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sample.app.HomeController;
import com.sample.app.model.EmployeeModel;
import com.sample.app.service.IEmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	IEmployeeService employeeService;
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	/*  Naivates to Home Page 
	 *  @param none
	 *  @return page
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home()
	{
		return "emphome";
	}
	
	/*  Save the Employee 
	 *  @param emp
	 *  @return page
	 */
	@RequestMapping(value = "/empsave", method = RequestMethod.GET)
	public String save(EmployeeModel emp)
	{
		try{
			
			employeeService.addEmployee(emp);
			
		}catch (Exception exp)
		{
			logger.error("Error Occured @save" + exp.toString());
		}
		
		return "emphome";
	}
	
	/*  Edit the Employee 
	 *  @param emp
	 *  @return page
	 */
	@RequestMapping(value = "/empedit", method = RequestMethod.GET)
	public String edit(EmployeeModel emp)
	{
		try{
			
			employeeService.updateEmployee(emp);
			
		}catch (Exception exp)
		{
			logger.error("Error Occured @edit" + exp.toString());
		}
		
		return "emphome";
	}
	
	/*  Delete the Employee 
	 *  @param emp
	 *  @return page
	 */
	@RequestMapping(value = "/empdelete", method = RequestMethod.GET)
	public String delete(EmployeeModel emp)
	{
		try{
			
			employeeService.deleteEmployee(emp);
			
		}catch (Exception exp)
		{
			logger.error("Error Occured @delete" + exp.toString());
		}
		
		return "emphome";
	}
	
	
}
