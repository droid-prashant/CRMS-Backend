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
import np.com.prashant.crimerecordmanagement.entities.PoliceStaffRegistration;
import np.com.prashant.crimerecordmanagement.repository.CriminalRecordRepository;

@RestController
@RequestMapping("api/criminalRecord")
public class CriminalRecordController {
	@Autowired
	CriminalRecordRepository repo;	
	@PostMapping("/create")
	public CriminalRecord saveCriminalRecord(@RequestBody CriminalRecord criminalRecord) {
		CriminalRecord c= new CriminalRecord(criminalRecord.getFirstName(),
				criminalRecord.getLastName(), criminalRecord.getDateOfBirth(),
				criminalRecord.getAge(),criminalRecord.getGender(),
				criminalRecord.getAddress(),criminalRecord.getCitizenshipId(),
				criminalRecord.getCrimeDone(),
				criminalRecord.getTelNo(),criminalRecord.getPunishmentDetails(),true
				);

		return repo.save(c);
	}
	private List<CriminalRecord>criminalList= new ArrayList();
	@GetMapping("/List")
	public List<CriminalRecord>listAll(){
		return repo.findAll();
	}
	@GetMapping("list/{id}")
	public CriminalRecord getCriminal(@PathVariable long id){
		for(CriminalRecord as:listAll()) {
			if(as.getId()==id) {
				return as;
			}
		}
		return null;
	}
	
	@PutMapping("update/{id}")
	public String updateCriminalRecord(@RequestBody CriminalRecord criminalRecord, @PathVariable Long id) {
		Optional<CriminalRecord>criminalDetails=repo.findById(id);
		CriminalRecord c=criminalDetails.get();
		Long id1=c.getId();
		repo.findById(id1).map(criminal->{
			if(criminalRecord.getFirstName()!=null) {
				criminal.setFirstName(criminalRecord.getFirstName());
			}
			if(criminalRecord.getLastName()!=null) {
				criminal.setLastName(criminalRecord.getLastName());
			}
			if(criminalRecord.getDateOfBirth()!=null) {
			criminal.setDateOfBirth(criminalRecord.getDateOfBirth());
			}
			if(criminalRecord.getGender()!=null) {
				criminal.setGender(criminalRecord.getGender());
			}
			
			if(criminalRecord.getCrimeDone()!=null) {
			criminal.setCrimeDone(criminalRecord.getCrimeDone());
			}
			return repo.save(criminal);
		});
			return"Updated Successfully";
	}
	@PostMapping("/delete/{id}")
	public String deleteCriminalRecord(@PathVariable Long id) {
		//find record
		//delete record
		repo.deleteById(id);
		return "Record deleted successfully";

	}
}



