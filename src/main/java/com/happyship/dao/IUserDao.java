package com.happyship.dao;

import java.util.List;

import com.happyship.models.User;

public interface IUserDao {

	void add(User entity);

	void saveOrUpdate(User entity);

	void update(User entity);

	void remove(User entity);

	User find(Integer key);

	List<User> getAll();
}
