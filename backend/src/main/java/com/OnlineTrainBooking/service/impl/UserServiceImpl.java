package com.OnlineTrainBooking.service.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.OnlineTrainBooking.Util.Email;
import com.OnlineTrainBooking.Util.Otp;
import com.OnlineTrainBooking.entity.Role;
import com.OnlineTrainBooking.entity.Train;
import com.OnlineTrainBooking.entity.TransactionDetails;
import com.OnlineTrainBooking.entity.User;
import com.OnlineTrainBooking.exceptions.InvalidCredentials;
import com.OnlineTrainBooking.exceptions.ResourceNotFoundException;
import com.OnlineTrainBooking.repository.RoleRepository;
import com.OnlineTrainBooking.repository.TrainRepository;
import com.OnlineTrainBooking.repository.UserRepository;
import com.OnlineTrainBooking.service.UserService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private TrainRepository  repository2;
	
	@Autowired
	private RoleRepository repository3;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private Email email;
	
	@Autowired
	private Otp otp;
	
	public static final String KEY="rzp_test_OkCVNSRH2C81g2";
	public static final String KEY_SECRET="ynNVr9HaIQQWKvXJBp33J5dQ";
	public static final String CURRENCY="INR";
	

	@Override
	public User addUser(User user) throws Exception {
		
		Optional<User> mail = repository.findByEmail(user.getEmail());
		if(mail.isPresent()) {
			throw new Exception("Email Already Present");
		}
		else {
			
			String generateOtp = otp.generateOtp();
			email.sendOtpMail(user.getEmail(), generateOtp);
			Role role = repository3.findById("User").get();
			Set<Role> userRole=new HashSet<>();
			userRole.add(role);
			user.setRole(userRole);
			user.setPassword(getEncodedPassword(user.getPassword()));
			user.setOtp(generateOtp);
			user.setOtpTime(LocalDateTime.now());
			return repository.save(user);
			
		}
	}
	@Override
	public User verifyAccount(String email, String otp) throws Exception {
		
			User emailobject = repository.findByEmail(email).orElseThrow(()-> new Exception("Email Not Found "));
			if(emailobject.getOtp().equals(otp) && 
					Duration.between(emailobject.getOtpTime(), LocalDateTime.now()).getSeconds()<(60)) {
				
				emailobject.setVerified(true);
				return repository.save(emailobject);
			}
			else
				throw new InvalidCredentials("Otp is not Correct");
	}

	@Override
	public String regenerateOtp(String mail) throws Exception {
		
		User user1 = repository.findByEmail(mail).orElseThrow(()-> new Exception("Email Not Found "));
		
		String generateOtp = otp.generateOtp();
		email.sendOtpMail(user1.getEmail(), generateOtp);
		user1.setOtp(generateOtp);
		user1.setOtpTime(LocalDateTime.now());
		repository.save(user1);
		
		return "Otp Generated Sucessfully...";
	}

	public String getEncodedPassword(String password) {
		
		return encoder.encode(password);
	}
	
	@Override
	public String intRoleAndUser() {
		
		Role adminRole=new Role();
		adminRole.setRolename("Admin");
		repository3.save(adminRole);
		
		Role userRole =new Role();
		userRole.setRolename("User");
		repository3.save(userRole);
		
		return "Success";
	}

	@Override
	public List<User> getallUsers() {
		
		List<User> findAll = repository.findAll();
		
		return findAll;
	}

	@Override
	public List<Train> getTrain(String fromplace, String toplace) throws ResourceNotFoundException {
		
			List<Train> collect = repository2.findAll().stream().filter(a -> a.getFromplace().equalsIgnoreCase(fromplace) 
					&& a.getToplace().equalsIgnoreCase(toplace)).collect(Collectors.toList());	
			
			if(collect.isEmpty())
				throw new ResourceNotFoundException("Didnt found train specified between the two stations");
			else
				return collect;
	}

	@Override
	public Train gettrainbyid(int tno) throws ResourceNotFoundException {
		Train train = repository2.findById(tno).orElseThrow(()-> 
		new ResourceNotFoundException("Train not found with id:"+tno));
		
		return train;
	}

	@Override
	public TransactionDetails createTransaction(double amount) throws RazorpayException {
		
		JSONObject  jsonObject=new JSONObject();
		jsonObject.put("currency",CURRENCY);
		jsonObject.put("amount", (amount*100));
		
		RazorpayClient client =new RazorpayClient(KEY, KEY_SECRET);
		Order order = client.orders.create(jsonObject);
		return preparetransactionDetails(order);
	}
	
	private TransactionDetails preparetransactionDetails(Order order) {
		
		String orderId=order.get("id");
		int amount=order.get("amount");
		String currency=order.get("currency");
		
		TransactionDetails  details=new TransactionDetails(orderId, amount, currency,KEY);
		
		return details;
	}
	@Override
	public User getUser(String name) throws ResourceNotFoundException {
		
		User user = repository.findById(name).orElseThrow(()-> 
		new ResourceNotFoundException("User Not Found with name:"+name));
		return user;
	}
	
	@Override
	public User checkMail(String mail) throws Exception {
		
		 User user = repository.findByEmail(mail).orElseThrow(()-> new ResourceNotFoundException("Email Is not Registered"));
		
		 if(user.isVerified()) {
			 
			 String otpp=otp.generateOtp();
			 email.sendOtpMail(mail, otpp);
			 user.setOtp(otpp); 
			 user.setEmail(mail);
			 user.setOtpTime(LocalDateTime.now());
			 return repository.save(user);
			 
		 }
		 else
		 {
			 throw new Exception("User is Present But not Verified!!!");
		 }
	}
	@Override
	public User addPassword(String email,String pass) throws Exception{
		
		User user = repository.findByEmail(email).orElseThrow(()-> new Exception("Email Not Found "));
		
		user.setPassword(getEncodedPassword(pass));
		
		return repository.save(user);
	}
}
