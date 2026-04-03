package com.mooo.bitarus.chucknorris;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.reactive.function.client.WebClient;

@Component
public class JokeService {
	private final Logger logger = LoggerFactory.getLogger(JokeService.class);
	private JokeRepository repo;
	private WebClient webClient;

	public JokeService(@Value("${chucknorris.url}") String chuckNorrisURL, JokeRepository repo,
			JokeMapper jokeMapper) {
		logger.info("JokeService created");
		logger.info("chuckNorris URL {}", chuckNorrisURL);
		this.repo = repo;
		this.webClient = WebClient.create(chuckNorrisURL);
	}

	@Transactional
	public Joke getJoke() {
		Joke joke = null;

		try {
			joke = this.webClient.get().retrieve().bodyToMono(Joke.class).toFuture().get();
		} catch (Exception ex) {
		}

		String ret = "";
		if (joke.getValue().length() <= 255)
			ret = joke.getValue();
		else
			ret = joke.getValue().substring(0, 255);

		JokeEntity je = new JokeEntity();
		je.setJoke(ret);
		JokeEntity saved = repo.saveAndFlush(je);
		logger.info(repo.count() + " " + saved);
		return joke;
	}

	public List<JokeResponse> getSavedJokes() {
		var jokes = new ArrayList<JokeResponse>();
		repo.findAll().forEach(item -> {
			var jokeText = new JokeResponse(item.getJoke());
			jokes.add(jokeText);
		});
		return jokes;
	}

	public int countJokes() {
		return repo.countJokes();
	}

	public List<Long> getIds(Pageable pageable) {
		return repo.getIds(pageable);
	}

	public JokeResponse getSavedJokeById(Long jokeId) {
		var jokeResponse = new JokeResponse();
		var res = repo.findByIdValue(jokeId);

		if (res.size() > 0) {
			var entity = repo.findByIdValue(jokeId).getFirst();
			jokeResponse = new JokeResponse(entity.getJoke());
		}
		return jokeResponse;
	}
}
