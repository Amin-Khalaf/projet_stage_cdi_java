package com.umanteam.dadakar.msg.back.webservice.implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.umanteam.dadakar.msg.back.dto.MessageDTO;
import com.umanteam.dadakar.msg.back.service.interfaces.IMessageService;
import com.umanteam.dadakar.msg.back.webservice.interfaces.IMessageWebService;

@RestController
@RequestMapping("${appli.path}/msgs")
@CrossOrigin(origins="*")
public class MessageWebService implements IMessageWebService {
	
	@Autowired
	private IMessageService messageService;

	@RequestMapping(value="/save", method=RequestMethod.POST)
	@Override
	public MessageDTO add(@RequestBody MessageDTO messageDTO) {
		return messageService.addOrUpdate(messageDTO);
	}

	@RequestMapping(value="/update", method=RequestMethod.PUT)
	@Override
	public MessageDTO update(@RequestBody MessageDTO messageDTO) {
		return messageService.addOrUpdate(messageDTO);
	}

	@RequestMapping(value="/del/{id}", method=RequestMethod.DELETE)
	@Override
	public void delete(@PathVariable("id") String id) {
		messageService.delete(id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<MessageDTO>> findAll() {
		List<MessageDTO> messageDTOs = messageService.findAll();
		if(messageDTOs.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<MessageDTO>>(messageDTOs, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<MessageDTO> findById(String id) {
		MessageDTO messageDTO = messageService.findById(id);
		if(messageDTO.getMsgId().equals("")) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<MessageDTO>(messageDTO, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/lte:{end}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<MessageDTO>> findByHoroLessThanEqual(@PathVariable("end") String horoEnd) {
		List<MessageDTO> messageDTOs = messageService.findByHoroLessThanEqual(LocalDateTime.parse(horoEnd));
		if(messageDTOs.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<MessageDTO>>(messageDTOs, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/gte:{start}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<MessageDTO>> findByHoroGreaterThanEqual(@PathVariable("start") String horoStart) {
		List<MessageDTO> messageDTOs = messageService.findByHoroGreaterThanEqual(LocalDateTime.parse(horoStart));
		if(messageDTOs.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<MessageDTO>>(messageDTOs, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/gte:{start}/lte:{end}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<MessageDTO>> findByHoroBetween(@PathVariable("start") String horoStart, @PathVariable("end") String horoEnd) {
		List<MessageDTO> messageDTOs = messageService.findByHoroBetween(LocalDateTime.parse(horoStart), LocalDateTime.parse(horoEnd));
		if(messageDTOs.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<MessageDTO>>(messageDTOs, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/sid:{sid}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<MessageDTO>> findBySenderId(@PathVariable("sid") String sid) {
		List<MessageDTO> messageDTOs = messageService.findBySenderId(sid);
		if(messageDTOs.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<MessageDTO>>(messageDTOs, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/sid:{sid}/lte:{end}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<MessageDTO>> findBySenderIdAndHoroLessThanEqual(@PathVariable("sid") String sid, @PathVariable("end") String horoEnd) {
		List<MessageDTO> messageDTOs = messageService.findBySenderIdAndHoroLessThanEqual(sid, LocalDateTime.parse(horoEnd));
		if(messageDTOs.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<MessageDTO>>(messageDTOs, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/sid:{sid}/gte:{start}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<MessageDTO>> findBySenderIdAndHoroGreaterThanEqual(@PathVariable("sid") String sid, @PathVariable("start") String horoStart) {
		List<MessageDTO> messageDTOs = messageService.findBySenderIdAndHoroGreaterThanEqual(sid, LocalDateTime.parse(horoStart));
		if(messageDTOs.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<MessageDTO>>(messageDTOs, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/sid:{sid}/gte:{start}/lte:{end}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<MessageDTO>> findBySenderIdAndHoroBetween(@PathVariable("sid") String sid, @PathVariable("start") String horoStart, @PathVariable("end") String horoEnd) {
		List<MessageDTO> messageDTOs = messageService.findBySenderIdAndHoroBetween(sid, LocalDateTime.parse(horoStart), LocalDateTime.parse(horoEnd));
		if(messageDTOs.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<MessageDTO>>(messageDTOs, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/rid:{rid}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<MessageDTO>> findByReceiverId(@PathVariable("rid") String rid) {
		List<MessageDTO> messageDTOs = messageService.findByReceiverId(rid);
		if(messageDTOs.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<MessageDTO>>(messageDTOs, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/rid:{rid}/lte:{end}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<MessageDTO>> findByReceiverIdAndHoroLessThanEqual(@PathVariable("rid") String rid, @PathVariable("end") String horoEnd) {
		List<MessageDTO> messageDTOs = messageService.findByReceiverIdAndHoroLessThanEqual(rid, LocalDateTime.parse(horoEnd));
		if(messageDTOs.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<MessageDTO>>(messageDTOs, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/rid:{rid}/gte:{start}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<MessageDTO>> findByReceiverIdAndHoroGreaterThanEqual(@PathVariable("rid") String rid, @PathVariable("start") String horoStart) {
		List<MessageDTO> messageDTOs = messageService.findByReceiverIdAndHoroGreaterThanEqual(rid, LocalDateTime.parse(horoStart));
		if(messageDTOs.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<MessageDTO>>(messageDTOs, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/rid:{id}/gte:{start}/lte{end}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<MessageDTO>> findByReceiverIdAndHoroBetween(@PathVariable("rid") String rid, @PathVariable("start") String horoStart, @PathVariable("end") String horoEnd) {
		List<MessageDTO> messageDTOs = messageService.findByReceiverIdAndHoroBetween(rid, LocalDateTime.parse(horoStart), LocalDateTime.parse(horoEnd));
		if(messageDTOs.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<MessageDTO>>(messageDTOs, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/sid:{sid}/rid:{rid}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<MessageDTO>> findBySenderIdAndReceiverId(@PathVariable("sid") String sid, @PathVariable("rid") String rid) {
		List<MessageDTO> messageDTOs = messageService.findBySenderIdAndReceiverId(sid, rid);
		if(messageDTOs.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<MessageDTO>>(messageDTOs, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/sid:{sid}/rid:{rid}/lte:{end}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<MessageDTO>> findBySenderIdAndReceiverIdAndHoroLessThanEqual(@PathVariable("sid") String sid, @PathVariable("rid") String rid, @PathVariable("end") String horoEnd) {
		List<MessageDTO> messageDTOs = messageService.findBySenderIdAndReceiverIdAndHoroLessThanEqual(sid, rid, LocalDateTime.parse(horoEnd));
		if(messageDTOs.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<MessageDTO>>(messageDTOs, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="//sid:{sid}/rid:{rid}/gte:{start}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<MessageDTO>> findBySenderIdAndReceiverIdAndHoroGreaterThanEqual(@PathVariable("sid") String sid, @PathVariable("rid") String rid, @PathVariable("start") String horoStart) {
		List<MessageDTO> messageDTOs = messageService.findBySenderIdAndReceiverIdAndHoroGreaterThanEqual(sid, rid, LocalDateTime.parse(horoStart));
		if(messageDTOs.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<MessageDTO>>(messageDTOs, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="//sid:{sid}/rid:{rid}/gte:{start}/lte:{end}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<MessageDTO>> findBySenderIdAndReceiverIdAndHoroBetween(@PathVariable("sid") String sid, @PathVariable("rid") String rid, @PathVariable("start") String horoStart, @PathVariable("end") String horoEnd) {
		List<MessageDTO> messageDTOs = messageService.findBySenderIdAndReceiverIdAndHoroBetween(sid, rid, LocalDateTime.parse(horoStart), LocalDateTime.parse(horoEnd));
		if(messageDTOs.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<MessageDTO>>(messageDTOs, HttpStatus.OK);
	}

}
