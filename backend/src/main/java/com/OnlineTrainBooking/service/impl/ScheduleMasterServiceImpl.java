package com.OnlineTrainBooking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.OnlineTrainBooking.entity.ScheduleMaster;
import com.OnlineTrainBooking.entity.TrainMaster;
import com.OnlineTrainBooking.exceptions.ResourceNotFoundException;
import com.OnlineTrainBooking.exceptions.TrainAlreadyExistsException;
import com.OnlineTrainBooking.repository.ScheduleMasterRepository;
import com.OnlineTrainBooking.service.ScheduleMasterService;

@Service
public class ScheduleMasterServiceImpl implements ScheduleMasterService {

	@Autowired
	private ScheduleMasterRepository masterRepository;
	
	@Override
	public ScheduleMaster addSchedule(ScheduleMaster master) throws Exception {
		Optional<ScheduleMaster> findById = masterRepository.findById(master.getId());
		if(findById.isEmpty())
		{
			return masterRepository.save(master);
		}
		else
			throw new Exception("Id already exists");
	}

	@Override
	public List<ScheduleMaster> getSchedule() throws ResourceNotFoundException {
		List<ScheduleMaster> findAll = masterRepository.findAll();
		if(findAll.isEmpty()) 
			throw new ResourceNotFoundException("Schdule List not found");
		else
			return findAll;
	}
	
	
}
