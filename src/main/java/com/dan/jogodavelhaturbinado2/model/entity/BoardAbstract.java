package com.dan.jogodavelhaturbinado2.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class BoardAbstract implements Serializable {

    @Transient
    protected final String X = "X";
    @Transient
    protected final String O = "O";
    @Transient
    protected final String TIED = "Tied";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String win;

    private int playedNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stringBoard_id_fk")
    private StringBoard stringBoard;

    @Transient
    private List<List<String>> boardInMatrixString;

    public BoardAbstract(){
        try {
            newGame();
        } catch (JsonProcessingException e) {
           
        }
    }

    public void print() {
        for (List<String> row : boardInMatrixString)
            System.out.println(row.toString());

    }

    public void markX(int lin, int col) {
        this.boardInMatrixString.get(lin).set(col, X);

        this.playedNumber++;
    }

    public void markO(int lin, int col) {
        this.boardInMatrixString.get(lin).set(col, O);
        this.playedNumber++;
    }

    public void markTied(int lin, int col) {

        this.boardInMatrixString.get(lin).set(col, TIED);
        this.playedNumber++;
    }

    public boolean isFinished() {

        if (this.playedNumber == 9) {
            this.win = "is a tied";
            return true;
        }

        if (verifyCol())
            return true;

        if (verifyLin())
            return true;

        if (verifyDia())
            return true;

        return false;

    }

    public boolean verifyLin() {

        for (List<String> row : boardInMatrixString)
            if (verify(row))
                return true;

        return false;

    }

    public boolean verifyCol() {
        List<String> column;

        for (int i = 0; i < 3; i++) {

            column = List.of(boardInMatrixString.get(0).get(i), boardInMatrixString.get(1).get(i), boardInMatrixString.get(2).get(i));

            if (verify(column))
                return true;

        }
        return false;
    }

    public boolean verifyDia() {

        List<String> diag;

        diag = List.of(boardInMatrixString.get(0).get(2), boardInMatrixString.get(1).get(1), boardInMatrixString.get(2).get(0));
        if (verify(diag))
            return true;

        diag = List.of(boardInMatrixString.get(0).get(0), boardInMatrixString.get(1).get(1), boardInMatrixString.get(2).get(2));
        if (verify(diag))
            return true;

        return false;
    }

    public boolean verify(List<String> list) {

        HashSet<String> listSet;

        for (int i = 0; i < 3; i++) {

            listSet = new HashSet<>(list);

            if (list.size() == 3 && !listSet.contains("")) {
                if (listSet.size() == 1) {
                    if (listSet.contains(X)) {
                        win = X;
                        return true;
                    }
                    if (listSet.contains(O)) {
                        win = O;
                        return true;
                    }

                }

            }

        }
        return false;
    }

    public boolean isNoMarked(int lin, int col) {
        if (boardInMatrixString.get(lin).get(col).isEmpty())
            return true;

        return false;

    }

    public List<List<String>> toList(String s) throws JsonMappingException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(s, new TypeReference<List<List<String>>>() {
        });

    }

    public String toString(List<List<String>> l) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(l);
    }
    public void newGame() throws JsonProcessingException{
        this.boardInMatrixString = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < 3; j++)
                row.add("");
            boardInMatrixString.add(row);
        }
        this.stringBoard = new StringBoard(toString(boardInMatrixString));

        this.print();
    }
}
