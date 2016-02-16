package com.training.tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.training.daos.Waiter;
import com.training.entity.OrderInfo;

public class RestaurantTestCases {

	OrderInfo orderInfo = null;
	Waiter waiter = null;
	@Before
	public void setUp() throws Exception {
		waiter = new Waiter();
		orderInfo = new OrderInfo();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1() {
		orderInfo = waiter.getOrderDetails(10);
		System.out.println(orderInfo);
	}
	@Test
	public void test2() {
		HashMap<Integer,Integer> test = new HashMap<>();
		test.put(201, 2);
		test.put(302, 1);
		orderInfo = new OrderInfo(103,11,1,"PENDING","UNPAID", test);
		waiter.addOrder(orderInfo);
	}
	
	@Ignore
	@Test
	public void test3() {
		System.out.println(waiter.checkOrderStatus(11));
	}
	
	@Ignore
	@Test
	public void test4() {
		System.out.println(waiter.checkPaymentStatus(11));
	}
	
	@Ignore
	@Test
	public void test5() {
		waiter.deleteOrder(11);
	}
	
	@Ignore
	@Test
	public void test6(){
		waiter.deleteOrderItems(11, 202);
	}
	
	@Ignore
	@Test
	public void test7(){
		System.out.println(waiter.getMenuItem(501));
	}

}
