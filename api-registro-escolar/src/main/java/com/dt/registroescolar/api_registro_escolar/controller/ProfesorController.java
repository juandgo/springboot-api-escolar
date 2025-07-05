package com.dt.registroescolar.api_registro_escolar.controller;

import com.dt.registroescolar.api_registro_escolar.entity.Estudiante;
import com.dt.registroescolar.api_registro_escolar.entity.Profesor;
import com.dt.registroescolar.api_registro_escolar.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @PostMapping("/registrar")
    public ResponseEntity<Profesor> registrarProfesor(@RequestBody Profesor profesor) {
        Profesor guardado = profesorService.registrarProfesor(profesor);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @GetMapping
    public ResponseEntity<List<Profesor>> listarProfesores() {
        return ResponseEntity.ok(profesorService.listarProfesores());
    }

    @GetMapping("/buscar/id/{idPersona}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long idPersona) {
        Optional<Profesor> profesor = profesorService.buscarPorId(idPersona);
        return profesor.isPresent() ?
                ResponseEntity.ok(profesor.get()) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profesor no encontrado");
    }

    @GetMapping("/buscar/especialidad/{especialidad}")
    public ResponseEntity<?> buscarPorEspecialidad(@PathVariable String especialidad) {
        Optional<Profesor> profesor = profesorService.buscarPorEspecialidad(especialidad);
        return profesor.isPresent() ?
                ResponseEntity.ok(profesor.get()) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profesor no encontrado");
    }

    @PutMapping("/actualizar/{idPersona}")
    public ResponseEntity<?> actualizarProfesor(
            @PathVariable Long idPersona,
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String email,
            @RequestParam String especialidad,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaContratacion
    ) {
        try {
            Profesor profesor = new Profesor();
            profesor.setNombre(nombre);
            profesor.setApellido(apellido);
            profesor.setEmail(email);
            profesor.setEspecialidad(especialidad);
            profesor.setFechaContratacion(fechaContratacion);

            Profesor actualizado = profesorService.actualizarProfesor(idPersona, profesor);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{idPersona}")
    public ResponseEntity<?> eliminarProfesor(@PathVariable Long idPersona) {
        try {
            profesorService.eliminarProfesor(idPersona);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar: " + e.getMessage());
        }
    }
}
