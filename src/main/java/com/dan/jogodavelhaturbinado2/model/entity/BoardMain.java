package com.dan.jogodavelhaturbinado2.model.entity;

import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BoardMain extends BoardAbstract {


    @JsonIgnore
    @OneToOne(mappedBy = "main")
    private BoardPlayer boardPlayer;
    public BoardMain(MatrixGame matrixGame) {
        super(matrixGame);

    }


//
//    @Override
//    public void markX(int row, int column) {
//        this.matrixGame.markX(row, column);
//    }
//
//    @Override
//    public void markO(int row, int column) {
//        this.matrixGame.markO(row, column);
//    }

    @Override
    public String verify(List<String> list) {

        for (int i = 0; i < 3; i++) {

            HashSet<String> listSet = new HashSet<>(list);

            if (list.size() == 3 && !listSet.contains("")) {
                if (listSet.size() == 1) {
                    if (listSet.contains(X)) {
                        setWin(X);
                        return X;
                    }
                    if (listSet.contains(O)) {
                        setWin(O);
                        return O;
                    }

                }

                if (listSet.size() == 2) {
                    if (listSet.contains(X) && listSet.contains(TIED)) {
                        setWin(X);
                        return X;
                    }
                    if (listSet.contains(O) && listSet.contains(TIED)) {
                        setWin(O);
                        return X;
                    }
                }
            }

        }
        return "Tied";
    }

    @Override
    public boolean isFinished() {

//        if (this.matrixGame.getNumberOfMarked() == 9) {
//            super.win = "is a tied";
//            return true;
//
//        }
//        if (verifyCol())
//
//            return true;
//        if (verifyLin())
//
//            return true;
//        if (verifyDia())
//
//            return true;

        return false;
    }

}
