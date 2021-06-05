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

import np.com.prashant.crimerecordmanagement.entities.CriminalPicture;
import np.com.prashant.crimerecordmanagement.entities.CriminalRecord;
import np.com.prashant.crimerecordmanagement.exceptions.FileStorageException;
import np.com.prashant.crimerecordmanagement.repository.CriminalPictureRepository;
import np.com.prashant.crimerecordmanagement.utils.AppConstants;

@Service
public class CriminalPictureSerivice {
	@Autowired
	CriminalPictureRepository cpr;
	@Value("${file.storage.location}")
	public String uploadDir;

	private Path fileStoragePath;
	public String fileStorageLocation;
	
	public CriminalPictureSerivice(@Value("${file.storage.location:temp}") String fileStorageLocation) {

		 this.fileStorageLocation = fileStorageLocation;
		fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();

		 try {
		Files.createDirectories(fileStoragePath);
		} catch (IOException e) {
		throw new RuntimeException("Issue in creating file directory");
		}
		}

	public CriminalPicture storeCriminalPicture(MultipartFile file, String username,
			CriminalRecord id, String fileDownloadUri,String newFileName) {

		if(!(file.getOriginalFilename().endsWith("png")|| 
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

			CriminalPicture addCriminalPicture= new CriminalPicture();
			addCriminalPicture.setFileName(newFileName);
			addCriminalPicture.setFileFormat(file.getContentType());
			addCriminalPicture.setUploadDirectiory(fileDownloadUri);
			addCriminalPicture.setUploadedBy(username);
			addCriminalPicture.setUploadedTime(dtf.format(now));
			addCriminalPicture.setCr(id);

			return cpr.save(addCriminalPicture);
		}catch(Exception ex) {
			throw new FileStorageException("could not store file"+newFileName+"Please try again!", ex);
		}
	}
	
	public Resource loadFileAsResource(String fileName) {
		try {
		//Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
		Path filePath= this.fileStoragePath.resolve(fileName).normalize();

		 Resource resource = new UrlResource(filePath.toUri());

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