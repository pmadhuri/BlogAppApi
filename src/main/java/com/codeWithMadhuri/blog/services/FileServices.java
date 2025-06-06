package com.codeWithMadhuri.blog.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileServices {

	
	String uploadImage(String path,MultipartFile file) throws IOException;
	InputStream getResources(String path,String fileName) throws FileNotFoundException;
}
