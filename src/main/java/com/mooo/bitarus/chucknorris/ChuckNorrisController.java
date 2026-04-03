package com.mooo.bitarus.chucknorris;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;

@Tag(name = "CNJokeController")
@Controller
public class ChuckNorrisController {
	private final Logger logger = LoggerFactory.getLogger(ChuckNorrisController.class);

	private JokeMapper jokeMapper;
	private JokeService jokeService;

	public ChuckNorrisController(JokeMapper jokeMapper, JokeService jokeService) {
		logger.info("ChuckNorrisController created");
		this.jokeMapper = jokeMapper;
		this.jokeService = jokeService;
	}

	@GetMapping("/chucknorris")
	@ResponseBody
	@Operation(summary = "Gets joke", description = "Gets a Chuck Norris random joke.")
	// https://localhost:8443/chucknorris
	public JokeResponse chucknorris() {
		return jokeMapper.jokeToJokeResponse(this.jokeService.getJoke());
	}

	@GetMapping("/getsavedjokes")
	@ResponseBody
	@Operation(summary = "Gets saved jokes", description = "Gets saved Chuck Norris jokes.")
	public List<JokeResponse> getSavedJokes() {
		return this.jokeService.getSavedJokes();
	}

	@GetMapping("/getsavedjokebyid")
	@ResponseBody
	@Operation(summary = "Get saved joke by id", description = "Get saved Chuck Norris joke by id.")
	public JokeResponse getSavedJokeById(Long jokeId) {
		return this.jokeService.getSavedJokeById(jokeId);
	}

	@GetMapping("/countjokes")
	@ResponseBody
	@Operation(summary = "Get count of saved jokes", description = "Count of saved jokes.")
	public int countJokes() {
		return this.jokeService.countJokes();
	}

	@GetMapping("/getids")
	@ResponseBody
	@Operation(summary = "Get jokes ids", description = "Gets jokes ids.")
	public List<Long> getIds(@RequestParam(defaultValue = "0") int page, // zero-based page index
			@RequestParam(defaultValue = "10") int size, // records per page
			@RequestParam(defaultValue = "id") String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());
		return this.jokeService.getIds(pageable);
	}

	@GetMapping("/getappnameversion")
	@ResponseBody
	@Operation(summary = "Get app name and version", description = "Get app name and version.")
	public AppNameVersionResponse getAppNameVersion() {
		Package mainPackage = Application.class.getPackage();
		String version = mainPackage.getImplementationVersion();
		String title = mainPackage.getImplementationTitle();
		return new AppNameVersionResponse(title, version);
	}

}
