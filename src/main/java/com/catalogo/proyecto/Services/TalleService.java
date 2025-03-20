package com.catalogo.proyecto.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.proyecto.Exceptions.InvalidDataException;
import com.catalogo.proyecto.Models.Talle;
import com.catalogo.proyecto.Repositories.IOTalle;

@Service
public class TalleService {
    @Autowired
    IOTalle repoTalle;

    public Talle guardarTalle(Talle talle) {
        if (talle.getNombre() == "") {
            throw new InvalidDataException("Talle invalida para guardarse");
        }
        return repoTalle.save(talle);
    }
}
