package com.anecico.fairbiomarket.domain.repo;

import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

import java.io.InputStream;


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
	
	public GridFSFile getGridFSFile(String id) {
		return gridFsTemplate.findOne(new Query(GridFsCriteria.where("_id").is(id)));
	}

	public GridFsResource getGridFsResource(String id) {
        GridFSFile gridFSFile = getGridFSFile(id);
        return  gridFsTemplate.getResource(gridFSFile.getFilename());
	}


	public GridFsResource[] getGridFsResources(String fileNamePattern) {
		return gridFsTemplate.getResources(fileNamePattern);

	}

	public void deleteFSFile(Object id) {
		gridFsTemplate.delete(new Query(GridFsCriteria.where("_id").is(id)));
	}
}
