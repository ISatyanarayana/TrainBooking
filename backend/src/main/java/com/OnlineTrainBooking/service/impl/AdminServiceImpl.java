package com.OnlineTrainBooking.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.OnlineTrainBooking.entity.Train;
import com.OnlineTrainBooking.entity.User;
import com.OnlineTrainBooking.exceptions.ResourceNotFoundException;
import com.OnlineTrainBooking.exceptions.TrainAlreadyExistsException;
import com.OnlineTrainBooking.repository.RoleRepository;
import com.OnlineTrainBooking.repository.TrainRepository;
import com.OnlineTrainBooking.repository.UserRepository;
import com.OnlineTrainBooking.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private TrainRepository trepository;
	
	@Autowired
	private UserRepository urepository;
	
	@Autowired
	private RoleRepository repository;
	
	@Override
	public List<Train> getallTrains() throws ResourceNotFoundException {
		List<Train> trains = trepository.findAll();
		if(trains.isEmpty()) {
			throw new ResourceNotFoundException("Trains Not found!!");
		}
		else
			return trains;
	}

	@Override
	public List<User> getallUsers() throws ResourceNotFoundException{
		
		  return urepository.findByRole_RolenameIs("User");
	}
	
	@Override
	public Train deleteTrain(int no) {
		trepository.deleteById(no);
		return trepository.findById(no).get();
	}

	@Override
	public Train updateTrain(int w,Train  train) throws ResourceNotFoundException {
		Train utrain= trepository.findById(w).orElseThrow(() -> 
		new ResourceNotFoundException("Train not found with id:"+w));
		utrain.setArrival(train.getArrival());
		utrain.setDeparture(train.getDeparture());
		utrain.setFromplace(train.getFromplace());
		utrain.setToplace(train.getToplace());
		utrain.setPrice(train.getPrice());
		utrain.setSchedule(train.getSchedule());
		return trepository.save(utrain);
	}

	@Override
	public Train addTrain(Train train) throws TrainAlreadyExistsException {
			Optional<Train> findById = trepository.findById(train.getTno());
			if(findById.isEmpty())
					return trepository.save(train);
			else
				throw new TrainAlreadyExistsException("Train already exists with id"+train.getTno());
	}
	
	@Override
	public User deactivateUser(String name) throws Exception {
		
		User deauser = urepository.findById(name).orElseThrow(() ->  
		new ResourceNotFoundException("User not found with name:"+name));
		deauser.setVerified(false);
		urepository.save(deauser);
		return deauser;
	}
}
