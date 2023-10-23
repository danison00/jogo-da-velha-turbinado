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
import com.dan.jogodavelhaturbinado2.service.interfaces.BoardPlayerService;
import com.dan.jogodavelhaturbinado2.service.interfaces.GameService;
import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("game")
public class BoardPlayerController {

    @Autowired
    private BoardPlayerService boardPlayerService;

    @Autowired
    private GameService gameSer;

    @Autowired
    private MatrixGameRepository matrixGameRep;

    @Autowired
    private BoardMainRepository boardMainRep;
    @Autowired
    private BoardSecundaryRepository boardSecundaryRep;

    @PostMapping("/newGame")
    public ResponseEntity<?> newBoardPlayer() {

        return ResponseEntity.ok().body(boardPlayerService.newGame());
    }

    @PostMapping("/selectBoardToPlay")
    public ResponseEntity<?> selectboardToPlay(@PathParam("boardPayerId") Long boardPlayerId,
            @PathParam("row") Integer row,
            @PathParam("column") Integer column) throws Exception {

        BoardPlayer board = boardPlayerService.selectBoardToPlay(boardPlayerId, row, column);

        return ResponseEntity.ok().body(board);

    }

    @PostMapping("/markX")
    public ResponseEntity<?> markX(@PathParam("boardPayerId") Long boardPlayerId, @PathParam("row") Integer row,
            @PathParam("column") Integer column) throws Exception {

        MatrixGame matrix = gameSer.markX(boardPlayerId, row, column);
        return ResponseEntity.ok().body(matrix);

    }

    @PostMapping("/markO")
    public ResponseEntity<?> markO(@PathParam("boardPayerId") Long boardPlayerId, @PathParam("row") Integer row,
            @PathParam("column") Integer column) {

        System.out.println(boardPlayerId + " " + row + " " + column);
        MatrixGame matrix = gameSer.markO(boardPlayerId, row, column);
        return ResponseEntity.ok().body(matrix);

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
        // return null;
    }

}
