package com.happyship.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the groupe database table.
 * 
 */
@Entity
@Table(name = "groupe")
@NamedQuery(name = "Groupe.findAll", query = "SELECT g FROM Groupe g")
public class Groupe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;

	@Column(name = "group_lib", nullable = false, length = 100)
	private String groupLib;

	// bi-directional many-to-many association to Article
	@ManyToMany
	@JoinTable(name = "groupe_article", joinColumns = {
			@JoinColumn(name = "groupe_id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "article_id", nullable = false) })
	private Set<Article> articles;

	// bi-directional many-to-many association to User
	@ManyToMany(mappedBy = "groupes")
	private Set<User> users;

	public Groupe() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupLib() {
		return this.groupLib;
	}

	public void setGroupLib(String groupLib) {
		this.groupLib = groupLib;
	}

	public Set<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Groupe [id=" + id + ", groupLib=" + groupLib + ", articles=" + articles + ", users=" + users + "]";
	}

}