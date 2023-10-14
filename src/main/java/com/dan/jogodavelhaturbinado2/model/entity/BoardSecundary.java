package com.dan.jogodavelhaturbinado2.model.entity;

import java.util.HashSet;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class BoardSecundary extends BoardAbstract {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "list_of_boards_Secundary_id_fk")
    private ListOfBoardSecundary listOfBoardSecundary;


    @Override
    public boolean verify(List<String> list) {
        HashSet<String> listSet;

        for (int i = 0; i < 3; i++) {

            listSet = new HashSet<>(list);

            if (list.size() == 3 && !listSet.contains("")) {
                if (listSet.size() == 1) {
                    if (listSet.contains(X)) {
                        setWin(X);
                        return true;
                    }
                    if (listSet.contains(O)) {
                        setWin(O);
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
