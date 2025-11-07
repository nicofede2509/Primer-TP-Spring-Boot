package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
public interface MensajeService {
    public String mostrarBienvenida();
    public String mostrarDespedida();
}
