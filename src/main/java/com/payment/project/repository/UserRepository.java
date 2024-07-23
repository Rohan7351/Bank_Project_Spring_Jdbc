package com.payment.project.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.payment.project.entities.Transaction;
import com.payment.project.entities.User;
import com.payment.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class UserRepository implements UserService {
    private  static  JdbcTemplate jdbcTemplate;
	private  DataSource dataSource;

	@Autowired
	public void setDataSource(DataSource dataSource) {	
		jdbcTemplate = new JdbcTemplate(dataSource);
	}



	public UserRepository() {
		
	}


	public User getUser(int userId) {
		
			String query="Select * from Users where userId=?";
			
			return jdbcTemplate.queryForObject(query, new UserRowMapper(), userId);

	}


	
	public User getUserByUpi(String upiId) {
		
		String query="Select * from Users where userUpi=?";
			
			 return jdbcTemplate.queryForObject(query, new UserRowMapper(), upiId);
		
	}
	
	
	public User getUserByAccountNo(String accountNumber) {
	
			String query="Select * from Users where userAccountNo=?";
			return jdbcTemplate.queryForObject(query, new UserRowMapper(), accountNumber);

	}
	
	public void addUser(String userName, String userPassword, String userEmail,String userNumber) {
		
//		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        String random = generateRandomId(6).toString();
        String upiId = userNumber + "@bank";
        String accountNo = random;
        String userPin = generateRandomId(4).toString();

        String query="INSERT INTO Users (userUpi, userAccountNo, userName, userPassword, userPin, userEmail, userNumber) VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(query,upiId,accountNo,userName,userPassword,userPin,userEmail,userNumber);

        System.out.println("Account Created Successfully...!!\n");
        System.out.println("***User Details*** \n ");
        System.out.println("User UPI Id : "+upiId);
        System.out.println("Your Account No is : "+accountNo);
        System.out.println("Your pin is : "+userPin+"\n");
    }

	
	public User authenticateUser(String userNumber, String userPassword) {
	
			String query="Select * from Users where userNumber=? AND userPassword=?";
			User user = null;
			try {
				user = jdbcTemplate.queryForObject(query, new UserRowMapper(), userNumber,userPassword );
				System.out.println("User is authenticated..!!");
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return user;
	}
	
	public void updateUserBalance(int userId, double newBalance) {
		String query="update Users set balance=? where userId=?";
//	
		jdbcTemplate.update(query, newBalance,userId );
	}

	public void changeUserPin(User user,String pin) {
		String query="update Users set userPin=? where userId=?";

		jdbcTemplate.update(query, pin, user.getUserId());
		
	}

	
	public void addTransaction(Transaction transaction) {
		try {
			String query="insert into Transactions(userId, amount, transactionType) values(?, ?, ?)";
			jdbcTemplate.update(query, transaction.getUserId(), transaction.getAmount(),transaction.getTransactionType());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public List<Transaction> getTransactionHistory(int userId){
		List<Transaction> transactions=new ArrayList<>();
		
			String query="select * from Transactions where userId=? order by transTimestamp desc";
			
			return jdbcTemplate.query(query, new TransactionRowMapper(), userId);
			  
	
	}
	
	public Integer generateRandomId(int n) {
		
	    Random random = new Random();
		int min = (int) Math.pow(10,n-1);   // let 1000
		int max = (int)(Math.pow(10,n)-1);  // let 9999 == 10000-1
		int num = random.nextInt((max-min)+1)+min;
		return num;
	}


	

}

class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//    	int userId, String upiId, String accountNo, String userName, String userPassword, String userPin,String userEmail, String userNumber, double balance
        User user = new User(rs.getInt("userId"),rs.getString("userUpi"),
        		rs.getString("userAccountNo"),
        		rs.getString("userName"),
        		rs.getString("userPassword"),
        		rs.getString("userPin"),
        		rs.getString("userEmail"),
                rs.getString("userNumber"),
                rs.getDouble("balance"));
        
        return user;
    }
}

class TransactionRowMapper implements RowMapper<Transaction> {
    @Override
    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
//  int transactionId, int userId, double amount, String transactionType, Timestamp transTimestamp
        Transaction transaction = new Transaction( rs.getInt("transactionId"),
        	       rs.getInt("userId"),
        	       rs.getDouble("amount"),
        	       rs.getString("transactionType"),
        	       rs.getTimestamp("transTimestamp"));
      
        return transaction;
    }
}
