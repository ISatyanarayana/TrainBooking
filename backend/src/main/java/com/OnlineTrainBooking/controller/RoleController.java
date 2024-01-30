package com.OnlineTrainBooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.OnlineTrainBooking.entity.Role;
import com.OnlineTrainBooking.service.impl.RoleServiceImpl;

@Controller
public class RoleController {

	
	@Autowired
	private RoleServiceImpl roleServiceImpl;
	
	@PostMapping("/createNewRole")
	public Role createNewRole(@RequestBody  Role role) {
		return roleServiceImpl.createNewRole(role);
	}
}
