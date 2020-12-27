package issue_tracker.service;

import org.springframework.stereotype.Service;

import issue_tracker.dao.UserRepository;
import issue_tracker.model.User;
import issue_tracker.model.UserDao;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Service
public class UserService {
	
	@Autowired
	public UserRepository userRepository;
	
	public User user;
	
	public ResponseEntity<String> userRegistration(UserDao userDao) {
		user = new User();
		user.setEmail(userDao.getEmail());
		if(userRepository.findByEmail(user.getEmail())!=null) {
			return new ResponseEntity<>(
					"User email" + user.getEmail() + "Already Present! Try Logging in.",
					HttpStatus.CONFLICT);
		}
		user.setHashedPassword(hashPassword(userDao.getPassword()));
		userRepository.save(user);
		return new ResponseEntity<>(
				"Registration Successful!",
				HttpStatus.CREATED);
	}
	
	public ResponseEntity<String> userCheck(UserDao userDao) {
		user = new User();
		user.setEmail(userDao.getEmail());
		if(userRepository.findByEmail(user.getEmail())!=null) {
			User userInDatabase = userRepository.findByEmail(user.getEmail());
			if(BCrypt.checkpw(userDao.getPassword(), userInDatabase.getHashedPassword())) {
				return new ResponseEntity<>(
						"Password Correct",
						HttpStatus.OK);
			}
			return new ResponseEntity<>(
					"Password Incorrect!",
					HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(
				"User not registered! Please Register.",
				HttpStatus.NOT_FOUND);
	}
	
	private String hashPassword(String plainTextPassword){
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	}
	
	public Iterable<User> selectAllUsers(){
		return userRepository.findAll();
	}
	
}
