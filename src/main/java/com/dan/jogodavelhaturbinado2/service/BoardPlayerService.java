package com.dan.jogodavelhaturbinado2.service;

import java.util.List;

import com.dan.jogodavelhaturbinado2.model.entity.BoardPlayer;
import com.dan.jogodavelhaturbinado2.model.entity.BoardSecundary;

public interface BoardPlayerService {

    BoardPlayer save(BoardPlayer boardPlayer);

    List<BoardPlayer> findAll();

    BoardPlayer findById(Long id);

    void deleteById(Long id);

    BoardPlayer selectBoardToPlay(Long boardPlayerId, int l, int col);

    BoardPlayer newGame();

}
