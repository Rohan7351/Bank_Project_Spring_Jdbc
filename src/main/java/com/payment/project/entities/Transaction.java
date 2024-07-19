package com.payment.project.entities;

import java.sql.Timestamp;
import java.util.Date;

public class Transaction {
	private int transactionId;
	private int userId;
	private double amount;
	private String transactionType;
	private Timestamp transTimestamp;
	
	public Transaction() {
		
	}
	
	
	public Transaction(int transactionId, int userId, double amount, String transactionType, Timestamp transTimestamp) {
		super();
		this.transactionId = transactionId;
		this.userId = userId;
		this.amount = amount;
		this.transactionType = transactionType;
		this.transTimestamp = transTimestamp;
	}
	
	public int getTransactionId() {
		return transactionId;
	}
	
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String getTransactionType() {
		return transactionType;
	}
	
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	public Timestamp getTransTimestamp() {
		return transTimestamp;
	}
	
	public void setTransTimestamp(Timestamp transTimestamp) {
		this.transTimestamp = transTimestamp;
	}
	
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", userId=" + userId + ", amount=" + amount
				+ ", transactionType=" + transactionType + ", timestamp=" + transTimestamp + "]";
	}
	
}
