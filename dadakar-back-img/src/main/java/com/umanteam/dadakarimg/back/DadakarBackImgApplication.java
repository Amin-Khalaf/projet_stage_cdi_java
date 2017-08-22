package com.umanteam.dadakarimg.back;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.umanteam.dadakarimg.back.repository.interfaces.IImageStorageRepository;
import com.umanteam.dadakarimg.back.service.interfaces.IImageStorageService;

@SpringBootApplication
public class DadakarBackImgApplication implements CommandLineRunner {

	@Autowired
	IImageStorageRepository imageStorageRepository;

	@Autowired
	IImageStorageService imageStorageService;

	@Autowired
	private ResourceLoader resourceLoader;

	public static void main(String[] args) {
		SpringApplication.run(DadakarBackImgApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

//		testRepository();

//		testService();
	}

	public void testRepository() throws IOException {
		System.out.println("=== Test image storage Repository ===");
		System.out.println("Storage 1 ---");
		Resource resource = resourceLoader.getResource("file:D:\\MesImages\\tests\\user1.png");
		DBObject metaData = new BasicDBObject();
		String filename = resource.getFilename();
		String id = imageStorageRepository.store(resource.getInputStream(), filename, "image/jpeg", metaData);
		System.out.println("image id: " + id);

		System.out.println("Storage 2 ---");
		resource = resourceLoader.getResource("file:D:\\MesImages\\tests\\utilisateur2.jpg");
		metaData = new BasicDBObject();
		filename = resource.getFilename();
		String id2 = imageStorageRepository.store(resource.getInputStream(), filename, "image/jpeg", metaData);
		System.out.println("image id: " + id2);

		System.out.println("Storage 3 ---");
		resource = resourceLoader.getResource("file:D:\\MesImages\\tests\\voiture1.jpg");
		metaData = new BasicDBObject();
		filename = resource.getFilename();
		String id3 = imageStorageRepository.store(resource.getInputStream(), filename, "image/jpeg", metaData);
		System.out.println("image id: " + id3);

		System.out.println("Find By Id '" + id + "' ----");
		GridFSDBFile byId = imageStorageRepository.getById(id);
		System.out.println("File Name:- " + byId.getFilename());
		System.out.println("Content Type:- " + byId.getContentType());

		System.out.println("Find By Name '" + filename + "' ----");
		GridFSDBFile byName = imageStorageRepository.getByFilename(filename);
		System.out.println("ID :- " + byName.getId());
		System.out.println("File Name:- " + byName.getFilename());
		System.out.println("Content Type:- " + byId.getContentType());

		System.out.println("List All Files----------------------");
		for (GridFSDBFile file : imageStorageRepository.findAll()) {
			System.out.println("File Name:- " + file.getFilename());
			System.out.println("Content Type:- " + file.getContentType());
			System.out.println("ID :- " + file.getId());
		}
	}

	public void testService() throws IOException {
		System.out.println("=== Test image storage Service ===");
		System.out.println("Storage 1 ---");
		Resource resource = resourceLoader.getResource("file:D:\\MesImages\\tests\\user1.png");
		DBObject metaData = new BasicDBObject();
		String filename = resource.getFilename();
		String id = imageStorageService.store(resource.getInputStream(), filename, "image/jpeg", metaData);
		System.out.println("image id: " + id);

		System.out.println("Storage 2 ---");
		resource = resourceLoader.getResource("file:D:\\MesImages\\tests\\utilisateur2.jpg");
		metaData = new BasicDBObject();
		filename = resource.getFilename();
		String id2 = imageStorageService.store(resource.getInputStream(), filename, "image/jpeg", metaData);
		System.out.println("image id: " + id2);

		System.out.println("Storage 3 ---");
		resource = resourceLoader.getResource("file:D:\\MesImages\\tests\\voiture1.jpg");
		metaData = new BasicDBObject();
		filename = resource.getFilename();
		String id3 = imageStorageService.store(resource.getInputStream(), filename, "image/jpeg", metaData);
		System.out.println("image id: " + id3);

		System.out.println("Find By Id '" + id + "' ----");
		GridFSDBFile byId = imageStorageService.getById(id);
		System.out.println("File Name:- " + byId.getFilename());
		System.out.println("Content Type:- " + byId.getContentType());

		System.out.println("Find By Name '" + filename + "' ----");
		GridFSDBFile byName = imageStorageService.getByFilename(filename);
		System.out.println("ID :- " + byName.getId());
		System.out.println("File Name:- " + byName.getFilename());
		System.out.println("Content Type:- " + byId.getContentType());

		System.out.println("List All Files----------------------");
		for (GridFSDBFile file : imageStorageService.findAll()) {
			System.out.println("File Name:- " + file.getFilename());
			System.out.println("Content Type:- " + file.getContentType());
			System.out.println("ID :- " + file.getId());
		}
	}
}
