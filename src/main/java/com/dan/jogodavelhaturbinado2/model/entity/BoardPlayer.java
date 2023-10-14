package com.dan.jogodavelhaturbinado2.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

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

@Data
@Entity
@Table
public class BoardPlayer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "board_player_id_fk")
    private BoardMain main;

    @OneToMany(mappedBy = "boardPlayer", cascade = CascadeType.ALL)
    private List<ListOfBoardSecundary> matrixBoardSecundary;

 

    public BoardPlayer() throws JsonProcessingException {
        
        this.main = new BoardMain();
        this.matrixBoardSecundary = new ArrayList<>();
        for (int i = 0; i < 3; i++) 
            this.matrixBoardSecundary.add(new ListOfBoardSecundary());
        
       
    }
    public void newGame() throws JsonProcessingException{
        this.main.newGame();
    }

}
