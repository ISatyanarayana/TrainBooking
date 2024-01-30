package com.OnlineTrainBooking.service;

import java.util.List;

import com.OnlineTrainBooking.entity.Train;
import com.OnlineTrainBooking.entity.User;
import com.OnlineTrainBooking.exceptions.ResourceNotFoundException;
import com.OnlineTrainBooking.exceptions.TrainAlreadyExistsException;

public interface AdminService {
		
		public List<Train> getallTrains() throws ResourceNotFoundException;
		
		public List<User> getallUsers() throws ResourceNotFoundException ;
		
		public Train deleteTrain(int no);
		
		public Train updateTrain(int w,Train train) throws ResourceNotFoundException;
		
		public Train addTrain(Train train) throws TrainAlreadyExistsException;
		
		public User deactivateUser(String n) throws ResourceNotFoundException, Exception;
		
}
