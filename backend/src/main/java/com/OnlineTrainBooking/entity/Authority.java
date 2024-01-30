package com.OnlineTrainBooking.entity;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Authority implements GrantedAuthority {
	
	public String auth;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.auth;
	}

}
