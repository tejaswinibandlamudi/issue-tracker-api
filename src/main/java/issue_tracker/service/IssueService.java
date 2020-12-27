package issue_tracker.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import issue_tracker.dao.IssueRepository;
import issue_tracker.model.Issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Service
public class IssueService {
	
	@Autowired
	public IssueRepository issueRepository;
	
	@Autowired
	public IssueTrackerMailService issueTrackerMailService;
	
	public Issue issue;
	
	public ResponseEntity<String> createNewIssue(Issue issue) {
		issueRepository.save(issue);
		issueTrackerMailService.sendEmail(issue);
		return new ResponseEntity<>(
				"Issue Created!",
				HttpStatus.CREATED);
	}
	
	public @ResponseBody Iterable<Issue> selectAllIssues(){
		return issueRepository.findAll();
	}
	
}
