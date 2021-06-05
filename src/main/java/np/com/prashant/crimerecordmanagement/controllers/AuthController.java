package np.com.prashant.crimerecordmanagement.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import np.com.prashant.crimerecordmanagement.models.ERole;
import np.com.prashant.crimerecordmanagement.models.Role;
import np.com.prashant.crimerecordmanagement.models.User;
import np.com.prashant.crimerecordmanagement.payload.request.LoginRequest;
import np.com.prashant.crimerecordmanagement.payload.request.SignupRequest;
import np.com.prashant.crimerecordmanagement.payload.response.JwtResponse;
import np.com.prashant.crimerecordmanagement.payload.response.MessageResponse;
import np.com.prashant.crimerecordmanagement.repository.RoleRepository;
import np.com.prashant.crimerecordmanagement.repository.UserRepository;
import np.com.prashant.crimerecordmanagement.services.EmailService;
import np.com.prashant.crimerecordmanagement.services.OtpService;
import np.com.prashant.crimerecordmanagement.security.jwt.JwtUtils;
import np.com.prashant.crimerecordmanagement.services.UserDetailsImpl;
import np.com.prashant.crimerecordmanagement.utils.AppConstants;

@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping("api/auth")

public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	EmailService emailService;

	@Autowired
	OtpService otpService;

	@PostMapping("/signin")
	public ResponseEntity<?>authenticatorUser(@Valid @RequestBody LoginRequest loginRequest){
		Authentication authentication= authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
						loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt=jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails= (UserDetailsImpl) authentication.getPrincipal();
		List<String>roles = userDetails.getAuthorities().stream().
				map(item->item.getAuthority()).collect(Collectors.toList());

		
		Optional<User>us=userRepository.findByUsername(loginRequest.getUsername());
		User u= us.get();
		boolean isEnabled=u.isEnabled();
		if(isEnabled== true) {
			return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getId(),
					userDetails.getUsername(),  userDetails.getEmail(),roles));

		}
		else {
//			String username= loginRequest.getUsername();
//			String clickme=ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.PATH_FOR_OTP_GENERATE)
//					.path(username).toUriString();
//
//					 return ResponseEntity.ok(new MessageResponse("Follow this link and verify your email first: "+clickme));
			String username= loginRequest.getUsername();
			Optional<User> user = userRepository.findByUsername(username);
			User user1 = user.get();
			String email = user1.getEmail();
			
			int otp=otpService.generateOTP(username);
			String message="Hello"+username+"use this otp to validate your email: "+String.valueOf(otp);
			try {
				emailService.sendOtpMessage(email, "OTP to validate email", message);
				
				
				return ResponseEntity.ok(new MessageResponse("Check your email you neeed to verify it"));
			}catch(MessagingException e) {
				return ResponseEntity.ok(new MessageResponse(e.getMessage()));
			}

			
		}


	}

	@PostMapping("/signup")
	public ResponseEntity<?>registerUser(@Valid @RequestBody SignupRequest signupRequest){
		if(userRepository.existsByUsername(signupRequest.getUsername())){
			return ResponseEntity.ok("Error: Username is already taken");  
		}
		if(userRepository.existsByEmail(signupRequest.getEmail())) {
			return ResponseEntity.ok("Error: Email is already in use!");
		}
		User user= new User(signupRequest.getUsername(),
				signupRequest.getEmail(),
				encoder.encode(signupRequest.getPassword()));
		Set<String>strRoles= signupRequest.getRole();
		Set<Role>roles=new HashSet<>();
		if(strRoles== null) {
			Role userRole= roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(()->new RuntimeException("Error: Role not found"));
			System.out.println("I'm Null!");
			roles.add(userRole);
		}
		else {
			strRoles.forEach(role->{
				switch(role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case"moderator" :
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default :
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					System.out.println("I'm from default");
					roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		String username= signupRequest.getUsername();
		String email=signupRequest.getEmail();
		int otp=otpService.generateOTP(username);
		String message="Hello"+username+"use this otp to validate your email:"+String.valueOf(otp);
		try {
			emailService.sendOtpMessage(email, "OTP to validate email", message);
			userRepository.save(user);
			String clickme=ServletUriComponentsBuilder.fromCurrentContextPath()
					.path(AppConstants.PATH_FOR_EMAIL_VERIFICATION).path(username).toUriString();
			return ResponseEntity.ok("follow this link"+clickme);
		}catch(MessagingException e) {
			return ResponseEntity.ok("Please use a valid email");
		}



	}

}


