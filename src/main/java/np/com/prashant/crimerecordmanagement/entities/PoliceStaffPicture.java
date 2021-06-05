package np.com.prashant.crimerecordmanagement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import np.com.prashant.crimerecordmanagement.models.User;

@Entity
@Table(name="policeSaffPicture")
public class PoliceStaffPicture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String uploadDirectiory;
	private String fileName;
	private String fileFormat;
	private String uploadedBy;
	private String uploadedTime;
	
	@ManyToOne
	private User user;
	
	public PoliceStaffPicture() {
		
	}

	public PoliceStaffPicture(String uploadDirectiory, String fileName, String fileFormat, String uploadedBy,
			String uploadedTime,User user) {
		super();
		this.uploadDirectiory = uploadDirectiory;
		this.fileName = fileName;
		this.fileFormat = fileFormat;
		this.uploadedBy = uploadedBy;
		this.uploadedTime = uploadedTime;
		this.user=user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUploadDirectiory() {
		return uploadDirectiory;
	}

	public void setUploadDirectiory(String uploadDirectiory) {
		this.uploadDirectiory = uploadDirectiory;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public String getUploadedTime() {
		return uploadedTime;
	}

	public void setUploadedTime(String uploadedTime) {
		this.uploadedTime = uploadedTime;
	}

	
	

}
