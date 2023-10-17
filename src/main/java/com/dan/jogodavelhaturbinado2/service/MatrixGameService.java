package com.dan.jogodavelhaturbinado2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dan.jogodavelhaturbinado2.repository.MatrixGameRepository;

@Service
public class MatrixGameService {
    
    @Autowired
    private MatrixGameRepository matrixGameRep;
}
