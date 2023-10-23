package com.dan.jogodavelhaturbinado2.gameLogistics.implementss;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dan.jogodavelhaturbinado2.gameLogistics.interfaces.BoardPlayerGameLogistic;
import com.dan.jogodavelhaturbinado2.model.entity.BoardPlayer;
import com.dan.jogodavelhaturbinado2.model.entity.BoardSecundary;

@Component
public class BoardPlayerGameLogistics implements BoardPlayerGameLogistic {

    @Override
    public BoardPlayer selectBoardToPlay(BoardPlayer boardPlayer, int row, int column) {

        var matrix = asMatrix(boardPlayer.getSecundaries());


        if (matrix.get(row).get(column).isFinished())
            throw new RuntimeException("Jogo já finalizado");

        boardPlayer.setBoardSecundaryCurrent(matrix.get(row).get(column));

        return boardPlayer;
    }

    private List<List<BoardSecundary>> asMatrix(List<BoardSecundary> list) {

        Comparator<BoardSecundary> comparator = (a, b) -> {

             return (int)a.getId().longValue()-(int)b.getId().longValue(); 

        };

        Collections.sort(list, comparator);

        return Arrays.asList(
                Arrays.asList(list.get(0), list.get(1), list.get(2)),
                Arrays.asList(list.get(3), list.get(4), list.get(5)),
                Arrays.asList(list.get(6), list.get(7), list.get(8)));

    }

}
