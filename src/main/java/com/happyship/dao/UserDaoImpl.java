package com.happyship.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.happyship.models.User;

@Transactional
@Repository
public class UserDaoImpl implements IUserDao {
	private EntityManager entityManager;

	@Override
	public void add(User user) {
		entityManager.persist(user);

	}

	@Override
	public void saveOrUpdate(User user) {
		User use = find(user.getId());
		use.setAddress(user.getAddress());
		use.setDateInscription(user.getDateInscription());
		use.setEmail(user.getEmail());
		use.setFirstname(user.getFirstname());
		use.setName(user.getName());
		entityManager.flush();
	}

	@Override
	public User find(Integer id) {
		return entityManager.find(User.class, id);

	}

	@Override
	public void update(User entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(User entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
