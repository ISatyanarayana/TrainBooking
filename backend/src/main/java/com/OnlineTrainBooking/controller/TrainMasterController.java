package com.OnlineTrainBooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.OnlineTrainBooking.entity.TrainMaster;
import com.OnlineTrainBooking.exceptions.ResourceNotFoundException;
import com.OnlineTrainBooking.exceptions.TrainAlreadyExistsException;
import com.OnlineTrainBooking.service.TrainMasterService;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
public class TrainMasterController {
	
	@Autowired
	private TrainMasterService masterService ;
	

	@PreAuthorize("hasRole('Admin')")
	@PostMapping("/addtrainmaster")
	public TrainMaster addTrain(@RequestBody TrainMaster tmaster) throws TrainAlreadyExistsException {
		
		return masterService.addTrain(tmaster);
	}
	
	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/gettrainmaster")
	public List<TrainMaster> getTrains() throws ResourceNotFoundException{
		
		return masterService.getTrains();
	}
	
	@PreAuthorize("hasRole('Admin')")
	@GetMapping("gettrain/{tno}")
	public TrainMaster getTrain(@PathVariable int tno) {
		System.out.println("gettrain Hitted");
		return masterService.getTrain(tno);
	}
}
