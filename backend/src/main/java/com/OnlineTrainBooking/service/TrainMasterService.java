package com.OnlineTrainBooking.service;

import java.util.List;

import com.OnlineTrainBooking.entity.TrainMaster;
import com.OnlineTrainBooking.exceptions.ResourceNotFoundException;
import com.OnlineTrainBooking.exceptions.TrainAlreadyExistsException;

public interface TrainMasterService  {

	
		public TrainMaster addTrain(TrainMaster tmaster) throws TrainAlreadyExistsException;
		
		public List<TrainMaster> getTrains() throws ResourceNotFoundException;
		
		public TrainMaster getTrain(int tno);
}
