package com.umanteam.dadakar.img.back.repository.implementation;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.umanteam.dadakar.img.back.repository.interfaces.IImageStorageRepository;

@Repository
public class ImageStorageRepository implements IImageStorageRepository {

	@Autowired
	GridFsTemplate gridFsTemplate;
	
	@Override
	public void delete(String fileName) {
		this.gridFsTemplate.delete(new Query(GridFsCriteria.whereFilename().is(fileName)));
	}
	
	@Override
	public String store(InputStream inputStream, String fileName, String contentType, DBObject metaData) {
		return this.gridFsTemplate.store(inputStream, fileName, contentType, metaData).getId().toString();
	}

	@Override
	public GridFSDBFile getById(String id) {
		return this.gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
	}

	@Override
	public GridFSDBFile getByFilename(String filename) {
		return this.gridFsTemplate.findOne(new Query(Criteria.where("filename").is(filename)));
	}

	@Override
	public List<GridFSDBFile> findAll() {
		return this.gridFsTemplate.find(null);
	}

}
