package com.mooo.bitarus.chucknorris;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JokeMapper {

    @Mapping(source = "value" , target = "response")
    JokeResponse jokeToJokeResponse(Joke joke);

    @Mapping(source = "response", target = "value")
    Joke jokeResponseToJoke(JokeResponse jokeResponse);
}

