package com.dan.jogodavelhaturbinado2.model.entity;

import java.sql.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class MatrixGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "matrixGame")
    private BoardSecundary boardSecundary;

    @JsonIgnore
    @OneToOne(mappedBy = "matrixGame")
    private BoardMain boardMain;

    String loc1 = "";
    String loc2 = "";
    String loc3 = "";
    String loc4 = "";
    String loc5 = "";
    String loc6 = "";
    String loc7 = "";
    String loc8 = "";
    String loc9 = "";

    @JsonIgnore
    public List<List<String>> getAsList() {

        return Arrays.asList(
                Arrays.asList(loc1, loc2, loc3),
                Arrays.asList(loc4, loc5, loc6),
                Arrays.asList(loc7, loc8, loc9));

    }

    public void putInLocs(List<List<String>> matrix) {

        this.loc1 = matrix.get(0).get(0);
        this.loc2 = matrix.get(0).get(1);
        this.loc3 = matrix.get(0).get(2);

        this.loc4 = matrix.get(1).get(0);
        this.loc5 = matrix.get(1).get(1);
        this.loc6 = matrix.get(1).get(2);

        this.loc7 = matrix.get(2).get(0);
        this.loc8 = matrix.get(2).get(1);
        this.loc9 = matrix.get(2).get(2);

    }

}
