-- SQL Commands for db:
create database bank_db;
use bank_db;

create table Users(
	userId int auto_increment primary key,
    userUpi varchar(50) not null unique,
    userAccountNo varchar(50) not null unique,
    userName varchar(50) not null,
    userPassword varchar(50) not null,
    userPin varchar(50) not null, 
    userEmail varchar(50) not null unique,
    userNumber varchar(50) not null unique, 
    balance double(10,2) not null default 0.0
);

create table Transactions(
	transactionId int auto_increment primary key,
    userId int,
    amount decimal(10,2) not null,
    transactionType varchar(50) not null,
    transTimestamp timestamp default current_timestamp,
    foreign key(userId) references Users(userId)
);

ALTER TABLE Users ADD CONSTRAINT userEmail UNIQUE(userEmail);
show databases;
show tables;
Select * from Users where userName="jhgg" AND userPassword="nmnm";

ALTER TABLE Users MODIFY COLUMN balance DOUBLE;


select * from Users;
delete from Users where transactionType='Received from user 1';

select * from Transactions;
delete 
from Transactions where userId=2;

INSERT INTO Users (userUpi, userAccountNo, userName, userPassword, userPin, userEmail, userNumber, balance) 
VALUES ('user123@upi', '1234567890', 'John Doe', 'password123', '1234', 'john.doe@example.com', '9876543210', 1000.00);


insert into Users(userName, userPassword, userEmail, balance) values("Rohan Rana", "rohan@123", "rohan@email.com", 42000);

Select * from Users where userNumber='check' AND userPassword='check';

drop table  Users;
drop table Transactions;

