package np.com.prashant.crimerecordmanagement.controllers;

import java.util.List; 
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import np.com.prashant.crimerecordmanagement.entities.AssignedComplaints;
import np.com.prashant.crimerecordmanagement.entities.CaseDetails;
import np.com.prashant.crimerecordmanagement.repository.AssignedComplaintsRepository;
import np.com.prashant.crimerecordmanagement.repository.CaseDetailsRepository;

@RestController
@RequestMapping("/api/caseDetails")
public class CaseDetailsController {
	@Autowired
	CaseDetailsRepository repo;
	
	@Autowired
	AssignedComplaintsRepository acr;
	
	@PostMapping("/create/{id}")
	public CaseDetails saveCaseDetails(@RequestBody CaseDetails caseDetails, @PathVariable Long id) {
		Optional<AssignedComplaints>ac=acr.findById(id);
		AssignedComplaints a=ac.get();
		CaseDetails c= new CaseDetails(caseDetails.getDate(),
				caseDetails.getDescription(),a);
		return repo.save(c);
	}
	
	@GetMapping("/list")
	public List<CaseDetails>ListAll(){
		return repo.findAll();
	}
	
	@GetMapping("/listByAcId/{acId}")
	public List<CaseDetails>listByAcId(@PathVariable Long acId){
		
		return repo.findByAcId(acId);
	}

	@PutMapping("update/{id}")
	public String updateCaseDetails(@RequestBody CaseDetails caseDetails, @PathVariable Long id) {
		Optional<CaseDetails>c=repo.findById(id);
		CaseDetails cd=c.get();
		Long id1=cd.getId();
		repo.findById(id1).map(mapper->{
			
			if(caseDetails.getDate()!=null) {
				mapper.setDate(caseDetails.getDate());
				
			}
			
			if(caseDetails.getDescription()!=null) {
				mapper.setDescription(caseDetails.getDescription());
			}
			
			return repo.save(mapper);
		});
		return "Updated Successfully";
	
	}
	
}
