package np.com.prashant.crimerecordmanagement.controllers;

import java.util.ArrayList;
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
import np.com.prashant.crimerecordmanagement.entities.CriminalRecord;
import np.com.prashant.crimerecordmanagement.entities.FinishedComplaints;
import np.com.prashant.crimerecordmanagement.entities.PublicRegistration;
import np.com.prashant.crimerecordmanagement.repository.AssignedComplaintsRepository;
import np.com.prashant.crimerecordmanagement.repository.FinishedComplaintsRepository;
import np.com.prashant.crimerecordmanagement.repository.PublicRepository;

@RestController
@RequestMapping("/api/finished_complaints")
public class FinishedComplaintsController {

	@Autowired
	FinishedComplaintsRepository repo;
	@Autowired
	AssignedComplaintsRepository acr;
	@Autowired
	PublicRepository pr;

	@PostMapping("/create/{publicId}/{id}")
	public FinishedComplaints saveFinishedComplaints
	(@RequestBody FinishedComplaints finishedComplaints, @PathVariable Long id, @PathVariable Long publicId) {
		Optional<AssignedComplaints>ac=acr.findById(id);
		AssignedComplaints l=ac.get();
		Optional<PublicRegistration>p=pr.findById(publicId);
		PublicRegistration g=p.get();
		FinishedComplaints f= new FinishedComplaints(finishedComplaints.getRemark(),g,l,true);
		
		acr.findById(id).map(updateAssigned->{
			updateAssigned.setRemark("finished");
					return acr.save(updateAssigned);
		});
		return repo.save(f);
	}

	private List<FinishedComplaints>finishedComplaintsList=new ArrayList<>();
	@GetMapping("/list")
	public List<FinishedComplaints>listAll(){
		return repo.findAll();
	}
	@GetMapping("list/{id}")
	public FinishedComplaints getFInish(@PathVariable long id){
		for (FinishedComplaints as:listAll()) {
			if(as.getId()==id) {
				return as;
			}
		}
		return null;
	}

	@PutMapping("update/{id}")
	public String updateFinishedComplaints(@RequestBody FinishedComplaints finishedComplaints, @PathVariable Long id) {
		//find record
		//change record
		if(id==finishedComplaints.getId()) {
			repo.save(finishedComplaints);
			return "Successfully Updated";
		}
		return"failed to Update";
	}
	@PostMapping("/delete/{id}")
	public String deleteFinishedComplaints(@PathVariable Long id) {
		//find record
		//delete record
		repo.deleteById(id);
		return "Complaint deleted successfully";

	}
}
