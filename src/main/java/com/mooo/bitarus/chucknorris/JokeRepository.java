package com.mooo.bitarus.chucknorris;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JokeRepository extends JpaRepository<JokeEntity, Long> {
}
