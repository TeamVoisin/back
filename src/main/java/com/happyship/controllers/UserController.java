package com.happyship.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.happyship.models.User;
import com.happyship.services.UserService;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = { "http://localhost:4200" })
public class UserController {

	@Autowired
	private UserService userService;

	// ------------ Retrieve all users ------------
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List getAllUsers() {
		return userService.getAllUsers();

	}

	// ------------ Retrieve a user ------------
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable Integer id) {
		return userService.getUser(id);
	}

	// ------------ Create a user ------------
	@RequestMapping(value = "/post/user", method = RequestMethod.POST)
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}

	// ------------ Update a user ------------
	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	public void updateUser(@RequestBody User user, @PathVariable Integer id) {
		userService.updateUser(id, user);
	}

	// ------------ Delete a user ------------
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
	}
}
