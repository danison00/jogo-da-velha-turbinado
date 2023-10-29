package com.dan.jogodavelhaturbinado2.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
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

    private boolean finished = false;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "matrixGame_id_fk")
    private MatrixGame matrixGame;

    public BoardAbstract(MatrixGame matrixGame){
        this.matrixGame = matrixGame;
    }
    public void markX(int row, int column) throws Exception {
        this.matrixGame.markX(row, column);
    }

    public void markO(int row, int column) throws Exception {
        this.matrixGame.markO(row, column);
    }


    public abstract String verify(List<String> list);


    public void routine() {
        if (this.matrixGame.getNumberOfMarked() == 9)
            setWin("tied");

        String win = verifyCol();
        if (!win.isBlank())
            setWin(win);

        win = verifyLin();
        if (!win.isBlank())
           setWin(win);


        win = verifyDia();
        if (!win.isBlank())
            setWin(win);


    }

    protected void setWin(String win){
        this.win = win;
        this.finished = true;

    }

    public String verifyLin() {
        String win = "";

        for (List<String> row : this.matrixGame.getMatrix()) {
            win = verify(row);
            if (!win.isBlank())
                return win;
        }
        return "";

    }

    public String verifyCol() {

        List<List<String>> matrix = this.matrixGame.getMatrix();
        List<String> column;
        String win;
        for (int i = 0; i < 3; i++) {
            column = List.of(matrix.get(0).get(i), matrix.get(1).get(i),
                    matrix.get(2).get(i));
            win = verify(column);
            if (!win.isBlank())
                return win;
        }
        return "";

    }

    public String verifyDia() {
        List<List<String>> matrix = this.matrixGame.getMatrix();
        List<String> diag = List.of(matrix.get(0).get(2), matrix.get(1).get(1),
                matrix.get(2).get(0));
        String win = "";
        win = verify(diag);
        if (!win.isBlank())
            return win;

        diag = List.of(matrix.get(0).get(0), matrix.get(1).get(1),
                matrix.get(2).get(2));
        win = verify(diag);
        if (!win.isBlank())
            return win;

        return "";
    }




}



