package np.com.prashant.crimerecordmanagement.payload.request;

import javax.validation.constraints.NotBlank;

public class ChangePasswordRequest {
	
	@NotBlank
	private String userName;
	@NotBlank
	private String newPassword;
	@NotBlank
	private String confirmPassword;
	
	
	
	

	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getNewPassword() {
		return newPassword;
	}
	
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
}
