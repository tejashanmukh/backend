package com.app.service;

import com.app.pojos.User;

public interface IUserService 
{


	int generateOtp();

	User findByEmail(String email);

	void updatePassword(User userPojo);
   
	
	
}
