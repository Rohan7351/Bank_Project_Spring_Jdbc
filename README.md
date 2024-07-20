-- SQL Commands for db:

create database wallet_db;
use wallet_db;

create table Users(
	userId int auto_increment primary key,
    userName varchar(50) not null,
    userPassword varchar(50) not null,
    userEmail varchar(50) not null unique,
    balance decimal(10,2) not null default 0.0
);

ALTER TABLE Users ADD CONSTRAINT userEmail UNIQUE(userEmail);

create table Transactions(
	transactionId int auto_increment primary key,
    userId int,
    amount decimal(10,2) not null,
    transactionType varchar(50) not null,
    transTimestamp timestamp default current_timestamp,
    foreign key(userId) references Users(userId)
);

show databases;
show tables;

Select * from Users where userName="jhgg" AND userPassword="nmnm";

select * from Users;
delete from Users where userId=2;

select * from Transactions;
delete from Transactions where transactionId=1;

insert into Users(userId, userName, userPassword, userEmail, balance) values(123, "Shobhit Yadav", "shobhit@123", "shobhit@email.com", 37000);
insert into Users(userName, userPassword, userEmail, balance) values("Rohan Rana", "rohan@123", "rohan@email.com", 42000);
