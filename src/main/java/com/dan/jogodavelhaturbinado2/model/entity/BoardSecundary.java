package com.dan.jogodavelhaturbinado2.model.entity;

import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BoardSecundary extends BoardAbstract {



    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "board_player_id_fk")
    private BoardPlayer boardPlayer;

    public BoardSecundary(MatrixGame matrixGame, BoardPlayer boardPlayer) {
        super(matrixGame);
        this.boardPlayer = boardPlayer;
    }

    @Override
    public String verify(List<String> list) {
        HashSet<String> listSet;

        for (int i = 0; i < 3; i++) {

            listSet = new HashSet<>(list);

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
            }
        }
        return "";
    }







//    @Override
//    public void incrementNumberOfMarked() {
//        this.matrixGame.incrementNumberOfMarked();
//    }
//
//    public void markO(int row, int column) {
//        this.matrixGame.markO(row, column);
//        incrementNumberOfMarked();
//    }


  // public boolean isEmpty(int row, int column) {
//        return this.matrixGame.isEmpty(row, column);
//    }

}
