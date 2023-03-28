package com.juniorsilvacc.fastservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.juniorsilvacc.fastservice.domain.dtos.UploadFileResponseDTO;
import com.juniorsilvacc.fastservice.services.FileStorageService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/file/v1")
@Tag(name = "File Endpoint")
public class FileController {

	@Autowired
	private FileStorageService service;
	
	@PostMapping(value = "/uploadFile")
	public UploadFileResponseDTO uploadFile(@RequestParam("file") MultipartFile file) {
		var filename = service.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
			.path("/api/file/v1/downloadFile/")
			.path(filename)
			.toUriString();
		
		return
			new UploadFileResponseDTO(
				filename, fileDownloadUri, file.getContentType(), file.getSize());
	}
	
}
