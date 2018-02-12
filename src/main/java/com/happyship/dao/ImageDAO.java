package com.happyship.dao;

import org.springframework.data.repository.CrudRepository;

import com.happyship.entities.Image;

public interface ImageDAO extends CrudRepository<Image, Integer> {

}
