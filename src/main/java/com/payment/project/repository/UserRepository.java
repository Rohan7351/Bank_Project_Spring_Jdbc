package com.payment.project.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.payment.project.entities.Transaction;
import com.payment.project.entities.User;
import com.payment.project.services.UserService;



public class UserRepository implements UserService {
private Connection con;

    
	public UserRepository() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_db", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public User getUser(int userId) {
		User user=null;
		
		try {
			String query="Select * from Users where userId=?";
			PreparedStatement st=con.prepareStatement(query);
			st.setInt(1, userId);
			ResultSet rs=st.executeQuery();
			if(rs.next()) {
				user=new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8), rs.getDouble(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}


	
	public User getUserByUpi(String upiId) {
		User user=null;
		
		try {
			String query="Select * from Users where userUpi=?";
			PreparedStatement st=con.prepareStatement(query);
			st.setString(1, upiId);
			ResultSet rs=st.executeQuery();
			if(rs.next()) {
				user=new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8), rs.getDouble(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	public User getUserByAccountNo(String accountNumber) {
		User user=null;
		
		try {
			String query="Select * from Users where userAccountNo=?";
			PreparedStatement st=con.prepareStatement(query);
			st.setString(1, accountNumber);
			ResultSet rs=st.executeQuery();
			if(rs.next()) {
				user=new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8), rs.getDouble(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public void addUser(String userName, String userPassword, String userEmail,String userNumber) {
		try {
			String random = generateRandomId(6).toString();
			
			String upiId = userNumber + "@bank";
			String accountNo = random;
			String userPin = generateRandomId(4).toString();
			
			String query="INSERT INTO Users (userUpi, userAccountNo, userName, userPassword, userPin, userEmail, userNumber) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st=con.prepareStatement(query);
			    st.setString(1, upiId);
	            st.setString(2, accountNo);
	            st.setString(3, userName);
	            st.setString(4, userPassword);
	            st.setString(5, userPin);
	            st.setString(6, userEmail);
	            st.setString(7, userNumber);
	          		
			st.executeUpdate();
			 
			System.out.println("Account Created Successfully...!!\n");
			System.out.println("***User Details*** \n ");
			System.out.println("User UPI Id : "+upiId);
			System.out.println("Your Account No is : "+accountNo);
			System.out.println("Your pin is : "+userPin+"\n");
		} catch (SQLException e) {
			System.out.println("Error creating the user  !Please try again \n ");
			e.printStackTrace();
		}
	}
	
	
	public User authenticateUser(String userNumber, String userPassword) {
		User user=null;
		
		try {
			String query="Select * from Users where userNumber=? AND userPassword=?";
			PreparedStatement st=con.prepareStatement(query);
			st.setString(1, userNumber);
			st.setString(2, userPassword);
			ResultSet rs=st.executeQuery();
			
			if(rs.next()) {
				
				System.out.println("User is authenticated..!!");
				user=new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getDouble(9));
			} else {
				System.out.println("No data found in database");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public void updateUserBalance(int userId, double newBalance) {
		try {
			String query="update Users set balance=? where userId=?";
			PreparedStatement st=con.prepareStatement(query);
			st.setDouble(1, newBalance);
			st.setInt(2, userId);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void changeUserPin(User user,String pin) {
		try {
			String query="update Users set userPin=? where userId=?";
			PreparedStatement st=con.prepareStatement(query);
			st.setString(1,pin );
			st.setInt(2, user.getUserId());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void addTransaction(Transaction transaction) {
		try {
			String query="insert into Transactions(userId, amount, transactionType) values(?, ?, ?)";
			PreparedStatement st=con.prepareStatement(query);
			st.setInt(1, transaction.getUserId());
			st.setDouble(2, transaction.getAmount());
			st.setString(3, transaction.getTransactionType());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<Transaction> getTransactionHistory(int userId){
		List<Transaction> transactions=new ArrayList<>();
		
		try {
			String query="select * from Transactions where userId=? order by transTimestamp desc";
			PreparedStatement st=con.prepareStatement(query);
			st.setInt(1, userId);
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				Transaction transaction=new Transaction(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(4), rs.getTimestamp(5));
				transactions.add(transaction);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactions;
	}
	
	public Integer generateRandomId(int n) {
		
	    Random random = new Random();
		int min = (int) Math.pow(10,n-1);   // let 1000
		int max = (int)(Math.pow(10,n)-1);  // let 9999 == 10000-1
		int num = random.nextInt((max-min)+1)+min;
		return num;
	}


	

}
