package com.happyship.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyship.dao.UserDao;
import com.happyship.entities.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public List getAllUsers() {

		List users = new ArrayList<>();
		userDao.findAll().forEach(users::add);

		return users;
	}

	public User getUser(Integer id) {
		return userDao.findOne(id);
	}

	public void addUser(User user) {
		userDao.save(user);
	}

	public void updateUser(Integer id, User user) {
		userDao.save(user);
	}

	public void deleteUser(Integer id) {
		userDao.delete(id);
	}

	public User findByEmail(String email) {

		return userDao.findByEmail(email);
	}

}
