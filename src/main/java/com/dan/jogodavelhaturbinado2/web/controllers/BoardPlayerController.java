package com.dan.jogodavelhaturbinado2.web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dan.jogodavelhaturbinado2.model.entity.BoardPlayer;
import com.dan.jogodavelhaturbinado2.service.BoardPlayerService;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("boardPlayer")
public class BoardPlayerController {
    
    @Autowired
    private BoardPlayerService boardPlayerService;

    @GetMapping("/newBoardPlayer")
    public ResponseEntity<?> newBoardPlayer() throws JsonProcessingException {
        var boardPlayer = new BoardPlayer();
        boardPlayer.newGame();
        boardPlayer = boardPlayerService.save(new BoardPlayer());
        
        return ResponseEntity.ok().body(boardPlayer);
    }
    
}
