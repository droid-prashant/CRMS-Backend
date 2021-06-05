package np.com.prashant.crimerecordmanagement.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import np.com.prashant.crimerecordmanagement.models.User;
import np.com.prashant.crimerecordmanagement.payload.request.ChangePasswordRequest;
import np.com.prashant.crimerecordmanagement.repository.UserRepository;
import np.com.prashant.crimerecordmanagement.services.UserDetailsImpl;

@RestController
@RequestMapping("/api/changePassword")
public class ChangePasswordController {

	@Autowired
	UserRepository repo;

	@Autowired
	PasswordEncoder encoder;

	@PutMapping("/{email}")
	public ResponseEntity<?>changePassword(String clickme, @PathVariable String email,
			@RequestBody ChangePasswordRequest changePass){
		String username=changePass.getUserName();
		Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Optional<User>userDetails=repo.findByEmail(email);
		User u=userDetails.get();
		String userName=((UserDetailsImpl)principal).getUsername();
		Long id=u.getId();
		if(username.equals(userName)) {
			String newPassword=changePass.getNewPassword();
			String confirmPassword=changePass.getConfirmPassword();
			if(newPassword.equals(confirmPassword)) {
				repo.findById(id).map(user->{
					user.setPassword(encoder.encode(newPassword));	
					return repo.save(user);
				});
				//User user= new User();
				return ResponseEntity.ok("Password changed successfully");
			}else {
				return ResponseEntity.ok("New password and confirm password does not match ");	
			}

		}else {
			return ResponseEntity.ok("Please enter your username correctly");
		}
	}
}