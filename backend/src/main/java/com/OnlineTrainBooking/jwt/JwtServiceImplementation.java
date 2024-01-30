package com.OnlineTrainBooking.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.OnlineTrainBooking.entity.User;
import com.OnlineTrainBooking.repository.UserRepository;

@Service
public class JwtServiceImplementation implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
		String username = jwtRequest.getUsername();
		String password = jwtRequest.getPassword();
		authenticate(username, password);

		UserDetails userDetails = loadUserByUsername(username);
		String newGeneratedToken = jwtUtil.generateToken(userDetails);

		User user = userRepository.findById(username).get();
		return new JwtResponse(user, newGeneratedToken);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findById(username).get();

		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					user.getAuthorities());
		} else {
			throw new UsernameNotFoundException("User Not found with Username :" + user);
		}
	}
}
//@Service
//public class JwtServiceImplementation implements UserDetailsService {
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<User> user = userRepository.findById(username);
//		if(user.isPresent()) {
//		if (user == null) {
//			throw new UsernameNotFoundException("User Not Found");
//		} 
//		return user;
//		}
//	}
//}
