package com.training.apps;

import com.training.daos.EmployeeDAO;
import com.training.entity.Employee;

public class RestaurantApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try {
			 //Employee emp = new Employee(103, "Shalini", 8867819333L, "Waiter");
			 EmployeeDAO dao = new EmployeeDAO();
			 //int rowadded = dao.add(emp);
			 //System.out.println(rowadded+" Row Added");
			 int rowdeleted = dao.delete(103);
			 System.out.println("Row Deleted : " + rowdeleted);
			 } catch (Exception e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }
	}

}
