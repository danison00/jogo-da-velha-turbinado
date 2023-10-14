package com.dan.jogodavelhaturbinado2.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class ListOfBoardSecundary implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "listOfBoardSecundary", cascade = CascadeType.ALL)
    private List<BoardSecundary> boards;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="board_player_id_fk")
    private BoardPlayer boardPlayer;

    public ListOfBoardSecundary(){
        this.newGame();
        
    }
    
    private void newGame() {
        this.boards = new ArrayList<>();
        
        for(int i = 0; i < 3; i++)
             boards.add(new BoardSecundary());
        
    }

    public List<BoardSecundary> get() {
        return this.boards;
    }

    public void add(BoardSecundary board) {
        this.boards.add(board);
    }

    public void set(int index, BoardSecundary board) {
        this.boards.set(index, board);
    }
}
