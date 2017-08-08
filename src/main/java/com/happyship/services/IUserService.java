package com.happyship.services;

import java.util.List;

import com.happyship.models.User;

public interface IUserService {

	void saveOrUpdate(User entity);

	List<User> getAll();

	User get(Integer id);

	void add(User entity);

	void update(User entity);

	void remove(User entity);

}
