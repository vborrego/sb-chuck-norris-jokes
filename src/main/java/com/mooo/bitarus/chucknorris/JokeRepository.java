package com.mooo.bitarus.chucknorris;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JokeRepository extends JpaRepository<JokeEntity, Long> {
    List<JokeEntity> findByIdValue(Long id);

    int countJokes();

    @Query("SELECT j.id FROM JokeEntity j ")
    List<Long> getIds(Pageable pageable);
}
