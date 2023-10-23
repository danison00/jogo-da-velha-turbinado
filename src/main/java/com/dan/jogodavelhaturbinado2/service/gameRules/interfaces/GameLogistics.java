package com.dan.jogodavelhaturbinado2.service.gameRules.interfaces;

import java.util.List;

import com.dan.jogodavelhaturbinado2.model.entity.BoardPlayer;
import com.dan.jogodavelhaturbinado2.model.entity.BoardSecundary;
import com.dan.jogodavelhaturbinado2.model.entity.MatrixGame;

public interface GameLogistics {

    BoardPlayer newGame();

    BoardPlayer selectBoardToPlay(BoardPlayer boardPlayer, int row, int column) throws Exception;

    BoardPlayer markX(BoardPlayer boardPlayer, int row, int column) throws Exception;

    BoardPlayer markO(BoardPlayer boardPlayer, int row, int column) throws Exception;

    void markTied(MatrixGame matrix, int row, int column);

    boolean isFinished(BoardSecundary board);

    boolean verifyDia(BoardSecundary board);

    boolean verifyCol(BoardSecundary board);

    boolean verifyLin(BoardSecundary board);

    boolean verify(BoardSecundary board, List<String> list);

    BoardPlayer routine(BoardPlayer boardPlayer);

}
