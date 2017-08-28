package com.happyship.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyship.dao.UserDao;
import com.happyship.entities.User;

@Service
public class UserService implements IUserService {
	@Autowired
	private UserDao userDao;

	/* (non-Javadoc)
	 * @see com.happyship.services.IUserService#getAllUsers()
	 */
	@Override
	public List getAllUsers() {

		List users = new ArrayList<>();
		userDao.findAll().forEach(users::add);

		return users;
	}

	/* (non-Javadoc)
	 * @see com.happyship.services.IUserService#getUser(java.lang.Integer)
	 */
	@Override
	public User getUser(Integer id) {
		return userDao.findOne(id);
	}

	/* (non-Javadoc)
	 * @see com.happyship.services.IUserService#addUser(com.happyship.entities.User)
	 */
	@Override
	public void addUser(User user) {
		userDao.save(user);
	}

	/* (non-Javadoc)
	 * @see com.happyship.services.IUserService#updateUser(java.lang.Integer, com.happyship.entities.User)
	 */
	@Override
	public void updateUser(Integer id, User user) {
		userDao.save(user);
	}

	/* (non-Javadoc)
	 * @see com.happyship.services.IUserService#deleteUser(java.lang.Integer)
	 */
	@Override
	public void deleteUser(Integer id) {
		userDao.delete(id);
	}

	/* (non-Javadoc)
	 * @see com.happyship.services.IUserService#findByEmail(java.lang.String)
	 */
	@Override
	public User findByEmail(String email) {

		return userDao.findByEmail(email);
	}

}
