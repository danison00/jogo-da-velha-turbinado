package com.dan.jogodavelhaturbinado2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dan.jogodavelhaturbinado2.model.entity.BoardSecundary;

@Repository
public interface BoardSecundaryRepository extends JpaRepository<BoardSecundary, Long>{
    
}
