package com.happyship.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.happyship.models.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {

}
