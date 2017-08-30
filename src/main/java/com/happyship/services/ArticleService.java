package com.happyship.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyship.dao.ArticleDao;
import com.happyship.entities.Article;

@Service
public class ArticleService implements IArticleService {
	@Autowired
	private ArticleDao articleDao;

	public List getAllArticles() {
		List articles = new ArrayList<Article>();
		articleDao.findAll().forEach(articles::add);

		return articles;
	}

	public Article getArticle(Integer id) {
		return articleDao.findOne(id);
	}

	public void addArticle(Article article) {

		articleDao.save(article);
	}

	public void updateArticle(Integer id, Article article) {
		articleDao.save(article);
	}

	public void deleteArticle(Integer id) {
		articleDao.delete(id);
	}

}