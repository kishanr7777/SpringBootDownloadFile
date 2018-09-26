package org.o7.planning.sbdownload.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletContext;

import org.o7.planning.sbdownload.util.MediaTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Example1Controller {
	
	private static final String DIRECTORY = "c:/PDF";
	private static final String DEFAULT_FILE_NAME = "java-tutorial.pdf";
	
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping("/download1")
	public ResponseEntity<InputStreamSource> downloadFile1(@RequestParam(defaultValue = DEFAULT_FILE_NAME) String filename) throws Exception{
		MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(servletContext, filename);
		System.out.println("fileName: " + filename);
        System.out.println("mediaType: " + mediaType);
        
        File file = new File(DIRECTORY+"/"+filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        
        return ResponseEntity.ok()//
        		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment);filename = "+file.getName())//
        		.contentType(mediaType)//
        		.contentLength(file.length())//
        		.body(resource);
	}

}
