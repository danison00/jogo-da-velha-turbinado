package com.dan.jogodavelhaturbinado2.gameLogistics.interfaces;

import com.dan.jogodavelhaturbinado2.model.entity.BoardPlayer;

public interface BoardPlayerGameLogistic {
    
    BoardPlayer selectBoardToPlay(BoardPlayer boardPlayer, int row, int column) throws Exception;
}
