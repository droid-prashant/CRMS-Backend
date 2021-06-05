package np.com.prashant.crimerecordmanagement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="criminalPicture")
public class CriminalPicture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String uploadDirectiory;
	private String fileName;
	private String fileFormat;
	private String uploadedBy;
	private String uploadedTime;
	@ManyToOne
	private CriminalRecord cr;
	public CriminalPicture() {
		
	}
	public CriminalPicture(String uploadDirectiory, String fileName, String fileFormat, CriminalRecord cr,
			String uploadedBy, String uploadedTime) {
		super();
		this.uploadDirectiory = uploadDirectiory;
		this.fileName = fileName;
		this.fileFormat = fileFormat;
		this.cr=cr;
		this.uploadedBy=uploadedBy;
		this.uploadedTime=uploadedTime;
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
	public String getFileFormat() {
		return fileFormat;
	}
	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}
	public CriminalRecord getCr() {
		return cr;
	}
	public void setCr(CriminalRecord cr) {
		this.cr = cr;
	}
	
	
	

}
