package np.com.prashant.crimerecordmanagement.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.mail.iap.Response;

import np.com.prashant.crimerecordmanagement.entities.PublicRegistration;
import np.com.prashant.crimerecordmanagement.payload.response.MessageResponse;
import np.com.prashant.crimerecordmanagement.repository.PublicRepository;

@RestController
@RequestMapping("/api/public")
public class PublicInformationController {
	@Autowired
	PublicRepository repo;
	@GetMapping("/greeting")
	public String greeting() {
		return "Hello";
	}

	
	//get= to show list of record
	@GetMapping("/list")
	public List<PublicRegistration> listAll(){
		return repo.findAll();
	}
	//get an individual record
	@GetMapping("list/{publicId}")
	public Optional<PublicRegistration>p_data(@PathVariable Long publicId){
		return repo.findById(publicId);
	}
	@PostMapping("/create")

	public ResponseEntity<?> savePublicRegistration(@RequestBody PublicRegistration publicRegistration){
		PublicRegistration lkg= new PublicRegistration(publicRegistration.getFirstName(),
				publicRegistration.getLastName(),
				publicRegistration.getGender(),
				publicRegistration.getAge(), publicRegistration.getAddress(),
				publicRegistration.getPhNum(), publicRegistration.getCitizenshipNumber(),
				publicRegistration.getIncidentDetails(), publicRegistration.getIncidentAddress(),
				publicRegistration.getRemark());
		 repo.save(lkg);
		 return ResponseEntity.ok(new MessageResponse("Complaint Registered Successfully"));
	}
}

