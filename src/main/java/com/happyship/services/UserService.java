package com.happyship.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyship.dao.IUserDao;
import com.happyship.models.User;

@Service
public class UserService implements IUserService {
	@Autowired
	private IUserDao userDao;

	@Override
	public void saveOrUpdate(User entity) {
		userDao.saveOrUpdate(entity);
	}

	@Override
	public User get(Integer id) {
		User obj = userDao.find(id);
		return obj;
	}

	@Override
	public void add(User entity) {
		userDao.add(entity);
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(User entity) {
		// TODO Auto-generated method stub

	}

}
