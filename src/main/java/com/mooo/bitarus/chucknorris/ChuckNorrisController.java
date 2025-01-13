package com.mooo.bitarus.chucknorris;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Tag(name="CNJokeController")
@Controller
public class ChuckNorrisController{
    private final Logger logger = LoggerFactory.getLogger(ChuckNorrisController.class);

    @Value("${chucknorris.url}")
    private String chuckNorrisURL;
    private ObjectMapper objectMapper;
    private JokeRepository repo;
    private JPAQueryFactory queryFactory;
    private JokeMapper jokeMapper;

    public ChuckNorrisController(JokeRepository repo, JPAQueryFactory queryFactory, JokeMapper jokeMapper){
        logger.info("ChuckNorrisController created");
        this.objectMapper = new ObjectMapper();
        this.repo = repo;
        this.queryFactory = queryFactory;
        this.jokeMapper = jokeMapper;
    }

    @GetMapping("/chucknorris")
    @ResponseBody
    @Operation(summary="Gets joke",  description="Gets a Chuck Norris random joke.")
    // http://localhost:8080/chucknorris
    public JokeResponse chucknorris() {
        String ret = "";
        Joke joke=null;

        try {
            URL url = new URL(chuckNorrisURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            logger.info( Integer.toString(connection.getResponseCode() ));

            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                joke = this.objectMapper.readValue(response.toString(), Joke.class);
                List<Joke> jokesList = new ArrayList<Joke>();
                jokesList.add(joke);
                List<Joke> jokesList2 = jokesList.stream().map( item -> {
                    item.setValue(item.getValue().toUpperCase());
                    return item;
                } ).toList();
                
                joke = jokesList2.get(0);
                if(joke.getValue().length() <=255 )
                  ret = joke.getValue();
                else
                  ret = joke.getValue().substring(0,255);

                JokeEntity je = new JokeEntity();
                je.setJoke(ret);
                JokeEntity saved = repo.saveAndFlush(je);
                logger.info( repo.count()  + " " + saved  );
            }

        }
        catch(Exception ex){
            logger.error("error",ex);
        }

        return jokeMapper.jokeToJokeResponse(joke);
    }

    @GetMapping("/getsavedjokes")
    @ResponseBody
    @Operation(summary="Gets saved jokes",  description="Gets saved Chuck Norris jokes.")
    public List<JokeProjection> getSavedJokes() {
        QJokeEntity jokeEntity = QJokeEntity.jokeEntity;
        return queryFactory
                .select( new QJokeProjection( jokeEntity.id , jokeEntity.joke )  )
                .from(jokeEntity)
                .fetch();
    }

    @GetMapping("/getsavedjokebyid")
    @ResponseBody
    @Operation(summary="Get saved joke by id",
      description="Get saved Chuck Norris joke by id.")
    public JokeProjection getSavedJokeById(Long jokeId) {
        QJokeEntity jokeEntity = QJokeEntity.jokeEntity;
        List<JokeProjection> ret = queryFactory
                .select( new QJokeProjection( jokeEntity.id , jokeEntity.joke )  )
                .from(jokeEntity)
                .where(jokeEntity.id.eq(jokeId))
                .fetch();
        if(ret.size() > 0) return ret.get(0);
        return null;
    }
}

