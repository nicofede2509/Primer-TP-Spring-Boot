package com.utn.tareas.repository;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Getter
@ToString
@Repository
public class TareaRepository {
    private Long idAutogenerado = 1L;
    private List<Tarea> tareas = new ArrayList<>();


    public TareaRepository(){
        Tarea tarea1 = Tarea.builder()
                .descripcion("Realizar Front End")
                .prioridad(Prioridad.BAJA)
                .completada(true)
                .build();
        Tarea tarea2 = Tarea.builder()
                .descripcion("Realizar Back End")
                .prioridad(Prioridad.MEDIA)
                .completada(false)
                .build();
        Tarea tarea3 = Tarea.builder()
                .descripcion("Armar Base de Datos")
                .prioridad(Prioridad.ALTA)
                .completada(false)
                .build();
        this.agregarTareas(tarea1);
        this.agregarTareas(tarea2);
        this.agregarTareas(tarea3);
    }
    public void agregarTareas(Tarea tarea){
        tarea.setId(validarID(tarea));
        tareas.add(tarea);
        idAutogenerado++;
    }

    public List<Tarea> obtenerTareas() {
        return tareas;
    }

    public Long validarID(Tarea tarea){
        if (tarea.getId() == null){
            tarea.setId(idAutogenerado);
        }
        else if (tareas.stream().anyMatch(t -> t.getId().equals(tarea.getId()))){
            tarea.setId(idAutogenerado++);
        }
        return tarea.getId();
    }

    public Optional<Tarea> buscarPorId(Long idBuscado){
        return tareas.stream().filter(t -> t.getId().equals(idBuscado)).findFirst();
    }

    public void eliminarTareaPorId(Long idBuscado){
        if (tareas.stream().anyMatch(t -> t.getId().equals(idBuscado))){
            tareas.remove(tareas.stream().filter(t -> t.getId().equals(idBuscado)).findFirst().get());
            System.out.println("Se eliminó correctamente la tarea de id: " + idBuscado);
        }else {
            System.out.println("No existe la tarea con el id proporcionado.");
        }
    }
}
