package com.OnlineTrainBooking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlineTrainBooking.entity.TrainMaster;
import com.OnlineTrainBooking.exceptions.InvalidCredentials;
import com.OnlineTrainBooking.exceptions.ResourceNotFoundException;
import com.OnlineTrainBooking.exceptions.TrainAlreadyExistsException;
import com.OnlineTrainBooking.repository.TrainMasterRepository;
import com.OnlineTrainBooking.service.TrainMasterService;

@Service
public class TrainMasterServiceImpl implements TrainMasterService{

	@Autowired
	private TrainMasterRepository masterRepository;
	
	@Override
	public TrainMaster addTrain(TrainMaster tmaster) throws TrainAlreadyExistsException {
		
		Optional<TrainMaster> findById = masterRepository.findById(tmaster.getTno());
		if(findById.isPresent()) {
			System.out.println("Details not added");
			throw new TrainAlreadyExistsException("TrainAlreadyExists");
		}
		else {
			
				return 	masterRepository.save(tmaster);
		}
	}

	@Override
	public List<TrainMaster> getTrains() throws ResourceNotFoundException {
		
		List<TrainMaster> findAll = masterRepository.findAll();
		
		if(findAll.isEmpty()) {
			throw new ResourceNotFoundException("Trains Not found");
		}
		else {
			return findAll;
		}
	}

	@Override
	public TrainMaster getTrain(int tno) {
		
			 TrainMaster trainMaster = masterRepository.findById(tno).get();
			return trainMaster;
	}
	
			
		

}
