package com.mycom.maven.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.mycom.maven.model.Employee;

public class EmployeeDao {
	
	List<Employee> employeeList = new ArrayList<Employee>();
	Session session = null;
	Transaction trans  = null;

	public ArrayList<Employee> getemployees() {
		try {
			System.out.println("Before creating session factory object");

			
			  SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			  System.out.println("Factory object created...before opening session");
			  
			  session = sessionFactory.openSession();
			  
			  //Employee emp = session.get(Employee.class, 102); 
			  Query query = session.createQuery("from employees"); 
			  List<Employee> list = query.list(); 
			  while(list != null) {
				  int id;
				  String employeeName;
				  String employeeAddress;
				  long employeePhone;
				  String employeeSalary;
				  
				  Employee emp = new Employee();
				  id = emp.getId();
				  employeeName = emp.getEmployeeName();
				  employeeAddress = emp.getEmployeeAddress();
				  employeePhone = emp.getEmployeePhone();
				  employeeSalary = emp.getEmployeeSalary();
				  
				  emp.setId(id);
				  emp.setEmployeeName(employeeName);
				  emp.setEmployeeAddress(employeeAddress);
				  emp.setEmployeePhone(employeePhone);
				  emp.setEmployeeSalary(employeeSalary);
				  
				  list.add(emp);
			  }
			  sessionFactory.close();
			 
		} finally {
		}

		return (ArrayList<Employee>) employeeList;
	}

	public int insertEmployee(Employee employee) {
		
		int rowCount = 0;
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		 System.out.println("Factory object created...before opening session");
		  Employee emp = new Employee();
		  session = sessionFactory.openSession();
		  trans = session.beginTransaction();

		emp.setEmployeeName(employee.getEmployeeName());
		emp.setEmployeeAddress(employee.getEmployeeAddress());
		emp.setEmployeePhone(employee.getEmployeePhone());
		emp.setEmployeeSalary(employee.getEmployeeSalary());
		session.persist(emp);
		trans.commit();	
		return rowCount;
	}

}
