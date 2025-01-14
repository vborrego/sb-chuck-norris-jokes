package com.mooo.bitarus.chucknorris;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.ArrayList;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.client.RestClient.UriSpec;
import org.springframework.web.client.RestClient.RequestBodySpec;
import reactor.core.publisher.Mono;
import java.util.concurrent.Future;

@Component
public class JokeService{
    private final Logger logger = LoggerFactory.getLogger(JokeService.class);
    //private ObjectMapper objectMapper;
    private JokeRepository repo;
    private JPAQueryFactory queryFactory;
    private WebClient client;
    private Joke responseJoke;

    public JokeService(@Value("${chucknorris.url}") String chuckNorrisURL, JokeRepository repo, JPAQueryFactory queryFactory, JokeMapper jokeMapper){
        logger.info("JokeService created");
        logger.info("chuckNorris URL {}", chuckNorrisURL);
        //this.objectMapper = new ObjectMapper();
        this.repo = repo;
        this.queryFactory = queryFactory;
        this.client = WebClient.create(chuckNorrisURL);        
    }

    public Joke getJoke() {
        Joke joke=null;

        try {
          joke = this.client.get().retrieve().bodyToMono(Joke.class).toFuture().get();
        }
        catch(Exception ex){            
        }

        String ret = "";
        if(joke.getValue().length() <=255 )
            ret = joke.getValue();
        else
            ret = joke.getValue().substring(0,255);

        JokeEntity je = new JokeEntity();
        je.setJoke(ret);
        JokeEntity saved = repo.saveAndFlush(je);
        logger.info(repo.count() + " " + saved  );
        return joke;
    }

    public List<JokeProjection> getSavedJokes() {
        QJokeEntity jokeEntity = QJokeEntity.jokeEntity;
        return queryFactory
                .select( new QJokeProjection( jokeEntity.id , jokeEntity.joke )  )
                .from(jokeEntity)
                .fetch();
    }

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

