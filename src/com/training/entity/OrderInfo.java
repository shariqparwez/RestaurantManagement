package com.training.entity;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderInfo {
	private int empId;
	private int orderNo;
	private int tableNo;
	private String status;
	private String payment;
	private HashMap<Integer,Integer> menuCodes;
	private ArrayList<String> menuItems;
	
	public OrderInfo() {
		super();
	}

	public OrderInfo(int empId, int orderNo, int tableNo, String status, String payment, HashMap<Integer,Integer> menuCodes, ArrayList<String> menuItems) {
		super();
		this.empId = empId;
		this.orderNo = orderNo;
		this.tableNo = tableNo;
		this.status = status;
		this.payment = payment;
		this.menuCodes = menuCodes;
		this.menuItems = menuItems;
	}
	
	public OrderInfo(int empId, int orderNo, int tableNo, String status, String payment, HashMap<Integer,Integer> menuCodes) {
		super();
		this.empId = empId;
		this.orderNo = orderNo;
		this.tableNo = tableNo;
		this.status = status;
		this.payment = payment;
		this.menuCodes = menuCodes;
	}

	public ArrayList<String> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(ArrayList<String> menuItems) {
		this.menuItems = menuItems;
	}

	public HashMap<Integer,Integer> getMenuCodes() {
		return menuCodes;
	}

	public void setMenuCodes(HashMap<Integer,Integer> menuCodes) {
		this.menuCodes = menuCodes;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getTableNo() {
		return tableNo;
	}

	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "ORDER DETAILS - \n empId = " + empId + "\n orderNo = " + orderNo + "\n tableNo = " + tableNo + "\n status = " + status
				+ "\n payment = " + payment + "\n menuCodes = " + menuCodes + "\n menuItems = " + menuItems;
	}



	
		
}
