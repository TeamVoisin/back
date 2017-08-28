package com.happyship.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@Table(name = "category")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;

	@Column(name = "category_lib", nullable = false, length = 100)
	private String categoryLib;

	// bi-directional many-to-one association to Article
	@OneToMany(mappedBy = "category")
	private Set<Article> articles;

	public Category() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryLib() {
		return this.categoryLib;
	}

	public void setCategoryLib(String categoryLib) {
		this.categoryLib = categoryLib;
	}

	public Set<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	public Article addArticle(Article article) {
		getArticles().add(article);
		article.setCategory(this);

		return article;
	}

	public Article removeArticle(Article article) {
		getArticles().remove(article);
		article.setCategory(null);

		return article;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", categoryLib=" + categoryLib + ", articles=" + articles + "]";
	}

}