package com.codeWithMadhuri.blog.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.codeWithMadhuri.blog.services.FileServices;
@Service
public class FileServiceImpl implements FileServices {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		//file name
		String originalfilename = file.getOriginalFilename();
		
		
		//random name generate file
		
		String randomId=UUID.randomUUID().toString();
		String fileName=randomId.concat(originalfilename.substring(originalfilename.lastIndexOf(".")));
		
		
		//fullpath
		String filePath=path+File.separator+fileName;
		
		
		
		//create folder if not exists
		File f=new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		//file copy
		Files.copy(file.getInputStream(),Paths.get(filePath));
		
		return fileName;
	}

	@Override
	public InputStream getResources(String path, String fileName) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
