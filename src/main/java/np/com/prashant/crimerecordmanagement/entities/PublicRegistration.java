package np.com.prashant.crimerecordmanagement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="public_details")
public class PublicRegistration {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long publicId;
	private String firstName;
	private String lastName;
	private String gender;
	private String age;
	private String address;
	private String phNum;
	private String citizenshipNumber;
	private String incidentDetails;
	private String incidentAddress;
	private String remark;

	@JsonIgnore
	@OneToOne(mappedBy = "pr")
	private AssignedComplaints ac;

	@JsonIgnore
	@OneToOne(mappedBy = "pr")
	private FinishedComplaints fc;


	public PublicRegistration() {


	}

	public PublicRegistration(String firstName, String lastName, String gender, String age, String address,
			String phNum,String citizenshipNumber, String incidentDetails, String incidentAddress, String remark) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.address = address;
		this.phNum = phNum;
		this.citizenshipNumber=citizenshipNumber;
		this.incidentDetails = incidentDetails;
		this.incidentAddress = incidentAddress;
		this.remark = "unassigned";
	}


	public Long getPublicId() {
		return publicId;
	}

	public void setPublicId(Long publicId) {
		this.publicId = publicId;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhNum() {
		return phNum;
	}
	public void setPhNum(String phNum) {
		this.phNum = phNum;
	}

	public String getCitizenshipNumber() {
		return citizenshipNumber;
	}
	public void setCitizenshipNumber(String citizenshipNumber) {
		this.citizenshipNumber = citizenshipNumber;
	}

	public String getIncidentDetails() {
		return incidentDetails;
	}

	public void setIncidentDetails(String incidentDetails) {
		this.incidentDetails = incidentDetails;
	}

	public String getIncidentAddress() {
		return incidentAddress;
	}

	public void setIncidentAddress(String incidentAddress) {
		this.incidentAddress = incidentAddress;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
