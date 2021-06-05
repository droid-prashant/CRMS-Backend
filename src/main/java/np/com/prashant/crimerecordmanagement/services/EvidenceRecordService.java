package np.com.prashant.crimerecordmanagement.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import np.com.prashant.crimerecordmanagement.entities.EvidenceRecord;
import np.com.prashant.crimerecordmanagement.entities.PoliceStaffPicture;
import np.com.prashant.crimerecordmanagement.exceptions.FileStorageException;
import np.com.prashant.crimerecordmanagement.models.User;
import np.com.prashant.crimerecordmanagement.repository.EvidenceRecordRepository;
import np.com.prashant.crimerecordmanagement.utils.AppConstants;

@Service
public class EvidenceRecordService {
	@Autowired
	EvidenceRecordRepository e_repo;
	@Value("${file.storage.location}")
	public String uploadDir;
	private Path fileStoragePath;
	public String fileStorageLocation;
	
	public EvidenceRecordService(@Value("${file.storage.location:temp}") String fileStorageLocation) {
		this.fileStorageLocation = fileStorageLocation;
		fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();

		try {
			Files.createDirectories(fileStoragePath);
		} catch (IOException e) {
			throw new RuntimeException("Issue in creating file directory");
	}
}
	
	public EvidenceRecord storeEvidenceRecordService(MultipartFile file, String username1,
			User username, String fileDownloadUri,String newFileName) {
		if(!(file.getOriginalFilename().endsWith("png")|| 
				file.getOriginalFilename().endsWith("mp4")||
				file.getOriginalFilename().endsWith("mp3")||
				file.getOriginalFilename().endsWith("txt")||
				file.getOriginalFilename().endsWith("pdf")||
				file.getOriginalFilename().endsWith("ppt")||
				file.getOriginalFilename().endsWith("doc")||
				file.getOriginalFilename().endsWith("PNG")||
				file.getOriginalFilename().endsWith("JPEG")||
				file.getOriginalFilename().endsWith("jpeg")||
				file.getOriginalFilename().endsWith("JPG")||
				file.getOriginalFilename().endsWith("jpg")))throw
		new FileStorageException("Invalid file format");
		
		Path filePath =Paths.get(fileStoragePath+"\\"+newFileName);

		try {
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		}catch(IOException e) {
			throw new RuntimeException("Issue in storing file", e);
		}
		try {
			//check if file's name contain invalid characters
			if(newFileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename containsinvalid path  sequence"+newFileName);
			}
			DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now= LocalDateTime.now();

			EvidenceRecord evidence= new EvidenceRecord();
			evidence.setFileName(newFileName);
			evidence.setFileFormat(file.getContentType());
			evidence.setUploadDirectiory(fileDownloadUri);
			evidence.setUploadedBy(username1);
			evidence.setUploadedTime(dtf.format(now));
			evidence.setImg_user(username);

			return e_repo.save(evidence);
		}catch(Exception ex) {
			throw new FileStorageException("could not store file"+newFileName+"Please try again!", ex);
		}
	}
	public Resource loadFileAsResource(String fileName){

		try {
			Path filePath=this.fileStoragePath.resolve(fileName).normalize();
			Resource resource= new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new FileStorageException(AppConstants.FILE_NOT_FOUND + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new FileStorageException(AppConstants.FILE_NOT_FOUND + fileName, ex);
		}
	}
	
}

