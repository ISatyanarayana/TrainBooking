package com.OnlineTrainBooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.OnlineTrainBooking.entity.StationMaster;
import com.OnlineTrainBooking.exceptions.ResourceNotFoundException;
import com.OnlineTrainBooking.service.StationMasterService;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
public class StationMasterController {

	@Autowired
	private StationMasterService masterService;

	@PreAuthorize("hasRole('Admin')")
	@PostMapping("/addstations")
	public StationMaster addStations(@RequestBody StationMaster master) {

		return 	masterService.addStations(master);
			
	}

	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/getStations")
	public List<StationMaster> getStations() throws ResourceNotFoundException{
		return masterService.getStations();
	}
}
