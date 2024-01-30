package com.OnlineTrainBooking.entity;


	import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

	@Entity
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public class User implements UserDetails {

		@Id	
		private String username;
		
		private String email;
		
		private String password;
		
		private long aadharno;
		
		private String otp;
		
		private LocalDateTime otpTime;
		
		private boolean verified;

		@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
		@JoinTable(name = "User_Role", joinColumns = { @JoinColumn(name = "User_Id") }, inverseJoinColumns = {@JoinColumn(name = "Role_Id") })
		private Set<Role> role;

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			Set<Authority> authorities = new HashSet<>();
			 this.role.forEach(userRole->{
				 authorities.add(new Authority(userRole.getRolename()));
			 });
		        return authorities;
		}
		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return true;
		}
		
	}
