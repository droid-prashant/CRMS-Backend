package np.com.prashant.crimerecordmanagement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="case_details")
public class CaseDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String date;
	private String description;
	private boolean isActive;
	
	@ManyToOne
	private AssignedComplaints ac;

	public CaseDetails() {

	}

	public CaseDetails(String date, String description, AssignedComplaints ac) {
		super();
		this.date = date;
		this.description = description;
		this.isActive=false;
		this.ac=ac;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public AssignedComplaints getAc() {
		return ac;
	}

	public void setAc(AssignedComplaints ac) {
		this.ac = ac;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
