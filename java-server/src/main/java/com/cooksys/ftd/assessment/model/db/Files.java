package com.cooksys.ftd.assessment.model.db;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cooksys.ftd.assessment.model.api.DownloadFile;
import com.cooksys.ftd.assessment.model.api.GetFiles;
import com.cooksys.ftd.assessment.model.api.UploadFile;

public class Files {
	
	private Logger log = LoggerFactory.getLogger(Files.class);

	private String filePath;
	private Object objs;
	GetFiles getter;
	UploadFile upload;
	DownloadFile download;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Object getObjs() {
		return objs;
	}

	public void setObjs() {
		try {
			log.info("Inside of Files");
			File file = new File(filePath);
			JAXBContext jaxbContext = JAXBContext.newInstance(GetFiles.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			jaxbUnmarshaller.setProperty("eclipselink.media-type", "application/json");
			jaxbUnmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, false);
			objs = (Object) jaxbUnmarshaller.unmarshal(file);
			System.out.println(objs);
			upload.createFiles(filePath, objs);
		
		} catch (JAXBException e) {
			log.info("Error in setting objs." + e);
		} finally {
			
		}
	}

}
