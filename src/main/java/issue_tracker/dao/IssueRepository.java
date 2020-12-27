package issue_tracker.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import issue_tracker.model.Issue;

public interface IssueRepository extends CrudRepository<Issue, Integer>{
	
}
