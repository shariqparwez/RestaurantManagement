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
	
	@Ignore
	@Test
	public void test2() {
		HashMap<Integer,Integer> test = new HashMap<>();
		test.put(101, 2);
		test.put(202, 1);
		orderInfo = new OrderInfo(104,11,4,"PENDING","UNPAID", test);
		waiter.addOrder(orderInfo);
	}
	
	@Test
	public void test3() {
		System.out.println(waiter.checkOrderStatus(11));
	}
	
	@Test
	public void test4() {
		System.out.println(waiter.checkPaymentStatus(11));
	}
	
	@Ignore
	@Test
	public void test5() {
		waiter.deleteOrder(11);
	}
	
	@Test
	public void test6(){
		waiter.deleteOrderItems(11, 202);
	}

}
