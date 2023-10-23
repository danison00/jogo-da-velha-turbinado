package com.dan.jogodavelhaturbinado2.service.busnessRules.implementations;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.dan.jogodavelhaturbinado2.model.entity.BoardPlayer;
import com.dan.jogodavelhaturbinado2.repository.BoardPlayerRepository;
import com.dan.jogodavelhaturbinado2.repository.BoardSecundaryRepository;
import com.dan.jogodavelhaturbinado2.service.busnessRules.interfaces.BoardPlayerService;
import com.dan.jogodavelhaturbinado2.service.gameRules.interfaces.GameLogistics;
import jakarta.transaction.Transactional;

@Service
public class BoardPlayerServiceImp implements BoardPlayerService {

    @Autowired
    private BoardPlayerRepository boardPlayerRep;

    @Autowired
    private BoardSecundaryRepository boardSecundaryRep;

    @Autowired
    private GameLogistics gameLogistics;

    @Transactional
    @Override
    public BoardPlayer save(BoardPlayer boardPlayer) {

        return boardPlayerRep.saveAndFlush(boardPlayer);
    }

    @Override
    public List<BoardPlayer> findAll() {

        return boardPlayerRep.findAll();
    }

    @Override
    public BoardPlayer findById(Long id) {

        return boardPlayerRep.findById(id).orElseThrow(() -> new RuntimeException("id inexistente!"));

    }

    @Override
    public void deleteById(Long id) {
        boardPlayerRep.deleteById(id);
    }

    @Override
    public BoardPlayer selectBoardToPlay(Long boardPlayerId, int row, int column) throws Exception {

        BoardPlayer boardPlayer = findById(boardPlayerId);
        boardPlayer = this.gameLogistics.selectBoardToPlay(boardPlayer, row, column);

        return this.boardPlayerRep.saveAndFlush(boardPlayer);
    }

    @Transactional
    @Override
    public BoardPlayer newGame() {

        var boardPlayer = gameLogistics.newGame();

        return boardPlayerRep.saveAndFlush(boardPlayer);
    }

    @Override
    public BoardPlayer markX(Long boardPlayerId, int row, int column) throws Exception {

        BoardPlayer boardPlayer = this.findById(boardPlayerId);

        boardPlayer = gameLogistics.markX(boardPlayer, row, column);

        if (boardPlayer.getBoardSecundaryCurrent() != null)
            boardSecundaryRep.save(boardPlayer.getBoardSecundaryCurrent());
        else
            this.save(boardPlayer);

        return boardPlayer;

    }

    @Override
    public BoardPlayer markO(Long boardPlayerId, Integer row, Integer column) throws Exception {
       
        BoardPlayer boardPlayer = this.findById(boardPlayerId);

        boardPlayer = gameLogistics.markO(boardPlayer, row, column);

        if (boardPlayer.getBoardSecundaryCurrent() != null)
            boardSecundaryRep.save(boardPlayer.getBoardSecundaryCurrent());
        else
            this.save(boardPlayer);

        return boardPlayer;
    }

}
