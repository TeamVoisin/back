package com.happyship.services;

import java.util.List;

import com.happyship.entities.User;

public interface IUserService {

	List<User> getAllUsers();

	User getUser(Integer id);

	boolean addUser(User user);

	void updateUser(Integer id, User user);

	void deleteUser(Integer id);

	User findByEmail(String email);

}