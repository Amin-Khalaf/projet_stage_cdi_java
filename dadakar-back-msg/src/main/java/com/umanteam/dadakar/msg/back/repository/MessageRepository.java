package com.umanteam.dadakar.msg.back.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.msg.back.entities.Message;

public interface MessageRepository extends MongoRepository<Message, String> {
	/* find by date */
	List<Message> findByHoroLessThanEqual(LocalDateTime horoEnd);
	List<Message> findByHoroGreaterThanEqual(LocalDateTime horoStart);
	List<Message> findByHoroBetween(LocalDateTime horoStart, LocalDateTime horoEnd);
	/* find by sender */
	List<Message> findBySenderId(String sid);
	List<Message> findBySenderIdAndHoroLessThanEqual(String sid, LocalDateTime horoEnd);
	List<Message> findBySenderIdAndHoroGreaterThanEqual(String sid, LocalDateTime horoStart);
	List<Message> findBySenderIdAndHoroBetween(String sid, LocalDateTime horoStart, LocalDateTime horoEnd);
	/* find by receiver */
	List<Message> findByReceiverId(String rid);
	List<Message> findByReceiverIdAndHoroLessThanEqual(String rid, LocalDateTime horoEnd);	
	List<Message> findByReceiverIdAndHoroGreaterThanEqual(String rid, LocalDateTime horoStart);	
	List<Message> findByReceiverIdAndHoroBetween(String rid, LocalDateTime horoStart, LocalDateTime horoEnd);	
	/* find by sender AND receiver */
	List<Message> findBySenderIdAndReceiverId(String sid, String rid);
	List<Message> findBySenderIdAndReceiverIdAndHoroLessThanEqual(String sid, String rid, LocalDateTime horoEnd);
	List<Message> findBySenderIdAndReceiverIdAndHoroGreaterThanEqual(String sid, String rid, LocalDateTime horoStart);
	List<Message> findBySenderIdAndReceiverIdAndHoroBetween(String sid, String rid, LocalDateTime horoStart, LocalDateTime horoEnd);
}
