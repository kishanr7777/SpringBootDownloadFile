package org.o7.planning.sbdownload.util;

import javax.servlet.ServletContext;

import org.springframework.http.MediaType;

public class MediaTypeUtils {
	
	public static MediaType getMediaTypeForFileName(ServletContext servletContext, String filename) {
		String mineType = servletContext.getMimeType(filename);
		try {
			MediaType mediaType = MediaType.parseMediaType(mineType);
			return mediaType;
		} catch (Exception e) {
			// TODO: handle exception
			return MediaType.APPLICATION_OCTET_STREAM;
		}
	}

}
