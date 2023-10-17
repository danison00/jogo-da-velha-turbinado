package com.dan.jogodavelhaturbinado2.service;

import java.util.List;


public interface GameService {
   
    void markX(int lin, int col);

    void markO(int lin, int col);

    void markTied(int lin, int col);

    boolean isFinished();

    boolean verify(List<String> list);
}
