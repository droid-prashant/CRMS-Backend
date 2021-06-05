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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import np.com.prashant.crimerecordmanagement.entities.PoliceStaffRegistration;
import np.com.prashant.crimerecordmanagement.entities.PublicRegistration;
import np.com.prashant.crimerecordmanagement.models.User;
import np.com.prashant.crimerecordmanagement.payload.response.MessageResponse;
import np.com.prashant.crimerecordmanagement.repository.PoliceRepository;
import np.com.prashant.crimerecordmanagement.repository.UserRepository;

@RestController
@RequestMapping("api/police")
public class PoliceRecordController {
	@Autowired
	PoliceRepository repo;
	
	@Autowired
	UserRepository user;

	@PostMapping("/create/{username}")
	public ResponseEntity<?> savePoliceStaffRegistration(@RequestBody PoliceStaffRegistration policeStaffRegistration,
			@PathVariable String username) {
		Optional<User>u=user.findByUsername(username);
		User user=u.get();
		PoliceStaffRegistration p= new PoliceStaffRegistration(
				policeStaffRegistration.getFirstName(),
				policeStaffRegistration.getLastName(),
				policeStaffRegistration.getFatherName(),
				policeStaffRegistration.getGender(),
				policeStaffRegistration.getPhoneNumber(),
				policeStaffRegistration.getEmail(),
				policeStaffRegistration.getAge(),
				policeStaffRegistration.getBatchNumber(),
				policeStaffRegistration.getRank(),
				policeStaffRegistration.getJoinedDateInPolice(),
				policeStaffRegistration.isActive(),user);
		repo.save(p);
		return ResponseEntity.ok(new MessageResponse("Staff registered successfully."));
		
	}
	
	
	@GetMapping("/list")
	public List<PoliceStaffRegistration>listAll(){
		return repo.findAll();
	}
	
	@GetMapping("/listByRank/{rank}")
	public List<PoliceStaffRegistration>listAll(@PathVariable String rank){
		return repo.findByrank(rank);
	}

	@GetMapping("/listByUserId/{obj_id}")
	public Optional<PoliceStaffRegistration>police(@PathVariable Long obj_id){
		return repo.findByobjId(obj_id);
	}
	@PutMapping("update/{id}")
	public String updatePoliceStaffRegistration(@RequestBody PoliceStaffRegistration policeStaffRegistration, @PathVariable Long id) {
		
		Optional<PoliceStaffRegistration>psr=repo.findById(id);
		PoliceStaffRegistration p=psr.get();
		Long id1=p.getId();
		repo.findById(id1).map(staff->{
			if(policeStaffRegistration.getFirstName()!=null) {
				staff.setFirstName(policeStaffRegistration.getFirstName());
			}
			if(policeStaffRegistration.getLastName()!=null){
				staff.setLastName(policeStaffRegistration.getLastName());
			}
			if(policeStaffRegistration.getFatherName()!=null) {
				staff.setFatherName(policeStaffRegistration.getFatherName());
			}
			if(policeStaffRegistration.getGender()!=null) {
				staff.setGender(policeStaffRegistration.getGender());
			}
			if(policeStaffRegistration.getPhoneNumber()!=null) {
				staff.setPhoneNumber(policeStaffRegistration.getPhoneNumber());
			}
			if(policeStaffRegistration.getEmail()!=null) {
				staff.setEmail(policeStaffRegistration.getEmail());
			}
			if(policeStaffRegistration.getAge()!=null) {
				staff.setAge(policeStaffRegistration.getAge());
			}
			if(policeStaffRegistration.getBatchNumber()!=null) {
				staff.setBatchNumber(policeStaffRegistration.getBatchNumber());
			}
			if(policeStaffRegistration.getRank()!=null) {
				staff.setRank(policeStaffRegistration.getRank());
			}
			if(policeStaffRegistration.getJoinedDateInPolice()!=null) {
				staff.setJoinedDateInPolice(policeStaffRegistration.getJoinedDateInPolice());
			}
			return repo.save(staff);	
		});
		return "Updated successfully";

	}
	@PostMapping("/delete/{id}")
	public String deletePoliceStaffRegistration(@PathVariable Long id) {
		//find record
		//delete record
		repo.deleteById(id);
		return "Complaint deleted successfully";

	}
}
