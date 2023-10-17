package com.dan.jogodavelhaturbinado2.gameLogistics.interfaces;

import com.dan.jogodavelhaturbinado2.model.entity.BoardPlayer;

public interface GameLogistics {

    void markX(BoardPlayer boardPlayer, int lin, int col);

    void markO(BoardPlayer boardPlayer, int lin, int col);

    void markTied(BoardPlayer boardPlayer, int lin, int col);

    boolean isFinished(BoardPlayer boardPlayer );

    boolean verifyDia(BoardPlayer boardPlayer );
    
    boolean verifyCol(BoardPlayer boardPlayer );
    
    boolean verifyLin(BoardPlayer boardPlayer );
    
}
