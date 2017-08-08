package happyship.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

	@RequestMapping("/happyship")
	public String index() {
		return "Greetings from  Boot!";
	}

	@RequestMapping("/tr")
	public String test() {
		return "Greetings from  Boot!";
	}
}