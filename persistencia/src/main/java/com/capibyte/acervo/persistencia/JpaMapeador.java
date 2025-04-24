package com.capibyte.acervo.persistencia;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JpaMapeador extends ModelMapper{

    @Autowired
    private static JpaMapeador instancia;

    public static JpaMapeador getInstance() {
        if (instancia == null) {
            instancia = new JpaMapeador();
        }
        return instancia;
    }

    
}
