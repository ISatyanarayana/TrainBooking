package com.OnlineTrainBooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.OnlineTrainBooking.entity.Train;
import com.OnlineTrainBooking.entity.TransactionDetails;
import com.OnlineTrainBooking.entity.User;
import com.OnlineTrainBooking.exceptions.InvalidCredentials;
import com.OnlineTrainBooking.exceptions.ResourceNotFoundException;
import com.OnlineTrainBooking.service.UserService;
import com.razorpay.RazorpayException;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;

@RestController
//@CrossOrigin(origins="http://localhost:4200/")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostConstruct
	public void initRoleandUser() {
		service.intRoleAndUser();
	}
		
	@PostMapping("/adduser")
	public User addUser(@RequestBody User user) throws Exception {
		
		return  service.addUser(user);
	}
	
	
	@GetMapping("/verify/{email}/{otp}")
	public User verifyAccount(@PathVariable String email,@PathVariable String otp) throws Exception{
		
		return service.verifyAccount(email, otp);
	}
	
	
	@GetMapping("/regenerateOtp/{mail}")
	public String regenerateOtp(@PathVariable String mail) throws Exception{
		
		return service.regenerateOtp(mail);
	}
	
	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/get-allusers")
	public List<User> getallUsers()
	{
		return service.getallUsers();
	}
	
	@PreAuthorize("hasRole('User')")
	@GetMapping("/getspecific-trains/{fromplace}/{toplace}")
	public List<Train> getTrain(@PathVariable String fromplace,@PathVariable String toplace) throws ResourceNotFoundException{
		
			if(fromplace.isEmpty() || toplace.isEmpty()) 
					
						throw new ResourceNotFoundException("INputs are null");
			else
				return service.getTrain(fromplace, toplace);
		
	}
	
	@PreAuthorize("hasRole('User')")
	@GetMapping("/trainbyid/{tno}")
	public Train gettrainbyid(@PathVariable int tno) throws ResourceNotFoundException, InvalidCredentials{
		if(tno==0) {
			throw new InvalidCredentials("train number is not valid");
		}
		else {
			return service.gettrainbyid(tno);
		}
	}
	
	@PreAuthorize("hasRole('User')")
	@GetMapping("/createtransaction/{amount}")
	public TransactionDetails createTransaction(@PathVariable double amount) throws RazorpayException {
		return service.createTransaction(amount);	
	}
	
	@GetMapping("/getuser/{name}")
	public User getUser(@PathVariable String name) throws ResourceNotFoundException {
		return service.getUser(name);	
	}
	
	@GetMapping("/checkMail/{mail}")
	public User checkMail(@PathVariable String mail) throws ResourceNotFoundException, MessagingException, Exception {
		return service.checkMail(mail);
	}
	
	@GetMapping("/pwd/{email}/{pass}")
	public User addPassword(@PathVariable String email,@PathVariable String pass) throws Exception {
		return service.addPassword(email,pass);
	}
}
