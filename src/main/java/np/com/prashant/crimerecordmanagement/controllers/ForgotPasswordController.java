package np.com.prashant.crimerecordmanagement.controllers;

import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import np.com.prashant.crimerecordmanagement.exceptions.ResourceNotFoundException;
import np.com.prashant.crimerecordmanagement.models.User;
import np.com.prashant.crimerecordmanagement.payload.request.ChangePasswordRequest;
import np.com.prashant.crimerecordmanagement.repository.UserRepository;
import np.com.prashant.crimerecordmanagement.services.EmailService;
import np.com.prashant.crimerecordmanagement.services.OtpService;
import np.com.prashant.crimerecordmanagement.utils.AppConstants;

@RestController
@RequestMapping("/api/forgotPassword")
public class ForgotPasswordController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public OtpService otpService;

	@Autowired
	public EmailService emailService;

	@Autowired
	PasswordEncoder encoder;

	@PostMapping("/changePassword/{username}")
	public ResponseEntity<?>forgotPassword(String clickme, @PathVariable String username,
			@RequestBody ChangePasswordRequest changePassword){
		String newPassword=changePassword.getNewPassword();
		String confirmPassword=changePassword.getConfirmPassword();
		try {
			if(newPassword.equals(confirmPassword)) {
				userRepository.findByUsername(username).map(changePass->{
					changePass.setPassword(encoder.encode(newPassword));
					return userRepository.save(changePass);
				}).orElseThrow(()->new ResourceNotFoundException("Username"+username+"not found"));
				return ResponseEntity.ok("Password change successfully");
			}else {
				return ResponseEntity.ok("Cannot change");
			}
		}catch(Exception e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}

	@PostMapping("/generateOtp")
	public String generateOtp(@RequestBody User user) {
		String username= user.getUsername();
		String email=user.getEmail();

		if(userRepository.existsByUsername(username)) {
			Optional<User>
			u=userRepository.findByUsername(username);
			User userDetail=u.get();
			String validEmail=userDetail.getEmail();

			if(email.equals(validEmail)) {
				int otp=otpService.generateOTP(username);
				String message="Hello"+username+"use this otp to validate your email:"+String.valueOf(otp);
				try {
					emailService.sendOtpMessage(validEmail, "OTP to validate email", message);
				}catch(MessagingException e) {
					e.printStackTrace();
				}
				String clickme=ServletUriComponentsBuilder.fromCurrentContextPath()
						.path(AppConstants.PATH_FOR_OTP_VALIDATION).path(username).toUriString();
				return ("follow this link"+clickme);
			}else {
				return ("This email doesnot match with this username");

			}}
		else {
			return("This username does not exist");
		}

	}
	@PostMapping("/validateOtp/{username}")
	public String validateOtp(@RequestParam("otpnum") int otpnum,@PathVariable String username){

		final String fail = "Entered Otp is NOT valid. Please Retry!";

		//Validate the Otp 
		if(otpnum >= 0) {
			int serverOtp = otpService.getotp(username);
			if(serverOtp > 0){
				if(otpnum == serverOtp){
					otpService.clearOtp(username);

					String clickme = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.PATH_FOR_PASSWORD_CHANGE)
							.path(username).toUriString();


					return (clickme);
				} 
				else {
					return fail;
				}
			}else {
				return fail;
			}
		}else {
			return fail;
		}

	}
}
