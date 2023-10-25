package com.dan.jogodavelhaturbinado2.service.gameRules.implementss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

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

        verifyStatusOfGame(boardPlayer, row, column, "X");
        boardPlayer.markX(row, column);
        routine(boardPlayer);

        return boardPlayer;
    }

    @Override
    public BoardPlayer markO(BoardPlayer boardPlayer, int row, int column) throws Exception {
        
        verifyStatusOfGame(boardPlayer, row, column, "O");
        boardPlayer.markO(row, column);
        routine(boardPlayer);

        return boardPlayer;
    }

    private void verifyStatusOfGame(BoardPlayer boardPlayer, int row, int column, String s) {

        if (boardPlayer.getBoardSecundaryCurrent() == null)
            throw new RuntimeException("selecione um jogo");

        if (!boardPlayer.getPlayerCurrent().equals(s))
            throw new RuntimeException("Aguarde o outro jogador");

        if (!boardPlayer.getBoardSecundaryCurrent().isEmpty(row, column))
            throw new RuntimeException("Casa já marcada");

    }

    @Override
    public void markTied(MatrixGame matrix, int row, int column) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'markTied'");
    }

    @Override
    public boolean isFinished(BoardSecundary board) {

        if (board.getNumberOfMarked() == 9) {
            board.setWin("Tied");
            return true;
        }

        if (verifyCol(board)) {

            return true;
        }

        if (verifyLin(board))
            return true;

        if (verifyDia(board))
            return true;

        return false;

    }

    @Override
    public boolean verifyDia(BoardSecundary board) {

        List<List<String>> matrix = board.getMatrixGame().getAsMatrix();

        List<String> diag;

        diag = List.of(matrix.get(0).get(2), matrix.get(1).get(1),
                matrix.get(2).get(0));
        if (verify(board, diag))
            return true;

        diag = List.of(matrix.get(0).get(0), matrix.get(1).get(1),
                matrix.get(2).get(2));
        if (verify(board, diag))
            return true;

        return false;
    }

    @Override
    public boolean verifyCol(BoardSecundary board) {

        List<List<String>> matrix = board.getMatrixGame().getAsMatrix();
        List<String> column;

        for (int i = 0; i < 3; i++) {

            column = List.of(matrix.get(0).get(i), matrix.get(1).get(i),
                    matrix.get(2).get(i));

            if (verify(board, column))
                return true;

        }
        return false;

    }

    @Override
    public boolean verifyLin(BoardSecundary board) {

        List<List<String>> matrix = board.getMatrixGame().getAsMatrix();

        for (List<String> row : matrix)
            if (verify(board, row))
                return true;

        return false;
    }

    @Override
    public boolean verify(BoardSecundary board, List<String> list) {

        HashSet<String> listSet;

        for (int i = 0; i < 3; i++) {

            listSet = new HashSet<>(list);

            if (list.size() == 3 && !listSet.contains("")) {
                if (listSet.size() == 1) {
                    if (listSet.contains("X")) {
                        board.setWin("X");
                        return true;
                    }
                    if (listSet.contains("O")) {
                        board.setWin("O");
                        return true;
                    }

                }

            }

        }
        return false;
    }

    @Override
    public BoardPlayer routine(BoardPlayer boardPlayer) {

        if (isFinished(boardPlayer.getBoardSecundaryCurrent())) {
            boardPlayer.getBoardSecundaryCurrent().setFinished(true);
            boardPlayer.setBoardSecundaryCurrent(null);

        }

        return boardPlayer;

    }

    @Override
    public BoardPlayer selectBoardToPlay(BoardPlayer boardPlayer, int row, int column) throws Exception {
        var matrix = asMatrix(boardPlayer.getSecundaries());

        if (boardPlayer.getBoardSecundaryCurrent() != null)
            throw new RuntimeException("termine o jogo anterior");

        if (matrix.get(row).get(column).isFinished())
            throw new RuntimeException("Jogo já finalizado");

        boardPlayer.setBoardSecundaryCurrent(matrix.get(row).get(column));
        boardPlayer.setPlayerCurrent((new Random().nextInt(2) == 0) ? "X" : "O");

        return boardPlayer;
    }

    private List<List<BoardSecundary>> asMatrix(List<BoardSecundary> list) {

        Collections.sort(list, (a, b) -> {

            return (int) a.getId().longValue() - (int) b.getId().longValue();

        });

        return Arrays.asList(
                Arrays.asList(list.get(0), list.get(1), list.get(2)),
                Arrays.asList(list.get(3), list.get(4), list.get(5)),
                Arrays.asList(list.get(6), list.get(7), list.get(8)));

    }

    @Override
    public BoardPlayer newGame() {

        BoardPlayer boardPlayer = new BoardPlayer();
        BoardMain main = new BoardMain(new MatrixGame());

        List<BoardSecundary> secundaries = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            secundaries.add(new BoardSecundary(new MatrixGame(), boardPlayer));
        }

        boardPlayer.setMain(main);
        boardPlayer.setSecundaries(secundaries);

        return boardPlayer;
    }

}
