package com.dan.jogodavelhaturbinado2.model.entity;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table
public class BoardPlayer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "board_main_id_fk")
    private BoardMain main;

    @OneToMany(mappedBy = "boardPlayer", cascade = CascadeType.ALL)
    private List<BoardSecundary> secundaries;

    private String playerCurrent = "";


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "board_secundary_current_id_fk")
    private BoardSecundary boardSecundaryCurrent;
    private int locBoardCurrentRow;
    private int locBoardCurrentColumn;

    public BoardPlayer(BoardMain main) {
        this.main = main;
    }

    @JsonIgnore
    public List<List<String>> getMatrix() {

        return this.boardSecundaryCurrent.getMatrixGame().getMatrix();
    }

    public void markX(int row, int column) throws Exception {

        getBoardSecundaryCurrent().markX(row, column);
        setPlayerCurrent("O");
        routine();

    }

    public void markO(int row, int column) throws Exception {

        getBoardSecundaryCurrent().markO(row, column);
        setPlayerCurrent("X");
        routine();
    }

    public void markXInMain(int row, int column) throws Exception {

        main.markX(row, column);
        setPlayerCurrent("O");

    }

    public void markOInMain(int row, int column) throws Exception {

        main.markO(row, column);
        setPlayerCurrent("X");

    }
    public void setLocXAndYCurrent(int row, int column){
        this.locBoardCurrentRow = row;
        this.locBoardCurrentColumn = column;
    }
    public void routine(){
        this.boardSecundaryCurrent.routine();
    }

}
