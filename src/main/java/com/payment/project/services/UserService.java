package com.payment.project.services;

import com.payment.project.entities.User;

public interface UserService {
	
	public User getUser(int userId);
	public void addUser(String userName, String userPassword, String userEmail,String userNumber) ;
	public User authenticateUser(String userName, String userPassword);
	public void updateUserBalance(int userId, double newBalance);
}
