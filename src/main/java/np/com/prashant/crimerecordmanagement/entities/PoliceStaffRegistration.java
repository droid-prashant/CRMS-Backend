package np.com.prashant.crimerecordmanagement.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import np.com.prashant.crimerecordmanagement.models.User;

@Entity
@Table(name="police_record")
public class PoliceStaffRegistration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String fatherName;
	private String gender;
	private String phoneNumber;
	private String email;
	private String age;
	private String batchNumber;
	private String rank;
	private String joinedDateInPolice;
	private boolean isActive;
	
	@JsonIgnore
	@OneToMany(mappedBy = "preg")
	private List<AssignedComplaints>asc= new ArrayList<>();
	
	@OneToOne
	private User obj;

	
	public PoliceStaffRegistration() {

	}


	public PoliceStaffRegistration(String firstName, String lastName, String fatherName, String gender, String phoneNumber,
			String email, String age, String batchNumber, String rank, String joinedDateInPolice, boolean isActive, User obj) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.fatherName = fatherName;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.age = age;
		this.batchNumber = batchNumber;
		this.rank = rank;
		this.joinedDateInPolice = joinedDateInPolice;
		this.isActive = isActive;
		this.obj=obj;
	}

	public User getObj() {
		return obj;
	}


	public void setObj(User obj) {
		this.obj = obj;
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


	public String getFatherName() {
		return fatherName;
	}


	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getBatchNumber() {
		return batchNumber;
	}


	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}


	public String getRank() {
		return rank;
	}


	public void setRank(String rank) {
		this.rank = rank;
	}


	public String getJoinedDateInPolice() {
		return joinedDateInPolice;
	}


	public void setJoinedDateInPolice(String joinedDateInPolice) {
		this.joinedDateInPolice = joinedDateInPolice;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public List<AssignedComplaints> getAsc() {
		return asc;
	}


	public void setAsc(List<AssignedComplaints> asc) {
		this.asc = asc;
	}


	@Override
	public String toString() {
		return "PoliceStaffRegistration [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", fatherName="
				+ fatherName + ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", email=" + email + ", age=" + age
				+ ", batchNumber=" + batchNumber + ", rank=" + rank + ", joinedDateInPolice=" + joinedDateInPolice
				+ ", isActive=" + isActive + "]";
	}




}

