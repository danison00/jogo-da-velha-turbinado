package com.dan.jogodavelhaturbinado2.model.entity;

import java.util.HashSet;
import java.util.List;
import jakarta.persistence.Entity;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;




@Entity
@Table
@Data
public class BoardMain extends BoardAbstract {



    public BoardMain(){
        super();
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
