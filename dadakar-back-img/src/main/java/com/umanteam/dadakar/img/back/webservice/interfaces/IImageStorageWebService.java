package com.umanteam.dadakar.img.back.webservice.interfaces;

//import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.umanteam.dadakar.img.back.dto.ImageDTO;

public interface IImageStorageWebService {

	public void storeImage(MultipartFile file);
	public void storeImageFromIonic(ImageDTO image);
	public ResponseEntity<InputStreamResource> getImageAsResource(String id);
	public ResponseEntity<InputStreamResource> getImageByFileName(String filename);
	ResponseEntity<byte[]> getImageByFileNameForIonic(String filename);
//	public ResponseEntity<List<InputStreamResource>> getAllImages();
}
