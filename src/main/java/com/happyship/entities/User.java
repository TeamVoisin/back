package com.happyship.entities;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name = "user")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;

	@Column(nullable = false, length = 100)
	private String address;

	@Column(nullable = false, length = 50)
	private String locality;

	@Column(nullable = false, length = 6, name = "postal_code")
	private int postalCode;

	@Column(nullable = false, name = "date_inscription")
	private String dateInscription = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

	@Column(nullable = false, length = 100)
	private String email;

	@Column(nullable = false, length = 100)
	private String firstname;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(nullable = false, length = 255)
	private String password;

	@Column(nullable = false)
	double latitude;

	@Column(nullable = false)
	double longitude;

	// bi-directional many-to-one association to Article
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private Set<Article> articles;

	// bi-directional many-to-many association to Groupe
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "user_groupe", joinColumns = {
			@JoinColumn(name = "user_id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "groupe_id", nullable = false) })
	private Set<Groupe> groupes;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDateInscription() {
		return this.dateInscription;
	}

	public void setDateInscription(String dateInscription) {
		this.dateInscription = dateInscription;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	public Article addArticle(Article article) {
		getArticles().add(article);
		article.setUser(this);

		return article;
	}

	public Article removeArticle(Article article) {
		getArticles().remove(article);
		article.setUser(null);

		return article;
	}

	public Set<Groupe> getGroupes() {
		return this.groupes;
	}

	public void setGroupes(Set<Groupe> groupes) {
		this.groupes = groupes;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public int getPostal_code() {
		return postalCode;
	}

	public void setPostal_code(int postalCode) {
		this.postalCode = postalCode;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public LatLng getALatitude(String address, int postalCode, String locality) {
		GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyDpuov5CcCDJk04BZfJkS0W4aiuIR-BIsE").build();
		GeocodingResult[] results = null;
		try {
			results = GeocodingApi.geocode(context, address + postalCode + locality).await();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return results[0].geometry.location;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", address=");
		builder.append(address);
		builder.append(", locality=");
		builder.append(locality);
		builder.append(", postalCode=");
		builder.append(postalCode);
		builder.append(", dateInscription=");
		builder.append(dateInscription);
		builder.append(", email=");
		builder.append(email);
		builder.append(", firstname=");
		builder.append(firstname);
		builder.append(", name=");
		builder.append(name);
		builder.append(", password=");
		builder.append(password);
		builder.append(", latitude=");
		builder.append(latitude);
		builder.append(", longitude=");
		builder.append(longitude);
		builder.append(", articles=");
		builder.append(articles);
		builder.append(", groupes=");
		builder.append(groupes);
		builder.append("]");
		return builder.toString();
	}

}
