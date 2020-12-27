package issue_tracker.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import issue_tracker.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer>{
	public Admin findByEmail(@Param("email") String email);
}

