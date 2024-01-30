package com.OnlineTrainBooking.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.OnlineTrainBooking.entity.Booking;
import com.OnlineTrainBooking.exceptions.ResourceNotFoundException;
import com.OnlineTrainBooking.service.BookingService;

import net.sf.jasperreports.engine.JRException;

@RestController
//@CrossOrigin(origins="http://localhost:4200/")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@PreAuthorize("hasRole('User')")
	@PostMapping("/addBooking")
	public Booking addBooking(@RequestBody Booking b) {
		
		return bookingService.addBooking(b);
	}
	
	@PreAuthorize("hasRole('User')")
	@GetMapping("/printTicket/{id}")
	public StreamingResponseBody exportReport(@PathVariable String id)throws FileNotFoundException, JRException, ResourceNotFoundException{
		        System.out.println("printticket");
		        return bookingService.exportReport(id); 
	}
	
	@PreAuthorize("hasRole('User')")
	@GetMapping("/bookings/{username}")
	public List<Booking> bookingsForUser(@PathVariable String username) throws ResourceNotFoundException {
		
		return bookingService.bookingsForUser(username);
	}
	
	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/bookings")
	public List<Booking> bookings(){
		
		return bookingService.bookings();
	}
	
	@PreAuthorize("hasRole('Admin')")
	@DeleteMapping("deletebooking/{id}")
	public Booking deleteBooking(@PathVariable  long id) throws ResourceNotFoundException {
			
		return bookingService.deleteBooking(id);
	}
}
