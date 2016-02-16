package com.training.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import com.training.entity.Employee;
import com.training.entity.OrderInfo;
import com.training.ifaces.RestDAO;
import com.training.utils.SqlConnection;

public class Waiter implements RestDAO<OrderInfo> {

	private Connection con;
	
	public Waiter() {
		super();
		con = SqlConnection.getOracleConnection();
		// TODO Auto-generated constructor stub
	}
	
	public Waiter(Connection con) {
		super();
		this.con = con;
	}

	@Override
	public int addOrder(OrderInfo t) {
		ArrayList<String> menuItem = new ArrayList<>();
		String sql1 = "insert into ORDERINFO values(?,?,?,?,?)";
		String sql2 = "insert into ORDERTEMPDETAILS values(?,?,?)";

		int rowAdded = 0;
		
		try {
			PreparedStatement pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, t.getOrderNo());
			pstmt1.setInt(2, t.getEmpId());
			pstmt1.setInt(3, t.getTableNo());
			pstmt1.setString(4, t.getStatus());
			pstmt1.setString(5, t.getPayment());
			rowAdded = pstmt1.executeUpdate();
			
			HashMap<Integer,Integer> menuCodes = t.getMenuCodes();
			Iterator it = menuCodes.entrySet().iterator();
			PreparedStatement pstmt2 = con.prepareStatement(sql2);
			while(it.hasNext()){
				Map.Entry pair = (Map.Entry)it.next();
				pstmt2.setInt(1, t.getOrderNo());
				pstmt2.setInt(2, (int) pair.getKey());
				pstmt2.setInt(3, (int) pair.getValue());
				rowAdded += pstmt2.executeUpdate();
				menuItem.add(getMenuItem((int) pair.getKey()));
			}
			t.setMenuItems(menuItem);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowAdded;
	}
	
	@Override
	public OrderInfo getOrderDetails(int orderNo) {
		ArrayList<String> menuItem = new ArrayList<>();
		int orderId = 0, empId = 0, tableNo = 0, menuCode = 0, quantity = 0;
		String status = null, payment = null;
		OrderInfo orderInfo = null;
		HashMap<Integer,Integer> menuCodes = new HashMap<Integer,Integer>();
		String sql1 = "select * from ORDERINFO where ORDERNO=?";
		String sql2 = "select * from ORDERTEMPDETAILS where ORDERID=?";
		
		try{
			PreparedStatement pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, orderNo);
			ResultSet rs = pstmt1.executeQuery();
			while(rs.next()){
				orderId = rs.getInt("ORDERNO");
				empId = rs.getInt("EMPID");
				tableNo = rs.getInt("TABLENO");
				status = rs.getString("STATUS");
				payment = rs.getString("PAYMENT");
			}
			PreparedStatement pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1, orderNo);
			ResultSet rs2 = pstmt2.executeQuery();
			while(rs2.next()){
				//System.out.println("in rs2");
				//System.out.println(rs2.getInt("MENUCODE"));
				menuCodes.put(rs2.getInt("MENUCODE"),rs2.getInt("QUANTITY"));
				menuItem.add(getMenuItem(rs2.getInt("MENUCODE")));
			}
			
			orderInfo = new OrderInfo(empId,orderId,tableNo,status,payment,menuCodes,menuItem);

		} catch(Exception e){
			e.printStackTrace();
		}
		return orderInfo;
	}

	@Override
	public String checkOrderStatus(int orderNo) {
		String orderStatus = null;
		String sql = "select * from ORDERINFO where ORDERNO=?";
		OrderInfo orderInfo = null;
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderNo);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				orderStatus = rs.getString("STATUS");
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return orderStatus;
	}

	@Override
	public String checkPaymentStatus(int orderNo) {
		String paymentStatus = null;
		String sql = "select * from ORDERINFO where ORDERNO=?";
		OrderInfo orderInfo = null;
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderNo);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				paymentStatus = rs.getString("PAYMENT");
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return paymentStatus;
	}

	@Override
	public int updateOrderDetails(int orderNo,int menuCode, int quantity) {
		
		String sql = "UPDATE ORDERTEMPDETAILS  SET quantity = ? WHERE orderId = ? AND menucode = ?";
		int rowUpdated = 0;
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, orderNo);
			pstmt.setInt(3, menuCode);
			rowUpdated = pstmt.executeUpdate();
		} catch (Exception e){
			e.printStackTrace();
		}
		return rowUpdated;
	}

	@Override
	public int deleteOrder(int orderNo) {
		String sql1 = "DELETE FROM ORDERINFO WHERE ORDERNO = ?";
		String sql2 = "DELETE FROM ORDERTEMPDETAILS WHERE ORDERID = ?";
		int rowDeleted = 0;
		try{
			PreparedStatement pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1, orderNo);
			rowDeleted += pstmt2.executeUpdate();
			PreparedStatement pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, orderNo);
			rowDeleted = pstmt1.executeUpdate();

			//set to constructor
		} catch (Exception e){
			e.printStackTrace();
		}
		return rowDeleted;
	}
	
	@Override
	public int deleteOrderItems(int orderNo, int menuCode) {
		String sql = "DELETE FROM ORDERTEMPDETAILS WHERE ORDERID = ? AND MENUCODE = ?";
		int rowDeleted = 0;
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderNo);
			pstmt.setInt(2, menuCode);
			rowDeleted = pstmt.executeUpdate();
			//set to constructor
		} catch (Exception e){
			e.printStackTrace();
		}
		return rowDeleted;
	}
	
	public String getMenuItem(int menuCode){
		String menuDetail = null;
		String sql = "select * from MENUITEMS where MENUCODE=?";
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, menuCode);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				menuDetail = rs.getString("DISHNAME");
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return menuDetail;
	}

}
