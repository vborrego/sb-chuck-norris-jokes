package com.mooo.bitarus.chucknorris;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;


@NamedQuery(name = "JokeEntity.countJokes", query = "SELECT COUNT(j) FROM JokeEntity j")
@NamedQuery(name = "JokeEntity.findByIdValue", query = "SELECT j FROM JokeEntity j WHERE j.id = :id")
@Entity
public class JokeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String joke;

	public JokeEntity() {
	}

	public String getJoke() {
		return this.joke;
	}

	public void setJoke(String joke) {
		this.joke = joke;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.format("<Joke %d %s>", this.id, this.joke);
	}
}
