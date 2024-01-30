package com.OnlineTrainBooking.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.OnlineTrainBooking.entity.User;
import com.OnlineTrainBooking.repository.UserRepository;

@RestController
public class JwtController {
	
	@Autowired
	private JwtServiceImplementation jwtServiceImplementation;
	
	@PostMapping("/authenticate")
	public JwtResponse generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		
		return jwtServiceImplementation.createJwtToken(jwtRequest);
	}
//
//	@Autowired
//	private JwtServiceImplementation jwtServiceImplementation;
//	
//	@Autowired
//	private AuthenticationManager authenticationManager;
//	
//	@Autowired(required = true)
//	private JwtUtil jwtUtil;
//	
//	@Autowired
//	private UserRepository repository;
//	
//	@PostMapping("/authenticate")
//	public JwtResponse generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
//
//		try {
//
//			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
//
//		} catch (Exception e) {
//			throw new Exception(e);
//		}
//
//		UserDetails userDetails = this.jwtServiceImplementation.loadUserByUsername(jwtRequest.getUsername());
//		String generateToken = this.jwtUtil.generateToken(userDetails);
//
//		User user=repository.findById(jwtRequest.getUsername()).get();
//        return new JwtResponse(user,generateToken);
//
//	}
//	
//	private void authenticate(String username, String password) throws Exception {
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//		} catch (DisabledException e) {
//			throw new Exception("USER_DISABLED", e);
//		} catch (BadCredentialsException e) {
//			throw new Exception("INVALID_CREDENTIALS", e);
//		}
//	}
//	
//	@GetMapping("/current-user")
//	public ResponseEntity<User> getCurrentUser(@RequestBody Principal principal){
//		UserDetails details = jwtServiceImplementation.loadUserByUsername(principal.getName());
//		User user = this.repository.findById(details.getUsername()).get();
//		return ResponseEntity.ok(user);
//	}
	
}
