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
	@Autowired
	ArticleDao articleDao;

	// ------------ récupère tous les articles ------------
	@RequestMapping(method = RequestMethod.GET)
	public List getAllArticles() {
		return articleService.getAllArticles();

	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public List searchAllArticles(@RequestBody Map<String, String> json) {
		String keyword = json.get("keyword");
		System.out.println(keyword);
		List<Article> list = articleDao.findByKeyword(keyword);
		return list;

	}

	// ------------ recupère un article ------------
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Article getArticle(@PathVariable Integer id) {
		return articleService.getArticle(id);
	}

	// ------------ crée un article ------------
	@RequestMapping(value = "/article", method = RequestMethod.POST)
	// ici on récupère un json du client
	public String addArticle(@RequestBody Map<String, String> json) {

		// on se sert de l'email pour retrouver l'utilisateur qui envoie l'article
		String email = json.get("email");
		User user = userService.findByEmail(email);
		Article article = new Article();
		article.setUser(user);
		// on récupère l'id pour trouver la cat et l'associer
		Integer category_id = Integer.parseInt(json.get("category_id"));
		Category category = categoryDao.findOne(category_id);
		article.setCategory(category);

		// on recupere les autres champs pour les associer à l'article
		String title = json.get("title");
		String url = json.get("url");
		String description = json.get("description");
		article.setDescription(description);
		article.setTitle(title);
		article.setUrl(url);
		System.out.println(article);
		// on enregistre l'article
		articleService.addArticle(article);
		return "article créé avec succès";

	}

	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	public Set getArticles(@RequestBody String email) {
		User user = userService.findByEmail(email);
		Set setArticle = user.getArticles();
		System.out.println("*****************************" + setArticle + "*********************");
		return setArticle;
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