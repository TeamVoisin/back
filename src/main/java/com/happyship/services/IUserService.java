package com.happyship.services;

import java.util.List;

import com.happyship.entities.User;

public interface IUserService {

	List getAllUsers();

	User getUser(Integer id);

	void addUser(User user);

	void updateUser(Integer id, User user);

	void deleteUser(Integer id);

	User findByEmail(String email);

}