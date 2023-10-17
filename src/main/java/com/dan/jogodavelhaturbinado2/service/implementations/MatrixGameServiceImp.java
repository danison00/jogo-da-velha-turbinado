package com.dan.jogodavelhaturbinado2.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dan.jogodavelhaturbinado2.model.entity.MatrixGame;
import com.dan.jogodavelhaturbinado2.repository.MatrixGameRepository;
import com.dan.jogodavelhaturbinado2.service.interfaces.MatrixGameService;

@Service
public class MatrixGameServiceImp implements MatrixGameService {

    @Autowired
    private MatrixGameRepository matrixGameRep;

    @Override
    public MatrixGame save(MatrixGame matrixGame) {
        return matrixGameRep.saveAndFlush(matrixGame);
    }

}
