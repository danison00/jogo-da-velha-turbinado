package com.dan.jogodavelhaturbinado2.service.busnessRules.interfaces;

import java.util.List;
import com.dan.jogodavelhaturbinado2.model.entity.BoardPlayer;
import com.dan.jogodavelhaturbinado2.model.entity.MatrixGame;

public interface BoardPlayerService {

    BoardPlayer save(BoardPlayer boardPlayer);

    List<BoardPlayer> findAll();

    BoardPlayer findById(Long id);

    void deleteById(Long id);

    BoardPlayer selectBoardToPlay(Long boardPlayerId, int row, int column) throws Exception;

    BoardPlayer newGame();

    BoardPlayer markX(Long boardPlayerId, int row, int column) throws Exception;

    BoardPlayer markO(Long boardPlayerId, Integer row, Integer column) throws Exception;

}
