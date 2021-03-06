package com.umanteam.dadakar.img.back.webservice.implementation;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.umanteam.dadakar.img.back.dto.ImageDTO;
import com.umanteam.dadakar.img.back.service.interfaces.IImageStorageService;
import com.umanteam.dadakar.img.back.webservice.interfaces.IImageStorageWebService;

@RestController
@RequestMapping("${appli.path}/img")
@CrossOrigin(origins = "*")
public class ImageStorageWebService implements IImageStorageWebService {

	@Autowired
	IImageStorageService imageStorageService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Override
	public void storeImage(@RequestParam("file") MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		DBObject metaData = new BasicDBObject();
		try {
			imageStorageService.store(file.getInputStream(), filename, file.getContentType(), metaData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/ionicupload", method = RequestMethod.POST)
	@Override
	public void storeImageFromIonic(@RequestBody ImageDTO image) {
		DBObject metaData = new BasicDBObject();
		imageStorageService.store(image, metaData);
		
	}

	@RequestMapping(value="/del/{filename:.+}", method = RequestMethod.DELETE)
	@Override
	public void delete(@PathVariable("filename") String fileName) {
		this.imageStorageService.delete(fileName);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity<InputStreamResource> getImageAsResource(@PathVariable("id") String id) {
		GridFSDBFile gridFsFile = imageStorageService.getById(id);
		if (gridFsFile == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok().contentLength(gridFsFile.getLength())
				.contentType(MediaType.parseMediaType(gridFsFile.getContentType()))
				.body(new InputStreamResource(gridFsFile.getInputStream()));
	}

	// need ":.+" in pathvariable so the extension is kept 
	@RequestMapping(value = "/name:{filename:.+}", method = RequestMethod.GET)
	@ResponseBody
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity<InputStreamResource> getImageByFileName(@PathVariable("filename") String filename) {
		GridFSDBFile gridFsFile = imageStorageService.getByFilename(filename);
		if (gridFsFile == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok().contentLength(gridFsFile.getLength())
				.contentType(MediaType.parseMediaType(gridFsFile.getContentType()))
				.body(new InputStreamResource(gridFsFile.getInputStream()));
	}
	
	@RequestMapping(value="ionic/name:{filename:.+}", method= RequestMethod.GET)
	@ResponseBody
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<byte[]> getImageByFileNameForIonic(@PathVariable("filename") String filename) {
		GridFSDBFile file = imageStorageService.getByFilename(filename);
		if(file == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		try {
			return ResponseEntity.ok().body(Base64.encodeBase64(StreamUtils.copyToByteArray(file.getInputStream())));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
	}

	// TODO if necessary implement getAllImages
	
//	@RequestMapping(method = RequestMethod.GET)
//	@ResponseBody
//	@Override
//	public ResponseEntity<List<InputStreamResource>> getAllImages() {
//		List<GridFSDBFile> listFiles = imageStorageService.findAll();
//		List<InputStreamResource> listStream = new ArrayList<>();
////		long streamLength = 0;
//		for (GridFSDBFile file : listFiles){
//			listStream.add(new InputStreamResource(file.getInputStream()));
////			streamLength += file.getLength();
//		}
//		return new ResponseEntity<List<InputStreamResource>>(listStream, HttpStatus.OK);
////		return ResponseEntity.ok().contentLength(streamLength)
////				.body(listStream);
//	}

}
