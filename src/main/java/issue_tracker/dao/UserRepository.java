package issue_tracker.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import issue_tracker.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	public User findByEmail(@Param("email") String email);
}
