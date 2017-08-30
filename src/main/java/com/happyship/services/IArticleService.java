package com.happyship.services;

import java.util.List;

import com.happyship.entities.Article;

public interface IArticleService {

	Article getArticle(Integer id);

	void addArticle(Article article);

	void updateArticle(Integer id, Article article);

	void deleteArticle(Integer id);

	List getAllArticles();;

}
