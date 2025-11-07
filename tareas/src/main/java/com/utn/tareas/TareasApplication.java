package com.utn.tareas;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.repository.TareaRepository;
import com.utn.tareas.service.MensajeService;
import com.utn.tareas.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TareasApplication implements CommandLineRunner {
    @Autowired
    private TareaService tareaService;

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    MensajeService mensajeService;
    public TareasApplication(TareaRepository tr, TareaService ts){
        this.tareaRepository = tr;
        this.tareaService = ts;
    }
	public static void main(String[] args) {
		SpringApplication.run(TareasApplication.class, args);

    }
    @Override
    public void run(String... args) {

        try {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println(mensajeService.mostrarBienvenida());
            System.out.println("----------------------------------------------------------------------------------");
            tareaService.mostrarConfiguraciones();
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println(tareaRepository.obtenerTareas());
            System.out.println("----------------------------------------------------------------------------------");
            tareaService.agregarTarea("Modificar Front end", Prioridad.ALTA);
            System.out.println("----------------------------------------------------------------------------------");
            tareaService.marcarTareaCompleta(3L);
            System.out.println("----------------------------------------------------------------------------------");
            tareaService.mostrarEstadisticas();
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println(tareaService.listarTareasCompletadas());
            System.out.println("----------------------------------------------------------------------------------");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }finally {
            System.out.println(mensajeService.mostrarDespedida());
        }
    }
}
