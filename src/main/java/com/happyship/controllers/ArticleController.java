package com.happyship.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.happyship.dao.CategoryDao;
import com.happyship.entities.Article;
import com.happyship.entities.Category;
import com.happyship.entities.User;
import com.happyship.services.IArticleService;
import com.happyship.services.IUserService;

@RestController
@RequestMapping("/articles")
@CrossOrigin(origins = { "http://localhost:4200" })

public class ArticleController {
	@Autowired
	private IUserService userService;
	@Autowired
	private IArticleService articleService;
	@Autowired
	CategoryDao categoryDao;

	// ------------ récupère tous les articles ------------
	@RequestMapping(method = RequestMethod.GET)
	public List getAllArticles() {
		return articleService.getAllArticles();

	}

	// ------------ recupère un article ------------
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Article getArticle(@PathVariable Integer id) {
		return articleService.getArticle(id);
	}

	// ------------ crée un article ------------
	@RequestMapping(value = "/article", method = RequestMethod.POST)
	public void addArticle(@RequestBody Map<String, String> json) {

		String email = json.get("email");
		System.out.println("*****************" + email + "******************");
		String title = json.get("title");
		String url = json.get("url");
		String description = json.get("description");
		Integer category_id = Integer.parseInt(json.get("category_id"));
		Category category = categoryDao.findOne(category_id);
		Article article = new Article();
		article.setDescription(description);
		article.setCategory(category);
		article.setTitle(title);
		article.setUrl(url);
		User user = userService.findByEmail(email);
		System.out.println(user);
		article.setUser(user);
		System.out.println(article);
		articleService.addArticle(article);

	}

	// ------------ met à jour un article------------
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateArticle(@RequestBody Article article, @PathVariable Integer id) {
		articleService.updateArticle(id, article);
	}

	// ------------ supprime un article ------------
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteArticle(@PathVariable Integer id) {
		articleService.deleteArticle(id);
	}

}