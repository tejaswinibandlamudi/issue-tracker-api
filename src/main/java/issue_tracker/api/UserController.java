package issue_tracker.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import issue_tracker.model.User;
import issue_tracker.model.UserDao;
import issue_tracker.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v3/user")
@RestController
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService=userService;
	}
	
	@PostMapping("/registration")
	public ResponseEntity<String> userRegistration(@Valid @NonNull @RequestBody UserDao userToRegister) {
		return userService.userRegistration(userToRegister);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> userCheck(@Valid @NonNull @RequestBody UserDao userToCheck) {
		return userService.userCheck(userToCheck);
	}

	@GetMapping("/all")
	public Iterable<User> selectAllUsers(){
		return userService.selectAllUsers();
	}
}
