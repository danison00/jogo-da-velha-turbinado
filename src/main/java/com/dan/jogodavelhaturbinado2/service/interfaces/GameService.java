package com.dan.jogodavelhaturbinado2.service.interfaces;

import com.dan.jogodavelhaturbinado2.model.entity.MatrixGame;

public interface GameService {
    
    MatrixGame markX(Long boardPlayerId, int row, int column) throws Exception;

    MatrixGame markO(Long boardPlayerId, Integer row, Integer column);


}
