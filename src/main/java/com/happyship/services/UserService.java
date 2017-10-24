package com.happyship.services;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyship.dao.UserDao;
import com.happyship.entities.User;

@Service
public class UserService implements IUserService {
	@Autowired
	private UserDao userDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.happyship.services.IUserService#getAllUsers()
	 */
	@Override
	public List<User> getAllUsers() {

		List<User> users = new ArrayList<>();
		userDao.findAll().forEach(users::add);

		return users;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.happyship.services.IUserService#getUser(java.lang.Integer)
	 */
	@Override
	public User getUser(Integer id) {
		return userDao.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.happyship.services.IUserService#addUser(com.happyship.entities.User)
	 */
	@Override
	public boolean addUser(User user) {
		List<User> users = this.getAllUsers();
		ListIterator<User> it = users.listIterator();
		while (it.hasNext()) {

			if (it.next().getEmail().equals(user.getEmail())) {
				System.out.println(it.next());
				System.out.println("utilisateur déjà enregistré avec cet email");
				return false;
			}
		}
		userDao.save(user);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.happyship.services.IUserService#updateUser(java.lang.Integer,
	 * com.happyship.entities.User)
	 */
	@Override
	public void updateUser(Integer id, User user) {
		userDao.save(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.happyship.services.IUserService#deleteUser(java.lang.Integer)
	 */
	@Override
	public void deleteUser(Integer id) {
		userDao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.happyship.services.IUserService#findByEmail(java.lang.String)
	 */
	@Override
	public User findByEmail(String email) {

		return userDao.findByEmail(email);
	}

}
