package com.OnlineTrainBooking.service;

import java.util.List;

import com.OnlineTrainBooking.entity.Train;
import com.OnlineTrainBooking.entity.TransactionDetails;
import com.OnlineTrainBooking.entity.User;
import com.OnlineTrainBooking.exceptions.ResourceNotFoundException;
import com.razorpay.RazorpayException;

import jakarta.mail.MessagingException;

public interface UserService {
	
	String intRoleAndUser();
	
	public User addUser(User user) throws Exception;
	
	public List<User> getallUsers();
	
	public List<Train> getTrain(String fromplace,String toplace) throws ResourceNotFoundException;
	
	public Train gettrainbyid(int tno) throws ResourceNotFoundException;
	
	public TransactionDetails createTransaction(double amount) throws RazorpayException;
	
	public User verifyAccount(String email,String otp) throws Exception;
	
	public String regenerateOtp(String mail) throws Exception;
	
	public User getUser(String name) throws ResourceNotFoundException;
	
	public User checkMail(String mail) throws ResourceNotFoundException, MessagingException, Exception;
	
	public User addPassword(String email,String pass) throws Exception;
}