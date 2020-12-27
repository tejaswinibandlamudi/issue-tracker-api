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

import issue_tracker.model.Admin;
import issue_tracker.model.AdminDao;
import issue_tracker.service.AdminService;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v3/admin")
@RestController
public class AdminController {
	
	private final AdminService AdminService;
	
	@Autowired
	public AdminController(AdminService AdminService) {
		this.AdminService=AdminService;
	}
	
	@PostMapping("/registration")
	public ResponseEntity<String> AdminRegistration(@Valid @NonNull @RequestBody AdminDao AdminToRegister) {
		return AdminService.AdminRegistration(AdminToRegister);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> AdminCheck(@Valid @NonNull @RequestBody AdminDao AdminToCheck) {
		return AdminService.AdminCheck(AdminToCheck);
	}

	@GetMapping("/all")
	public Iterable<Admin> selectAllAdmins(){
		return AdminService.selectAllAdmins();
	}
}
