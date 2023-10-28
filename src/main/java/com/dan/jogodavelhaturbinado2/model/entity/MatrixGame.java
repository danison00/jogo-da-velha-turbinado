package com.dan.jogodavelhaturbinado2.model.entity;

import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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

    private int numberOfMarked = 0;

    @Transient
    private List<List<String>> matrix;

    public List<List<String>> getAsMatrix() {

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

    public void markX(int row, int column) {

        this.matrix = this.getAsMatrix();
        matrix.get(row).set(column, "X");
        this.putInLocs(matrix);
    }

    public void markO(int row, int column) {

        this.matrix = this.getAsMatrix();
        matrix.get(row).set(column, "O");
        this.putInLocs(matrix);
    }
    public void incrementNumberOfMarked(){
        this.numberOfMarked++;
    }

    public boolean isEmpty(int row, int column) {
       return this.getAsMatrix().get(row).get(column).isEmpty();
    }

}
