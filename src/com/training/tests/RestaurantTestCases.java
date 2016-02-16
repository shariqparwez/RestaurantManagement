package com.training.tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
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
		test.put(101, 2);
		test.put(202, 1);
		orderInfo = new OrderInfo(104,11,4,"PENDING","UNPAID", test);
		waiter.addOrder(orderInfo);
	}

}
