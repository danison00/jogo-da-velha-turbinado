package com.dan.jogodavelhaturbinado2.service.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dan.jogodavelhaturbinado2.gameLogistics.interfaces.BoardPlayerGameLogistic;
import com.dan.jogodavelhaturbinado2.model.entity.BoardMain;
import com.dan.jogodavelhaturbinado2.model.entity.BoardPlayer;
import com.dan.jogodavelhaturbinado2.model.entity.BoardSecundary;
import com.dan.jogodavelhaturbinado2.model.entity.MatrixGame;
import com.dan.jogodavelhaturbinado2.repository.BoardMainRepository;
import com.dan.jogodavelhaturbinado2.repository.BoardPlayerRepository;
import com.dan.jogodavelhaturbinado2.repository.BoardSecundaryRepository;
import com.dan.jogodavelhaturbinado2.service.interfaces.BoardPlayerService;

import jakarta.transaction.Transactional;

@Service
public class BoardPlayerServiceImp implements BoardPlayerService {

    @Autowired
    private BoardPlayerRepository boardPlayerRep;

    @Autowired
    private BoardMainRepository boardMainRep;

    @Autowired
    private BoardSecundaryRepository boardSecundaryRep;

    @Autowired
    private BoardPlayerGameLogistic boardPlayerGameLogistic;



    @Transactional
    @Override
    public BoardPlayer save(BoardPlayer boardPlayer) {

        return boardPlayerRep.saveAndFlush(boardPlayer);
    }

    @Override
    public List<BoardPlayer> findAll() {

        return boardPlayerRep.findAll();
    }

    @Override
    public BoardPlayer findById(Long id) {

        return boardPlayerRep.findById(id).orElseThrow(() -> new RuntimeException("id inexistente!"));

    }

    @Override
    public void deleteById(Long id) {
        boardPlayerRep.deleteById(id);
    }

    @Override
    public BoardPlayer selectBoardToPlay(Long boardPlayerId, int row, int column) {

        BoardPlayer boardPlayer = findById(boardPlayerId);

        if(boardPlayer.getBoardSecundaryCurrent() != null)
            throw new RuntimeException("termine o jogo anterior");

        boardPlayer = this.boardPlayerGameLogistic.selectBoardToPlay(boardPlayer, row, column);

        return this.boardPlayerRep.saveAndFlush(boardPlayer);      
    }

    @Transactional
    @Override
    public BoardPlayer newGame() {

        BoardPlayer boardPlayer = new BoardPlayer();
        BoardMain main = new BoardMain(new BoardPlayer(), new MatrixGame());
        main.setBoardPlayer(boardPlayer);

        List<BoardSecundary> secundaries = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            secundaries.add(new BoardSecundary(new MatrixGame(), boardPlayer));
        }

        boardMainRep.saveAndFlush(main);
        boardSecundaryRep.saveAll(secundaries);
        
        return this.save(boardPlayer);
    }

 

}
