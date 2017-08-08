package happyship.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.happyship.models.User;
import com.happyship.services.IUserService;

@Controller
@RequestMapping("/register")
@CrossOrigin(origins = { "http://localhost:4200/" })
public class UserController {

	@Autowired
	private IUserService userService;

	@PostMapping("/register")
	public ResponseEntity<Void> add(@RequestBody User user, UriComponentsBuilder builder) {
		userService.add(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/register?id={id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping("user")
	public ResponseEntity<User> updateArticle(@RequestBody User user) {
		userService.saveOrUpdate(user);
		;
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
