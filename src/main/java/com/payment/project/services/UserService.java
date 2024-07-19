package com.payment.project.services;

import com.payment.project.entities.User;

public interface UserService {
      void addUser(User user);
      void addAmount(String uId, int amount);
      void withdrawAmount(String uId, int amount);
	  void showUsers();
}
