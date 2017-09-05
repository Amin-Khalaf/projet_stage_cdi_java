package com.umanteam.dadakar.admin.front.service.implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.umanteam.dadakar.admin.front.dto.Message;
import com.umanteam.dadakar.admin.front.service.interfaces.IMessageService;

@Service
public class MessageService implements IMessageService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${msg.path}")
	private String msgPath;

	@Override
	public Message add(Message message) {
		HttpEntity<Message> requestMsg = new HttpEntity<Message>(message);
		ResponseEntity<Message> responseMsg = restTemplate.exchange(msgPath + "/save", HttpMethod.POST, requestMsg, Message.class);
		if(responseMsg.getStatusCode() != HttpStatus.OK) return null;
		message = responseMsg.getBody();
		return message;
	}
	
	@Override
	public Message update(Message message) {
		HttpEntity<Message> msgRequest = new HttpEntity<Message>(message);
		ResponseEntity<Message> msgResponse = restTemplate.exchange(msgPath + "/update", HttpMethod.PUT, msgRequest, Message.class);
		if(msgResponse.getStatusCode() != HttpStatus.OK) return null;
		message = msgResponse.getBody();
		return message;
	}

	@Override
	public void delete(String id) {
		restTemplate.delete(msgPath + "/del" + id);
	}

	@Override
	public List<Message> findAll() {
		ResponseEntity<List<Message>> msgResponse = restTemplate.exchange(msgPath, HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {});
		List<Message> messages = msgResponse.getBody();
		return messages;
	}

	@Override
	public Message findById(String id) {
		String url = msgPath + "/" + id;
		ResponseEntity<Message> msgResponse = restTemplate.getForEntity(url, Message.class);
		Message message = msgResponse.getBody();
		return message;
	}

	@Override
	public List<Message> findByHoroLessThanEqual(LocalDateTime horoEnd) {
		String url = msgPath + "/lte:" + horoEnd.toString();
		ResponseEntity<List<Message>> msgResponse = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {});
		List<Message> messages = msgResponse.getBody();
		return messages;
	}

	@Override
	public List<Message> findByHoroGreaterThanEqual(LocalDateTime horoStart) {
		String url = msgPath + "/gte:" + horoStart.toString();
		ResponseEntity<List<Message>> msgResponse = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {});
		List<Message> messages = msgResponse.getBody();
		return messages;
	}

	@Override
	public List<Message> findByHoroBetween(LocalDateTime horoStart, LocalDateTime horoEnd) {
		String url = msgPath + "/gte:" + horoStart.toString() + "/lte:" + horoEnd.toString();
		ResponseEntity<List<Message>> msgResponse = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {});
		List<Message> messages = msgResponse.getBody();
		return messages;
	}

	@Override
	public List<Message> findBySenderId(String sid) {
		String url = msgPath + "/sid:" + sid;
		ResponseEntity<List<Message>> msgResponse = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {});
		List<Message> messages = msgResponse.getBody();
		return messages;
	}

	@Override
	public List<Message> findBySenderIdAndHoroLessThanEqual(String sid, LocalDateTime horoEnd) {
		String url = msgPath + "/sid:" + sid + "/lte:" + horoEnd.toString();
		ResponseEntity<List<Message>> msgResponse = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {});
		List<Message> messages = msgResponse.getBody();
		return messages;
	}

	@Override
	public List<Message> findBySenderIdAndHoroGreaterThanEqual(String sid, LocalDateTime horoStart) {
		String url = msgPath + "/sid:" + sid + "/lte:" + horoStart.toString();
		ResponseEntity<List<Message>> msgResponse = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {});
		List<Message> messages = msgResponse.getBody();
		return messages;
	}

	@Override
	public List<Message> findBySenderIdAndHoroBetween(String sid, LocalDateTime horoStart, LocalDateTime horoEnd) {
		String url = msgPath + "/sid:" + sid + "/gte:" + horoStart.toString() + "/lte:" + horoEnd.toString();
		ResponseEntity<List<Message>> msgResponse = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {});
		List<Message> messages = msgResponse.getBody();
		return messages;
	}

	@Override
	public List<Message> findByReceiverId(String rid) {
		String url = msgPath + "/rid:" + rid;
		ResponseEntity<List<Message>> msgResponse = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {});
		List<Message> messages = msgResponse.getBody();
		return messages;
	}

	@Override
	public List<Message> findByReceiverIdAndHoroLessThanEqual(String rid, LocalDateTime horoEnd) {
		String url = msgPath + "/rid:" + rid + "/lte:" + horoEnd.toString();
		ResponseEntity<List<Message>> msgResponse = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {});
		List<Message> messages = msgResponse.getBody();
		return messages;
	}

	@Override
	public List<Message> findByReceiverIdAndHoroGreaterThanEqual(String rid, LocalDateTime horoStart) {
		String url = msgPath + "/rid:" + rid + "/gte:" + horoStart.toString();
		ResponseEntity<List<Message>> msgResponse = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {});
		List<Message> messages = msgResponse.getBody();
		return messages;
	}

	@Override
	public List<Message> findByReceiverIdAndHoroBetween(String rid, LocalDateTime horoStart, LocalDateTime horoEnd) {
		String url = msgPath + "/rid:" + rid + "/gte:" + horoStart.toString() + "/lte:" + horoEnd.toString();
		ResponseEntity<List<Message>> msgResponse = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {});
		List<Message> messages = msgResponse.getBody();
		return messages;
	}

	@Override
	public List<Message> findBySenderIdAndReceiverId(String sid, String rid) {
		String url = msgPath + "/sid:" + sid + "/rid:" + rid;
		ResponseEntity<List<Message>> msgResponse = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {});
		List<Message> messages = msgResponse.getBody();
		return messages;
	}

	@Override
	public List<Message> findBySenderIdAndReceiverIdAndHoroLessThanEqual(String sid, String rid, LocalDateTime horoEnd) {
		String url = msgPath + "/sid:" + sid + "/rid:" + rid + "/lte:" + horoEnd.toString();
		ResponseEntity<List<Message>> msgResponse = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {});
		List<Message> messages = msgResponse.getBody();
		return messages;
	}

	@Override
	public List<Message> findBySenderIdAndReceiverIdAndHoroGreaterThanEqual(String sid, String rid, LocalDateTime horoStart) {
		String url = msgPath + "/sid:" + sid + "/rid:" + rid + "/gte:" + horoStart.toString();
		ResponseEntity<List<Message>> msgResponse = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {});
		List<Message> messages = msgResponse.getBody();
		return messages;
	}

	@Override
	public List<Message> findBySenderIdAndReceiverIdAndHoroBetween(String sid, String rid, LocalDateTime horoStart, LocalDateTime horoEnd) {
		String url = msgPath + "/sid:" + sid + "/rid:" + rid + "/gte:" + horoStart.toString() + "/lte:" + horoEnd.toString();
		ResponseEntity<List<Message>> msgResponse = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {});
		List<Message> messages = msgResponse.getBody();
		return messages;
	}

}
