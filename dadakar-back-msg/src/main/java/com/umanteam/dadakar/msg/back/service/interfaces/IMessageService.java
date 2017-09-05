package com.umanteam.dadakar.msg.back.service.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import com.umanteam.dadakar.msg.back.dto.MessageDTO;

public interface IMessageService {
	MessageDTO addOrUpdate(MessageDTO messageDTO);
	void delete(String id);
	List<MessageDTO> findAll();
	MessageDTO findById(String id);
	/* find by date */
	List<MessageDTO> findByHoroLessThanEqual(LocalDateTime horoEnd);
	List<MessageDTO> findByHoroGreaterThanEqual(LocalDateTime horoStart);
	List<MessageDTO> findByHoroBetween(LocalDateTime horoStart, LocalDateTime horoEnd);
	/* find by sender */
	List<MessageDTO> findBySenderId(String sid);
	List<MessageDTO> findBySenderIdAndHoroLessThanEqual(String sid, LocalDateTime horoEnd);
	List<MessageDTO> findBySenderIdAndHoroGreaterThanEqual(String sid, LocalDateTime horoStart);
	List<MessageDTO> findBySenderIdAndHoroBetween(String sid, LocalDateTime horoStart, LocalDateTime horoEnd);
	/* find by receiver */
	List<MessageDTO> findByReceiverId(String rid);
	List<MessageDTO> findByReceiverIdAndHoroLessThanEqual(String rid, LocalDateTime horoEnd);	
	List<MessageDTO> findByReceiverIdAndHoroGreaterThanEqual(String rid, LocalDateTime horoStart);	
	List<MessageDTO> findByReceiverIdAndHoroBetween(String rid, LocalDateTime horoStart, LocalDateTime horoEnd);	
	/* find by sender AND receiver */
	List<MessageDTO> findBySenderIdAndReceiverId(String sid, String rid);
	List<MessageDTO> findBySenderIdAndReceiverIdAndHoroLessThanEqual(String sid, String rid, LocalDateTime horoEnd);
	List<MessageDTO> findBySenderIdAndReceiverIdAndHoroGreaterThanEqual(String sid, String rid, LocalDateTime horoStart);
	List<MessageDTO> findBySenderIdAndReceiverIdAndHoroBetween(String sid, String rid, LocalDateTime horoStart, LocalDateTime horoEnd);
}
