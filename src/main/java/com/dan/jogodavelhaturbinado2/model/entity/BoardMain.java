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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "board_player_id_fk")
    private BoardPlayer boardPlayer;

   

    public BoardMain(BoardPlayer boardPlayer) {
        this.boardPlayer = boardPlayer;
    }

    @Override
    public boolean verify(List<String> list) {

        for (int i = 0; i < 3; i++) {

            HashSet<String> listSet = new HashSet<>(list);

            if (list.size() == 3 && !listSet.contains("")) {
                if (listSet.size() == 1) {
                    if (listSet.contains(X)) {
                        setWin(X);
                        ;
                        return true;
                    }
                    if (listSet.contains(O)) {
                        setWin(O);
                        return true;
                    }

                }

                if (listSet.size() == 2) {
                    if (listSet.contains(X) && listSet.contains(TIED)) {
                        setWin(X);
                        return true;
                    }
                    if (listSet.contains(O) && listSet.contains(TIED)) {
                        setWin(O);
                        return true;
                    }
                }
            }

        }
        return false;
    }

}
