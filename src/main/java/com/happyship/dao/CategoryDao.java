package com.happyship.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.happyship.entities.Category;

@Repository
public interface CategoryDao extends CrudRepository<Category, Integer> {

}
