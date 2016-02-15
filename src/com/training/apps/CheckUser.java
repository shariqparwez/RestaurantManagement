package com.training.apps;

import com.training.entity.User;
import com.training.utils.ValidateEmployee;

public class CheckUser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user1 = new User("shariq","sapient");
		User user2 = new User("Vaibhav","lenovo");
		
		ValidateEmployee validate = new ValidateEmployee();
		System.out.println(validate.Validate(user1));
		System.out.println(validate.Validate(user2));
	}

}
