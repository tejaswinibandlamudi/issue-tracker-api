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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import issue_tracker.model.Issue;
import issue_tracker.service.IssueService;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/issue")
@RestController
public class IssueController {
	
	private IssueService issueService;
	
	@Autowired
	public IssueController(IssueService issueService) {
		this.issueService=issueService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> userRegistration(@Valid @NonNull @RequestBody Issue issue) {
		return issueService.createNewIssue(issue);
	}
	
	@GetMapping("/all")
	public @ResponseBody Iterable<Issue> selectAllIssues() {
		return issueService.selectAllIssues();
	}
	@GetMapping("")
	public String test() {
		return "Hello World!";
	}
}
