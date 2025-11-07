package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class MensajeProdService implements MensajeService{
    @Override
    public String mostrarBienvenida() {
        return "Bienvenido al espacio de producción";
    }

    @Override
    public String mostrarDespedida() {
        return "Usted ha salido del espacio de producción";
    }
}
