package com.training.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.training.entity.*;
import com.training.utils.*;

import com.training.ifaces.EmpDAO;
import com.training.utils.SqlConnection;

public class EmployeeDAO implements EmpDAO<Employee> {
	
	private Connection con;

	public EmployeeDAO(Connection con) {
		super();
		this.con = con;
	}
	
	public EmployeeDAO() {
		super();
		con = SqlConnection.getOracleConnection();
	}

	@Override
	public int add(Employee t) {
		String sql = "insert into EMPLOYEE values(?,?,?,?)";
		int rowAdded = 0;
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, t.getEmployeeId());
			pstmt.setString(2, t.getEmployeeName());
			pstmt.setString(4, t.getDesignation());
			pstmt.setLong(3, t.getPhoneNumber());
			
			rowAdded = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowAdded;
	}

	@Override
	public Employee find(int key) {
		String sql = "select * from EMPLOYEE where employeeId=?";
		Employee emp = null;
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, key);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				emp=getEmployee(rs);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return emp;
	}

	@Override
	public List<Employee> findAll() {
		ArrayList<Employee> empList = new ArrayList<Employee>();
		String sql = "SELECT * FROM employee";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Employee emp=getEmployee(rs);
				empList.add(emp);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return empList;
	}

	@Override
	public int update(int key, long handPhone) {
		// TODO Auto-generated method stub
		String sql = "UPDATE employee SET phoneNumber = ? WHERE employeeId = ?";
		Employee emp = null;
		int rowUpdated = 0;
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(2, key);
			pstmt.setLong(1, handPhone);
			rowUpdated = pstmt.executeUpdate();
		} catch (Exception e){
			e.printStackTrace();
		}
		return rowUpdated;
	}

	@Override
	public int delete(int key) {
		String sql = "DELETE FROM employee WHERE empId = ?";
		int rowDeleted = 0;
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, key);
			rowDeleted = pstmt.executeUpdate();
			
			//set to constructor
		} catch (Exception e){
			e.printStackTrace();
		}
		return rowDeleted;
	}
	
	private Employee getEmployee(ResultSet rs){
		Employee emp = null;
		try{
			int employeeId = rs.getInt("employeeId");
			String employeeName = rs.getString("employeeName");
			long handPhone = rs.getLong("phoneNumber");
			String designation = rs.getString("designation");
			
			emp = new Employee(employeeId,employeeName,handPhone,designation);

		} catch(Exception e){
			e.printStackTrace();
		}
		return emp;
	}

}
