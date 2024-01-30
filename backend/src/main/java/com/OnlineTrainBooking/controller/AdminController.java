package com.OnlineTrainBooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OnlineTrainBooking.entity.Train;
import com.OnlineTrainBooking.entity.User;
import com.OnlineTrainBooking.exceptions.ResourceNotFoundException;
import com.OnlineTrainBooking.exceptions.TrainAlreadyExistsException;
import com.OnlineTrainBooking.service.AdminService;

@RestController
@RequestMapping("/admin")
//@CrossOrigin(origins="http://localhost:4200/")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/getalltrains")
	public List<Train> getallTrains() throws ResourceNotFoundException{
		
		return adminService.getallTrains();
	}
	
	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/getallusers")
	public List<User> getallUsers() throws ResourceNotFoundException{
		
		return adminService.getallUsers();
	}
	
	@PreAuthorize("hasRole('Admin')")
	@DeleteMapping("/deletetrain/{no}")
	public Train deleteTrain(@PathVariable int no) {
		return adminService.deleteTrain(no);
	}
	
	@PreAuthorize("hasRole('Admin')")
	@PutMapping("/updateTrain/{w}")
	public Train updateTrain(@PathVariable int w, @RequestBody Train t ) throws ResourceNotFoundException{
	
		return adminService.updateTrain(w,t);
	}
	
	@PreAuthorize("hasRole('Admin')")
	@PostMapping("/addtrain")
	public Train addTrain(@RequestBody Train train) throws TrainAlreadyExistsException{
		
		return adminService.addTrain(train);
	}
	
	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/deactivate/{name}")
	public User deactivateUser(@PathVariable String name) throws Exception {
		
		return adminService.deactivateUser(name);
	}
}
