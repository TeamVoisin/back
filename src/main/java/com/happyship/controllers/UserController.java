package com.happyship.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.happyship.entities.User;
import com.happyship.services.UserService;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = { "http://localhost:4200" })
public class UserController {

	@Autowired
	private UserService userService;

	// ------------ récupère tous les utilisateurs ------------
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List getAllUsers() {
		return userService.getAllUsers();

	}

	// ------------ recupère un utilisateur ------------
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable Integer id) {
		return userService.getUser(id);
	}

	// ------------ crée un utilisateur ------------
	@RequestMapping(value = "/post/user", method = RequestMethod.POST)
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}

	// ------------ met à jour un utilisateur------------
	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	public void updateUser(@RequestBody User user, @PathVariable Integer id) {
		userService.updateUser(id, user);
	}

	// ------------ supprime un utilisateur ------------
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
	}
}
