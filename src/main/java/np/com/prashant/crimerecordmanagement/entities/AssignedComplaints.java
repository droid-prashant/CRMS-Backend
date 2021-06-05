package np.com.prashant.crimerecordmanagement.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="assigned_complaints")
public class AssignedComplaints {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String recruitedOfficers;
	private String remark;
	private boolean isActive;
	

	
	@JsonIgnore
	@OneToMany(mappedBy = "ac")
	private List<CaseDetails>cd= new ArrayList<>();
	
	@JsonIgnore
	@OneToOne(mappedBy = "ac")
	private FinishedComplaints fc;
	
	@OneToOne
	private PublicRegistration pr;
	
	@ManyToOne
	private PoliceStaffRegistration preg;

	public AssignedComplaints() {

	}

	public AssignedComplaints(String recruitedOfficers,String remark, PublicRegistration pr, PoliceStaffRegistration preg, boolean isActive) {
		super();
		this.recruitedOfficers=recruitedOfficers;
		this.remark = "assigned";
		this.pr=pr;		
		this.preg=preg;
		this.isActive=true;
	}

	public PoliceStaffRegistration getPreg() {
		return preg;
	}

	public void setPreg(PoliceStaffRegistration preg) {
		this.preg = preg;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecruitedOfficers() {
		return recruitedOfficers;
	}

	public void setRecruitedOfficers(String recruitedOfficers) {
		this.recruitedOfficers = recruitedOfficers;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<CaseDetails> getCd() {
		return cd;
	}

	public void setCd(List<CaseDetails> cd) {
		this.cd = cd;
	}

	public FinishedComplaints getFc() {
		return fc;
	}

	public void setFc(FinishedComplaints fc) {
		this.fc = fc;
	}
	
	
	
}
