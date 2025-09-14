package com.mooo.bitarus.chucknorris;

import com.querydsl.core.annotations.QueryProjection;

public record JokeProjection(Long id, String joke) {

	@QueryProjection
	public JokeProjection {
	}
}
