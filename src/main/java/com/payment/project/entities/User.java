package com.payment.project.entities;

public class User {
	private int userId;
	private String upiId;
	private String accountNo;
	private String userName;
	private String userPassword;
	private String userPin;
	private String userEmail;
	private String userNumber;
	private double balance;
	
	
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", upiId=" + upiId + ", accountNo=" + accountNo + ", userName=" + userName
				+ ", userPassword=" + userPassword + ", userPin=" + userPin + ", userEmail=" + userEmail
				+ ", userNumber=" + userNumber + ", balance=" + balance + "]";
	}
	public User(String userName, String userPassword, String userEmail, String userNumber) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.userNumber = userNumber;
	}
	public User(int userId, String upiId, String accountNo, String userName, String userPassword, String userPin,
			String userEmail, String userNumber, double balance) {
		super();
		this.userId = userId;
		this.upiId = upiId;
		this.accountNo = accountNo;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userPin = userPin;
		this.userEmail = userEmail;
		this.userNumber = userNumber;
		this.balance = balance;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUpiId() {
		return upiId;
	}
	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserPin() {
		return userPin;
	}
	public void setUserPin(String userPin) {
		this.userPin = userPin;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	
	
	
}
