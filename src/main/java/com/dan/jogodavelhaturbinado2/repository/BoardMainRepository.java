package com.dan.jogodavelhaturbinado2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dan.jogodavelhaturbinado2.model.entity.BoardMain;

@Repository
public interface BoardMainRepository extends JpaRepository<BoardMain, Long>{

}
