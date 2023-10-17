package com.dan.jogodavelhaturbinado2.gameLogistics.interfaces;

import com.dan.jogodavelhaturbinado2.model.entity.BoardPlayer;
import com.dan.jogodavelhaturbinado2.model.entity.MatrixGame;

public interface GameLogistics {

    MatrixGame markX(MatrixGame matrix, int row, int column);

    void markO(BoardPlayer boardPlayer, int lin, int col);

    void markTied(BoardPlayer boardPlayer, int lin, int col);

    boolean isFinished(BoardPlayer boardPlayer );

    boolean verifyDia(BoardPlayer boardPlayer );
    
    boolean verifyCol(BoardPlayer boardPlayer );
    
    boolean verifyLin(BoardPlayer boardPlayer );
    
}
