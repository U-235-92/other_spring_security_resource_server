package com.other.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.other.app.dto.MessageDTO;
import com.other.app.entity.Message;
import com.other.app.service.MessageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app")
public class MessgeController {

	private final MessageService  messageService;
	
	@ResponseBody
	@DeleteMapping("/delete_message/{id}")
	public String deleteMessage(@PathVariable long id) {
		messageService.deleteMessage(id);
		return String.format("Message with id: %d deletetd", id);
	}
	
	@ResponseBody
	@PostMapping("/write_message")
	public String writeMessage(@RequestBody MessageDTO messageDTO) {
		messageService.writeMessage(messageDTO);
		return "Message wrote";
	}
	
	@ResponseBody
	@GetMapping("/read_messages")
	public List<Message> readMessages() {
		return messageService.readMessages();
	}
}
