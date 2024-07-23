package com.payment.project.services;

public class UserServiceImpl {
    int a;
    
    
    
    
    
    

	@Override
	public String toString() {
		return "UserServiceImpl [a=" + a + "]";
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		System.out.println("UserService inject setter ");
		this.a = a;
	}

	public UserServiceImpl(int a) {
		super();
		System.out.println("UserService inject con");
		this.a = a;
	}

	public UserServiceImpl() {
		super();
		System.out.println("UserService inject");
		// TODO Auto-generated constructor stub
	}
    


}
