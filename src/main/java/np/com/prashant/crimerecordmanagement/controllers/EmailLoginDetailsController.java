package np.com.prashant.crimerecordmanagement.controllers;

import java.util.Optional;

import javax.mail.MessagingException;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import np.com.prashant.crimerecordmanagement.models.User;
import np.com.prashant.crimerecordmanagement.repository.UserRepository;
import np.com.prashant.crimerecordmanagement.services.EmailService;

@RestController
@RequestMapping("/api/sendMail")
public class EmailLoginDetailsController {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	PasswordEncoder encoder;

	@PostMapping("loginDetails/{email}")
	public String sendMail(@PathVariable String email) {
		if(userRepository.existsByEmail(email)) {
			Optional<User>userDetails=userRepository.findByEmail(email);
			User u=userDetails.get();
			String username=u.getUsername();
			String validEmail=u.getEmail();
			if(email.equals(validEmail)) {
				String message="Hello" +email+ "Your username is:"
			    +username+ "Your password is: p0lice123";
				
				try {
					emailService.sendOtpMessage(email, "This is your username "
							+ "and your password", message);

				}catch(MessagingException e) {
					e.printStackTrace();
				}
			}
		}
			return "username and password sent";
	}
	
	

	}
