package com.umanteam.dadakar.img.back.service.interfaces;

import java.io.InputStream;
import java.util.List;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

public interface IImageStorageService {

	public String store(InputStream inputStream, String fileName, String contentType, DBObject metaData);

	public GridFSDBFile getById(String id);

	public GridFSDBFile getByFilename(String filename);

	public List<GridFSDBFile> findAll();

}
