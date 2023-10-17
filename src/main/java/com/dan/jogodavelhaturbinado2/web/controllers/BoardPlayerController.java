package com.dan.jogodavelhaturbinado2.web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dan.jogodavelhaturbinado2.model.entity.BoardMain;
import com.dan.jogodavelhaturbinado2.model.entity.BoardPlayer;
import com.dan.jogodavelhaturbinado2.model.entity.BoardSecundary;
import com.dan.jogodavelhaturbinado2.model.entity.MatrixGame;
import com.dan.jogodavelhaturbinado2.repository.BoardMainRepository;
import com.dan.jogodavelhaturbinado2.repository.BoardSecundaryRepository;
import com.dan.jogodavelhaturbinado2.repository.MatrixGameRepository;
import com.dan.jogodavelhaturbinado2.service.BoardPlayerService;
import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.websocket.server.PathParam;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("boardPlayer")
public class BoardPlayerController {

    @Autowired
    private BoardPlayerService boardPlayerService;

    @Autowired
    private MatrixGameRepository matrixGameRep;

    @Autowired
    private BoardMainRepository boardMainRep;
    @Autowired
    private BoardSecundaryRepository boardSecundaryRep;

    @GetMapping("/newGame")
    public ResponseEntity<?> newBoardPlayer() {
        
        return ResponseEntity.ok().body(boardPlayerService.newGame());
    }

    @PostMapping("/selectBoardToPlay")
    public ResponseEntity<?> selectboardToPlay(@PathParam("boardPayerId") Long boardPlayerId, @PathParam("l") Integer l,
            @PathParam("c") Integer c) {

        var board = boardPlayerService.selectBoardToPlay(boardPlayerId, l, c);

        return ResponseEntity.ok().body(board);
    }



    @GetMapping("/test2")
    public ResponseEntity<?> testmatrix2() {

        var boardPlayer = boardPlayerService.findById(Long.valueOf(1));
        // var matrix = this.matrixGameRep.findById(Long.valueOf(1)).get();

        // var matrixAsList = matrix.getAsList();

        // matrixAsList.get(1).set(1, "X");

        // matrix.putInLocs(matrixAsList);

        // System.out.println(matrix);
        // System.out.println(matrixAsList);
        // System.out.println(matrix.getAsList());

         return ResponseEntity.ok().body(boardPlayer);
        //return null;
    }

}
