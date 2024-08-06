package com.other.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.other.app.dto.MessageDTO;
import com.other.app.entity.Message;
import com.other.app.repository.MessageRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class MessageService {

	private final MessageRepository messageRepository;
	
	public void deleteMessage(long id) {
		messageRepository.deleteById(id);
	}
	
	public List<Message> readMessages() {
		return messageRepository.findAll();
	}
	
	public void writeMessage(MessageDTO messageDTO) {
		Message message = new Message();
		message.setText(messageDTO.getText());
		messageRepository.save(message);
	}
}

