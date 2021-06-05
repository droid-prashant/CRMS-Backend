package np.com.prashant.crimerecordmanagement.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="criminal_record")
public class CriminalRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String age;
	private String gender;
	private String address;
	private String citizenshipId;
	private String crimeDone;
	private String telNo;
	private String punishmentDetails;
	private boolean isActive;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cr")
	private List<CriminalPicture>cp=new ArrayList<>();
	
	public CriminalRecord() {
		
	}


	
	public CriminalRecord(String firstName, String lastName, String dateOfBirth, String age, String gender,
			String address, String citizenshipId, String crimeDone, String telNo, String punishmentDetails,
			boolean isActive) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.citizenshipId = citizenshipId;
		this.crimeDone = crimeDone;
		this.telNo = telNo;
		this.punishmentDetails = punishmentDetails;
		this.isActive = true;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
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



	public String getDateOfBirth() {
		return dateOfBirth;
	}



	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}



	public String getAge() {
		return age;
	}



	public void setAge(String age) {
		this.age = age;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getCitizenshipId() {
		return citizenshipId;
	}



	public void setCitizenshipId(String citizenshipId) {
		this.citizenshipId = citizenshipId;
	}



	public String getCrimeDone() {
		return crimeDone;
	}



	public void setCrimeDone(String crimeDone) {
		this.crimeDone = crimeDone;
	}



	public String getTelNo() {
		return telNo;
	}



	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}



	public String getPunishmentDetails() {
		return punishmentDetails;
	}



	public void setPunishmentDetails(String punishmentDetails) {
		this.punishmentDetails = punishmentDetails;
	}



	public boolean isActive() {
		return isActive;
	}



	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}



	@Override
	public String toString() {
		return "CriminalRecord [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", gender=" + gender + ", citizenshipId=" + citizenshipId + ", crimeDone=" + crimeDone
				+ ", punishmentDetails=" + punishmentDetails + ", isActive=" + isActive + "]";
	}
	
	
	
	
}
