package com.umanteam.dadakarimg.back.webservice;

//import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface IImageStorageWebService {

	public void storeImage(MultipartFile file);
	public ResponseEntity<InputStreamResource> getImageAsResource(String id);
	public ResponseEntity<InputStreamResource> getImageByFileName(String filename);
//	public ResponseEntity<List<InputStreamResource>> getAllImages();
}
