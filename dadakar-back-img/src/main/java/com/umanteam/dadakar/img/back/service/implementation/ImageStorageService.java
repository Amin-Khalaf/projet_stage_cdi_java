package com.umanteam.dadakar.img.back.service.implementation;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.umanteam.dadakar.img.back.dto.ImageDTO;
import com.umanteam.dadakar.img.back.repository.interfaces.IImageStorageRepository;
import com.umanteam.dadakar.img.back.service.interfaces.IImageStorageService;

@Service
public class ImageStorageService implements IImageStorageService {

	@Autowired
	IImageStorageRepository imageStorageRepository;
	
	@Override
	public String store(InputStream inputStream, String fileName, String contentType, DBObject metaData) {
		return imageStorageRepository.store(inputStream, fileName, contentType, metaData);
	}
	
	@Override
	public String store(ImageDTO image, DBObject metaData) {
		return imageStorageRepository.store(new ByteArrayInputStream(Base64.decodeBase64(image.getImage())), image.getName(), image.getType(), metaData);
	}

	@Override
	public GridFSDBFile getById(String id) {
		return imageStorageRepository.getById(id);
	}

	@Override
	public GridFSDBFile getByFilename(String filename) {
		return imageStorageRepository.getByFilename(filename);
	}

	@Override
	public List<GridFSDBFile> findAll() {
		return imageStorageRepository.findAll();
	}

}
