package np.com.prashant.crimerecordmanagement.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import np.com.prashant.crimerecordmanagement.entities.AssignedComplaints;
import np.com.prashant.crimerecordmanagement.entities.PoliceStaffRegistration;
import np.com.prashant.crimerecordmanagement.entities.PublicRegistration;
import np.com.prashant.crimerecordmanagement.repository.AssignedComplaintsRepository;
import np.com.prashant.crimerecordmanagement.repository.PoliceRepository;
import np.com.prashant.crimerecordmanagement.repository.PublicRepository;

@RestController
@RequestMapping("/api/assigned_Complaints")
@PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
public class AssignedComplaintsController {
	@Autowired
	AssignedComplaintsRepository repo;

	@Autowired
	PublicRepository publicRepository;
	@Autowired
	PoliceRepository p_repo;
	@PostMapping("/create/{publicId}/{id}")
	public AssignedComplaints saveAssignedComplaints
	(@RequestBody AssignedComplaints assignedComplaints, @PathVariable Long publicId, @PathVariable Long id) {
		
		Optional<PublicRegistration>pr=publicRepository.findById(publicId);
		Optional<PoliceStaffRegistration>qr=p_repo.findById(id);
		PublicRegistration p=pr.get();
		PoliceStaffRegistration q=qr.get();
		AssignedComplaints a= new AssignedComplaints(assignedComplaints.getRecruitedOfficers(),
				assignedComplaints.getRemark(),p,q, true);

		publicRepository.findById(publicId).map(updatePublic->{
			updatePublic.setRemark("assigned");
			return publicRepository.save(updatePublic);
		});
		return repo.save(a);

	}

	@GetMapping("/list")
	public List<AssignedComplaints>listAll() {
		return repo.findAll();
	}
	@GetMapping("list/{id}")
	public Optional<AssignedComplaints> getAssign(@PathVariable long id){
		return repo.findById(id);
	}
	@GetMapping("listComplaintsByPregId/{pregId}")
	public List<AssignedComplaints> getBypregId(@PathVariable long pregId){
		return repo.findAssignedComplaintsByPregId(pregId);
	
	}
}
