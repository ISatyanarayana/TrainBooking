package com.OnlineTrainBooking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlineTrainBooking.entity.Role;
import com.OnlineTrainBooking.repository.RoleRepository;

@Service
public class RoleServiceImpl {
	
	@Autowired
	private RoleRepository repository;
	
	public Role createNewRole(Role role) {
		
		return repository.save(role);
	}
			
}
