package com.dan.jogodavelhaturbinado2.gameLogistics.interfaces;

import java.util.List;

import com.dan.jogodavelhaturbinado2.model.entity.BoardSecundary;
import com.dan.jogodavelhaturbinado2.model.entity.MatrixGame;

public interface GameLogistics {

    MatrixGame markX(MatrixGame matrix, int row, int column);

    MatrixGame markO(MatrixGame matrix, int row, int column);

    void markTied(MatrixGame matrix, int row, int column);

    boolean isFinished(BoardSecundary board);

    boolean verifyDia(BoardSecundary board);

    boolean verifyCol(BoardSecundary board);

    boolean verifyLin(BoardSecundary board);

    boolean verify(BoardSecundary board, List<String> list);

    BoardSecundary routine(BoardSecundary board);



}
