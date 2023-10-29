package com.dan.jogodavelhaturbinado2.service.gameRules.implementss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import com.dan.jogodavelhaturbinado2.model.entity.BoardMain;
import com.dan.jogodavelhaturbinado2.model.entity.BoardPlayer;
import com.dan.jogodavelhaturbinado2.model.entity.BoardSecundary;
import com.dan.jogodavelhaturbinado2.model.entity.MatrixGame;
import com.dan.jogodavelhaturbinado2.service.gameRules.interfaces.GameLogistics;

@Component
public class GameLogisticsImp implements GameLogistics {

    @Override
    public BoardPlayer markX(BoardPlayer boardPlayer, int row, int column) throws Exception {

        verifyStatusOfGame(boardPlayer, "X");
        boardPlayer.markX(row, column);
        routine(boardPlayer);

        return boardPlayer;
    }

    @Override
    public BoardPlayer markO(BoardPlayer boardPlayer, int row, int column) throws Exception {
        
        verifyStatusOfGame(boardPlayer, "O");
        boardPlayer.markO(row, column);
        routine(boardPlayer);

        return boardPlayer;
    }

    private void verifyStatusOfGame(BoardPlayer boardPlayer, String s) {

        if (boardPlayer.getBoardSecundaryCurrent() == null)
            throw new RuntimeException("selecione um jogo");

        if (!boardPlayer.getPlayerCurrent().equals(s))
            throw new RuntimeException("Aguarde o outro jogador");


    }

    @Override
    public void markTied(MatrixGame matrix, int row, int column) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'markTied'");
    }

    @Override
    public boolean isFinished(BoardSecundary board) {

        return false;

    }

    @Override
    public BoardPlayer routine(BoardPlayer boardPlayer) throws Exception {

        if(boardPlayer.getBoardSecundaryCurrent().isFinished()){
            int row = boardPlayer.getLocBoardCurrentRow();
            int column = boardPlayer.getLocBoardCurrentColumn();
            String win = boardPlayer.getBoardSecundaryCurrent().getWin();

            if (win.equals("X"))
                boardPlayer.markXInMain(row, column);
            if (win.equals("O"))
                boardPlayer.markOInMain(row, column);
            boardPlayer.setBoardSecundaryCurrent(null);
            verifyBoardMain(boardPlayer);

        }

        return boardPlayer;

    }

    private void verifyBoardMain(BoardPlayer boardPlayer) {




    }

    @Override
    public BoardPlayer selectBoardToPlay(BoardPlayer boardPlayer, int row, int column) throws Exception {
        var matrix = asMatrix(boardPlayer.getSecundaries());

        if (boardPlayer.getBoardSecundaryCurrent() != null)
            throw new RuntimeException("termine o jogo anterior");

        if (matrix.get(row).get(column).isFinished())
            throw new RuntimeException("Jogo já finalizado");

        boardPlayer.setLocXAndYCurrent(row, column);
        boardPlayer.setBoardSecundaryCurrent(matrix.get(row).get(column));
        boardPlayer.setPlayerCurrent((new Random().nextInt(2) == 0) ? "X" : "O");

        return boardPlayer;
    }

    private List<List<BoardSecundary>> asMatrix(List<BoardSecundary> list) {

        list.sort((a, b) -> {

            return (int) a.getId().longValue() - (int) b.getId().longValue();

        });

        return Arrays.asList(
                Arrays.asList(list.get(0), list.get(1), list.get(2)),
                Arrays.asList(list.get(3), list.get(4), list.get(5)),
                Arrays.asList(list.get(6), list.get(7), list.get(8)));

    }

    @Transactional
    @Override
    public BoardPlayer newGame() {

        BoardMain main = new BoardMain(new MatrixGame());
        BoardPlayer boardPlayer = new BoardPlayer();

        List<BoardSecundary> secundaries = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            secundaries.add(new BoardSecundary(new MatrixGame(), boardPlayer));
        }
       // main.setBoardPlayer(boardPlayer);
        boardPlayer.setMain(main);
        boardPlayer.setSecundaries(secundaries);

        return boardPlayer;
    }

}
