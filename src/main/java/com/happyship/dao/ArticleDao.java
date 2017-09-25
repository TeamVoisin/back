package com.happyship.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.happyship.entities.Article;

@Repository
public interface ArticleDao extends CrudRepository<Article, Integer> {
	@Query("SELECT a FROM Article a  WHERE a.description LIKE CONCAT('%',:keyword,'%') OR a.title LIKE CONCAT('%',:keyword,'%') ")
	List<Article> findByKeyword(@Param(value = "keyword") String keyword);

	@Transactional
	@Modifying
	@Query("Update Article Set description = :description, title = :title Where id= :id")
	void update(@Param("id") Integer id, @Param("description") String description, @Param("title") String title);
}
