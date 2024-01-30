package com.OnlineTrainBooking.service;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.OnlineTrainBooking.entity.Booking;
import com.OnlineTrainBooking.exceptions.ResourceNotFoundException;

import net.sf.jasperreports.engine.JRException;

public interface BookingService {

		public Booking addBooking(Booking b);
		
		public StreamingResponseBody exportReport(String i) throws FileNotFoundException, JRException, ResourceNotFoundException ;

		public List<Booking> bookingsForUser(String username) throws ResourceNotFoundException;
		
		public List<Booking> bookings();
		
		public Booking deleteBooking(long id) throws ResourceNotFoundException;
}
