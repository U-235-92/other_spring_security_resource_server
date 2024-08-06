package com.other.app.configuration;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.other.app.entity.Message;
import com.other.app.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppConfiguration {

	private final MessageRepository messageRepository;
	
	@Bean
	protected ApplicationRunner dataLoader() {
		return args -> {
			Message messge1 = new Message();
			messge1.setText("Message 1 text");
			Message messge2 = new Message();
			messge2.setText("Message 2 text");
			messageRepository.save(messge1);
			messageRepository.save(messge2);
		};
	}
}
