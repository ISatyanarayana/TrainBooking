package com.OnlineTrainBooking.EmailConfig;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfiguration {

	@Value("${spring.mail.host}")
	private String mailHost;
	
	@Value("${spring.mail.port}")
	private String mailPort;
	
	@Value("${spring.mail.username}")
	private String mailUsername;
	
	@Value("${spring.mail.password}")
	private String mailPassword;
	
	@Bean
	JavaMailSender getjavaMailSender() {
		
		JavaMailSenderImpl impl=new JavaMailSenderImpl();
		impl.setHost(mailHost);
		impl.setPort(Integer.parseInt(mailPort));
		impl.setUsername(mailUsername);
		impl.setPassword(mailPassword);
		
		Properties properties = impl.getJavaMailProperties();
		properties.put("mail.smtp.starttls.enable", "true");
		return impl;
	}
}
