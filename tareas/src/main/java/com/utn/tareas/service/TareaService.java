package com.utn.tareas.service;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.repository.TareaRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@ToString
@Service
public class TareaService {
    @Value("${app.nombre}")
    private String nombreAplicacion;

    @Value("${app.max-tareas}")
    private int maxTareas;

    @Value("${app.mostrar-estadisticas}")
    private boolean mostrarEstadisticas;

    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository){
        this.tareaRepository = tareaRepository;
    }

    public void agregarTarea(String descripcion, Prioridad prioridad) {
        Tarea tareaAAgregar = Tarea.builder().build();
        tareaAAgregar.setDescripcion(descripcion);
        tareaAAgregar.setPrioridad(prioridad);
        if(tareaRepository.obtenerTareas().size() >= maxTareas){
            System.out.println("Error, límite máximo de tareas alcanzado");
        }else{
            tareaRepository.agregarTareas(tareaAAgregar);
            System.out.println("Tarea agregada con éxito a la cola de tareas.");
        }
    }

    public void listarTareas(){
        System.out.println(tareaRepository.obtenerTareas());
    }

    public List<Tarea> listarTareasPendiente(){
        List<Tarea> tareasTotales = tareaRepository.obtenerTareas();
        return tareasTotales.stream().filter(tarea -> !tarea.isCompletada()).toList();
    }

    public List<Tarea> listarTareasCompletadas(){
        List<Tarea> tareasTotales = tareaRepository.obtenerTareas();
        return tareasTotales.stream().filter(Tarea::isCompletada).toList();
    }

    public void marcarTareaCompleta(Long id){
        Optional<Tarea> tareasTotales = tareaRepository.buscarPorId(id);
        if(tareasTotales.stream().anyMatch(tarea -> !tarea.isCompletada())){
            tareasTotales.ifPresent(tarea -> tarea.setCompletada(true));
            System.out.println("Tarea completada con éxito.");
        }else {
            System.out.println("La tarea ya estaba completa anteriormente.");
        }
    }

    public void obtenerEstadisticas(){
        List<Tarea> todasLasTareas = tareaRepository.obtenerTareas();
        int tareasTotales = todasLasTareas.size();
        int tareasPendientes = Math.toIntExact(todasLasTareas.stream().
                filter(tarea -> !tarea.isCompletada()).count());
        int tareasCompletas = Math.toIntExact(todasLasTareas.stream().
                filter(Tarea::isCompletada).count());
        System.out.println("La cantidad de tareas es de: " + tareasTotales +
                ", la cantidad de tareas pendientes es de: " + tareasPendientes +
                " y la cantidad de tareas completas es de: " + tareasCompletas);
    }

    public void mostrarConfiguraciones(){
        System.out.println("Nombre: " + nombreAplicacion);
        System.out.println("Máximo de tareas permitidas: " + maxTareas);
        System.out.println("Mostrar estadísticas: " + mostrarEstadisticas);
    }
}
