package com.juniorsilvacc.fastservice.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

import com.juniorsilvacc.fastservice.services.exceptions.FileStorageException;

public class UploadUtil {
	
	public static boolean uploadImage(MultipartFile image) {
		final String storage = "C:\\Code\\UploadDir";
		boolean sucessUpload = false;
		
		if(!image.isEmpty()) {
			String nameFile = image.getOriginalFilename();
			
			try {
				
				String fileUploadImage = storage;
				File dir = new File(fileUploadImage);
				
				if(!dir.exists()) {
					dir.mkdirs();
				}
				
				if (nameFile.contains("..")) {
					throw new FileStorageException(
						"Desculpe! O nome do arquivo contém uma sequência de caminho inválida " + nameFile);
				}
				
				File serverFile = new File(dir.getAbsolutePath() + File.separator + nameFile);
				
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

                stream.write(image.getBytes());
                stream.close();

                sucessUpload = true;
                
			} catch (Exception e) {
				throw new FileStorageException(
						"Não foi possível armazenar o arquivo " + nameFile + ".Por favor, tente novamente!", e);
			} 
			
		} else {
			throw new FileStorageException(
					"Você falhou em carregr o arquivo, o campo está vazio");
		}
		
		return sucessUpload;
	}

}
