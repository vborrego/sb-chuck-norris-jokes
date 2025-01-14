package com.mooo.bitarus.chucknorris;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.ArrayList;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="CNJokeController")
@Controller
public class ChuckNorrisController{
    private final Logger logger = LoggerFactory.getLogger(ChuckNorrisController.class);

    private JokeMapper jokeMapper;
    private JokeService jokeService;

    public ChuckNorrisController(JokeMapper jokeMapper, JokeService jokeService){
        logger.info("ChuckNorrisController created");
        this.jokeMapper = jokeMapper;
        this.jokeService = jokeService;
    }

    @GetMapping("/chucknorris")
    @ResponseBody
    @Operation(summary="Gets joke",  description="Gets a Chuck Norris random joke.")
    // http://localhost:8181/chucknorris
    public JokeResponse chucknorris() {
        return jokeMapper.jokeToJokeResponse(this.jokeService.getJoke());
    }

    @GetMapping("/getsavedjokes")
    @ResponseBody
    @Operation(summary="Gets saved jokes",  description="Gets saved Chuck Norris jokes.")
    public List<JokeProjection> getSavedJokes() {
        return this.jokeService.getSavedJokes();                
    }

    @GetMapping("/getsavedjokebyid")
    @ResponseBody
    @Operation(summary="Get saved joke by id", description="Get saved Chuck Norris joke by id.")
    public JokeProjection getSavedJokeById(Long jokeId) {
        return this.jokeService.getSavedJokeById(jokeId);
    }
}

