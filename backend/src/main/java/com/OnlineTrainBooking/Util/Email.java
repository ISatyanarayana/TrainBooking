package com.OnlineTrainBooking.Util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.OnlineTrainBooking.entity.User;
import com.OnlineTrainBooking.repository.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component	
public class Email {

		@Autowired
		private JavaMailSender javaMailSender;
		
		@Autowired
		private UserRepository repository;
		
		public void sendOtpMail(String email,String otp) throws MessagingException {
			
				Optional<User> findByEmail = repository.findByEmail(email);
				
				User user = findByEmail.get();
				String username = user.getUsername();
				
				MimeMessage message=javaMailSender.createMimeMessage();
				MimeMessageHelper helper=new MimeMessageHelper(message);
				helper.setTo(email);
				helper.setSubject("Email Verification");
//				String format = String.format("your otp for email verification is: %s", otp);
				String format = String.format("<p><b>This is a system-generated email. Please do not reply to this email ID.</b></p>"
					    + "<p><b>If you have a query or need any clarification, you may:</b></p>"
					    + "<ol>"
					    + "<li><b>Call our 24-hour Customer Care at <strong>100</strong></b></li>"
					    + "<li><b>Email us at <a href='mailto:care@irb.co.in'>care@irb.co.in</a></b></li>"
					    + "</ol>"
					    + "<hr>"
		                + "<p>Dear "+username+",</p>"
		                + "<p>Your One Time Password (OTP) for log in  on Indian Railway Booking is <strong>" + otp + "</strong>.</p>"
		                + "<p>Please note, this OTP is valid only for the mentioned transaction and cannot be used for any other transaction.</p>"
		                + "<p><b>Important:</b> Please do not share this One Time Password with anyone.</p>"
		                + "<p>For any problems, please contact us at our 24*7 Customer Support:</p>"
		                + "<p>Call: 14646 OR 0755-6610661 / 0755-4090600 (Language: Hindi and English)</p>"
		                + "<p>Email: <a href='mailto:care@irb.co.in'>care@irb.co.in</a></p>"
		                + "<p>We solicit your continued patronage to our services.</p>"
		                + "<hr>"
		                + "<p><b>Information:</b> For any enquiries or information regarding your transaction with Indian Railway Booking, do not"
		                + " provide your credit/debit card details by any means to IRB. All your queries"
		                + " can be replied on the basis of 10 digit IRB Transaction id/ PNR no./User id."
		                + " IRB does not store the credit/debit/eWallet Transaction password/OTP card information in any form during the transaction.</p>"
		                + "<hr>"
		                + "<p><b>Warm Regards,</b></p>"
		                + "<p><b>Customer Care</b><br>"
		                + "<b>Internet Ticketing - IRB</b></p>", otp);
				helper.setText(format,true);
				javaMailSender.send(message);
		}
}
