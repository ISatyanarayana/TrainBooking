package com.OnlineTrainBooking.service;

import java.util.List;

import com.OnlineTrainBooking.entity.ScheduleMaster;
import com.OnlineTrainBooking.exceptions.ResourceNotFoundException;

public interface ScheduleMasterService {
	
	public ScheduleMaster addSchedule(ScheduleMaster master) throws Exception;
	
	public List<ScheduleMaster> getSchedule() throws ResourceNotFoundException;
	

}
