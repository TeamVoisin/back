package com.happyship.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.happyship.dao.ArticleDao;
import com.happyship.dao.CategoryDao;
import com.happyship.entities.Article;
import com.happyship.entities.Category;
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
	@Autowired
	ArticleDao articleDao;

	// ------------ récupère tous les articles ------------
	@RequestMapping(method = RequestMethod.GET)
	public List getAllArticles() {
		return articleService.getAllArticles();

	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public List searchAllArticles(@RequestBody Map<String, String> json) {
		List<Article> list = articleDao.findByKeyword(json.get("keyword"));
		return list;

	}

	// ------------ crée un article ------------
	@RequestMapping(value = "/article", method = RequestMethod.POST)
	// ici on récupère un json du client
	public String addArticle(@RequestBody Map<String, String> json) {

		// on se sert de l'email pour retrouver l'utilisateur qui envoie l'article
		Article article = new Article();
		article.setUser(userService.findByEmail(json.get("email")));
		// on récupère l'id pour trouver la cat et l'associer
		Category category = categoryDao.findOne(Integer.parseInt(json.get("category_id")));
		article.setCategory(category);

		// on recupere les autres champs pour les associer à l'article
		article.setDescription(json.get("description"));
		article.setTitle(json.get("title"));
		article.setUrl(json.get("url"));
		// on enregistre l'article
		articleService.addArticle(article);
		return "article créé avec succès";

	}

	// récupère une liste d'article
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	public Set getArticles(@RequestBody String email) {
		Set setArticle = userService.findByEmail(email).getArticles();
		return setArticle;
	}

	// ------------ supprime un article mais avec un POST donc ne respecte pas le
	// REST------------
	/*
	 * @RequestMapping(value = "/delete", method = RequestMethod.POST) public void
	 * deleteArticle(@RequestBody Map<String, String> json) { Integer id =
	 * Integer.parseInt(json.get("id")); articleService.deleteArticle(id); }
	 */

	// ---------------supprime un article avec un delete----------
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteArticle(@PathVariable Integer id) {
		articleService.deleteArticle(id);
	}

	// ------------ met à jour un article ------------
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void updateArticle(@RequestBody Map<String, String> json) {
		articleService.updateArticle(Integer.parseInt(json.get("id")), json.get("description"), json.get("title"));

	}

}