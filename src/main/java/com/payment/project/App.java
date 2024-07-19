package com.payment.project;

import java.util.List;
import java.util.Scanner;

import com.payment.project.entities.Transaction;
import com.payment.project.entities.User;
import com.payment.project.repository.UserRepository;

public class App
{
	private UserRepository userRepo;
	private User currentUser;
	
	public App() {
		userRepo=new UserRepository();
	}
	
	public void showMenu() {
		Scanner sc=new Scanner(System.in);
		
		boolean exit=true;
		
		while(exit) {
			if(currentUser==null) {
				System.out.println("1. Register new user");
				System.out.println("2. Login");
				System.out.println("3. Exit");
				System.out.println("Select an option:");
				int choice=sc.nextInt();sc.nextLine();
				
				switch(choice) {
					case 1:
						createAccount();
						break;
					case 2:
						login();
						break;
					case 3:
						exit=false;
						System.out.println("Exited Sucessfully");
						break;
					default:
						System.out.println("Invalid option....Try again !\n");
				}
			} else {
				System.out.println("1. Show balance");
				System.out.println("2. Deposit Money");
				System.out.println("3. Withdraw Money");
				System.out.println("4. Transfer Money to other User ");
				System.out.println("5. Print transaction history");
				System.out.println("6. Logout");
				System.out.println("7. Exit");
				System.out.println("Select an option:");
				
				int choice=sc.nextInt();sc.nextLine();
				
				switch(choice) {
				case 1:
					showBalance();
					break;
				case 2:
					depositMoney();
					break;
				case 3:
					withdrawMoney();
					break;
				case 4:
					fundTransfer();
					break;
				case 5:
					printTransactionHistory();
					break;
				case 6:
					logout();
					break;
				case 7:
					exit=false;
					System.out.println("Exited Sucessfully");
					break;
				default:
					System.out.println("Invalid option....Try again !\n");
			}
			}
		}
	}
	
	private void createAccount() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter user name: ");
		String userName=sc.nextLine();
		System.out.println("Enter password: ");
		String userPassword=sc.nextLine();
		System.out.println("Enter user email: ");
		String userEmail=sc.nextLine();
		userRepo.addUser(userName, userPassword, userEmail);
		System.out.println("Account Created Successfully...!!\n");
	}
	
	private void login() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter user name: ");
		String userName=sc.nextLine();
		System.out.println("Enter password: ");
		String userPassword=sc.nextLine();
		
		currentUser=userRepo.authenticateUser(userName, userPassword);
		if(currentUser!=null) {
			System.out.println("Login Successfull\n");
		} else {
			System.out.println("Invalid user name or password\n");
		}
	}
	
	private void logout() {
		currentUser=null;
		System.out.println("Logged out...!!\n");
	}
	
	private void showBalance() {
		if(currentUser==null) {
			System.out.println("Please login first.");
			return;
		}
		System.out.println("Current balance : " + currentUser.getBalance()+"\n");
	}
	
	public void depositMoney() {
		if(currentUser==null) {
			System.out.println("Please login first.");
			return;
		}
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter amount to deposit : ");
		double amount=sc.nextDouble();
		currentUser.setBalance(currentUser.getBalance()+amount);
		
		userRepo.updateUserBalance(currentUser.getUserId(), currentUser.getBalance());
		
		Transaction transaction=new Transaction();
		transaction.setUserId(currentUser.getUserId());
		transaction.setAmount(amount);
		transaction.setTransactionType("Deposit");
		
		userRepo.addTransaction(transaction);
		System.out.println("Money Deposited Successfully..!!");
	}
	
	public void withdrawMoney() {
		if(currentUser==null) {
			System.out.println("Please login first.");
			return;
		}
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter amount to withdraw : ");
		double amount=sc.nextDouble();
		
		if(currentUser.getBalance()<amount) {
			System.out.println("Insufficient balance.");
			return;
		}
		currentUser.setBalance(currentUser.getBalance()-amount);
		
		userRepo.updateUserBalance(currentUser.getUserId(), currentUser.getBalance());
		
		Transaction transaction=new Transaction();
		transaction.setUserId(currentUser.getUserId());
		transaction.setAmount(amount);
		transaction.setTransactionType("Withdraw");
		
		userRepo.addTransaction(transaction);
		System.out.println("Money Withdrawl Successfull..!!");
	}
	
	private void fundTransfer() {
		if(currentUser==null) {
			System.out.println("Please login first.");
			return;
		}
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter recipient's user Id : ");
		int recipientUserId=sc.nextInt();
		System.out.println("Enter amount to transfer : ");
		double amount=sc.nextDouble();
		
		
		
		if(currentUser.getBalance()<amount) {
			System.out.println("Insufficient balance.");
			return;
		}
		
		User recipientUser=userRepo.getUser(recipientUserId);
		if(recipientUser==null) {
			System.out.println("Recipient User not found");
			return;
		}
		
		if(recipientUser.getUserId()==currentUser.getUserId()) {
			System.out.println("Fund transer not allowed to self account...Please try any other..!");
			return;
		}
		
		currentUser.setBalance(currentUser.getBalance()-amount);
		userRepo.updateUserBalance(currentUser.getUserId(), currentUser.getBalance());
		
		recipientUser.setBalance(recipientUser.getBalance()+amount);
		userRepo.updateUserBalance(recipientUser.getUserId(), recipientUser.getBalance());
		
		Transaction transaction=new Transaction();
		transaction.setUserId(currentUser.getUserId());
		transaction.setAmount(amount);
		transaction.setTransactionType("Transfer to user "+recipientUserId);
		userRepo.addTransaction(transaction);
		
		Transaction recipientTransaction=new Transaction();
		recipientTransaction.setUserId(recipientUserId);
		recipientTransaction.setAmount(amount);
		recipientTransaction.setTransactionType("Received from user "+currentUser.getUserId());
		userRepo.addTransaction(recipientTransaction);
		
		System.out.println("Money transferred Sucessfully...!");
	}
	
	
	public void printTransactionHistory() {
		if(currentUser==null) {
			System.out.println("Please login first.");
			return;
		}
		List<Transaction> transactions=userRepo.getTransactionHistory(currentUser.getUserId());
		System.out.println("Transaction History : ");
		
		for(Transaction t:transactions) {
			System.out.println(t.getTransTimestamp()+" -- "+t.getTransactionType()+": "+t.getAmount());
		}
	}
	
	
	
    public static void main( String[] args )
    {
    	App app=new App();
    	System.out.println(" Payment System       \n");
    	app.showMenu();        
        
    }
}
	
