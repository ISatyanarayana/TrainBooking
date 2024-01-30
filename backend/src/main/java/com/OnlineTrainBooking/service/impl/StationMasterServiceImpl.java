package com.OnlineTrainBooking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlineTrainBooking.entity.StationMaster;
import com.OnlineTrainBooking.exceptions.ResourceNotFoundException;
import com.OnlineTrainBooking.repository.StationMasterRepository;
import com.OnlineTrainBooking.service.StationMasterService;

@Service
public class StationMasterServiceImpl implements StationMasterService {

	@Autowired
	private StationMasterRepository masterRepository;
	
	@Override
	public StationMaster addStations(StationMaster master) {
		
		return masterRepository.save(master);
	}

	@Override
	public List<StationMaster> getStations() throws ResourceNotFoundException {
		List<StationMaster> findAll = masterRepository.findAll();
		if(findAll.isEmpty()) {
			throw new ResourceNotFoundException("Stations Not Found");
		}
		else
			return findAll;
	}
	
		
}
