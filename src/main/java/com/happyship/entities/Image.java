package com.happyship.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "image_model")
public class Image implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private String type;

	@Column(name = "image")
	private byte[] image;

	// bi-directional many-to-one association to Article
	@JsonIgnore
	@OneToMany(mappedBy = "image")
	private Set<Article> articles;

	public Image() {
	}

	public Image(int id, String name, String type, byte[] image) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.image = image;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getimage() {
		return this.image;
	}

	public void setimage(byte[] image) {
		this.image = image;
	}
}
