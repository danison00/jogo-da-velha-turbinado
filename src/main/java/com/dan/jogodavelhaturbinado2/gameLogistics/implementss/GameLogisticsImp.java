package com.dan.jogodavelhaturbinado2.gameLogistics.implementss;

import java.util.List;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dan.jogodavelhaturbinado2.gameLogistics.interfaces.GameLogisticBoardMain;
import com.dan.jogodavelhaturbinado2.model.entity.BoardPlayer;
import com.dan.jogodavelhaturbinado2.model.entity.MatrixGame;

@Component
public class GameLogisticsImp implements GameLogisticBoardMain{


    @Override
    public MatrixGame markX(MatrixGame matrix, int row, int column){

        List<List<String>> list =  matrix.getAsList();
        list.get(row).set(column, "X");
        matrix.putInLocs(list);

        return matrix;
      
    }

    @Override
    public void markTied(BoardPlayer boardPlayer, int lin, int col) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'markTied'");
    }

    @Override
    public boolean isFinished(BoardPlayer boardPlayer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isFinished'");
    }

    @Override
    public boolean verifyDia(BoardPlayer boardPlayer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verifyDia'");
    }

    @Override
    public boolean verifyCol(BoardPlayer boardPlayer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verifyCol'");
    }

    @Override
    public boolean verifyLin(BoardPlayer boardPlayer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verifyLin'");
    }


    @Override
    public boolean verify(List<String> list) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verify'");
    }

    @Override
    public void markO(BoardPlayer boardPlayer, int lin, int col) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'markO'");
    }

    






    
}
