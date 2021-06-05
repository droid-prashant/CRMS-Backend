package np.com.prashant.crimerecordmanagement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="finished_complaints")
public class FinishedComplaints {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String remark;
	private boolean isActive;
	
	@OneToOne
	private PublicRegistration pr;
	
	@OneToOne
	private AssignedComplaints ac;
	
	public FinishedComplaints() {

	}

	public FinishedComplaints( String remark, PublicRegistration pr, AssignedComplaints ac, boolean isActive) {
		super();
		this.remark = "finished";
		this.pr=pr;
		this.ac=ac;
		this.isActive=true;


	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public PublicRegistration getPr() {
		return pr;
	}

	public void setPr(PublicRegistration pr) {
		this.pr = pr;
	}

	public AssignedComplaints getAc() {
		return ac;
	}

	public void setAc(AssignedComplaints ac) {
		this.ac = ac;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	
}



