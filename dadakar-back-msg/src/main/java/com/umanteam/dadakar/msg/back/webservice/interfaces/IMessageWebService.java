package com.umanteam.dadakar.msg.back.webservice.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.umanteam.dadakar.msg.back.dto.MessageDTO;

public interface IMessageWebService {
	MessageDTO add(MessageDTO messageDTO);
	MessageDTO update(MessageDTO messageDTO);
	void delete(String id);
	ResponseEntity<List<MessageDTO>> findAll();
	ResponseEntity<MessageDTO> findById(String id);
	/* find by date */
	ResponseEntity<List<MessageDTO>> findByHoroLessThanEqual(String horoEnd);
	ResponseEntity<List<MessageDTO>> findByHoroGreaterThanEqual(String horoStart);
	ResponseEntity<List<MessageDTO>> findByHoroBetween(String horoStart, String horoEnd);
	/* find by sender */
	ResponseEntity<List<MessageDTO>> findBySenderId(String sid);
	ResponseEntity<List<MessageDTO>> findBySenderIdAndHoroLessThanEqual(String sid, String horoEnd);
	ResponseEntity<List<MessageDTO>> findBySenderIdAndHoroGreaterThanEqual(String sid, String horoStart);
	ResponseEntity<List<MessageDTO>> findBySenderIdAndHoroBetween(String sid, String horoStart, String horoEnd);
	/* find by receiver */
	ResponseEntity<List<MessageDTO>> findByReceiverId(String rid);
	ResponseEntity<List<MessageDTO>> findByReceiverIdAndHoroLessThanEqual(String rid, String horoEnd);	
	ResponseEntity<List<MessageDTO>> findByReceiverIdAndHoroGreaterThanEqual(String rid, String horoStart);	
	ResponseEntity<List<MessageDTO>> findByReceiverIdAndHoroBetween(String rid, String horoStart, String horoEnd);	
	/* find by sender AND receiver */
	ResponseEntity<List<MessageDTO>> findBySenderIdAndReceiverId(String sid, String rid);
	ResponseEntity<List<MessageDTO>> findBySenderIdAndReceiverIdAndHoroLessThanEqual(String sid, String rid, String horoEnd);
	ResponseEntity<List<MessageDTO>> findBySenderIdAndReceiverIdAndHoroGreaterThanEqual(String sid, String rid, String horoStart);
	ResponseEntity<List<MessageDTO>> findBySenderIdAndReceiverIdAndHoroBetween(String sid, String rid, String horoStart, String horoEnd);
}
