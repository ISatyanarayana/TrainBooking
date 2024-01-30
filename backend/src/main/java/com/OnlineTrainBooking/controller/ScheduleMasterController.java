package com.OnlineTrainBooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.OnlineTrainBooking.entity.ScheduleMaster;
import com.OnlineTrainBooking.exceptions.ResourceNotFoundException;
import com.OnlineTrainBooking.service.ScheduleMasterService;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
public class ScheduleMasterController {

	@Autowired
	private ScheduleMasterService masterService;
	
	@PreAuthorize("hasRole('Admin')")
	@PostMapping("/addschedule")
	public ScheduleMaster addSchedule(@RequestBody ScheduleMaster master) throws Exception{
		return masterService.addSchedule(master);
	}
	
	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/getschedule")
	public List<ScheduleMaster> getSchedule() throws ResourceNotFoundException{
		return masterService.getSchedule();
	}
}
