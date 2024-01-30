package com.OnlineTrainBooking.service.impl;

import java.awt.print.Book;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.OnlineTrainBooking.entity.Booking;
import com.OnlineTrainBooking.entity.User;
import com.OnlineTrainBooking.exceptions.ResourceNotFoundException;
import com.OnlineTrainBooking.repository.BookingRepository;
import com.OnlineTrainBooking.repository.UserRepository;
import com.OnlineTrainBooking.service.BookingService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	 private UserRepository  repository;
	
	@Override
	public Booking addBooking(Booking booking) {
		
		booking.setDate(LocalDate.now());
		Optional<User> findById = repository.findById(booking.getUsername());
		User user=findById.get();
		booking.setEmail(user.getEmail());
		
		return bookingRepository.save(booking);	
		
		}

	@Override
	public StreamingResponseBody exportReport(String id) throws FileNotFoundException, JRException, ResourceNotFoundException {

		//String path="D:\\Downloads";
			
		Optional<Booking> bookingOptional = bookingRepository.findAll().stream().filter(a-> a.getTransactionId().equals(id)).findFirst();
		
		if (bookingOptional.isPresent()) {
		Booking booking = bookingOptional.get();
		
		
		
		File file=ResourceUtils.getFile("classpath:Train.jrxml");
		JasperReport jasperReport=JasperCompileManager.compileReport(file.getAbsolutePath());
		List list=new ArrayList<Booking>();
		list.add(booking);
		JRBeanCollectionDataSource datasource=new JRBeanCollectionDataSource(list);
		Map<String,Object> map=new HashMap<>();
		map.put("created by", "TrainBooking");
		JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,map ,datasource);
		//JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\ticket.pdf");
		
		return outputStream -> {
		    try {
		        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		    } catch (JRException e) {
		        e.printStackTrace(); // Log the exception or use a logger
		        throw new RuntimeException("Error exporting report to PDF", e);
		    }
		};
		}
		else {
			throw new ResourceNotFoundException("Booking not found for ID: " + id);
		}
	}
	
	@Override
	public List<Booking> bookingsForUser(String username) throws ResourceNotFoundException {
		
		List<Booking> collect = bookingRepository.findAll().stream().filter(a -> a.getUsername().equalsIgnoreCase(username))
				.collect(Collectors.toList());
		
		if(collect.isEmpty()) 
			throw new ResourceNotFoundException("Bookings not found for the user:"+username);
		else
			return collect;
	}
	
	@Override
	public List<Booking> bookings(){
			
		return   bookingRepository.findAll();	
	}

	@Override
	public Booking deleteBooking(long id) throws ResourceNotFoundException {
		
		Booking dbooking = bookingRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Booking Not found for id :"+id));
		
		bookingRepository.delete(dbooking);
		
		return dbooking;
	}
}
