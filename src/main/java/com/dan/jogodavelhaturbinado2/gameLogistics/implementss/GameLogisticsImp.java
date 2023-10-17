package com.dan.jogodavelhaturbinado2.gameLogistics.implementss;

import java.util.HashSet;
import java.util.List;
import org.springframework.stereotype.Component;
import com.dan.jogodavelhaturbinado2.gameLogistics.interfaces.GameLogistics;
import com.dan.jogodavelhaturbinado2.model.entity.BoardSecundary;
import com.dan.jogodavelhaturbinado2.model.entity.MatrixGame;

@Component
public class GameLogisticsImp implements GameLogistics {

    @Override
    public MatrixGame markX(MatrixGame matrix, int row, int column) {

        List<List<String>> list = matrix.getAsList();
        list.get(row).set(column, "X");
        matrix.putInLocs(list);

        return matrix;

    }

    @Override
    public MatrixGame markO(MatrixGame matrix, int row, int column) {
        List<List<String>> list = matrix.getAsList();
        list.get(row).set(column, "O");
        matrix.putInLocs(list);

        return matrix;
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

        if (verifyCol(board)){
            
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

        List<List<String>> matrix = board.getMatrixGame().getAsList();

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

        List<List<String>> matrix = board.getMatrixGame().getAsList();
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

        List<List<String>> matrix = board.getMatrixGame().getAsList();

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
    public BoardSecundary routine(BoardSecundary board) {
        
        if(isFinished(board)){
            board.setFinished(true);

        }

        return board;


    }

}
