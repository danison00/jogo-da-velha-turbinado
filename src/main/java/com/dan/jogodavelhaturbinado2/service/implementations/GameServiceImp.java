package com.dan.jogodavelhaturbinado2.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dan.jogodavelhaturbinado2.gameLogistics.interfaces.GameLogistics;
import com.dan.jogodavelhaturbinado2.model.entity.BoardPlayer;
import com.dan.jogodavelhaturbinado2.model.entity.BoardSecundary;
import com.dan.jogodavelhaturbinado2.model.entity.MatrixGame;
import com.dan.jogodavelhaturbinado2.service.interfaces.BoardPlayerService;
import com.dan.jogodavelhaturbinado2.service.interfaces.GameService;
import com.dan.jogodavelhaturbinado2.service.interfaces.MatrixGameService;

@Service
public class GameServiceImp implements GameService {

    @Autowired
    private BoardPlayerService boardPlayerSer;

    @Autowired
    private GameLogistics gameLogistics;

    @Autowired
    private MatrixGameService matrixGameSer;

    @Override
    public BoardPlayer markX(Long boardPlayerId, int row, int column) {
       

        BoardSecundary board = boardPlayerSer.findById(boardPlayerId).getBoardSecundaryCurrent();

        MatrixGame matrixGame = board.getMatrixGame();

        matrixGame = gameLogistics.markX(matrixGame, row, column);
        
        matrixGameSer.save(matrixGame);

        return null;
    }
    
}
