package com.happyship.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.happyship.entities.Article;

@Repository
public interface ArticleDao extends CrudRepository<Article, Integer> {

}
