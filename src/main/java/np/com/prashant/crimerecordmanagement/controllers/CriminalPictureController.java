package np.com.prashant.crimerecordmanagement.controllers;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import net.bytebuddy.utility.RandomString;
import np.com.prashant.crimerecordmanagement.entities.CriminalRecord;
import np.com.prashant.crimerecordmanagement.repository.CriminalPictureRepository;
import np.com.prashant.crimerecordmanagement.repository.CriminalRecordRepository;
import np.com.prashant.crimerecordmanagement.services.CriminalPictureSerivice;
import np.com.prashant.crimerecordmanagement.services.UserDetailsImpl;
import np.com.prashant.crimerecordmanagement.utils.AppConstants;

@RestController
@RequestMapping("/api/criminalPicture")
public class CriminalPictureController {
	@Autowired
	CriminalPictureRepository cpr;
	@Autowired
	CriminalRecordRepository crr;
	@Autowired
	CriminalPictureSerivice cps;

	@PostMapping("/uploadPicture/{id}")
	public ResponseEntity<?>addProfilePicture
	(@RequestParam(required = true, value ="file")
	MultipartFile file,@PathVariable Long id)
			throws JsonParseException, JsonMappingException, IOException
	{
		Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long userId=((UserDetailsImpl)principal).getId();
		String username=((UserDetailsImpl)principal).getUsername();

		Optional<CriminalRecord>cr =crr.findById(id);
		CriminalRecord u=cr.get();

		String randomChars = RandomString.make();
		String fileName=StringUtils.cleanPath(file.getOriginalFilename());
		String newFileName=randomChars+"_"+username+"_"+fileName;

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH_FOR_CRIMINAL_PICTURE)
				.path(newFileName).toUriString();

		//make a call to the service,
		cps.storeCriminalPicture(file, username, u, fileDownloadUri, newFileName);

		return ResponseEntity.ok("Criminal Picture uploaded successfully.");
	}

	@GetMapping("downloadFile/{fileName}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
	Resource resource = cps.loadFileAsResource(fileName);
	String contentType = null;
	try {
	contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
	} catch (IOException ex) {
	ex.printStackTrace();
	}
	if (contentType == null) {
	contentType = AppConstants.DEFAULT_CONTENT_TYPE;
	}
	return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
	.header(HttpHeaders.CONTENT_DISPOSITION,
	String.format(AppConstants.FILE_DOWNLOAD_HTTP_HEADER, resource.getFilename()))
	.body(resource);
	}
	
}
