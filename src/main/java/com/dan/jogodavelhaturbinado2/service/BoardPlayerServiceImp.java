package com.dan.jogodavelhaturbinado2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dan.jogodavelhaturbinado2.model.entity.BoardPlayer;
import com.dan.jogodavelhaturbinado2.repository.BoardPlayerRepository;

@Service
public class BoardPlayerServiceImp implements BoardPlayerService {

    @Autowired
    private BoardPlayerRepository boardPlayerRepository;

    @Override
    public BoardPlayer save(BoardPlayer boardPlayer) {

        return boardPlayerRepository.saveAndFlush(boardPlayer);
    }

    @Override
    public List<BoardPlayer> findAll() {
        
        return  boardPlayerRepository.findAll();
    }

    @Override
    public BoardPlayer findById(Long id) {
        
        return boardPlayerRepository.findById(id).orElseThrow(() -> new RuntimeException("id inexistente!"));

    }

    @Override
    public void deleteById(Long id) {
       boardPlayerRepository.deleteById(id);
    }

}
