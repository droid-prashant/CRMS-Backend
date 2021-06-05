package np.com.prashant.crimerecordmanagement.controllers;

import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import np.com.prashant.crimerecordmanagement.exceptions.ResourceNotFoundException;
import np.com.prashant.crimerecordmanagement.models.User;
import np.com.prashant.crimerecordmanagement.payload.response.MessageResponse;
import np.com.prashant.crimerecordmanagement.repository.UserRepository;
import np.com.prashant.crimerecordmanagement.services.EmailService;
import np.com.prashant.crimerecordmanagement.services.OtpService;
import np.com.prashant.crimerecordmanagement.utils.AppConstants;

@RestController
@RequestMapping("/api/email")
public class EmailVerificationController {
	@Autowired
	public OtpService otpService;

	@Autowired
	public EmailService emailService;

	@Autowired
	UserRepository userRepository;

	@PostMapping("/generateOtp/{username}")
	public  String generateOtp(@PathVariable String username, 
			@RequestBody User user) {
		if(userRepository.existsByUsername(username)){

			String email=user.getEmail();
			Optional<User>userDetail=userRepository.findByUsername(username);
			User u=userDetail.get();
			String validEmail=u.getEmail();
			if(email.equals(validEmail)) {
				
				int otp=otpService.generateOTP(username);
				String message="Hello"+username+
						"use this OTP to validate your email:"+String.valueOf(otp);
				try {
					emailService.sendOtpMessage(email, "Otp to validate email", message);

				}catch(MessagingException e) {
					e.printStackTrace();
				}
				String clickme = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path(AppConstants.PATH_FOR_EMAIL_VERIFICATION)
						.path(username).toUriString();

				return clickme;

			}else {
				return "The email you provided for registration did not match this email.";
			}
		}
		else {
			return "This username does not exist";
		}
	}
	@PostMapping("/emailVerification/{username}")
	public ResponseEntity<?> emailVerification(@RequestParam("otpnum") 
	int otpnum,@PathVariable String username){

		//Validate the Otp 
		if(otpnum >= 0){

			int serverOtp = otpService.getotp(username);
			if(serverOtp > 0){
				if(otpnum == serverOtp){
					otpService.clearOtp(username);

					userRepository.findByUsername(username).map(enableUser->{
						enableUser.setEnabled(true);
						return userRepository.save(enableUser);
					}).orElseThrow(()->new ResourceNotFoundException("Username"+username+"not found"));
					return ResponseEntity.ok(new MessageResponse("Email verified you can log into the system now."));
				}
				else {
					return  ResponseEntity.ok(new MessageResponse("Otp did not match"));
				}
			}else
			{
				return ResponseEntity.ok(new MessageResponse("No otp found in the server for "+username));
			}
		}else{
			{
				return ResponseEntity.ok(new MessageResponse("No otp passed by user."));
			}
		}
	}
}
