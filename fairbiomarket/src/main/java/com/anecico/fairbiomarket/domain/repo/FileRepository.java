package com.anecico.fairbiomarket.domain.repo;

import java.io.InputStream;

import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;


@Component
public class FileRepository {
	@Autowired
	private GridFsTemplate gridFsTemplate;
	
	public String storeFile(InputStream inputStream, String filename, String contentType, DBObject metaData) {
		//  we can save additional metadata along with the file by passing a DBObject to the store method.
//		DBObject metaData = new BasicDBObject();
//		metaData.put("user", "alex");

		return  gridFsTemplate.store(inputStream, filename, contentType, metaData).toString();
	}
	
	public GridFsResource getGridFSFile(String id) {
		GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
		GridFsResource gridFsResource = gridFsTemplate.getResource(gridFSFile.getFilename());
		return  gridFsResource;
	}
	/*
	public GridFsResource getResource(String location) {
		 return gridFsMongoTemplate.getResource(location);
	}
	*/

	public GridFsResource[] getResources(String fileNamePattern) {
		return gridFsTemplate.getResources(fileNamePattern);

	}

	public void deleteFSFile(Object id) {
		gridFsTemplate.delete(new Query(Criteria.where("_id").is(id)));
	}
}
