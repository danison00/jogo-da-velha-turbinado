package com.dan.jogodavelhaturbinado2.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dan.jogodavelhaturbinado2.gameLogistics.interfaces.GameLogistics;
import com.dan.jogodavelhaturbinado2.model.entity.BoardPlayer;
import com.dan.jogodavelhaturbinado2.model.entity.BoardSecundary;
import com.dan.jogodavelhaturbinado2.model.entity.MatrixGame;
import com.dan.jogodavelhaturbinado2.repository.BoardSecundaryRepository;
import com.dan.jogodavelhaturbinado2.service.interfaces.BoardPlayerService;
import com.dan.jogodavelhaturbinado2.service.interfaces.GameService;
import com.dan.jogodavelhaturbinado2.service.interfaces.MatrixGameService;

import jakarta.transaction.Transactional;

@Service
public class GameServiceImp implements GameService {

    @Autowired
    private BoardPlayerService boardPlayerSer;

    @Autowired
    private GameLogistics gameLogistics;

    @Autowired
    private MatrixGameService matrixGameSer;

    @Autowired
    private BoardSecundaryRepository boardSecundaryRep;

    @Transactional
    @Override
    public MatrixGame markX(Long boardPlayerId, int row, int column) throws Exception {

        BoardPlayer boardPlayer = boardPlayerSer.findById(boardPlayerId);
        BoardSecundary boardSec = boardPlayer.getBoardSecundaryCurrent();

        if (boardPlayer.getBoardSecundaryCurrent() == null)
            throw new RuntimeException("selecione um jogo");

        MatrixGame matrixGame = boardSec.getMatrixGame();

        if (!matrixGame.getAsList().get(row).get(column).isEmpty())
            throw new RuntimeException("Casa já marcada");

        matrixGame = gameLogistics.markX(matrixGame, row, column);
        boardSec.incrementNumberOfMarked();

        boardSec = gameLogistics.routine(boardSec);

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

        MatrixGame matrixGame = boardSec.getMatrixGame();

        if (!matrixGame.getAsList().get(row).get(column).isEmpty())
            throw new RuntimeException("Casa já marcada");

        matrixGame = gameLogistics.markO(matrixGame, row, column);
        boardSec.incrementNumberOfMarked();

        boardSec = gameLogistics.routine(boardSec);

        if (boardSec.isFinished()) {
            boardPlayer.setBoardSecundaryCurrent(null);
            boardPlayerSer.save(boardPlayer);
        }

        boardSecundaryRep.save(boardSec);

        return matrixGame;
    }

}
