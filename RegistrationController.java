package com.nextgen.contactmanager.Controller;
import com.nextgen.contactmanager.Model.MyUser;
import com.nextgen.contactmanager.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@Configuration
@RestController
public class RegistrationController {
	@Autowired
	private UserRepository repo;
	@Autowired
	PasswordEncoder encoder;

	@PostMapping("/signup/user")
	public RedirectView createUser(@RequestBody MyUser user) {
		user.setPassword(encoder.encode(user.getPassword()));
		repo.save(user);
		return new RedirectView("/login");
	}
	@PostMapping("/register/user")
	public RedirectView newUser(MyUser user) {
		user.setPassword(encoder.encode(user.getPassword()));
		repo.save(user);
		return new RedirectView("/login");
	}
}
