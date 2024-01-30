package com.OnlineTrainBooking.Util;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Otp {

		
	public String generateOtp() {
//		Random random=new Random();
//		int nine = random.nextInt(999999);
//		String va = Integer.toString(nine);
//		
//		while(va.length()<6){
//				
//			va="0"+va;
//		}
//		return va;
	    Random random = new Random();
	    StringBuilder otpBuilder = new StringBuilder();

	    for (int i = 0; i < 6; i++) {
	        // Generate a random number between 0 and 1 to decide whether to add a digit or an alphabet
	        int choice = random.nextInt(2);

	        if (choice == 0) {
	            // Add a random digit
	            int digit = random.nextInt(10);
	            otpBuilder.append(digit);
	        } else {
	            // Add a random alphabet (upper case)
	            char alphabet = (char) (random.nextInt(26) + 'A');
	            otpBuilder.append(alphabet);
	        }
	    }

	    return otpBuilder.toString();

	}
}
