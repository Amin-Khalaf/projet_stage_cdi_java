package com.umanteam.dadakar.msg.back.service.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.msg.back.dto.MessageDTO;
import com.umanteam.dadakar.msg.back.entities.Message;
import com.umanteam.dadakar.msg.back.repository.MessageRepository;
import com.umanteam.dadakar.msg.back.service.interfaces.IMessageService;

@Service("messageService")
public class MessageService implements IMessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	/* copy from messageDTO to Message */
	private Message messageDTOToMessage(MessageDTO messageDTO) {
		Message message = new Message();
		BeanUtils.copyProperties(messageDTO, message);
		return message;
	}
	
	/* copy from Message to MessageDTO */
	private MessageDTO messageToMessageDTO(Message message) {
		MessageDTO messageDTO = new MessageDTO();
		BeanUtils.copyProperties(message, messageDTO);
		return messageDTO;
	}

	@Override
	public MessageDTO addOrUpdate(MessageDTO messageDTO) {
		return messageToMessageDTO(messageRepository.save(messageDTOToMessage(messageDTO)));
	}

	@Override
	public void delete(String id) {
		messageRepository.delete(id);
	}

	@Override
	public List<MessageDTO> findAll() {
		List<MessageDTO> messageDTOs = new ArrayList<>();
		List<Message> messages = messageRepository.findAll();
		if(messages != null) for (Message message: messages) messageDTOs.add(messageToMessageDTO(message));
		return messageDTOs;
	}

	@Override
	public MessageDTO findById(String id) {
		Message message = messageRepository.findOne(id);
		if(message != null) return messageToMessageDTO(message);
		return new MessageDTO();
	}

	@Override
	public List<MessageDTO> findByHoroLessThanEqual(LocalDateTime horoEnd) {
		List<MessageDTO> messageDTOs = new ArrayList<>();
		List<Message> messages = messageRepository.findByHoroLessThanEqual(horoEnd);
		if(messages != null) for (Message message: messages) messageDTOs.add(messageToMessageDTO(message));
		return messageDTOs;
	}
	
	@Override
	public List<MessageDTO> findByHoroGreaterThanEqual(LocalDateTime horoStart) {
		List<MessageDTO> messageDTOs = new ArrayList<>();
		List<Message> messages = messageRepository.findByHoroGreaterThanEqual(horoStart);
		if(messages != null) for (Message message: messages) messageDTOs.add(messageToMessageDTO(message));
		return messageDTOs;
	}
	
	@Override
	public List<MessageDTO> findByHoroBetween(LocalDateTime horoStart, LocalDateTime horoEnd) {
		List<MessageDTO> messageDTOs = new ArrayList<>();
		List<Message> messages = messageRepository.findByHoroBetween(horoStart, horoEnd);
		if(messages != null) for (Message message: messages) messageDTOs.add(messageToMessageDTO(message));
		return messageDTOs;
	}

	@Override
	public List<MessageDTO> findBySenderId(String sid) {
		List<MessageDTO> messageDTOs = new ArrayList<>();
		List<Message> messages = messageRepository.findBySenderId(sid);
		if(messages != null) for (Message message: messages) messageDTOs.add(messageToMessageDTO(message));
		return messageDTOs;
	}

	@Override
	public List<MessageDTO> findBySenderIdAndHoroLessThanEqual(String sid, LocalDateTime horoEnd) {
		List<MessageDTO> messageDTOs = new ArrayList<>();
		List<Message> messages = messageRepository.findBySenderIdAndHoroLessThanEqual(sid, horoEnd);
		if(messages != null) for (Message message: messages) messageDTOs.add(messageToMessageDTO(message));
		return messageDTOs;
	}

	@Override
	public List<MessageDTO> findBySenderIdAndHoroGreaterThanEqual(String sid, LocalDateTime horoStart) {
		List<MessageDTO> messageDTOs = new ArrayList<>();
		List<Message> messages = messageRepository.findBySenderIdAndHoroGreaterThanEqual(sid, horoStart);
		if(messages != null) for (Message message: messages) messageDTOs.add(messageToMessageDTO(message));
		return messageDTOs;
	}

	@Override
	public List<MessageDTO> findBySenderIdAndHoroBetween(String sid, LocalDateTime horoStart, LocalDateTime horoEnd) {
		List<MessageDTO> messageDTOs = new ArrayList<>();
		List<Message> messages = messageRepository.findBySenderIdAndHoroBetween(sid, horoStart, horoEnd);
		if(messages != null) for (Message message: messages) messageDTOs.add(messageToMessageDTO(message));
		return messageDTOs;
	}

	@Override
	public List<MessageDTO> findByReceiverId(String rid) {
		List<MessageDTO> messageDTOs = new ArrayList<>();
		List<Message> messages = messageRepository.findByReceiverId(rid);
		if(messages != null) for (Message message: messages) messageDTOs.add(messageToMessageDTO(message));
		return messageDTOs;
	}

	@Override
	public List<MessageDTO> findByReceiverIdAndHoroLessThanEqual(String rid, LocalDateTime horoEnd) {
		List<MessageDTO> messageDTOs = new ArrayList<>();
		List<Message> messages = messageRepository.findByReceiverIdAndHoroLessThanEqual(rid, horoEnd);
		if(messages != null) for (Message message: messages) messageDTOs.add(messageToMessageDTO(message));
		return messageDTOs;
	}

	@Override
	public List<MessageDTO> findByReceiverIdAndHoroGreaterThanEqual(String rid, LocalDateTime horoStart) {
		List<MessageDTO> messageDTOs = new ArrayList<>();
		List<Message> messages = messageRepository.findByReceiverIdAndHoroGreaterThanEqual(rid, horoStart);
		if(messages != null) for (Message message: messages) messageDTOs.add(messageToMessageDTO(message));
		return messageDTOs;
	}

	@Override
	public List<MessageDTO> findByReceiverIdAndHoroBetween(String rid, LocalDateTime horoStart, LocalDateTime horoEnd) {
		List<MessageDTO> messageDTOs = new ArrayList<>();
		List<Message> messages = messageRepository.findByReceiverIdAndHoroBetween(rid, horoStart, horoEnd);
		if(messages != null) for (Message message: messages) messageDTOs.add(messageToMessageDTO(message));
		return messageDTOs;
	}

	@Override
	public List<MessageDTO> findBySenderIdAndReceiverId(String sid, String rid) {
		List<MessageDTO> messageDTOs = new ArrayList<>();
		List<Message> messages = messageRepository.findBySenderIdAndReceiverId(sid, rid);
		if(messages != null) for (Message message: messages) messageDTOs.add(messageToMessageDTO(message));
		return messageDTOs;
	}

	@Override
	public List<MessageDTO> findBySenderIdAndReceiverIdAndHoroLessThanEqual(String sid, String rid,
			LocalDateTime horoEnd) {
		List<MessageDTO> messageDTOs = new ArrayList<>();
		List<Message> messages = messageRepository.findBySenderIdAndReceiverIdAndHoroLessThanEqual(sid, rid, horoEnd);
		if(messages != null) for (Message message: messages) messageDTOs.add(messageToMessageDTO(message));
		return messageDTOs;
	}

	@Override
	public List<MessageDTO> findBySenderIdAndReceiverIdAndHoroGreaterThanEqual(String sid, String rid,
			LocalDateTime horoStart) {
		List<MessageDTO> messageDTOs = new ArrayList<>();
		List<Message> messages = messageRepository.findBySenderIdAndReceiverIdAndHoroGreaterThanEqual(sid, rid, horoStart);
		if(messages != null) for (Message message: messages) messageDTOs.add(messageToMessageDTO(message));
		return messageDTOs;
	}

	@Override
	public List<MessageDTO> findBySenderIdAndReceiverIdAndHoroBetween(String sid, String rid, LocalDateTime horoStart, LocalDateTime horoEnd) {
		List<MessageDTO> messageDTOs = new ArrayList<>();
		List<Message> messages = messageRepository.findBySenderIdAndReceiverIdAndHoroBetween(sid, rid, horoStart, horoEnd);
		if(messages != null) for (Message message: messages) messageDTOs.add(messageToMessageDTO(message));
		return messageDTOs;
	}

}
