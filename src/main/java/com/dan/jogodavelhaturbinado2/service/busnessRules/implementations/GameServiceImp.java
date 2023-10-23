package com.dan.jogodavelhaturbinado2.service.busnessRules.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dan.jogodavelhaturbinado2.model.entity.BoardPlayer;
import com.dan.jogodavelhaturbinado2.model.entity.BoardSecundary;
import com.dan.jogodavelhaturbinado2.model.entity.MatrixGame;
import com.dan.jogodavelhaturbinado2.repository.BoardSecundaryRepository;
import com.dan.jogodavelhaturbinado2.service.busnessRules.interfaces.BoardPlayerService;
import com.dan.jogodavelhaturbinado2.service.busnessRules.interfaces.GameService;
import com.dan.jogodavelhaturbinado2.service.gameRules.interfaces.GameLogistics;

import jakarta.transaction.Transactional;

@Service
public class GameServiceImp implements GameService {

    @Autowired
    private BoardPlayerService boardPlayerSer;

    @Autowired
    private GameLogistics gameLogistics;


    @Autowired
    private BoardSecundaryRepository boardSecundaryRep;

    @Transactional
    @Override
    public MatrixGame markX(Long boardPlayerId, int row, int column) throws Exception {

        BoardPlayer boardPlayer = boardPlayerSer.findById(boardPlayerId);
        BoardSecundary boardSec = boardPlayer.getBoardSecundaryCurrent();

        if (boardPlayer.getBoardSecundaryCurrent() == null)
            throw new RuntimeException("selecione um jogo");

        if (!boardPlayer.getPlayerCurrent().equals("X"))
            throw new RuntimeException("Aguarde o jogador O jogar");

        MatrixGame matrixGame = boardSec.getMatrixGame();

        if (!matrixGame.getAsList().get(row).get(column).isEmpty())
            throw new RuntimeException("Casa já marcada");

        matrixGame = gameLogistics.markX(matrixGame, row, column);
        boardSec.incrementNumberOfMarked();

        boardSec = gameLogistics.routine(boardSec);
        boardPlayer.setPlayerCurrent("O");

        if (boardSec.isFinished()) {
            boardPlayer.setBoardSecundaryCurrent(null);
            boardPlayerSer.save(boardPlayer);
        }

        boardSecundaryRep.save(boardSec);

        return matrixGame;
    }

    @Transactional
    @Override
    public MatrixGame markO(Long boardPlayerId, Integer row, Integer column) {

        BoardPlayer boardPlayer = boardPlayerSer.findById(boardPlayerId);
        BoardSecundary boardSec = boardPlayer.getBoardSecundaryCurrent();

        if (boardPlayer.getBoardSecundaryCurrent() == null)
            throw new RuntimeException("selecione um jogo");

        if (!boardPlayer.getPlayerCurrent().equals("O"))
            throw new RuntimeException("Aguarde o jogador X jogar");
            
        MatrixGame matrixGame = boardSec.getMatrixGame();

        if (!matrixGame.getAsList().get(row).get(column).isEmpty())
            throw new RuntimeException("Casa já marcada");

        matrixGame = gameLogistics.markO(matrixGame, row, column);
        boardSec.incrementNumberOfMarked();

        boardSec = gameLogistics.routine(boardSec);

        boardPlayer.setPlayerCurrent("X");

        if (boardSec.isFinished()) {
            boardPlayer.setBoardSecundaryCurrent(null);
            boardPlayerSer.save(boardPlayer);
        }

        boardSecundaryRep.save(boardSec);

        return matrixGame;
    }

}
