package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class MensajeDevService implements MensajeService{

    @Override
    public String mostrarBienvenida() {
        return "Usted ingresó al ambiente de development";
    }

    @Override
    public String mostrarDespedida() {
        return "Usted salió del espacio de development";
    }
}
