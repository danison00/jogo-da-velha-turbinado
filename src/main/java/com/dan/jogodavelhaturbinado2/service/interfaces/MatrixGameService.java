package com.dan.jogodavelhaturbinado2.service.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dan.jogodavelhaturbinado2.model.entity.MatrixGame;
import com.dan.jogodavelhaturbinado2.repository.MatrixGameRepository;


public interface MatrixGameService {
    
    MatrixGame save(MatrixGame matrixGame);
    

}
