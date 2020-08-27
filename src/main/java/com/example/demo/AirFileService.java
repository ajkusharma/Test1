package com.example.demo;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

@Service
public class AirFileService {
	
	

	    @Value("${app.upload.dir:${user.home}}")
	    public String uploadDir;

	    public void uploadFile(MultipartFile file) throws Exception {

	        try {
	            Path copyLocation = Paths
	                .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
	            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new Exception("Could not store file " + file.getOriginalFilename()
	                + ". Please try again!");
	        }
	    }
	}



