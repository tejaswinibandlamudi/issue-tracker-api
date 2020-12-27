package issue_tracker.service;

import org.springframework.stereotype.Service;

import issue_tracker.dao.AdminRepository;
import issue_tracker.model.Admin;
import issue_tracker.model.AdminDao;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Service
public class AdminService {
	
	@Autowired
	public AdminRepository AdminRepository;
	
	public Admin Admin;
	
	public ResponseEntity<String> AdminRegistration(AdminDao AdminDao) {
		Admin = new Admin();
		Admin.setEmail(AdminDao.getEmail());
		if(AdminRepository.findByEmail(Admin.getEmail())!=null) {
			return new ResponseEntity<>(
					"Admin email" + Admin.getEmail() + "Already Present! Try Logging in.",
					HttpStatus.CONFLICT);
		}
		Admin.setHashedPassword(hashPassword(AdminDao.getPassword()));
		AdminRepository.save(Admin);
		return new ResponseEntity<>(
				"Registration Successful!",
				HttpStatus.CREATED);
	}
	
	public ResponseEntity<String> AdminCheck(AdminDao AdminDao) {
		Admin = new Admin();
		Admin.setEmail(AdminDao.getEmail());
		if(AdminRepository.findByEmail(Admin.getEmail())!=null) {
			Admin AdminInDatabase = AdminRepository.findByEmail(Admin.getEmail());
			if(BCrypt.checkpw(AdminDao.getPassword(), AdminInDatabase.getHashedPassword())) {
				return new ResponseEntity<>(
						"Password Correct",
						HttpStatus.OK);
			}
			return new ResponseEntity<>(
					"Password Incorrect!",
					HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(
				"Admin not registered! Please Register.",
				HttpStatus.NOT_FOUND);
	}
	
	private String hashPassword(String plainTextPassword){
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	}
	
	public Iterable<Admin> selectAllAdmins(){
		return AdminRepository.findAll();
	}
	
}
