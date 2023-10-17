package com.dan.jogodavelhaturbinado2.service.interfaces;

import com.dan.jogodavelhaturbinado2.model.entity.BoardPlayer;

public interface GameService {
    
    BoardPlayer markX(Long boardPlayerId, int row, int column);

}
