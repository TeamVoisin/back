package com.happyship.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.happyship.entities.User;
import com.happyship.services.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = { "http://localhost:4200" })
public class UserController {

	@Autowired
	private UserService userService;

	// ------------ récupère tous les utilisateurs ------------
	@RequestMapping(method = RequestMethod.GET)
	public List getAllUsers() {
		return userService.getAllUsers();

	}

	// ------------ recupère un utilisateur ------------
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable Integer id) {
		return userService.getUser(id);
	}

	// ------------ crée un utilisateur ------------
	@RequestMapping(method = RequestMethod.POST)
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}

	// ------------ met à jour un utilisateur------------
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateUser(@RequestBody User user, @PathVariable Integer id) {
		userService.updateUser(id, user);
	}

	// ------------ supprime un utilisateur ------------
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
	}

	// ------recoit des infos du formulaire de login-------
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestBody Map<String, String> json) throws ServletException {
		if (json.get("email") == null || json.get("password") == null) {
			throw new ServletException("veuillez remplir les champs");
		}

		String email = json.get("email");
		String password = json.get("password");

		User user = userService.findByEmail(email);

		if (user == null) {
			throw new ServletException("pas d'email trouvé");
		}

		String pwd = user.getPassword();

		if (!password.equals(pwd)) {
			throw new ServletException("le mot de passe ne correspond pas au mail");
		}

		// retourne un jeton qui servira à authentifier l'individu
		return Jwts.builder().setSubject(email).claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secret key").compact();

	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public User user(@RequestBody String email) {
		System.out.println("+++++++++++++++++++++++++++++" + email + "+++++++++++++++++++++++++");
		User user = userService.findByEmail(email);
		return user;
	};

}
