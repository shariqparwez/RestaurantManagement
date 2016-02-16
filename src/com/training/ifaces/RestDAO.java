package com.training.ifaces;

import com.training.entity.OrderInfo;

public interface RestDAO<T> {
	
	public int addOrder(T t);
	public OrderInfo getOrderDetails(int orderNo);
	public String checkOrderStatus(int orderNo);
	public String checkPaymentStatus(int orderNo);
	public int updateOrderDetails(int orderNo, int menuCode, int quantity);
	public int deleteOrder(int orderNo);
	public int deleteOrderItems(int orderNo, int menuCode);
}
