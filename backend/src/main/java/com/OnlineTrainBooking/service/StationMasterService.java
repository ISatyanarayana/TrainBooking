package com.OnlineTrainBooking.service;

import java.util.List;

import com.OnlineTrainBooking.entity.StationMaster;
import com.OnlineTrainBooking.exceptions.ResourceNotFoundException;

public interface StationMasterService {

	public StationMaster addStations(StationMaster master) ;
	public List<StationMaster> getStations() throws ResourceNotFoundException;
}
