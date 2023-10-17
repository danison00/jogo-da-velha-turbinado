package com.dan.jogodavelhaturbinado2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dan.jogodavelhaturbinado2.model.entity.BoardPlayer;
import java.util.List;
import java.util.Optional;

@Repository
public interface BoardPlayerRepository extends JpaRepository<BoardPlayer, Long> {

    Optional<BoardPlayer> findById(Long id);

    @Query("SELECT b from BoardPlayer b WHERE b.id = 1")
    Optional<BoardPlayer> findFullById(@Param("id")Long id);

}
